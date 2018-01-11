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

object example4_HPF_clusters {

  val home_path = "../data/example4/"

  val exposure_tag = "all_periods"
  val exposure_range = Some(0, 100000)

  val routes_tag = "Oral"
  val routes_list = List("DIETARY", "INTRAESOPHAGEAL", "INTRAGASTRIC", "NASOGASTRIC", "ORAL", "ORAL GAVAGE", "OROPHARYNGEAL")

  val species_tag = "Rat"
  val species_list = List("RAT")
  val organ_filter = List("liver")

  val patterns1 = getPatterns(home_path + "cluster_definition/cluster1_new.txt")
  val patterns2 = getPatterns(home_path + "cluster_definition/cluster2_new.txt")
  val patternslist = List(("cluster1", patterns1), ("cluster2", patterns2))

  def getPatterns(file: String) = {

    val patternsDT = DataFrame(file)
    val patterns = patternsDT.projectField("pattern")
    val patternsMap = {
      val l = for (pattern <- patterns) yield ({
        val findings = patternsDT.filter("pattern", pattern).projectField("finding")
        val findings_expanded = findings.map(Ontologies_DB.expandTerm(_)).flatten
        println(pattern + " -> " + findings_expanded)
        pattern -> findings_expanded.toList
      })

      l.toMap
    }
    patternsMap
  }

  def extract = {

    for ((patterntag, patterns) <- patternslist) {
      val l = Observations_querys.getEndpoints_v3(
        observationSource = "HistopathologicalFinding",
        admin_routes = routes_list,
        species = species_list,
        exposure_period = exposure_range,
        relevanceFiltering = true,
        organs = organ_filter,
        patterns = patterns,
        patterntag = patterntag,
        conditions_tag = species_tag + "_" + routes_tag + "_" + exposure_tag,
        path = home_path)

      for ((data, filename) <- l) data.toText(home_path + "/" + filename)

    }
  }
}