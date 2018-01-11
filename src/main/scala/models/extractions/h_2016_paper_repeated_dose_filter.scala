package models.extractions

import models.ontologies._
import models.dataframe._
import models.etox_reports._
import slick.driver.PostgresDriver.simple._
import slick.jdbc.{ StaticQuery => Q }
import java.io.File

import models._

import models.FileUtils

object h2016_paper_repeated_dose_filter {

  val home_path = "/data/paper_repeated_dose_filter/"
  FileUtils.tmpPath = home_path + "sql/"

  val tagging = true
  val debug = false
  val structure = false
  val aggregations = List(Observations_querys.NumFindings, Observations_querys.LOAEL,Observations_querys.NumFindingsDistinct)

  val CCFindings = List(
    "Alanine Aminotransferase", "Aspartate Aminotransferase", "Gamma Glutamyl Transferase", "Bilirubin", "Direct Bilirubin", "Indirect Bilirubin", "Alkaline Phosphatase")

  val exposures = List(
    ("4weeks_broad", Some(20, 32)),
    ("4weeks_narrow", Some(20, 20)))

  val routes_clusters =
    List(
      ("Oral_broad", List("ORAL", "ORAL GAVAGE")),
      ("Oral_narrow", List("ORAL")))

  val species_clusters =
    List(("Rat_broad", List("RAT", "MOUSE")),
      ("Rat_narrow", List("RAT")))

  def clustered_extract2(exposure: (String, Option[(Int, Int)]), rout: (String, List[String]), spe: (String, List[String]), pat: (String, Map[String, List[String]]),ag:Observations_querys.AggregationMethod) = {

    val (tag_exposure, exp) = exposure
    val (tag_route, routes) = rout
    val (tag_specie, species) = spe
    val (patterntag, patterns) = pat

    val l = Observations_querys.getEndpoints_v3(
      observationSource = "HistopathologicalFinding",
      admin_routes = routes,
      species = species,
      exposure_period = exp,
      relevanceFiltering = true,
      organs = List("liver"),
      patterns = patterns,
      patterntag = patterntag,
      conditions_tag = tag_specie + "_" + tag_route + "_" + tag_exposure,
      dropstructure = !structure,
      newtagging = tagging,
      path = home_path,
      debug = debug,
      aggregation = ag)
    for ((dt, filename) <- l) dt.toText(home_path + "/clustered/" + filename)
  }

  def unclustered_extract2(exposure: (String, Option[(Int, Int)]), rout: (String, List[String]), spe: (String, List[String]),ag:Observations_querys.AggregationMethod) = {

    val (tag_exposure, exp) = exposure
    val (tag_route, routes) = rout
    val (tag_specie, species) = spe

    val l2 = Observations_querys.getEndpoints_v3(
      observationSource = "ClinicalChemicalFinding",
      observations = CCFindings,
      admin_routes = routes,
      species = species,
      exposure_period = exp,
      relevanceFiltering = true,
      conditions_tag = tag_specie + "_" + tag_route + "_" + tag_exposure,
      dropstructure = !structure,
      newtagging = tagging,
      debug = debug,
      path = home_path,
      aggregation = ag)

    for ((dt, filename) <- l2) dt.toText(home_path + "/unclustered/" + filename)

    val l = Observations_querys.getEndpoints_v3(
      observationSource = "HistopathologicalFinding",
      admin_routes = routes,
      species = species,
      exposure_period = exp,
      relevanceFiltering = true,
      organs = List("liver"),
      conditions_tag = tag_specie + "_" + tag_route + "_" + tag_exposure,
      dropstructure = !structure,
      newtagging = tagging,
      debug = debug,
      path = home_path,
      aggregation = ag)

    for ((dt, filename) <- l) dt.toText(home_path + "/unclustered/" + filename)

    val l3 = Observations_querys.getEndpoints_v3(
      observationSource = "OrganWeights",
      observations = List("liver"),
      admin_routes = routes,
      species = species,
      exposure_period = exp,
      relevanceFiltering = true,
      conditions_tag = tag_specie + "_" + tag_route + "_" + tag_exposure,
      dropstructure = !structure,
      newtagging = tagging,
      debug = debug,
      path = home_path,
      aggregation = ag)

    for ((dt, filename) <- l3) dt.toText(home_path + "/unclustered/" + filename)
    println("Extracted: ", tag_exposure, tag_route, tag_specie)
  }

  def unclustered_extract(ag:Observations_querys.AggregationMethod) = {
    for (
      (ex, r, spe) <- exposures.zip(routes_clusters).zip(species_clusters).map { case ((a, b), c) => (a, b, c) }
    ) {
      println(ex, r, spe)
      unclustered_extract2(ex, r, spe,ag)
    }

  }

  def cluster_extract(ag:Observations_querys.AggregationMethod)= {
    def getPatterns = {
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

      val patterns1 = getPatterns(home_path + "cluster_definition/cluster1_new.txt")
      val patterns2 = getPatterns(home_path + "cluster_definition/cluster2_new.txt")
      val patternslist = List(("cluster1_new", patterns1), ("cluster2_new", patterns2))
      patternslist
    }

    for (
      (e, r, s) <- exposures.zip(routes_clusters).zip(species_clusters).map { case ((a, b), c) => (a, b, c) }
    ) for (p <- getPatterns) clustered_extract2(e, r, s, p,ag)

  }

  def extract = {
    for (ag <- this.aggregations) {
      unclustered_extract(ag)
      cluster_extract(ag)
    }
  }
}

