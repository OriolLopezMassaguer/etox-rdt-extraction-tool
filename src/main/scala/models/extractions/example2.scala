package models.extractions

import models.dataframe._
import models.ontologies._
import models.etox_reports._
import slick.driver.PostgresDriver.simple._
import slick.jdbc.{ StaticQuery => Q }
import java.io.File

import models._
import models.ontologies.Ontologies_DB
import models.FileUtils

object example2_CCF_transaminases {

  val home_path = "../data/example2/"

  val CCFindings = List(
    "Alanine Aminotransferase",
    "Aspartate Aminotransferase",
    "Gamma Glutamyl Transferase",
    "Bilirubin", "Direct Bilirubin",
    "Indirect Bilirubin",
    "Alkaline Phosphatase")

  val exposure_tag = "4weeks"
  val exposure_range = Some(0, 35)

  val routes_tag = "Oral"
  val routes_list = List("DIETARY", "INTRAESOPHAGEAL", "INTRAGASTRIC", "NASOGASTRIC", "ORAL", "ORAL GAVAGE", "OROPHARYNGEAL")

  val species_tag = "Rat"
  val species_list = List("RAT")

  def extract = {

    val l = Observations_querys.getEndpoints_v3(
      observationSource = "ClinicalChemicalFinding",
      observations = CCFindings,
      admin_routes = routes_list,
      species = species_list,
      exposure_period = exposure_range,
      relevanceFiltering = true,
      conditions_tag = species_tag + "_" + routes_tag + "_" + exposure_tag,
      path = home_path)

    for ((data, filename) <- l) data.toText(home_path + "/" + filename)

  }
}