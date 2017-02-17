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

object h2016_paper_repeated_dose_nd {

  val home_path = "/data/paper_repeated_dose_nd/"
  FileUtils.tmpPath = home_path + "sql/"

  def getQ = Observations_querys.getRangeDoses(List("INTRAESOPHAGEAL", "INTRAGASTRIC", "NASOGASTRIC", "ORAL", "ORAL GAVAGE", "OROPHARYNGEAL"), List("RAT"), Some(0, 1000000), List())

  val tagging = true
  val debug = false
  val structure = true
  val aggregations = List(Observations_querys.NumFindings, Observations_querys.LOAEL, Observations_querys.NumFindingsDistinct)

  val CCFindings = List(
    "Alanine Aminotransferase", "Aspartate Aminotransferase", "Gamma Glutamyl Transferase", "Bilirubin", "Direct Bilirubin", "Indirect Bilirubin", "Alkaline Phosphatase")

  val exposures = List(
    //("4weeks", Some(0, 35)),
    //("120days", Some(36, 120)),
    //("120plus", Some(121, 10000)),
    ("all_periods", Some(0, 100000)))

  val routes_clusters =
    List(
      ("Oral", List("INTRAESOPHAGEAL", "INTRAGASTRIC", "NASOGASTRIC", "ORAL", "ORAL GAVAGE", "OROPHARYNGEAL")))
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
      (tag_specie, species) <- species_clusters;
      agg <- aggregations
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
        debug = debug, path = home_path,
        aggregation = agg)

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
        debug = debug, path = home_path,
        aggregation = agg)

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
        debug = debug, path = home_path,
        aggregation = agg)

      for ((dt, filename) <- l3) dt.toText(home_path + "/unclustered/" + filename)

    }

  }

  def cluster_extract = {

    val degP = getPatterns(home_path + "cluster_definition/degeneration_profile.txt")
    val infP = getPatterns(home_path + "cluster_definition/inflammatory_liver_changes_profile.txt")
    val nnP = getPatterns(home_path + "cluster_definition/non_neoplasic_profile.txt")

    val patternslist = List(("degenerative_lesions", degP), ("inflammatory_liver_changes", infP), ("non_neoplasic", nnP))

    for (
      (tag_exposure, exp) <- exposures;
      (tag_route, routes) <- routes_clusters;
      (tag_specie, species) <- species_clusters;
      (patterntag, patterns) <- patternslist;
      agg <- aggregations
    ) {
      val negatives = getNegatives(species, routes, exp)
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
        debug = debug, path = home_path,
        aggregation = agg)
      for ((dt, filename) <- l) {
        val dt2 = if (filename.contains("pivoted"))
          dt.union_tagged(negatives,"positive","negative")
        else
          dt
        dt2.toText(home_path + "/clustered/" + filename)

      }

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

  def getNegatives(species: List[String], admin_routes: List[String], exposure_period: Option[(Int, Int)]) = {

    val dfRangeDoses = models.etox_reports.Observations_querys.getRangeDoses(admin_routes, species, exposure_period, List())

    val qNoHPF = Observations_querys.getNoHPFCompounds(
      observations = List(),
      admin_routes,
      "HistopathologicalFinding",
      species = species,
      relevanceFiltering = true,
      exposure_period = exposure_period,
      organs = List("liver"))

    val dfNoHPF = models.dataframe.DataFrame(Querys_etox_reports.con, qNoHPF).project("subst_id").unique
    val setNoHPF = dfNoHPF.projectField("subst_id")

    val qCompsDose = Observations_querys.RangeDoses_Agg(
      admin_routes = admin_routes,
      species = species,
      exposure_period = exposure_period)

    val qCompsDose1000 = qCompsDose.filter(_._2 > BigDecimal(1000.0))

    val dfCompsDose = models.dataframe.DataFrame(Querys_etox_reports.con, qCompsDose)

    dfNoHPF.toText(home_path + "/nohpf.tsv")
    println(dfNoHPF.size)
    dfCompsDose.toText(home_path + "/compsdose.tsv")
    println(dfCompsDose.size)

    val dfCfiltered = dfCompsDose
      .filter(row => if (row("min") == "") false else row("max").toFloat >= 1000.0)
      .filter(row => !setNoHPF.contains(row("subst_id")))
    dfCfiltered.toText(home_path + "/compsdose_filt.tsv")

    println(qNoHPF.selectStatement)
    println(qCompsDose1000.selectStatement)
    dfRangeDoses.toText(home_path + "/rangedoses.tsv")
    val d1 = Observations_querys.dtComps
      .join(dfCfiltered, "subst_id", "subst_id")

    d1.toText(home_path + "/negatives.tsv")

    val d2 = d1.join(dfRangeDoses, "subst_id", "database_substance_id")
    d2.toText(home_path + "/negatives2.tsv")
    d2.project("database_substance_id", "smiles", "min_dose", "max_dose", "molecular_weight", "mw_rdkit")

  }

}



