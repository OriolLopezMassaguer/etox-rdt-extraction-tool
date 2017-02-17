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

object FileDebug {

  def getOrgan(source: String, organs: List[String]) = {
    (source, organs) match {
      case ("HistopathologicalFinding", List()) => None
      case ("HistopathologicalFinding", lo)     => Some(lo.head)
      case _                                    => None
    }
  }

  def postfix(source: String, agg: Observations_querys.AggregationMethod, TR: Boolean, change: String, organ: Option[String], patterntag: String = "") = {
    val TRS = if (TR) "TR" else "All"
    val aggS = agg match {
      case Observations_querys.LOAEL               => "LOEL"
      case Observations_querys.Existence           => "Flag"
      case Observations_querys.MaxFoldChange       => "MaxFoldChange"
      case Observations_querys.NumFindingsDistinct => "NumFindingsDistinct"
      case Observations_querys.NumFindings         => "NumFindings"
    }
    val l = if (source == "HistopathologicalFinding") {
      organ match {
        case Some(organ) => List(organ, TRS, aggS)
        case None        => List(TRS, aggS)
      }
    } else
      List(TRS, aggS, change)

    if (patterntag != "") l.mkString("_") + "_" + patterntag else l.mkString("_")
  }

  def write_descriptions(species: List[String],
                         admin_routes: List[String],
                         exposure_period: Option[(Int, Int)],
                         relevancefiltering: List[Boolean],
                         observation_source: List[String],
                         organs: List[String],
                         observations: List[String],
                         path: String) = {
    val descriptions = getDescription(species, admin_routes, exposure_period, relevancefiltering, organs, observations)

    val fos = new PrintStream(path + "/description" + observation_source.head + ".txt")
    descriptions.map(s => fos.println(s))
    fos.close
  }

  def getDescription(species: List[String], admin_routes: List[String], exposure_period: Option[(Int, Int)], relevancefiltering: List[Boolean], organs: List[String], observations: List[String]) = {
    val expStr = exposure_period match {
      case None               => "all periods"
      case Some((exp1, exp2)) => "From " + exp1 + " days to " + exp2 + " days"
    }
    val l = List("Query filtering:", "", "Species: " + species, "",
      "Admin route: " + admin_routes, "",
      "Exposure period: " + expStr, "",
      "Treatment related only? " + relevancefiltering, "",
      "Organs (HPF Only): " + organs, "",
      "Organs expanded (HPF Only): " + organs.map(Ontologies_DB.expandTerm(_)).flatten, "",
      "Findings: " + observations, "", "Findings expanded: " + observations.map(Ontologies_DB.expandTerm(_)).flatten)
    l
  }
  def fileName(source: String, postfix: String) = List(source, postfix).mkString("_")
}

object Observations_querys {

  lazy val liver_expansion = List("liver").map(Ontologies_DB.expandTerm(_)).flatten

  val sqlConnection = Querys_etox_reports.con

  val conn = Querys_etox_reports.con
  def clean_HPF_temp = {
    val qsi = "truncate table findings_all_q"
    Querys_helper.executeQuery(conn, qsi)
  }

  type ObservationQuery = slick.driver.PostgresDriver.simple.Query[(Tables.Compounds, Tables.Study, Tables.FindingsAll), (Tables.CompoundsRow, Tables.StudyRow, Tables.FindingsAllRow), Seq]

  lazy val qComps = for (c <- CompoundQuerys.CompoundsAll) yield (c.databaseSubstanceId, c.smiles, c.molecularWeight, c.mw_rdkit, c)
  lazy val dtComps = DataFrame(Querys_etox_reports.con, qComps)

  abstract class AggregationMethod
  case object Existence extends AggregationMethod
  case object LOAEL extends AggregationMethod
  case object MaxFoldChange extends AggregationMethod
  case object NumFindings extends AggregationMethod
  case object NumFindingsDistinct extends AggregationMethod

  val rnd = new scala.util.Random()

  lazy val sourcesPrefix = Map(
    "UrianalysisFinding" -> "UAF",
    "ClinicalChemicalFinding" -> "CCF",
    "HistopathologicalFinding" -> "HPF",
    "ClinicalHaematologicalFinding" -> "CHF",
    "OrganWeights" -> "OW")
  lazy val sourcesText = Map(
    "All" -> "Quantitative observations",
    "UrianalysisFinding" -> "Urianalysis Findings",
    "ClinicalChemicalFinding" -> "Clinical Chemical Findings",
    "HistopathologicalFinding" -> "Histopathological Findings",
    "ClinicalHaematologicalFinding" -> "Clinical Haematological Findings",
    "OrganWeights" -> "Organ Weights")

  lazy val getObservationsSources = sourcesPrefix.keys.toList
  lazy val getQuantitativeObservationsSources = ((sourcesPrefix.keys.toSet) - "HistopathologicalFinding").toList

  def ObservationsRAW_AllFields(
    admin_routes: List[String] = List(),
    species: List[String] = List(),
    exposure_period: Option[(Int, Int)] = None,
    observation_source: List[String] = List(),
    relevancefiltering: Boolean = true,
    sexlist: List[String] = List(),
    organs: List[String] = List(),
    doseMin: Option[Float] = None,
    doseMax: Option[Float] = None,
    changes: List[String] = List(),
    observations: List[String] = List()) = {

    def optionallyFilterByObservation(observations: List[String])(observationsQ: ObservationQuery) = {
      val observations_expanded = observations.map(Ontologies_DB.expandTerm(_)).flatten
      println("Observations expansion: ")
      println(observations)
      println(" -----> ")
      println(observations_expanded)
      observations_expanded match {
        case List() => observationsQ
        case obs    => observationsQ.filter(_._3.observationNormalised inSet obs)
      }
    }

    def optionallyFilterByRelevance(relevancefiltering: Boolean)(observations: ObservationQuery) = {
      if (relevancefiltering)
        observations.filter(_._3.relevance === "Treatment related")
      else
        observations
    }

    def optionallyFilterByChange(changes: List[String])(observations: ObservationQuery) = {
      changes match {
        case List()  => observations
        case sources => observations.filter(_._3.change inSet changes)
      }
    }

    def optionallyFilterByObservationSource(observatonSource: List[String])(observations: ObservationQuery) = {
      observatonSource match {
        case List()  => observations
        case sources => observations.filter(_._3.source inSet sources)
      }
    }

    def optionallyFilterByOrgans(organs: List[String])(observations: ObservationQuery) = {
      organs match {
        case List()        => observations
        case List("liver") => observations.filter(_._3.organNormalised inSet this.liver_expansion)
        case sources => {
          val organs_expanded = organs.map(Ontologies_DB.expandTerm(_)).flatten
          println("Organs expansion: ")
          organs_expanded.map(println)
          observations.filter(_._3.organNormalised inSet organs_expanded)
        }

      }
    }
    def optionallyFilterByMinDose(doseMin: Option[Float])(observations: ObservationQuery) = {
      doseMin match {
        case Some(min) => observations.filter(_._3.dose >= BigDecimal(min))
        case None      => observations
      }
    }

    def optionallyFilterByMaxDose(doseMax: Option[Float])(observations: ObservationQuery) = {
      doseMax match {
        case Some(max) => observations.filter(_._3.dose <= BigDecimal(max))
        case None      => observations
      }
    }

    def optionallyFilterBySexList(sexList: List[String])(observations: ObservationQuery) = {
      sexList match {
        case List()  => observations
        case sources => observations.filter(_._3.sex inSet sexList)
      }
    }

    val subs: List[String] = List() //h2016_global_parameters.substids
    val q = subs match {
      case List() => for (
        (compound, study) <- CompoundQuerys.CompoundsStudies_Filter(admin_routes, species, exposure_period);
        obs <- FindingsAll if study.studyId === obs.studyId && study.substId === obs.substId && !compound.smiles.isEmpty
      ) yield (compound, study, obs)
      case l => {
        val q = for (
          (compound, study) <- CompoundQuerys.CompoundsStudies_Filter(admin_routes, species, exposure_period);
          obs <- FindingsAll if study.studyId === obs.studyId && study.substId === obs.substId && !compound.smiles.isEmpty
        ) yield (compound, study, obs)
        val q2 = q.filter(_._2.substId inSet l)
        q2
      }

    }

    val qf2 = for (
      r <- optionallyFilterByObservation(observations)(optionallyFilterByChange(changes)(optionallyFilterByOrgans(organs)(optionallyFilterBySexList(sexlist)(optionallyFilterByMinDose(doseMax)(optionallyFilterByMinDose(doseMin)(optionallyFilterByRelevance(relevancefiltering)(optionallyFilterByObservationSource(observation_source)(q))))))))
    ) yield (r._1, r._2, r._3)

    qf2
  }

  def ObservationsRAW(
    admin_routes: List[String] = List(),
    species: List[String] = List(),
    exposure_period: Option[(Int, Int)] = None,
    observation_source: List[String] = List(),
    relevancefiltering: Boolean = true,
    sexlist: List[String] = List(),
    organs: List[String] = List(),
    doseMin: Option[Float] = None,
    doseMax: Option[Float] = None,
    changes: List[String] = List(),
    observations: List[String] = List()) = {
    for (q <- this.ObservationsRAW_AllFields(admin_routes, species, exposure_period, observation_source, relevancefiltering, sexlist, organs, doseMin, doseMax, changes, observations))
      yield (q._3)
  }

  def RangeDoses_Agg(
    admin_routes: List[String] = List(),
    species: List[String] = List(),
    exposure_period: Option[(Int, Int)] = None,
    observation_source: List[String] = List(),
    sexlist: List[String] = List(),
    routes: List[String] = List()) = {
    val qRAW = ObservationsRAW(
      admin_routes,
      species,
      exposure_period,
      observation_source,
      sexlist = sexlist,
      relevancefiltering = false)
      .filter(_.dose > BigDecimal(0.0))
      .groupBy(
        { case a => a.substId })
      .map {
        case (g, r) => (g,
          r.map({ case a => a.dose }).max,
          r.map({ case a => a.dose }).min)
      }
    qRAW
  }

  def Observations_Agg(observations: List[String] = List(),
                       admin_routes: List[String] = List(),
                       species: List[String] = List(),
                       exposure_period: Option[(Int, Int)] = None,
                       observation_source: List[String] = List(),
                       relevancefiltering: Boolean = true,
                       sexlist: List[String],
                       organs: List[String] = List(),
                       doseMin: Option[Float] = None,
                       doseMax: Option[Float] = None,
                       aggregationMethod: AggregationMethod = Existence,
                       changes: List[String] = List(),
                       prefix: String = "",
                       postfix: String = "",
                       patterns: Map[String, List[String]],
                       onlyPatterns: Boolean = true,
                       debug: Boolean = false) = {

    val id: Int = rnd.nextInt
    val findingsQ = for (f <- Tables.FindingsAllQ if f.int4 === id) yield (f)
    def groupedQ = {

      aggregationMethod match {
        case MaxFoldChange => {
          val gp = findingsQ.groupBy({ case a => (a.substId, a.termTop) })
          val qg1 = gp.map { case (parameter, hpfs) => (parameter, hpfs.map({ case ccf => ccf.averagefoldchange }).max) }
          qg1
        }
        case NumFindingsDistinct => {
          val gp = findingsQ.groupBy({ case a => (a.substId, a.termTop) })
          val qg1 = gp.map { case (parameter, hpfs) => (parameter, hpfs.map({ case ccf => ccf.observationNormalised }).countDistinct) }
          qg1
        }
        case NumFindings => {
          val gp = findingsQ.groupBy({ case a => (a.substId, a.termTop) })
          val qg1 = gp.map { case (parameter, hpfs) => (parameter, hpfs.map({ case ccf => ccf.observationNormalised }).size) }
          qg1
        }
        case _ => {
          val gp = findingsQ.groupBy({ case a => (a.substId, a.termTop) })
          val qg1 = gp.map { case (parameter, hpfs) => (parameter, hpfs.map({ case ccf => ccf.dose }).min) }
          qg1
        }
      }
    }

    def qObsSelect =
      {
        val organsSelected = if (observation_source.head == "HistopathologicalFinding") organs else List()
        val changesSelected = if (observation_source.head == "HistopathologicalFinding") List() else changes
        val qObs = ObservationsRAW(admin_routes, species, exposure_period, observation_source, relevancefiltering, sexlist, organsSelected, doseMin, doseMax, changesSelected)
          .filter(_.dose > BigDecimal(0.0))
        val qObsProjected = for (q <- qObs) yield (q.substId, q.dose, q.source, q.observationNormalised)
        qObsProjected.selectStatement
      }

    def InsertFindingsPatterns = {
      def insert_patterns(qp: String, id: Int, source: String) = {
        val l = for ((pattern, findings) <- patterns) yield ({
          println("******")
          println(findings)
          println("******")
          val findings_expanded = findings.map(Ontologies_DB.expandTerm(_)).flatten.toList
          val findings_list = for (finding <- findings) yield ("'" + finding.replace("'", "''") + "'")
          findings_expanded.map(println)
          findings_list.map(println)
          val q = "insert into findings_all_q " +
            "(" +
            "select " + id + ",'pattern_" + pattern + "' term_top, findings.subst_id subst_id,findings.dose dose,observation_normalised observation_normalised" +
            " from (" + qp + ") findings " +
            " where findings.source = '" + source + "'" +
            " and findings.observation_normalised in (" + findings_list.mkString(",") + ")" +
            ")"
          println("-----------------------")
          println(q)
          println("-----------------------")
          q
        })

        l
      }

      for (q <- insert_patterns(qObsSelect, id, observation_source.head)) Querys_helper.executeQuery(conn, q)

    }

    def InsertFindingGroups = {

      def buildInsertNormalisedObservationTerms(qp: String, id: Int, term: Option[String], source: String) = {
        term match {
          case None => "insert into findings_all_q select " +
            id + ",findings.observation_normalised term_top, findings.subst_id subst_id,findings.dose dose " +
            " from (" + qp + ") findings " +
            " where findings.source = '" + source + "'"
          case Some(term) =>
            {
              val termEscaped = term.replace("'", "''")

              val optional_clause = if (observation_source.head == "HistopathologicalFinding")
                "(select child_term from label_params('" + termEscaped + "'))" +
                  " union " +
                  " (select parent_term from label_params('" + termEscaped + "'))" +
                  " union "
              else
                ""
              "insert into findings_all_q " +
                "(select " + id + ",'" + termEscaped + "' term_top, findings.subst_id subst_id,findings.dose dose " +
                " from (" + qp + ") findings " +
                " where findings.source = '" + source + "'" +
                " and findings.observation_normalised in (" +
                optional_clause +
                " (select '" + termEscaped + "'::text)) ) "
            }
        }
      }
      println("To expand observations")
      println(observations)
      val observations_expanded = if (debug) Ontologies_DB.expandObservations(observations, observation_source.head).take(10) else Ontologies_DB.expandObservations(observations, observation_source.head)
      println("Expanded observations")
      println(observations_expanded)
      observations_expanded match {
        case List()  => Querys_helper.executeQuery(conn, buildInsertNormalisedObservationTerms(qObsSelect, id, None, observation_source.head))
        case observs => for ((obse, i) <- observations_expanded) Querys_helper.executeQuery(conn, buildInsertNormalisedObservationTerms(qObsSelect, id, Some(obse), observation_source.head))
      }
    }

    val dtMain = getRangeDoses(admin_routes, species, exposure_period, sexlist)

    if (onlyPatterns) {
      println("Patterns")
      InsertFindingsPatterns
    } else {
      println("Groups & patterns")
      InsertFindingGroups
      InsertFindingsPatterns
    }

    //Unaggregated

    val findingsQProjected = for (f <- findingsQ) yield (f.int4, f.dose, f.termTop, f.substId, f.observationNormalised)
    //Querys_helper.exportQuery(findingsQProjected.selectStatement, "/data/paper_repeated_dose/unclustered/dtQ_UnAgg.html")

    val dtUnAgg = models.dataframe.DataFrame(Querys_etox_reports.con, findingsQProjected).renameFields(Map("subst_id" -> "database_substance_id"))
    //dtUnAgg.toText("/data/paper_repeated_dose/unclustered/dt_unagg.tsv")

    val dtUnAgg_Unpivoted = dtMain.join(dtUnAgg, "database_substance_id", "database_substance_id")
    //dtUnAgg.toText("/data/paper_repeated_dose/unclustered/dt_unagg_unpivoted.tsv")
    //Aggregated

    val dtAgg = models.dataframe.DataFrame(Querys_etox_reports.con, groupedQ).renameFields(Map("subst_id" -> "database_substance_id"))
    //Querys_helper.exportQuery(groupedQ.selectStatement, "/data/paper_repeated_dose/unclustered/dtQ_Agg.html")
    dtAgg.toText("/data/paper_repeated_dose/unclustered/dt_agg.tsv")

    val dtPivoted = aggregationMethod match {
      case Existence           => dtAgg.pivot_simple("database_substance_id", List("term_top"), prefix, postfix)
      case LOAEL               => dtAgg.pivotMeasure("database_substance_id", List("term_top"), "min", prefix, postfix)
      case MaxFoldChange       => dtAgg.pivotMeasure("database_substance_id", List("term_top"), "max", prefix, postfix)
      case NumFindings         => dtAgg.pivotMeasure("database_substance_id", List("term_top"), "count", prefix, postfix)
      case NumFindingsDistinct => dtAgg.pivotMeasure("database_substance_id", List("term_top"), "count", prefix, postfix)
    }
    //dtPivoted.toText("/data/paper_repeated_dose/unclustered/dt_pivoted.tsv")

    val dtAllPivoted = dtMain.join(dtPivoted, "database_substance_id", "database_substance_id")
    val dtAllUnpivoted = dtMain.join(dtAgg, "database_substance_id", "database_substance_id")
    //dtAllPivoted.toText("/data/paper_repeated_dose/unclustered/dt_Allpivoted.tsv")
    //dtAllUnpivoted.toText("/data/paper_repeated_dose/unclustered/dt_AllUnPivoted.tsv")
    //dtMain.toText("/data/paper_repeated_dose/unclustered/dt_main.tsv")

    (dtAllPivoted, dtAllUnpivoted, dtUnAgg_Unpivoted, id)
  }

  def getNoHPFCompounds(observations: List[String] = List(),
                        admin_routes: List[String] = List(),
                        observationSource: String = "",
                        species: List[String] = List(),
                        relevanceFiltering: Boolean = true,
                        exposure_period: Option[(Int, Int)] = None,
                        organs: List[String] = List(),
                        sex_list: List[String] = List()) = {
    val qObs = ObservationsRAW(admin_routes, species, exposure_period, List(observationSource), relevanceFiltering, sex_list, organs)
    val qObsProjected = for (q <- qObs if q.observationNormalised.isNotNull) yield (q.substId, q.dose, q.source, q.observationNormalised)
    qObsProjected
  }
  def getRangeDoses(admin_routes: List[String] = List(), species: List[String] = List(), exposure_period: Option[(Int, Int)] = None, sexlist: List[String]): DataFrame = {
    val qR = Observations_querys.RangeDoses_Agg(
      admin_routes = admin_routes,
      species = species,
      exposure_period = exposure_period,
      sexlist = sexlist)

    val dtR0 = models.dataframe.DataFrame(Querys_etox_reports.con, qR)
    println(qR.selectStatement)
    //dtR0.toText("rd0.txt")
    val dtR = dtR0
      .renameFields(Map("subst_id" -> "database_substance_id", "min" -> "min_dose", "max" -> "max_dose"))
    dtR
  }

  def getEndpoints_v3(
    observations: List[String] = List(),
    admin_routes: List[String] = List(),
    observationSource: String = "",
    species: List[String] = List(),
    relevanceFiltering: Boolean = true,
    changes: List[String] = List("Increased"),
    exposure_period: Option[(Int, Int)] = None,
    organs: List[String] = List(),
    sex_list: List[String] = List(),
    dropstructure: Boolean = true,
    patterns: Map[String, List[String]] = Map(),
    conditions_tag: String = "",
    patterntag: String = "",
    newtagging: Boolean = false,
    debug: Boolean = false,
    path: String = "",
    aggregation: Observations_querys.AggregationMethod = Observations_querys.LOAEL) = {

    def anonymize(dt: DataFrame) = {
      dt.join(models.etox_reports.CompoundQuerys.getAnonymousCompoundMap, "database_substance_id", "database_substance_id")

    }

    val onlyPatterns = !patterns.isEmpty

    val postfix = FileDebug.postfix(observationSource, aggregation, relevanceFiltering, "Increased", FileDebug.getOrgan(observationSource, organs), patterntag)

    val prefix = if (newtagging) sourcesPrefix(observationSource) + "_" + conditions_tag else sourcesPrefix(observationSource)
    val (dtPivoted, dtUnpivoted, dtUnAggUnpivoted, id) = Observations_Agg(
      observations = observations,
      admin_routes = admin_routes,
      species = species,
      exposure_period = exposure_period,
      observation_source = List(observationSource),
      relevancefiltering = relevanceFiltering,
      sexlist = sex_list,
      organs,
      None,
      None,
      aggregationMethod = aggregation,
      changes = changes,
      prefix = prefix,
      postfix = postfix,
      patterns = patterns,
      onlyPatterns = onlyPatterns,
      debug = debug)

    val prefile = FileDebug.fileName(observationSource + "_" + conditions_tag, postfix)

    val filenameUnpivoted = FileDebug.fileName(observationSource + "_" + conditions_tag + "_unpivoted", postfix) + ".tsv"
    val filenamePivoted = FileDebug.fileName(observationSource + "_" + conditions_tag + "_pivoted", postfix) + ".tsv"
    val filenameUnAggUnpivoted = FileDebug.fileName(observationSource + "_" + conditions_tag + "_unagg_unpivoted", postfix) + ".tsv"

    val cs = conditions_tag.split("_")
    val (specie, route, exposure) = (cs(0), cs(1), cs(2))
    val dtUnpivotedMod = dtUnpivoted
      .addConstantField("specie", specie)
      .addConstantField("route", route)
      .addConstantField("exposure", exposure)
      .addConstantField("findingtype", observationSource)

    val dtUnAggUnpivotedMod = dtUnAggUnpivoted
      .addConstantField("specie", specie)
      .addConstantField("route", route)
      .addConstantField("exposure", exposure)
      .addConstantField("findingtype", observationSource)

    //    List(
    //      (anonymize(dtUnpivotedMod), filenameUnpivoted),
    //      (anonymize(dtPivoted), filenamePivoted),
    //      (anonymize(dtUnAggUnpivotedMod), filenameUnAggUnpivoted))

    val l = List(
      (dtUnpivotedMod, filenameUnpivoted),
      (dtPivoted, filenamePivoted),
      (dtUnAggUnpivotedMod, filenameUnAggUnpivoted))
    val l2 = if (!dropstructure)
      for ((d, fname) <- l)
        yield (d.join(dtComps.project(List("database_substance_id", "smiles", "molecular_weight", "mw_rdkit")), "database_substance_id", "database_substance_id"), fname)
    else
      l
    FileDebug.write_descriptions(species, admin_routes, exposure_period, List(relevanceFiltering), List(observationSource), organs, observations, path)
    l2
  }

}