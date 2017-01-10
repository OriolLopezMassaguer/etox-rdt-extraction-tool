package models.etox_reports

import slick.driver.PostgresDriver.simple._
import slick.jdbc.{ StaticQuery => Q }
import slick.lifted.ColumnExtensionMethods

import slick.ast._
import models.dataframe._
import java.sql.DriverManager
import java.sql.ResultSet
import gudusoft.gsqlparser.TGSqlParser
import gudusoft.gsqlparser.pp.para.GFmtOptFactory
import gudusoft.gsqlparser.pp.para.GOutputFmt
import gudusoft.gsqlparser.pp.stmtformatter.FormatterFactory
import gudusoft.gsqlparser.EDbVendor
import Tables._

object Querys_etox_reports {
  val cfg = models.Config
  var db: Database = Database.forURL(cfg.dbURLetox, cfg.dbUseretox, cfg.dbPasswordetox)
  Class.forName("org.postgresql.Driver")
  var con = DriverManager.getConnection(cfg.dbURLetox, cfg.dbUseretox, cfg.dbPasswordetox)
  var sqlConnection: java.sql.Connection = con

  val url = cfg.dbURLetox + "?user=" + cfg.dbUseretox + "&password=" + cfg.dbPasswordetox
}

object StudiesQuerys {

  type StudyQuery = Query[Tables.Study, Tables.StudyRow, Seq]

  def optionallyFilterByMinExposure(min_exp: Option[Int])(studies: StudyQuery): StudyQuery =
    min_exp match {
      case Some(min_exposure) ⇒ studies.filter(_.exposurePeriodDays >= min_exposure)
      case None ⇒ studies
    }

  def optionallyFilterByMaxExposure(max_exp: Option[Int])(studies: StudyQuery): StudyQuery =
    max_exp match {
      case Some(max_exposure) ⇒ studies.filter(_.exposurePeriodDays <= max_exposure)
      case None ⇒ studies
    }

  def getStudies(min_exposure_period: Option[Int] = None, max_exposure_period: Option[Int] = None) = {
    optionallyFilterByMaxExposure(max_exposure_period)(optionallyFilterByMinExposure(min_exposure_period)(Study))
  }

  val getStudiesSubacute = getStudies(Some(20), Some(35))
  val getStudiesSubchronic = getStudies(Some(81), Some(122))

  def Studies_admin_routes = {
    for ((comp, study) <- CompoundQuerys.CompoundsStudies)
      yield (study.administrationRoute)
  }

  def getSpecies = {
    Querys_etox_reports.db withSession {
      implicit session =>
        val q = Q.queryNA[String]("select distinct species from study order by species")
        val l2 = q.buildColl[List]
        l2
    }
  }

  def getSpeciesNormalised = {
    Querys_etox_reports.db withSession {
      implicit session =>
        val q = Q.queryNA[String]("select distinct normalised_species from study order by normalised_species")
        val l2 = q.buildColl[List]
        l2
    }
  }
  def getSexNormalised = {
    Querys_etox_reports.db withSession {
      implicit session =>
        val q = Q.queryNA[String]("select distinct normalised_sex from study order by normalised_sex")
        val l2 = q.buildColl[List]
        l2
    }
  }

  def getAdminroutes = {
    Querys_etox_reports.db withSession {
      implicit session =>
        val q = Q.queryNA[String]("select distinct administration_route from study  order by administration_route")
        val l2 = q.buildColl[List]
        println(l2)
        l2
    }
  }

  def getAdminroutesNormalised = {
    Querys_etox_reports.db withSession {
      implicit session =>
        val q = Q.queryNA[String]("select distinct normalised_administration_route from study  order by normalised_administration_route")
        q.buildColl[List]
    }
  }
}

object SynonymQuerys {
  val sqlConnection = Querys_etox_reports.con


  def synonyms(table: String, col: String) = {
    val qTerms = models.etox_reports.Tables.InputOntoEtoxOntologyTerms
    val qSynonyms = models.etox_reports.Tables.InputOntoVxSynonyms
    val q = for (
      term <- qTerms;
      synonym <- qSynonyms if term.ontologyTermId === synonym.ontologyTermId
        && synonym.vxTable === table
        && synonym.vxColumn === col
    ) yield (term, synonym)
    DataFrame(sqlConnection,q).project(List("VX_VALUE", "TERM_NAME")).renameFields(Map("VX_VALUE" -> "synonym", "TERM_NAME" -> "term"))
  }

  def getIdsForSynonyms(synonyms: List[String], table: String, col: String) = {

    val qTerms = models.etox_reports.Tables.InputOntoEtoxOntologyTerms
    val qSynonyms = models.etox_reports.Tables.InputOntoVxSynonyms
    val q = for (
      term <- qTerms;
      synonym <- qSynonyms if term.ontologyTermId === synonym.ontologyTermId
        && synonym.vxTable === table
        && synonym.vxColumn === col
    //&& synonym.vxValue inSet Set()
    ) yield (term, synonym)

  }
}

object CompoundQuerys {

  lazy val rodents = List("Guinea Pig", "Mouse", "Rabbit", "Rat")

  lazy val rodents_normalised = List("GUINEA PIG", "MOUSE", "RABBIT", "RAT")

  lazy val oral_routes = List("Drinking water", "Ground diet", "Intragastric", "Oesophageal gavage", "Ora in capsule", "Oral",
    "Oral (admixture with the diet)", "Oral (by gavage)", "Oral (diet)", "Oral (dietary)", "Oral (gastric tube)", "Oral (gavage)", "Oral (intubation)", "Oral (via gelatin capsules)",
    "Oral , by gavage", "Oral administration", "Oral by dietary admix", "Oral by gavage", "Oral by gavage, twice daily", "Oral by stomach tube", "Oral diet",
    "Oral drinking", "Oral feeding", "Oral gavage", "Oral gavage (dexametason); Intravenous (GGA_IRIS_SER013)", "Oral gavage once daily", "Oral gavage plus subcutaneous dosing",
    "Oral gavage ZD7054, Oral, via drinking water ZD1033", "Oral gavage, once daily", "Oral gavage, twice daily", "Oral in capsule", "Oral in capsules", "Oral in diet", "Oral in drinking water",
    "Oral in feed", "Oral in food", "Oral inhalation", "Oral intubation", "Oral(intubation)", "Oral, by gavage", "Oral, by gavage, once daily", "Oral, by gavage, twice daily", "Oral, by intubation",
    "Oral, by stomach tube", "Oral, Dietary admix", "Oral, once daily", "Oral, with stomach tube", "Oral, with the aid of a metal rodent intubator", "Orally", "Orally (by gavage)",
    "Orally by capsule", "Orally by gastric tube", "Orally by gavage", "Orally by gavage as a suspension, daily", "Orally by gavage once a day", "Orally by gavage, once daily",
    "Orally by intubation", "Orally by stomach tube", "Orally in capsules", "Orally in gelatin capsules", "Orally in gelatine capsules", "Orally via gastric tube",
    "Orally via gelatin capsule", "Orally with feed", "Oraly in capsules", "Orat", "p.o.", "Per os / gavage", "PO (Gavage)", "Via the food")

  lazy val oral_routes_normalised = List("ORAL", "ORAL GAVAGE")

  def CompoundsAll = {
    val q = for (
      compound <- Compounds if !compound.smiles.isEmpty
    ) yield (compound)
    q
  }

  lazy val getAnonymousCompoundMap: DataFrame = {
    val qR = for (c <- Compounds) yield c
    val dtR0 = DataFrame(Querys_etox_reports.con, qR)
    val dtR = dtR0.renameFields(Map("subst_id" -> "database_substance_id"))
    dtR.addRowNum("compound_id")
      .project(List("compound_id", "database_substance_id"))
  }

  def CompoundsStudies = {
    val q = for (
      compound <- Compounds if !compound.smiles.isEmpty;
      study <- Study if study.substId === compound.substId
    ) yield (compound, study)
    q
  }

  def CompoundsStudies_Filter(oral_routes: List[String], species: List[String], minmax: Option[(Int, Int)]) = {
    for (
      (compound, study) <- CompoundsStudies if filterByExposurePeriod(study, minmax)
        && filterByAdministrationRouteNormalised(study, oral_routes)
        && filterBySpecieNormalised(study, species)
    ) yield (compound, study)
  }

  def CompoundsStudies_Rodents_Oral_30days = CompoundsStudies_Filter(oral_routes, rodents, Some((22, 32)))

  def filterByExposurePeriod(study: Tables.Study, minmax: Option[(Int, Int)]) =
    {
      minmax match {
        case None => study.exposurePeriodDays === 0 || true
        case Some((min, max)) => study.exposurePeriodDays >= min && study.exposurePeriodDays <= max
      }
    }

  def filterByAdministrationRouteNormalised(study: Tables.Study, admroutes: List[String]) = {
    admroutes match {
      case List() => study.normalisedAdministrationRoute === "aaa" || true
      case _ => study.normalisedAdministrationRoute inSet admroutes
    }
  }

  def filterBySpecieNormalised(study: Tables.Study, species: List[String]) = {
    species match {
      case List() => study.normalisedSpecies === "aaa" || true
      case _ => study.normalisedSpecies inSet species
    }
  }
}
