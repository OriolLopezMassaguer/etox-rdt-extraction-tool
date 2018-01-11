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

object example1_HPF_liver {

  val home_path = "../data/example1/"

  val tagging = true
  val debug = false
  val structure = true

  val exposure_tag = "all_periods"
  val exposure_range = Some(0, 100000)

  val routes_tag = "Oral"
  val routes_list = List("DIETARY", "INTRAESOPHAGEAL", "INTRAGASTRIC", "NASOGASTRIC", "ORAL", "ORAL GAVAGE", "OROPHARYNGEAL")

  val species_tag = "Rat"
  val species_list = List("RAT")
  val organ_filter = List("liver")

  def extract = {

    val l = Observations_querys.getEndpoints_v3(
      observationSource = "HistopathologicalFinding",
      admin_routes = routes_list,
      species = species_list,
      exposure_period = exposure_range,
      relevanceFiltering = true,
      organs = organ_filter,
      conditions_tag = species_tag + "_" + routes_tag + "_" + exposure_tag,
      path = home_path)

    for ((data, filename) <- l) data.toText(home_path + "/" + filename)

  }
}