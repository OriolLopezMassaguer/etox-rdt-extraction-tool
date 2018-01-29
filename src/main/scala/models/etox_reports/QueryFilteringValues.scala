package models.etox_reports

import slick.driver.PostgresDriver.simple._
import slick.jdbc.{ StaticQuery => Q }
import slick.jdbc._
import slick.driver.JdbcDriver.backend.Database
import slick.lifted.ColumnExtensionMethods
import slick.lifted._
import slick.ast._
import slick.ast._

import Database.dynamicSession
import java.io.PrintStream

import java.sql.DriverManager
import java.sql.ResultSet
import gudusoft.gsqlparser.TGSqlParser
import gudusoft.gsqlparser.pp.para.GFmtOptFactory
import gudusoft.gsqlparser.pp.para.GOutputFmt
import gudusoft.gsqlparser.pp.stmtformatter.FormatterFactory
import gudusoft.gsqlparser.EDbVendor
import Tables._
import models.extractions._
import models.ontologies._
import models.ontologies._
import models.dataframe._
import scala.reflect._

object Observations_filtering_values {
  //Studies parameters
  private val qNormalisedAdminRoute = CompoundQuerys.CompoundsStudies.map(_._2.normalisedAdministrationRoute)
  private val qNormalisedSex = CompoundQuerys.CompoundsStudies.map(_._2.normalisedSex)
  private val qNormalisedSpecies = CompoundQuerys.CompoundsStudies.map(_._2.normalisedSpecies)

  private val listQs = List(
    ("NormalisedAdminRoute", qNormalisedAdminRoute),
    ("NormalisedSex", qNormalisedSex),
    ("NormalisedSpecies", qNormalisedSpecies))

  private val listQsFindings = List(
    ("OrgansNormalied", FindingsAll.map(_.organNormalised)),
    ("Findings_Types", FindingsAll.map(_.source)),
    ("ClinicalChemicalFinding", FindingsAll.filter(_.source inSet Set("ClinicalChemicalFinding")).map(_.observationNormalised)),
    ("UrianalysisFindingsFindings", FindingsAll.filter(_.source inSet Set("UrianalysisFinding")).map(_.observationNormalised)),
    ("HistopathologicalFinding", FindingsAll.filter(_.source inSet Set("HistopathologicalFinding")).map(_.observationNormalised)),
    ("ClinicalHaematologicalFinding", FindingsAll.filter(_.source inSet Set("ClinicalHaematologicalFinding")).map(_.observationNormalised)),
    ("OrganWeights", FindingsAll.filter(_.source inSet Set("OrganWeights")).map(_.observationNormalised)))
  val listQs_all = listQs ++ listQsFindings

  def export_filtering_values_all(path: String = "../data/filtering_values/") = {
    val ldts = listQs_all.map(t => (t._1, models.dataframe.DataFrame(Querys_etox_reports.con, t._2)))
    ldts.map(d => (d._2.toText(path + "/" + d._1 + ".tsv")))
  }

  def export_filtering_values(field: String, filename: String) = {    
    val lq = listQs_all.toMap
    val q = lq(field)
    val df = models.dataframe.DataFrame(Querys_etox_reports.con, q)
    df.toText(filename)
  }

  val filteringValues = {
    val r = (for ((name, query) <- listQs_all) yield ({
      val df = models.dataframe.DataFrame(Querys_etox_reports.con, query)
      val field = df.getFields()(0)
      val s = df.projectField(field)
      name -> s
    }))

    r.toMap

  }
}