package models.ontologies

import scalaz._
import scalaz.Scalaz._
import scala.collection.JavaConversions._

import models.dataframe._
import models._
import models.Config
import scala.slick.jdbc.StaticQuery.staticQueryToInvoker

import slick.driver.PostgresDriver.simple._
import slick.jdbc.{ StaticQuery => Q }
import slick.jdbc._
import slick.driver.JdbcDriver.backend.Database
import slick.lifted.ColumnExtensionMethods
import slick.lifted._
import slick.ast._
import Database.dynamicSession

object Ontologies_Files_old {

  val input_path = Config.data_path + "/ontology/input/"
  val output_path = Config.data_path + "/ontology/output/"

  val ontology_terms_file = input_path + "etox_ontology_terms_20160128.txt"
  val ontology_relatonships_file = input_path + "etox_ontology_relationships_20160128.txt"
  val vx_synonyms_file = input_path + "etox_vx_synonyms_20160128.txt"
  val ontology_terms_dt = DataFrame(ontology_terms_file)
  val ontology_relationships_dt = DataFrame(ontology_relatonships_file)
  val vs_synonyms_dt = DataFrame(vx_synonyms_file)

  val mp_ontology_anatomy_term_id_name = ontology_terms_dt.filter("ONTOLOGY_NAME", "anatomy").getMap("ONTOLOGY_TERM_ID", "TERM_NAME")
  val mp_ontology_anatomy_name_term_id = ontology_terms_dt.filter("ONTOLOGY_NAME", "anatomy").getMap("TERM_NAME", "ONTOLOGY_TERM_ID")

  val mp_ontology_histopathology_term_id_name = ontology_terms_dt.filter("ONTOLOGY_NAME", "histopathology").getMap("ONTOLOGY_TERM_ID", "TERM_NAME")
  val mp_ontology_histopathology_name_term_id = ontology_terms_dt.filter("ONTOLOGY_NAME", "histopathology").getMap("TERM_NAME", "ONTOLOGY_TERM_ID")

  def getChilds(ontology_term_id: String): Set[String] = {
    val childs = ontology_relationships_dt.filter("RELATED_ONTOLOGY_TERM_ID", ontology_term_id)
    val inmediate_childs = childs.projectField("ONTOLOGY_TERM_ID")
    val recursive_childs = inmediate_childs.map(getChilds(_))
    inmediate_childs ++ recursive_childs.flatten
  }

  def getChildsAnatomy(term: String): Set[String] = {
    //println("Getting childs anatomy:" + term)
    val term_id = this.mp_ontology_anatomy_name_term_id(term)
    getChilds(term_id).map(mp_ontology_anatomy_term_id_name) + term
  }

  def getChildsHistopathology(term: String): Set[String] = {
    //println("Getting childs HP:" + term)
    val term_id = this.mp_ontology_histopathology_name_term_id(term)
    getChilds(term_id).map(mp_ontology_histopathology_term_id_name) + term
  }

  private def getTree(ontology_term_id: String): Tree[String] = {
    val childs = ontology_relationships_dt.filter("RELATED_ONTOLOGY_TERM_ID", ontology_term_id)
    val inmediate_childs = childs.projectField("ONTOLOGY_TERM_ID")
    if (inmediate_childs.size == 0)
      ontology_term_id.leaf
    else {
      val recursive_childs = inmediate_childs.map(getTree(_)).toList
      ontology_term_id.node(recursive_childs: _*)
    }
  }
  val treeAnatomy = getTree("MA:0000001").map(id => (id, mp_ontology_anatomy_term_id_name(id)))
  val treeHistopathology = getTree("MC:0000001").map(id => (id, mp_ontology_histopathology_term_id_name(id)))

  //val ontoDBAnat = new OntologyDB(treeAnatomy, models.etox_reports.HistopathologicalFindingsQuerys.getOrgansTermsCount)

}

object Ontologies_DB {
  private def expandTermBase(term: String): List[String] = {
    val term_escaped= term.replace("'", "''")

    val qs = " ( (select child_term from label_params('" + term_escaped + "'))" +
      " union" +
      "(select parent_term from label_params('" + term_escaped + "'))" +
      " union " +
      " (select '" + term_escaped + "')) "

    println(qs)
    val q = Q.queryNA[String](qs)
    val l = models.etox_reports.Querys_etox_reports.db.withDynSession {
      q.list
    }
    l.toSet.toList
  }

  val expandTerm = scalaz.Memo.immutableHashMapMemo[String, List[String]] { term: String => expandTermBase(term) }
  //val expandTerm = (term: String) => expandTermBase(term)
  val all_hpf_observations = expandTerm("morphologic change").zipWithIndex

  def expandObservations(observations: List[String], observation_source: String) = {

    if (observations.isEmpty && observation_source == "HistopathologicalFinding")
      all_hpf_observations
    else
      observations.map(expandTerm(_)).flatten.toSet.toList.zipWithIndex
  }
}