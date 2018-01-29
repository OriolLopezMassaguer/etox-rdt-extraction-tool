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