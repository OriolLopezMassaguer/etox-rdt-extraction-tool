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

object h2016_paper_repeated_dose {

  val home_path = "/data/paper_repeated_dose/"
  FileUtils.tmpPath=home_path+"sql/"
  
  val tagging = true
  val debug=false
  val structure=true

  val CCFindings = List(
    "Alanine Aminotransferase", "Aspartate Aminotransferase", "Gamma Glutamyl Transferase", "Bilirubin", "Direct Bilirubin", "Indirect Bilirubin", "Alkaline Phosphatase")

  val exposures = List(
    //("4weeks", Some(0, 35)),
    //("120days", Some(36, 120)),
    //("120plus", Some(121, 10000)),
    ("all_periods",Some(0, 100000)))

  val routes_clusters =
    List(
      ("Oral", List("DIETARY", "INTRAESOPHAGEAL", "INTRAGASTRIC", "NASOGASTRIC", "ORAL", "ORAL GAVAGE", "OROPHARYNGEAL")))
      //("Respiratory", List("ENDOTRACHEAL", "NASAL", "RESPIRATORY (INHALATION)")),
      //("Parenteral", List("INTRAMUSCULAR", "INTRAPERITONEAL", "INTRAVENOUS", "INTRAVENOUS BOLUS", "INTRAVENOUS DRIP", "PARENTERAL", "SUBCUTANEOUS")),
      //("Other", List("CUTANEOUS", "INTRA-ARTICULAR", "INTRADERMAL", "INTRAOCULAR", "INTRATHECAL", "INTRAUTERINE", "PERCUTANEOUS", "RECTAL", "SUBARACHNOID", "UNASSIGNED", "UNKNOWN", "VAGINAL")))

  val species_clusters =
    List(
      ("Rat", List("RAT")))
      //("Dog", List("DOG")),
      //("Mouse", List("MOUSE")),
      //("Monkey", List("MARMOSET", "MONKEY", "BABOON")),
      //("Other", List("RABBIT", "HAMSTER", "PIG", "GUINEA PIG")))

  

  def unclustered_extract = {

    for (
      (tag_exposure, exp) <- exposures;
      (tag_route, routes) <- routes_clusters;
      (tag_specie, species) <- species_clusters
    ) {


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
        debug=true)

      for ((dt,filename) <- l2) dt.toText(home_path + "/unclustered/" + filename)

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
        debug = debug)

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
        debug = debug)

      for ((dt, filename) <- l3) dt.toText(home_path + "/unclustered/" + filename)

    }

  }

  def cluster_extract = {

    val patterns1 = getPatterns(home_path + "cluster_definition/cluster1_new.txt")
    val patterns2 = getPatterns(home_path + "cluster_definition/cluster2_new.txt")
    val patternslist = List(("cluster1_new", patterns1), ("cluster2_new", patterns2))

    for (
      (tag_exposure, exp) <- exposures;
      (tag_route, routes) <- routes_clusters;
      (tag_specie, species) <- species_clusters;
      (patterntag, patterns) <- patternslist
    ) {
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
        debug=debug)
      for ((dt,filename) <- l) dt.toText(home_path + "/clustered/" + filename)
    }
  }
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
    unclustered_extract
    cluster_extract
  }
}


