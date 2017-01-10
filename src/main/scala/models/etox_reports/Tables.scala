package models.etox_reports

import scala.slick.jdbc.{GetResult => GR}
import scala.slick.lifted.ProvenShape.proveShapeOf
import scala.slick.model.ForeignKeyAction


object Tables extends {
  val profile = scala.slick.driver.PostgresDriver
} with Tables

/** Slick data model trait for extension, choice of backend or usage in the cake pattern. (Make sure to initialize this late.) */
trait Tables {
  val profile: scala.slick.driver.JdbcProfile
  import profile.simple._
  import scala.slick.model.ForeignKeyAction
  import scala.slick.collection.heterogenous._
  import scala.slick.collection.heterogenous.syntax._
  // NOTE: GetResult mappers for plain SQL are only generated for tables where Slick knows how to map the types of all columns.
  import scala.slick.jdbc.{GetResult => GR}
  
  /** DDL for all tables. Call .create to execute. */
  lazy val ddl = AllTerms.ddl ++ Ccfinding.ddl ++ Clinicalhaematologicalfindings.ddl ++ Compounds.ddl ++ Effectlevels.ddl ++ FindingsAll.ddl ++ FindingsAllGrouped.ddl ++ FindingsAllLevels.ddl ++ FindingsAllLevelsGrouped.ddl ++ FindingsAllQ.ddl ++ Generaltoxiceffects.ddl ++ Grossnecropsy.ddl ++ Hpfinding.ddl ++ HpfLevels.ddl ++ HyperplasiaFindingsLevels.ddl ++ InputAdditionalfindings.ddl ++ InputAnimalspergrouppersex.ddl ++ InputClinicalchemicalfindings.ddl ++ InputClinicalhaematologicalfindings.ddl ++ InputClinicalhaemostasisfindings.ddl ++ InputClinicalsigns.ddl ++ InputEffectlevels.ddl ++ InputFreetextstudydesign.ddl ++ InputGeneraltoxiceffects.ddl ++ InputGrossnecropsy.ddl ++ InputHistopathologicalfindings.ddl ++ InputOntoEtoxOntologyRelationships.ddl ++ InputOntoEtoxOntologyTerms.ddl ++ InputOntoVxSynonyms.ddl ++ InputOrganweights.ddl ++ InputStructures.ddl ++ InputStudies.ddl ++ InputSubstances.ddl ++ InputToxicokinetics.ddl ++ InputUrinalysisfindings.ddl ++ OntoTermsRelations.ddl ++ Organweights.ddl ++ Study.ddl ++ StudyRangeTime.ddl ++ TimepointUnitsMapping.ddl ++ Urianalysisfindings.ddl
  
  /** Entity class storing rows of table AllTerms
   *  @param term Database column term DBType(text), Length(2147483647,true), Default(None) */
  case class AllTermsRow(term: Option[String] = None)
  /** GetResult implicit for fetching AllTermsRow objects using plain SQL queries */
  implicit def GetResultAllTermsRow(implicit e0: GR[Option[String]]): GR[AllTermsRow] = GR{
    prs => import prs._
    AllTermsRow(<<?[String])
  }
  /** Table description of table all_terms. Objects of this class serve as prototypes for rows in queries. */
  class AllTerms(_tableTag: Tag) extends Table[AllTermsRow](_tableTag, "all_terms") {
    def * = term <> (AllTermsRow, AllTermsRow.unapply)
    
    /** Database column term DBType(text), Length(2147483647,true), Default(None) */
    val term: Column[Option[String]] = column[Option[String]]("term", O.Length(2147483647,varying=true), O.Default(None))
  }
  /** Collection-like TableQuery object for table AllTerms */
  lazy val AllTerms = new TableQuery(tag => new AllTerms(tag))
  
  /** Entity class storing rows of table Ccfinding
   *  @param substId Database column subst_id DBType(varchar), Length(100,true), Default(None)
   *  @param studyId Database column study_id DBType(varchar), Length(100,true), Default(None)
   *  @param dose Database column dose DBType(numeric), Default(None)
   *  @param sex Database column sex DBType(varchar), Length(100,true), Default(None)
   *  @param standarisedSex Database column standarised_sex DBType(varchar), Length(100,true), Default(None)
   *  @param normalisedSex Database column normalised_sex DBType(varchar), Length(100,true), Default(None)
   *  @param timepoint Database column timepoint DBType(float8), Default(None)
   *  @param timepointUnit Database column timepoint_unit DBType(varchar), Length(30,true), Default(None)
   *  @param relevance Database column relevance DBType(varchar), Length(1000,true), Default(None)
   *  @param sd Database column sd DBType(numeric), Default(None)
   *  @param clinicalChemistryParameter Database column clinical_chemistry_parameter DBType(varchar), Length(1000,true), Default(None)
   *  @param standarisedParameter Database column standarised_parameter DBType(varchar), Length(1000,true), Default(None)
   *  @param averageFoldChange Database column average_fold_change DBType(numeric), Default(None)
   *  @param finding Database column finding DBType(varchar), Length(1000,true), Default(None)
   *  @param averageValue Database column average_value DBType(numeric), Default(None)
   *  @param unit Database column unit DBType(varchar), Length(1000,true), Default(None)
   *  @param standarisedParameterRevised Database column standarised_parameter_revised DBType(varchar), Length(1000,true), Default(None) */
  case class CcfindingRow(substId: Option[String] = None, studyId: Option[String] = None, dose: Option[scala.math.BigDecimal] = None, sex: Option[String] = None, standarisedSex: Option[String] = None, normalisedSex: Option[String] = None, timepoint: Option[Double] = None, timepointUnit: Option[String] = None, relevance: Option[String] = None, sd: Option[scala.math.BigDecimal] = None, clinicalChemistryParameter: Option[String] = None, standarisedParameter: Option[String] = None, averageFoldChange: Option[scala.math.BigDecimal] = None, finding: Option[String] = None, averageValue: Option[scala.math.BigDecimal] = None, unit: Option[String] = None, standarisedParameterRevised: Option[String] = None)
  /** GetResult implicit for fetching CcfindingRow objects using plain SQL queries */
  implicit def GetResultCcfindingRow(implicit e0: GR[Option[String]], e1: GR[Option[scala.math.BigDecimal]], e2: GR[Option[Double]]): GR[CcfindingRow] = GR{
    prs => import prs._
    CcfindingRow.tupled((<<?[String], <<?[String], <<?[scala.math.BigDecimal], <<?[String], <<?[String], <<?[String], <<?[Double], <<?[String], <<?[String], <<?[scala.math.BigDecimal], <<?[String], <<?[String], <<?[scala.math.BigDecimal], <<?[String], <<?[scala.math.BigDecimal], <<?[String], <<?[String]))
  }
  /** Table description of table ccfinding. Objects of this class serve as prototypes for rows in queries. */
  class Ccfinding(_tableTag: Tag) extends Table[CcfindingRow](_tableTag, "ccfinding") {
    def * = (substId, studyId, dose, sex, standarisedSex, normalisedSex, timepoint, timepointUnit, relevance, sd, clinicalChemistryParameter, standarisedParameter, averageFoldChange, finding, averageValue, unit, standarisedParameterRevised) <> (CcfindingRow.tupled, CcfindingRow.unapply)
    
    /** Database column subst_id DBType(varchar), Length(100,true), Default(None) */
    val substId: Column[Option[String]] = column[Option[String]]("subst_id", O.Length(100,varying=true), O.Default(None))
    /** Database column study_id DBType(varchar), Length(100,true), Default(None) */
    val studyId: Column[Option[String]] = column[Option[String]]("study_id", O.Length(100,varying=true), O.Default(None))
    /** Database column dose DBType(numeric), Default(None) */
    val dose: Column[Option[scala.math.BigDecimal]] = column[Option[scala.math.BigDecimal]]("dose", O.Default(None))
    /** Database column sex DBType(varchar), Length(100,true), Default(None) */
    val sex: Column[Option[String]] = column[Option[String]]("sex", O.Length(100,varying=true), O.Default(None))
    /** Database column standarised_sex DBType(varchar), Length(100,true), Default(None) */
    val standarisedSex: Column[Option[String]] = column[Option[String]]("standarised_sex", O.Length(100,varying=true), O.Default(None))
    /** Database column normalised_sex DBType(varchar), Length(100,true), Default(None) */
    val normalisedSex: Column[Option[String]] = column[Option[String]]("normalised_sex", O.Length(100,varying=true), O.Default(None))
    /** Database column timepoint DBType(float8), Default(None) */
    val timepoint: Column[Option[Double]] = column[Option[Double]]("timepoint", O.Default(None))
    /** Database column timepoint_unit DBType(varchar), Length(30,true), Default(None) */
    val timepointUnit: Column[Option[String]] = column[Option[String]]("timepoint_unit", O.Length(30,varying=true), O.Default(None))
    /** Database column relevance DBType(varchar), Length(1000,true), Default(None) */
    val relevance: Column[Option[String]] = column[Option[String]]("relevance", O.Length(1000,varying=true), O.Default(None))
    /** Database column sd DBType(numeric), Default(None) */
    val sd: Column[Option[scala.math.BigDecimal]] = column[Option[scala.math.BigDecimal]]("sd", O.Default(None))
    /** Database column clinical_chemistry_parameter DBType(varchar), Length(1000,true), Default(None) */
    val clinicalChemistryParameter: Column[Option[String]] = column[Option[String]]("clinical_chemistry_parameter", O.Length(1000,varying=true), O.Default(None))
    /** Database column standarised_parameter DBType(varchar), Length(1000,true), Default(None) */
    val standarisedParameter: Column[Option[String]] = column[Option[String]]("standarised_parameter", O.Length(1000,varying=true), O.Default(None))
    /** Database column average_fold_change DBType(numeric), Default(None) */
    val averageFoldChange: Column[Option[scala.math.BigDecimal]] = column[Option[scala.math.BigDecimal]]("average_fold_change", O.Default(None))
    /** Database column finding DBType(varchar), Length(1000,true), Default(None) */
    val finding: Column[Option[String]] = column[Option[String]]("finding", O.Length(1000,varying=true), O.Default(None))
    /** Database column average_value DBType(numeric), Default(None) */
    val averageValue: Column[Option[scala.math.BigDecimal]] = column[Option[scala.math.BigDecimal]]("average_value", O.Default(None))
    /** Database column unit DBType(varchar), Length(1000,true), Default(None) */
    val unit: Column[Option[String]] = column[Option[String]]("unit", O.Length(1000,varying=true), O.Default(None))
    /** Database column standarised_parameter_revised DBType(varchar), Length(1000,true), Default(None) */
    val standarisedParameterRevised: Column[Option[String]] = column[Option[String]]("standarised_parameter_revised", O.Length(1000,varying=true), O.Default(None))
    
    /** Foreign key referencing Study (database name ccfinding_subst_id_fkey) */
    lazy val studyFk = foreignKey("ccfinding_subst_id_fkey", (substId, studyId), Study)(r => (r.substId, r.studyId), onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.Cascade)
  }
  /** Collection-like TableQuery object for table Ccfinding */
  lazy val Ccfinding = new TableQuery(tag => new Ccfinding(tag))
  
  /** Entity class storing rows of table Clinicalhaematologicalfindings
   *  @param substId Database column subst_id DBType(varchar), Length(100,true), Default(None)
   *  @param studyId Database column study_id DBType(varchar), Length(100,true), Default(None)
   *  @param averagefoldchange Database column averagefoldchange DBType(numeric), Default(None)
   *  @param averagevalue Database column averagevalue DBType(numeric), Default(None)
   *  @param clinicalhaematologyparameter Database column clinicalhaematologyparameter DBType(varchar), Length(500,true), Default(None)
   *  @param standardisedparameter Database column standardisedparameter DBType(varchar), Length(500,true), Default(None)
   *  @param normalisedClinicalhaematologyparameter Database column normalised_clinicalhaematologyparameter DBType(varchar), Length(500,true), Default(None)
   *  @param dosemgkg Database column dosemgkg DBType(numeric), Default(None)
   *  @param finding Database column finding DBType(varchar), Length(100,true), Default(None)
   *  @param relevance Database column relevance DBType(varchar), Length(100,true), Default(None)
   *  @param sd Database column sd DBType(numeric), Default(None)
   *  @param sex Database column sex DBType(varchar), Length(100,true), Default(None)
   *  @param normalisedSex Database column normalised_sex DBType(varchar), Length(100,true), Default(None)
   *  @param standardisedsex Database column standardisedsex DBType(varchar), Length(100,true), Default(None)
   *  @param timepoint Database column timepoint DBType(numeric), Default(None)
   *  @param timepointunit Database column timepointunit DBType(varchar), Length(100,true), Default(None)
   *  @param unit Database column unit DBType(varchar), Length(1000,true), Default(None) */
  case class ClinicalhaematologicalfindingsRow(substId: Option[String] = None, studyId: Option[String] = None, averagefoldchange: Option[scala.math.BigDecimal] = None, averagevalue: Option[scala.math.BigDecimal] = None, clinicalhaematologyparameter: Option[String] = None, standardisedparameter: Option[String] = None, normalisedClinicalhaematologyparameter: Option[String] = None, dosemgkg: Option[scala.math.BigDecimal] = None, finding: Option[String] = None, relevance: Option[String] = None, sd: Option[scala.math.BigDecimal] = None, sex: Option[String] = None, normalisedSex: Option[String] = None, standardisedsex: Option[String] = None, timepoint: Option[scala.math.BigDecimal] = None, timepointunit: Option[String] = None, unit: Option[String] = None)
  /** GetResult implicit for fetching ClinicalhaematologicalfindingsRow objects using plain SQL queries */
  implicit def GetResultClinicalhaematologicalfindingsRow(implicit e0: GR[Option[String]], e1: GR[Option[scala.math.BigDecimal]]): GR[ClinicalhaematologicalfindingsRow] = GR{
    prs => import prs._
    ClinicalhaematologicalfindingsRow.tupled((<<?[String], <<?[String], <<?[scala.math.BigDecimal], <<?[scala.math.BigDecimal], <<?[String], <<?[String], <<?[String], <<?[scala.math.BigDecimal], <<?[String], <<?[String], <<?[scala.math.BigDecimal], <<?[String], <<?[String], <<?[String], <<?[scala.math.BigDecimal], <<?[String], <<?[String]))
  }
  /** Table description of table clinicalhaematologicalfindings. Objects of this class serve as prototypes for rows in queries. */
  class Clinicalhaematologicalfindings(_tableTag: Tag) extends Table[ClinicalhaematologicalfindingsRow](_tableTag, "clinicalhaematologicalfindings") {
    def * = (substId, studyId, averagefoldchange, averagevalue, clinicalhaematologyparameter, standardisedparameter, normalisedClinicalhaematologyparameter, dosemgkg, finding, relevance, sd, sex, normalisedSex, standardisedsex, timepoint, timepointunit, unit) <> (ClinicalhaematologicalfindingsRow.tupled, ClinicalhaematologicalfindingsRow.unapply)
    
    /** Database column subst_id DBType(varchar), Length(100,true), Default(None) */
    val substId: Column[Option[String]] = column[Option[String]]("subst_id", O.Length(100,varying=true), O.Default(None))
    /** Database column study_id DBType(varchar), Length(100,true), Default(None) */
    val studyId: Column[Option[String]] = column[Option[String]]("study_id", O.Length(100,varying=true), O.Default(None))
    /** Database column averagefoldchange DBType(numeric), Default(None) */
    val averagefoldchange: Column[Option[scala.math.BigDecimal]] = column[Option[scala.math.BigDecimal]]("averagefoldchange", O.Default(None))
    /** Database column averagevalue DBType(numeric), Default(None) */
    val averagevalue: Column[Option[scala.math.BigDecimal]] = column[Option[scala.math.BigDecimal]]("averagevalue", O.Default(None))
    /** Database column clinicalhaematologyparameter DBType(varchar), Length(500,true), Default(None) */
    val clinicalhaematologyparameter: Column[Option[String]] = column[Option[String]]("clinicalhaematologyparameter", O.Length(500,varying=true), O.Default(None))
    /** Database column standardisedparameter DBType(varchar), Length(500,true), Default(None) */
    val standardisedparameter: Column[Option[String]] = column[Option[String]]("standardisedparameter", O.Length(500,varying=true), O.Default(None))
    /** Database column normalised_clinicalhaematologyparameter DBType(varchar), Length(500,true), Default(None) */
    val normalisedClinicalhaematologyparameter: Column[Option[String]] = column[Option[String]]("normalised_clinicalhaematologyparameter", O.Length(500,varying=true), O.Default(None))
    /** Database column dosemgkg DBType(numeric), Default(None) */
    val dosemgkg: Column[Option[scala.math.BigDecimal]] = column[Option[scala.math.BigDecimal]]("dosemgkg", O.Default(None))
    /** Database column finding DBType(varchar), Length(100,true), Default(None) */
    val finding: Column[Option[String]] = column[Option[String]]("finding", O.Length(100,varying=true), O.Default(None))
    /** Database column relevance DBType(varchar), Length(100,true), Default(None) */
    val relevance: Column[Option[String]] = column[Option[String]]("relevance", O.Length(100,varying=true), O.Default(None))
    /** Database column sd DBType(numeric), Default(None) */
    val sd: Column[Option[scala.math.BigDecimal]] = column[Option[scala.math.BigDecimal]]("sd", O.Default(None))
    /** Database column sex DBType(varchar), Length(100,true), Default(None) */
    val sex: Column[Option[String]] = column[Option[String]]("sex", O.Length(100,varying=true), O.Default(None))
    /** Database column normalised_sex DBType(varchar), Length(100,true), Default(None) */
    val normalisedSex: Column[Option[String]] = column[Option[String]]("normalised_sex", O.Length(100,varying=true), O.Default(None))
    /** Database column standardisedsex DBType(varchar), Length(100,true), Default(None) */
    val standardisedsex: Column[Option[String]] = column[Option[String]]("standardisedsex", O.Length(100,varying=true), O.Default(None))
    /** Database column timepoint DBType(numeric), Default(None) */
    val timepoint: Column[Option[scala.math.BigDecimal]] = column[Option[scala.math.BigDecimal]]("timepoint", O.Default(None))
    /** Database column timepointunit DBType(varchar), Length(100,true), Default(None) */
    val timepointunit: Column[Option[String]] = column[Option[String]]("timepointunit", O.Length(100,varying=true), O.Default(None))
    /** Database column unit DBType(varchar), Length(1000,true), Default(None) */
    val unit: Column[Option[String]] = column[Option[String]]("unit", O.Length(1000,varying=true), O.Default(None))
    
    /** Foreign key referencing Study (database name clinicalhaematologicalfindings_subst_id_fkey) */
    lazy val studyFk = foreignKey("clinicalhaematologicalfindings_subst_id_fkey", (substId, studyId), Study)(r => (r.substId, r.studyId), onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.Cascade)
  }
  /** Collection-like TableQuery object for table Clinicalhaematologicalfindings */
  lazy val Clinicalhaematologicalfindings = new TableQuery(tag => new Clinicalhaematologicalfindings(tag))
  
  /** Entity class storing rows of table Compounds
   *  @param compoundId Database column compound_id DBType(serial), AutoInc, PrimaryKey
   *  @param smiles Database column smiles DBType(varchar), Length(1000,true), Default(None)
   *  @param substId Database column subst_id DBType(varchar), Length(100,true), Default(None)
   *  @param queryText Database column query_text DBType(varchar), Length(100,true), Default(None)
   *  @param dbVersion Database column db_version DBType(varchar), Length(100,true), Default(None)
   *  @param softwareVersion Database column software_version DBType(varchar), Length(100,true), Default(None)
   *  @param cdkTitle Database column cdk_title DBType(varchar), Length(100,true), Default(None)
   *  @param dbDescription Database column db_description DBType(varchar), Length(100,true), Default(None)
   *  @param dbName Database column db_name DBType(varchar), Length(100,true), Default(None)
   *  @param databaseSubstanceId Database column database_substance_id DBType(varchar), Length(100,true), Default(None)
   *  @param inchi Database column inchi DBType(varchar), Length(1000,true), Default(None)
   *  @param substanceStatus Database column substance_status DBType(varchar), Length(100,true), Default(None)
   *  @param casNumber Database column cas_number DBType(varchar), Length(100,true), Default(None)
   *  @param commonName Database column common_name DBType(varchar), Length(1000,true), Default(None)
   *  @param molecularWeight Database column molecular_weight DBType(varchar), Length(100,true), Default(None)
   *  @param pharmacologicalAction Database column pharmacological_action DBType(varchar), Length(1000,true), Default(None)
   *  @param viticLegacyRecno Database column vitic_legacy_recno DBType(varchar), Length(100,true), Default(None)
   *  @param molecularFormula Database column molecular_formula DBType(varchar), Length(1000,true), Default(None)
   *  @param imgBase64 Database column img_base64 DBType(varchar), Length(500000,true), Default(None)
   *  @param source Database column source DBType(varchar), Length(100,true), Default(None)
   *  @param m Database column m DBType(mol), Length(2147483647,false), Default(None) */
  case class CompoundsRow(compoundId: Int, smiles: Option[String] = None, substId: Option[String] = None, queryText: Option[String] = None, dbVersion: Option[String] = None, softwareVersion: Option[String] = None, cdkTitle: Option[String] = None, dbDescription: Option[String] = None, dbName: Option[String] = None, databaseSubstanceId: Option[String] = None, inchi: Option[String] = None, substanceStatus: Option[String] = None, casNumber: Option[String] = None, commonName: Option[String] = None, molecularWeight: Option[String] = None, pharmacologicalAction: Option[String] = None, viticLegacyRecno: Option[String] = None, molecularFormula: Option[String] = None, imgBase64: Option[String] = None, source: Option[String] = None, m: Option[String] = None)
  /** GetResult implicit for fetching CompoundsRow objects using plain SQL queries */
  implicit def GetResultCompoundsRow(implicit e0: GR[Int], e1: GR[Option[String]]): GR[CompoundsRow] = GR{
    prs => import prs._
    CompoundsRow.tupled((<<[Int], <<?[String], <<?[String], <<?[String], <<?[String], <<?[String], <<?[String], <<?[String], <<?[String], <<?[String], <<?[String], <<?[String], <<?[String], <<?[String], <<?[String], <<?[String], <<?[String], <<?[String], <<?[String], <<?[String], <<?[String]))
  }
  /** Table description of table compounds. Objects of this class serve as prototypes for rows in queries. */
  class Compounds(_tableTag: Tag) extends Table[CompoundsRow](_tableTag, "compounds") {
    def * = (compoundId, smiles, substId, queryText, dbVersion, softwareVersion, cdkTitle, dbDescription, dbName, databaseSubstanceId, inchi, substanceStatus, casNumber, commonName, molecularWeight, pharmacologicalAction, viticLegacyRecno, molecularFormula, imgBase64, source, m) <> (CompoundsRow.tupled, CompoundsRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (compoundId.?, smiles, substId, queryText, dbVersion, softwareVersion, cdkTitle, dbDescription, dbName, databaseSubstanceId, inchi, substanceStatus, casNumber, commonName, molecularWeight, pharmacologicalAction, viticLegacyRecno, molecularFormula, imgBase64, source, m).shaped.<>({r=>import r._; _1.map(_=> CompoundsRow.tupled((_1.get, _2, _3, _4, _5, _6, _7, _8, _9, _10, _11, _12, _13, _14, _15, _16, _17, _18, _19, _20, _21)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))
    
    /** Database column compound_id DBType(serial), AutoInc, PrimaryKey */
    val compoundId: Column[Int] = column[Int]("compound_id", O.AutoInc, O.PrimaryKey)
    /** Database column smiles DBType(varchar), Length(1000,true), Default(None) */
    val smiles: Column[Option[String]] = column[Option[String]]("smiles", O.Length(1000,varying=true), O.Default(None))
    /** Database column subst_id DBType(varchar), Length(100,true), Default(None) */
    val substId: Column[Option[String]] = column[Option[String]]("subst_id", O.Length(100,varying=true), O.Default(None))
    /** Database column query_text DBType(varchar), Length(100,true), Default(None) */
    val queryText: Column[Option[String]] = column[Option[String]]("query_text", O.Length(100,varying=true), O.Default(None))
    /** Database column db_version DBType(varchar), Length(100,true), Default(None) */
    val dbVersion: Column[Option[String]] = column[Option[String]]("db_version", O.Length(100,varying=true), O.Default(None))
    /** Database column software_version DBType(varchar), Length(100,true), Default(None) */
    val softwareVersion: Column[Option[String]] = column[Option[String]]("software_version", O.Length(100,varying=true), O.Default(None))
    /** Database column cdk_title DBType(varchar), Length(100,true), Default(None) */
    val cdkTitle: Column[Option[String]] = column[Option[String]]("cdk_title", O.Length(100,varying=true), O.Default(None))
    /** Database column db_description DBType(varchar), Length(100,true), Default(None) */
    val dbDescription: Column[Option[String]] = column[Option[String]]("db_description", O.Length(100,varying=true), O.Default(None))
    /** Database column db_name DBType(varchar), Length(100,true), Default(None) */
    val dbName: Column[Option[String]] = column[Option[String]]("db_name", O.Length(100,varying=true), O.Default(None))
    /** Database column database_substance_id DBType(varchar), Length(100,true), Default(None) */
    val databaseSubstanceId: Column[Option[String]] = column[Option[String]]("database_substance_id", O.Length(100,varying=true), O.Default(None))
    /** Database column inchi DBType(varchar), Length(1000,true), Default(None) */
    val inchi: Column[Option[String]] = column[Option[String]]("inchi", O.Length(1000,varying=true), O.Default(None))
    /** Database column substance_status DBType(varchar), Length(100,true), Default(None) */
    val substanceStatus: Column[Option[String]] = column[Option[String]]("substance_status", O.Length(100,varying=true), O.Default(None))
    /** Database column cas_number DBType(varchar), Length(100,true), Default(None) */
    val casNumber: Column[Option[String]] = column[Option[String]]("cas_number", O.Length(100,varying=true), O.Default(None))
    /** Database column common_name DBType(varchar), Length(1000,true), Default(None) */
    val commonName: Column[Option[String]] = column[Option[String]]("common_name", O.Length(1000,varying=true), O.Default(None))
    /** Database column molecular_weight DBType(varchar), Length(100,true), Default(None) */
    val molecularWeight: Column[Option[String]] = column[Option[String]]("molecular_weight", O.Length(100,varying=true), O.Default(None))
    /** Database column pharmacological_action DBType(varchar), Length(1000,true), Default(None) */
    val pharmacologicalAction: Column[Option[String]] = column[Option[String]]("pharmacological_action", O.Length(1000,varying=true), O.Default(None))
    /** Database column vitic_legacy_recno DBType(varchar), Length(100,true), Default(None) */
    val viticLegacyRecno: Column[Option[String]] = column[Option[String]]("vitic_legacy_recno", O.Length(100,varying=true), O.Default(None))
    /** Database column molecular_formula DBType(varchar), Length(1000,true), Default(None) */
    val molecularFormula: Column[Option[String]] = column[Option[String]]("molecular_formula", O.Length(1000,varying=true), O.Default(None))
    /** Database column img_base64 DBType(varchar), Length(500000,true), Default(None) */
    val imgBase64: Column[Option[String]] = column[Option[String]]("img_base64", O.Length(500000,varying=true), O.Default(None))
    /** Database column source DBType(varchar), Length(100,true), Default(None) */
    val source: Column[Option[String]] = column[Option[String]]("source", O.Length(100,varying=true), O.Default(None))
    /** Database column m DBType(mol), Length(2147483647,false), Default(None) */
    val m: Column[Option[String]] = column[Option[String]]("m", O.Length(2147483647,varying=false), O.Default(None))
    
    /** Uniqueness Index over (substId) (database name compounds_subst_id_key) */
    val index1 = index("compounds_subst_id_key", substId, unique=true)
  }
  /** Collection-like TableQuery object for table Compounds */
  lazy val Compounds = new TableQuery(tag => new Compounds(tag))
  
  /** Entity class storing rows of table Effectlevels
   *  @param substId Database column subst_id DBType(varchar), Length(100,true), Default(None)
   *  @param studyId Database column study_id DBType(varchar), Length(100,true), Default(None)
   *  @param effectleveltype Database column effectleveltype DBType(varchar), Length(1000,true), Default(None)
   *  @param effectlevelunit Database column effectlevelunit DBType(varchar), Length(1000,true), Default(None)
   *  @param effectlevelvalue Database column effectlevelvalue DBType(numeric), Default(None)
   *  @param standardisedeffectlevel Database column standardisedeffectlevel DBType(varchar), Length(1000,true), Default(None) */
  case class EffectlevelsRow(substId: Option[String] = None, studyId: Option[String] = None, effectleveltype: Option[String] = None, effectlevelunit: Option[String] = None, effectlevelvalue: Option[scala.math.BigDecimal] = None, standardisedeffectlevel: Option[String] = None)
  /** GetResult implicit for fetching EffectlevelsRow objects using plain SQL queries */
  implicit def GetResultEffectlevelsRow(implicit e0: GR[Option[String]], e1: GR[Option[scala.math.BigDecimal]]): GR[EffectlevelsRow] = GR{
    prs => import prs._
    EffectlevelsRow.tupled((<<?[String], <<?[String], <<?[String], <<?[String], <<?[scala.math.BigDecimal], <<?[String]))
  }
  /** Table description of table effectlevels. Objects of this class serve as prototypes for rows in queries. */
  class Effectlevels(_tableTag: Tag) extends Table[EffectlevelsRow](_tableTag, "effectlevels") {
    def * = (substId, studyId, effectleveltype, effectlevelunit, effectlevelvalue, standardisedeffectlevel) <> (EffectlevelsRow.tupled, EffectlevelsRow.unapply)
    
    /** Database column subst_id DBType(varchar), Length(100,true), Default(None) */
    val substId: Column[Option[String]] = column[Option[String]]("subst_id", O.Length(100,varying=true), O.Default(None))
    /** Database column study_id DBType(varchar), Length(100,true), Default(None) */
    val studyId: Column[Option[String]] = column[Option[String]]("study_id", O.Length(100,varying=true), O.Default(None))
    /** Database column effectleveltype DBType(varchar), Length(1000,true), Default(None) */
    val effectleveltype: Column[Option[String]] = column[Option[String]]("effectleveltype", O.Length(1000,varying=true), O.Default(None))
    /** Database column effectlevelunit DBType(varchar), Length(1000,true), Default(None) */
    val effectlevelunit: Column[Option[String]] = column[Option[String]]("effectlevelunit", O.Length(1000,varying=true), O.Default(None))
    /** Database column effectlevelvalue DBType(numeric), Default(None) */
    val effectlevelvalue: Column[Option[scala.math.BigDecimal]] = column[Option[scala.math.BigDecimal]]("effectlevelvalue", O.Default(None))
    /** Database column standardisedeffectlevel DBType(varchar), Length(1000,true), Default(None) */
    val standardisedeffectlevel: Column[Option[String]] = column[Option[String]]("standardisedeffectlevel", O.Length(1000,varying=true), O.Default(None))
    
    /** Foreign key referencing Study (database name effectlevels_subst_id_fkey) */
    lazy val studyFk = foreignKey("effectlevels_subst_id_fkey", (substId, studyId), Study)(r => (r.substId, r.studyId), onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.Cascade)
  }
  /** Collection-like TableQuery object for table Effectlevels */
  lazy val Effectlevels = new TableQuery(tag => new Effectlevels(tag))
  
  /** Row type of table FindingsAll */
  type FindingsAllRow = HCons[Option[String],HCons[Option[String],HCons[Option[String],HCons[Option[String],HCons[Option[String],HCons[Option[String],HCons[Option[String],HCons[Option[String],HCons[Option[String],HCons[Option[String],HCons[Option[String],HCons[Option[scala.math.BigDecimal],HCons[Option[String],HCons[Option[String],HCons[Option[String],HCons[Option[scala.math.BigDecimal],HCons[Option[scala.math.BigDecimal],HCons[Option[String],HCons[Option[Double],HCons[Option[String],HCons[Option[String],HCons[Option[Int],HCons[Option[Int],HCons[Option[Double],HCons[Option[Double],HNil]]]]]]]]]]]]]]]]]]]]]]]]]
  /** Constructor for FindingsAllRow providing default values if available in the database schema. */
  def FindingsAllRow(substId: Option[String] = None, studyId: Option[String] = None, source: Option[String] = None, relevance: Option[String] = None, observationStandarised: Option[String] = None, observationVerbatim: Option[String] = None, observationNormalised: Option[String] = None, organStandarised: Option[String] = None, organVerbatim: Option[String] = None, organNormalised: Option[String] = None, change: Option[String] = None, dose: Option[scala.math.BigDecimal] = None, sex: Option[String] = None, standarisedSex: Option[String] = None, normalisedSex: Option[String] = None, averagefoldchange: Option[scala.math.BigDecimal] = None, averagevalue: Option[scala.math.BigDecimal] = None, unit: Option[String] = None, timepoint: Option[Double] = None, timepointunit: Option[String] = None, grade: Option[String] = None, numAnimalsTotal: Option[Int] = None, numAnimalsAffected: Option[Int] = None, timepointDays: Option[Double] = None, timepointRelative: Option[Double] = None): FindingsAllRow = {
    substId :: studyId :: source :: relevance :: observationStandarised :: observationVerbatim :: observationNormalised :: organStandarised :: organVerbatim :: organNormalised :: change :: dose :: sex :: standarisedSex :: normalisedSex :: averagefoldchange :: averagevalue :: unit :: timepoint :: timepointunit :: grade :: numAnimalsTotal :: numAnimalsAffected :: timepointDays :: timepointRelative :: HNil
  }
  /** GetResult implicit for fetching FindingsAllRow objects using plain SQL queries */
  implicit def GetResultFindingsAllRow(implicit e0: GR[Option[String]], e1: GR[Option[scala.math.BigDecimal]], e2: GR[Option[Double]], e3: GR[Option[Int]]): GR[FindingsAllRow] = GR{
    prs => import prs._
    <<?[String] :: <<?[String] :: <<?[String] :: <<?[String] :: <<?[String] :: <<?[String] :: <<?[String] :: <<?[String] :: <<?[String] :: <<?[String] :: <<?[String] :: <<?[scala.math.BigDecimal] :: <<?[String] :: <<?[String] :: <<?[String] :: <<?[scala.math.BigDecimal] :: <<?[scala.math.BigDecimal] :: <<?[String] :: <<?[Double] :: <<?[String] :: <<?[String] :: <<?[Int] :: <<?[Int] :: <<?[Double] :: <<?[Double] :: HNil
  }
  /** Table description of table findings_all. Objects of this class serve as prototypes for rows in queries. */
  class FindingsAll(_tableTag: Tag) extends Table[FindingsAllRow](_tableTag, "findings_all") {
    def * = substId :: studyId :: source :: relevance :: observationStandarised :: observationVerbatim :: observationNormalised :: organStandarised :: organVerbatim :: organNormalised :: change :: dose :: sex :: standarisedSex :: normalisedSex :: averagefoldchange :: averagevalue :: unit :: timepoint :: timepointunit :: grade :: numAnimalsTotal :: numAnimalsAffected :: timepointDays :: timepointRelative :: HNil
    
    /** Database column subst_id DBType(varchar), Length(100,true), Default(None) */
    val substId: Column[Option[String]] = column[Option[String]]("subst_id", O.Length(100,varying=true), O.Default(None))
    /** Database column study_id DBType(varchar), Length(100,true), Default(None) */
    val studyId: Column[Option[String]] = column[Option[String]]("study_id", O.Length(100,varying=true), O.Default(None))
    /** Database column source DBType(varchar), Length(50,true), Default(None) */
    val source: Column[Option[String]] = column[Option[String]]("source", O.Length(50,varying=true), O.Default(None))
    /** Database column relevance DBType(varchar), Length(100,true), Default(None) */
    val relevance: Column[Option[String]] = column[Option[String]]("relevance", O.Length(100,varying=true), O.Default(None))
    /** Database column observation_standarised DBType(varchar), Length(1000,true), Default(None) */
    val observationStandarised: Column[Option[String]] = column[Option[String]]("observation_standarised", O.Length(1000,varying=true), O.Default(None))
    /** Database column observation_verbatim DBType(varchar), Length(1000,true), Default(None) */
    val observationVerbatim: Column[Option[String]] = column[Option[String]]("observation_verbatim", O.Length(1000,varying=true), O.Default(None))
    /** Database column observation_normalised DBType(varchar), Length(1000,true), Default(None) */
    val observationNormalised: Column[Option[String]] = column[Option[String]]("observation_normalised", O.Length(1000,varying=true), O.Default(None))
    /** Database column organ_standarised DBType(varchar), Length(1000,true), Default(None) */
    val organStandarised: Column[Option[String]] = column[Option[String]]("organ_standarised", O.Length(1000,varying=true), O.Default(None))
    /** Database column organ_verbatim DBType(varchar), Length(1000,true), Default(None) */
    val organVerbatim: Column[Option[String]] = column[Option[String]]("organ_verbatim", O.Length(1000,varying=true), O.Default(None))
    /** Database column organ_normalised DBType(varchar), Length(1000,true), Default(None) */
    val organNormalised: Column[Option[String]] = column[Option[String]]("organ_normalised", O.Length(1000,varying=true), O.Default(None))
    /** Database column change DBType(varchar), Length(1000,true), Default(None) */
    val change: Column[Option[String]] = column[Option[String]]("change", O.Length(1000,varying=true), O.Default(None))
    /** Database column dose DBType(numeric), Default(None) */
    val dose: Column[Option[scala.math.BigDecimal]] = column[Option[scala.math.BigDecimal]]("dose", O.Default(None))
    /** Database column sex DBType(varchar), Length(1000,true), Default(None) */
    val sex: Column[Option[String]] = column[Option[String]]("sex", O.Length(1000,varying=true), O.Default(None))
    /** Database column standarised_sex DBType(varchar), Length(1000,true), Default(None) */
    val standarisedSex: Column[Option[String]] = column[Option[String]]("standarised_sex", O.Length(1000,varying=true), O.Default(None))
    /** Database column normalised_sex DBType(varchar), Length(1000,true), Default(None) */
    val normalisedSex: Column[Option[String]] = column[Option[String]]("normalised_sex", O.Length(1000,varying=true), O.Default(None))
    /** Database column averagefoldchange DBType(numeric), Default(None) */
    val averagefoldchange: Column[Option[scala.math.BigDecimal]] = column[Option[scala.math.BigDecimal]]("averagefoldchange", O.Default(None))
    /** Database column averagevalue DBType(numeric), Default(None) */
    val averagevalue: Column[Option[scala.math.BigDecimal]] = column[Option[scala.math.BigDecimal]]("averagevalue", O.Default(None))
    /** Database column unit DBType(varchar), Length(1000,true), Default(None) */
    val unit: Column[Option[String]] = column[Option[String]]("unit", O.Length(1000,varying=true), O.Default(None))
    /** Database column timepoint DBType(float8), Default(None) */
    val timepoint: Column[Option[Double]] = column[Option[Double]]("timepoint", O.Default(None))
    /** Database column timepointunit DBType(varchar), Length(1000,true), Default(None) */
    val timepointunit: Column[Option[String]] = column[Option[String]]("timepointunit", O.Length(1000,varying=true), O.Default(None))
    /** Database column grade DBType(varchar), Length(1000,true), Default(None) */
    val grade: Column[Option[String]] = column[Option[String]]("grade", O.Length(1000,varying=true), O.Default(None))
    /** Database column num_animals_total DBType(int4), Default(None) */
    val numAnimalsTotal: Column[Option[Int]] = column[Option[Int]]("num_animals_total", O.Default(None))
    /** Database column num_animals_affected DBType(int4), Default(None) */
    val numAnimalsAffected: Column[Option[Int]] = column[Option[Int]]("num_animals_affected", O.Default(None))
    /** Database column timepoint_days DBType(float8), Default(None) */
    val timepointDays: Column[Option[Double]] = column[Option[Double]]("timepoint_days", O.Default(None))
    /** Database column timepoint_relative DBType(float8), Default(None) */
    val timepointRelative: Column[Option[Double]] = column[Option[Double]]("timepoint_relative", O.Default(None))
    
    /** Foreign key referencing Study (database name findings_all_subst_id_fkey) */
    lazy val studyFk = foreignKey("findings_all_subst_id_fkey", substId :: studyId :: HNil, Study)(r => r.substId :: r.studyId :: HNil, onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.Cascade)
    
    /** Index over (observationStandarised) (database name observation) */
    val index1 = index("observation", observationStandarised :: HNil)
    /** Index over (source) (database name source) */
    val index2 = index("source", source :: HNil)
  }
  /** Collection-like TableQuery object for table FindingsAll */
  lazy val FindingsAll = new TableQuery(tag => new FindingsAll(tag))
  
  /** Entity class storing rows of table FindingsAllGrouped
   *  @param substId Database column subst_id DBType(varchar), Length(100,true), Default(None)
   *  @param source Database column source DBType(varchar), Length(50,true), Default(None)
   *  @param relevance Database column relevance DBType(varchar), Length(100,true), Default(None)
   *  @param observationNormalised Database column observation_normalised DBType(varchar), Length(1000,true), Default(None)
   *  @param organNormalised Database column organ_normalised DBType(varchar), Length(1000,true), Default(None)
   *  @param change Database column change DBType(varchar), Length(1000,true), Default(None)
   *  @param dose Database column dose DBType(numeric), Default(None)
   *  @param cnt Database column cnt DBType(int4), Default(None)
   *  @param flag Database column flag DBType(int4), Default(None) */
  case class FindingsAllGroupedRow(substId: Option[String] = None, source: Option[String] = None, relevance: Option[String] = None, observationNormalised: Option[String] = None, organNormalised: Option[String] = None, change: Option[String] = None, dose: Option[scala.math.BigDecimal] = None, cnt: Option[Int] = None, flag: Option[Int] = None)
  /** GetResult implicit for fetching FindingsAllGroupedRow objects using plain SQL queries */
  implicit def GetResultFindingsAllGroupedRow(implicit e0: GR[Option[String]], e1: GR[Option[scala.math.BigDecimal]], e2: GR[Option[Int]]): GR[FindingsAllGroupedRow] = GR{
    prs => import prs._
    FindingsAllGroupedRow.tupled((<<?[String], <<?[String], <<?[String], <<?[String], <<?[String], <<?[String], <<?[scala.math.BigDecimal], <<?[Int], <<?[Int]))
  }
  /** Table description of table findings_all_grouped. Objects of this class serve as prototypes for rows in queries. */
  class FindingsAllGrouped(_tableTag: Tag) extends Table[FindingsAllGroupedRow](_tableTag, "findings_all_grouped") {
    def * = (substId, source, relevance, observationNormalised, organNormalised, change, dose, cnt, flag) <> (FindingsAllGroupedRow.tupled, FindingsAllGroupedRow.unapply)
    
    /** Database column subst_id DBType(varchar), Length(100,true), Default(None) */
    val substId: Column[Option[String]] = column[Option[String]]("subst_id", O.Length(100,varying=true), O.Default(None))
    /** Database column source DBType(varchar), Length(50,true), Default(None) */
    val source: Column[Option[String]] = column[Option[String]]("source", O.Length(50,varying=true), O.Default(None))
    /** Database column relevance DBType(varchar), Length(100,true), Default(None) */
    val relevance: Column[Option[String]] = column[Option[String]]("relevance", O.Length(100,varying=true), O.Default(None))
    /** Database column observation_normalised DBType(varchar), Length(1000,true), Default(None) */
    val observationNormalised: Column[Option[String]] = column[Option[String]]("observation_normalised", O.Length(1000,varying=true), O.Default(None))
    /** Database column organ_normalised DBType(varchar), Length(1000,true), Default(None) */
    val organNormalised: Column[Option[String]] = column[Option[String]]("organ_normalised", O.Length(1000,varying=true), O.Default(None))
    /** Database column change DBType(varchar), Length(1000,true), Default(None) */
    val change: Column[Option[String]] = column[Option[String]]("change", O.Length(1000,varying=true), O.Default(None))
    /** Database column dose DBType(numeric), Default(None) */
    val dose: Column[Option[scala.math.BigDecimal]] = column[Option[scala.math.BigDecimal]]("dose", O.Default(None))
    /** Database column cnt DBType(int4), Default(None) */
    val cnt: Column[Option[Int]] = column[Option[Int]]("cnt", O.Default(None))
    /** Database column flag DBType(int4), Default(None) */
    val flag: Column[Option[Int]] = column[Option[Int]]("flag", O.Default(None))
  }
  /** Collection-like TableQuery object for table FindingsAllGrouped */
  lazy val FindingsAllGrouped = new TableQuery(tag => new FindingsAllGrouped(tag))
  
  /** Row type of table FindingsAllLevels */
  type FindingsAllLevelsRow = HCons[Option[String],HCons[Option[String],HCons[Option[String],HCons[Option[String],HCons[Option[String],HCons[Option[String],HCons[Option[String],HCons[Option[String],HCons[Option[String],HCons[Option[String],HCons[Option[String],HCons[Option[scala.math.BigDecimal],HCons[Option[String],HCons[Option[String],HCons[Option[String],HCons[Option[scala.math.BigDecimal],HCons[Option[scala.math.BigDecimal],HCons[Option[String],HCons[Option[Double],HCons[Option[String],HCons[Option[String],HCons[Option[Int],HCons[Option[Int],HCons[Option[Double],HCons[Option[Double],HCons[Option[String],HCons[Option[String],HCons[Option[String],HCons[Option[String],HCons[Option[String],HCons[Option[String],HCons[Option[String],HCons[Option[String],HCons[Option[String],HCons[Option[String],HNil]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]
  /** Constructor for FindingsAllLevelsRow providing default values if available in the database schema. */
  def FindingsAllLevelsRow(substId: Option[String] = None, studyId: Option[String] = None, source: Option[String] = None, relevance: Option[String] = None, observationStandarised: Option[String] = None, observationVerbatim: Option[String] = None, observationNormalised: Option[String] = None, organStandarised: Option[String] = None, organVerbatim: Option[String] = None, organNormalised: Option[String] = None, change: Option[String] = None, dose: Option[scala.math.BigDecimal] = None, sex: Option[String] = None, standarisedSex: Option[String] = None, normalisedSex: Option[String] = None, averagefoldchange: Option[scala.math.BigDecimal] = None, averagevalue: Option[scala.math.BigDecimal] = None, unit: Option[String] = None, timepoint: Option[Double] = None, timepointunit: Option[String] = None, grade: Option[String] = None, numAnimalsTotal: Option[Int] = None, numAnimalsAffected: Option[Int] = None, timepointDays: Option[Double] = None, timepointRelative: Option[Double] = None, level1: Option[String] = None, level2: Option[String] = None, level3: Option[String] = None, level4: Option[String] = None, level5: Option[String] = None, level6: Option[String] = None, level7: Option[String] = None, level8: Option[String] = None, childTerm: Option[String] = None, pathTerms: Option[String] = None): FindingsAllLevelsRow = {
    substId :: studyId :: source :: relevance :: observationStandarised :: observationVerbatim :: observationNormalised :: organStandarised :: organVerbatim :: organNormalised :: change :: dose :: sex :: standarisedSex :: normalisedSex :: averagefoldchange :: averagevalue :: unit :: timepoint :: timepointunit :: grade :: numAnimalsTotal :: numAnimalsAffected :: timepointDays :: timepointRelative :: level1 :: level2 :: level3 :: level4 :: level5 :: level6 :: level7 :: level8 :: childTerm :: pathTerms :: HNil
  }
  /** GetResult implicit for fetching FindingsAllLevelsRow objects using plain SQL queries */
  implicit def GetResultFindingsAllLevelsRow(implicit e0: GR[Option[String]], e1: GR[Option[scala.math.BigDecimal]], e2: GR[Option[Double]], e3: GR[Option[Int]]): GR[FindingsAllLevelsRow] = GR{
    prs => import prs._
    <<?[String] :: <<?[String] :: <<?[String] :: <<?[String] :: <<?[String] :: <<?[String] :: <<?[String] :: <<?[String] :: <<?[String] :: <<?[String] :: <<?[String] :: <<?[scala.math.BigDecimal] :: <<?[String] :: <<?[String] :: <<?[String] :: <<?[scala.math.BigDecimal] :: <<?[scala.math.BigDecimal] :: <<?[String] :: <<?[Double] :: <<?[String] :: <<?[String] :: <<?[Int] :: <<?[Int] :: <<?[Double] :: <<?[Double] :: <<?[String] :: <<?[String] :: <<?[String] :: <<?[String] :: <<?[String] :: <<?[String] :: <<?[String] :: <<?[String] :: <<?[String] :: <<?[String] :: HNil
  }
  /** Table description of table findings_all_levels. Objects of this class serve as prototypes for rows in queries. */
  class FindingsAllLevels(_tableTag: Tag) extends Table[FindingsAllLevelsRow](_tableTag, "findings_all_levels") {
    def * = substId :: studyId :: source :: relevance :: observationStandarised :: observationVerbatim :: observationNormalised :: organStandarised :: organVerbatim :: organNormalised :: change :: dose :: sex :: standarisedSex :: normalisedSex :: averagefoldchange :: averagevalue :: unit :: timepoint :: timepointunit :: grade :: numAnimalsTotal :: numAnimalsAffected :: timepointDays :: timepointRelative :: level1 :: level2 :: level3 :: level4 :: level5 :: level6 :: level7 :: level8 :: childTerm :: pathTerms :: HNil
    
    /** Database column subst_id DBType(varchar), Length(100,true), Default(None) */
    val substId: Column[Option[String]] = column[Option[String]]("subst_id", O.Length(100,varying=true), O.Default(None))
    /** Database column study_id DBType(varchar), Length(100,true), Default(None) */
    val studyId: Column[Option[String]] = column[Option[String]]("study_id", O.Length(100,varying=true), O.Default(None))
    /** Database column source DBType(varchar), Length(50,true), Default(None) */
    val source: Column[Option[String]] = column[Option[String]]("source", O.Length(50,varying=true), O.Default(None))
    /** Database column relevance DBType(varchar), Length(100,true), Default(None) */
    val relevance: Column[Option[String]] = column[Option[String]]("relevance", O.Length(100,varying=true), O.Default(None))
    /** Database column observation_standarised DBType(varchar), Length(1000,true), Default(None) */
    val observationStandarised: Column[Option[String]] = column[Option[String]]("observation_standarised", O.Length(1000,varying=true), O.Default(None))
    /** Database column observation_verbatim DBType(varchar), Length(1000,true), Default(None) */
    val observationVerbatim: Column[Option[String]] = column[Option[String]]("observation_verbatim", O.Length(1000,varying=true), O.Default(None))
    /** Database column observation_normalised DBType(varchar), Length(1000,true), Default(None) */
    val observationNormalised: Column[Option[String]] = column[Option[String]]("observation_normalised", O.Length(1000,varying=true), O.Default(None))
    /** Database column organ_standarised DBType(varchar), Length(1000,true), Default(None) */
    val organStandarised: Column[Option[String]] = column[Option[String]]("organ_standarised", O.Length(1000,varying=true), O.Default(None))
    /** Database column organ_verbatim DBType(varchar), Length(1000,true), Default(None) */
    val organVerbatim: Column[Option[String]] = column[Option[String]]("organ_verbatim", O.Length(1000,varying=true), O.Default(None))
    /** Database column organ_normalised DBType(varchar), Length(1000,true), Default(None) */
    val organNormalised: Column[Option[String]] = column[Option[String]]("organ_normalised", O.Length(1000,varying=true), O.Default(None))
    /** Database column change DBType(varchar), Length(1000,true), Default(None) */
    val change: Column[Option[String]] = column[Option[String]]("change", O.Length(1000,varying=true), O.Default(None))
    /** Database column dose DBType(numeric), Default(None) */
    val dose: Column[Option[scala.math.BigDecimal]] = column[Option[scala.math.BigDecimal]]("dose", O.Default(None))
    /** Database column sex DBType(varchar), Length(1000,true), Default(None) */
    val sex: Column[Option[String]] = column[Option[String]]("sex", O.Length(1000,varying=true), O.Default(None))
    /** Database column standarised_sex DBType(varchar), Length(1000,true), Default(None) */
    val standarisedSex: Column[Option[String]] = column[Option[String]]("standarised_sex", O.Length(1000,varying=true), O.Default(None))
    /** Database column normalised_sex DBType(varchar), Length(1000,true), Default(None) */
    val normalisedSex: Column[Option[String]] = column[Option[String]]("normalised_sex", O.Length(1000,varying=true), O.Default(None))
    /** Database column averagefoldchange DBType(numeric), Default(None) */
    val averagefoldchange: Column[Option[scala.math.BigDecimal]] = column[Option[scala.math.BigDecimal]]("averagefoldchange", O.Default(None))
    /** Database column averagevalue DBType(numeric), Default(None) */
    val averagevalue: Column[Option[scala.math.BigDecimal]] = column[Option[scala.math.BigDecimal]]("averagevalue", O.Default(None))
    /** Database column unit DBType(varchar), Length(1000,true), Default(None) */
    val unit: Column[Option[String]] = column[Option[String]]("unit", O.Length(1000,varying=true), O.Default(None))
    /** Database column timepoint DBType(float8), Default(None) */
    val timepoint: Column[Option[Double]] = column[Option[Double]]("timepoint", O.Default(None))
    /** Database column timepointunit DBType(varchar), Length(1000,true), Default(None) */
    val timepointunit: Column[Option[String]] = column[Option[String]]("timepointunit", O.Length(1000,varying=true), O.Default(None))
    /** Database column grade DBType(varchar), Length(1000,true), Default(None) */
    val grade: Column[Option[String]] = column[Option[String]]("grade", O.Length(1000,varying=true), O.Default(None))
    /** Database column num_animals_total DBType(int4), Default(None) */
    val numAnimalsTotal: Column[Option[Int]] = column[Option[Int]]("num_animals_total", O.Default(None))
    /** Database column num_animals_affected DBType(int4), Default(None) */
    val numAnimalsAffected: Column[Option[Int]] = column[Option[Int]]("num_animals_affected", O.Default(None))
    /** Database column timepoint_days DBType(float8), Default(None) */
    val timepointDays: Column[Option[Double]] = column[Option[Double]]("timepoint_days", O.Default(None))
    /** Database column timepoint_relative DBType(float8), Default(None) */
    val timepointRelative: Column[Option[Double]] = column[Option[Double]]("timepoint_relative", O.Default(None))
    /** Database column level1 DBType(text), Length(2147483647,true), Default(None) */
    val level1: Column[Option[String]] = column[Option[String]]("level1", O.Length(2147483647,varying=true), O.Default(None))
    /** Database column level2 DBType(text), Length(2147483647,true), Default(None) */
    val level2: Column[Option[String]] = column[Option[String]]("level2", O.Length(2147483647,varying=true), O.Default(None))
    /** Database column level3 DBType(text), Length(2147483647,true), Default(None) */
    val level3: Column[Option[String]] = column[Option[String]]("level3", O.Length(2147483647,varying=true), O.Default(None))
    /** Database column level4 DBType(text), Length(2147483647,true), Default(None) */
    val level4: Column[Option[String]] = column[Option[String]]("level4", O.Length(2147483647,varying=true), O.Default(None))
    /** Database column level5 DBType(text), Length(2147483647,true), Default(None) */
    val level5: Column[Option[String]] = column[Option[String]]("level5", O.Length(2147483647,varying=true), O.Default(None))
    /** Database column level6 DBType(text), Length(2147483647,true), Default(None) */
    val level6: Column[Option[String]] = column[Option[String]]("level6", O.Length(2147483647,varying=true), O.Default(None))
    /** Database column level7 DBType(text), Length(2147483647,true), Default(None) */
    val level7: Column[Option[String]] = column[Option[String]]("level7", O.Length(2147483647,varying=true), O.Default(None))
    /** Database column level8 DBType(text), Length(2147483647,true), Default(None) */
    val level8: Column[Option[String]] = column[Option[String]]("level8", O.Length(2147483647,varying=true), O.Default(None))
    /** Database column child_term DBType(text), Length(2147483647,true), Default(None) */
    val childTerm: Column[Option[String]] = column[Option[String]]("child_term", O.Length(2147483647,varying=true), O.Default(None))
    /** Database column path_terms DBType(_text), Length(2147483647,false), Default(None) */
    val pathTerms: Column[Option[String]] = column[Option[String]]("path_terms", O.Length(2147483647,varying=false), O.Default(None))
  }
  /** Collection-like TableQuery object for table FindingsAllLevels */
  lazy val FindingsAllLevels = new TableQuery(tag => new FindingsAllLevels(tag))
  
  /** Entity class storing rows of table FindingsAllLevelsGrouped
   *  @param substId Database column subst_id DBType(varchar), Length(100,true), Default(None)
   *  @param source Database column source DBType(varchar), Length(50,true), Default(None)
   *  @param relevance Database column relevance DBType(varchar), Length(100,true), Default(None)
   *  @param observationNormalised Database column observation_normalised DBType(varchar), Length(1000,true), Default(None)
   *  @param organNormalised Database column organ_normalised DBType(varchar), Length(1000,true), Default(None)
   *  @param change Database column change DBType(varchar), Length(1000,true), Default(None)
   *  @param dose Database column dose DBType(numeric), Default(None)
   *  @param cnt Database column cnt DBType(int4), Default(None)
   *  @param flag Database column flag DBType(int4), Default(None)
   *  @param level1 Database column level1 DBType(text), Length(2147483647,true), Default(None)
   *  @param level2 Database column level2 DBType(text), Length(2147483647,true), Default(None)
   *  @param level3 Database column level3 DBType(text), Length(2147483647,true), Default(None)
   *  @param level4 Database column level4 DBType(text), Length(2147483647,true), Default(None)
   *  @param level5 Database column level5 DBType(text), Length(2147483647,true), Default(None)
   *  @param level6 Database column level6 DBType(text), Length(2147483647,true), Default(None)
   *  @param level7 Database column level7 DBType(text), Length(2147483647,true), Default(None)
   *  @param level8 Database column level8 DBType(text), Length(2147483647,true), Default(None)
   *  @param childTerm Database column child_term DBType(text), Length(2147483647,true), Default(None)
   *  @param pathTerms Database column path_terms DBType(_text), Length(2147483647,false), Default(None) */
  case class FindingsAllLevelsGroupedRow(substId: Option[String] = None, source: Option[String] = None, relevance: Option[String] = None, observationNormalised: Option[String] = None, organNormalised: Option[String] = None, change: Option[String] = None, dose: Option[scala.math.BigDecimal] = None, cnt: Option[Int] = None, flag: Option[Int] = None, level1: Option[String] = None, level2: Option[String] = None, level3: Option[String] = None, level4: Option[String] = None, level5: Option[String] = None, level6: Option[String] = None, level7: Option[String] = None, level8: Option[String] = None, childTerm: Option[String] = None, pathTerms: Option[String] = None)
  /** GetResult implicit for fetching FindingsAllLevelsGroupedRow objects using plain SQL queries */
  implicit def GetResultFindingsAllLevelsGroupedRow(implicit e0: GR[Option[String]], e1: GR[Option[scala.math.BigDecimal]], e2: GR[Option[Int]]): GR[FindingsAllLevelsGroupedRow] = GR{
    prs => import prs._
    FindingsAllLevelsGroupedRow.tupled((<<?[String], <<?[String], <<?[String], <<?[String], <<?[String], <<?[String], <<?[scala.math.BigDecimal], <<?[Int], <<?[Int], <<?[String], <<?[String], <<?[String], <<?[String], <<?[String], <<?[String], <<?[String], <<?[String], <<?[String], <<?[String]))
  }
  /** Table description of table findings_all_levels_grouped. Objects of this class serve as prototypes for rows in queries. */
  class FindingsAllLevelsGrouped(_tableTag: Tag) extends Table[FindingsAllLevelsGroupedRow](_tableTag, "findings_all_levels_grouped") {
    def * = (substId, source, relevance, observationNormalised, organNormalised, change, dose, cnt, flag, level1, level2, level3, level4, level5, level6, level7, level8, childTerm, pathTerms) <> (FindingsAllLevelsGroupedRow.tupled, FindingsAllLevelsGroupedRow.unapply)
    
    /** Database column subst_id DBType(varchar), Length(100,true), Default(None) */
    val substId: Column[Option[String]] = column[Option[String]]("subst_id", O.Length(100,varying=true), O.Default(None))
    /** Database column source DBType(varchar), Length(50,true), Default(None) */
    val source: Column[Option[String]] = column[Option[String]]("source", O.Length(50,varying=true), O.Default(None))
    /** Database column relevance DBType(varchar), Length(100,true), Default(None) */
    val relevance: Column[Option[String]] = column[Option[String]]("relevance", O.Length(100,varying=true), O.Default(None))
    /** Database column observation_normalised DBType(varchar), Length(1000,true), Default(None) */
    val observationNormalised: Column[Option[String]] = column[Option[String]]("observation_normalised", O.Length(1000,varying=true), O.Default(None))
    /** Database column organ_normalised DBType(varchar), Length(1000,true), Default(None) */
    val organNormalised: Column[Option[String]] = column[Option[String]]("organ_normalised", O.Length(1000,varying=true), O.Default(None))
    /** Database column change DBType(varchar), Length(1000,true), Default(None) */
    val change: Column[Option[String]] = column[Option[String]]("change", O.Length(1000,varying=true), O.Default(None))
    /** Database column dose DBType(numeric), Default(None) */
    val dose: Column[Option[scala.math.BigDecimal]] = column[Option[scala.math.BigDecimal]]("dose", O.Default(None))
    /** Database column cnt DBType(int4), Default(None) */
    val cnt: Column[Option[Int]] = column[Option[Int]]("cnt", O.Default(None))
    /** Database column flag DBType(int4), Default(None) */
    val flag: Column[Option[Int]] = column[Option[Int]]("flag", O.Default(None))
    /** Database column level1 DBType(text), Length(2147483647,true), Default(None) */
    val level1: Column[Option[String]] = column[Option[String]]("level1", O.Length(2147483647,varying=true), O.Default(None))
    /** Database column level2 DBType(text), Length(2147483647,true), Default(None) */
    val level2: Column[Option[String]] = column[Option[String]]("level2", O.Length(2147483647,varying=true), O.Default(None))
    /** Database column level3 DBType(text), Length(2147483647,true), Default(None) */
    val level3: Column[Option[String]] = column[Option[String]]("level3", O.Length(2147483647,varying=true), O.Default(None))
    /** Database column level4 DBType(text), Length(2147483647,true), Default(None) */
    val level4: Column[Option[String]] = column[Option[String]]("level4", O.Length(2147483647,varying=true), O.Default(None))
    /** Database column level5 DBType(text), Length(2147483647,true), Default(None) */
    val level5: Column[Option[String]] = column[Option[String]]("level5", O.Length(2147483647,varying=true), O.Default(None))
    /** Database column level6 DBType(text), Length(2147483647,true), Default(None) */
    val level6: Column[Option[String]] = column[Option[String]]("level6", O.Length(2147483647,varying=true), O.Default(None))
    /** Database column level7 DBType(text), Length(2147483647,true), Default(None) */
    val level7: Column[Option[String]] = column[Option[String]]("level7", O.Length(2147483647,varying=true), O.Default(None))
    /** Database column level8 DBType(text), Length(2147483647,true), Default(None) */
    val level8: Column[Option[String]] = column[Option[String]]("level8", O.Length(2147483647,varying=true), O.Default(None))
    /** Database column child_term DBType(text), Length(2147483647,true), Default(None) */
    val childTerm: Column[Option[String]] = column[Option[String]]("child_term", O.Length(2147483647,varying=true), O.Default(None))
    /** Database column path_terms DBType(_text), Length(2147483647,false), Default(None) */
    val pathTerms: Column[Option[String]] = column[Option[String]]("path_terms", O.Length(2147483647,varying=false), O.Default(None))
  }
  /** Collection-like TableQuery object for table FindingsAllLevelsGrouped */
  lazy val FindingsAllLevelsGrouped = new TableQuery(tag => new FindingsAllLevelsGrouped(tag))
  
  /** Row type of table FindingsAllQ */
  type FindingsAllQRow = HCons[Option[Int],HCons[Option[String],HCons[Option[String],HCons[Option[String],HCons[Option[String],HCons[Option[String],HCons[Option[String],HCons[Option[String],HCons[Option[String],HCons[Option[String],HCons[Option[String],HCons[Option[String],HCons[Option[String],HCons[Option[scala.math.BigDecimal],HCons[Option[String],HCons[Option[String],HCons[Option[String],HCons[Option[scala.math.BigDecimal],HCons[Option[scala.math.BigDecimal],HCons[Option[String],HCons[Option[Double],HCons[Option[String],HCons[Option[String],HCons[Option[Int],HCons[Option[Int],HCons[Option[Double],HCons[Option[Double],HNil]]]]]]]]]]]]]]]]]]]]]]]]]]]
  /** Constructor for FindingsAllQRow providing default values if available in the database schema. */
  def FindingsAllQRow(int4: Option[Int] = None, termTop: Option[String] = None, substId: Option[String] = None, studyId: Option[String] = None, source: Option[String] = None, relevance: Option[String] = None, observationStandarised: Option[String] = None, observationVerbatim: Option[String] = None, observationNormalised: Option[String] = None, organStandarised: Option[String] = None, organVerbatim: Option[String] = None, organNormalised: Option[String] = None, change: Option[String] = None, dose: Option[scala.math.BigDecimal] = None, sex: Option[String] = None, standarisedSex: Option[String] = None, normalisedSex: Option[String] = None, averagefoldchange: Option[scala.math.BigDecimal] = None, averagevalue: Option[scala.math.BigDecimal] = None, unit: Option[String] = None, timepoint: Option[Double] = None, timepointunit: Option[String] = None, grade: Option[String] = None, numAnimalsTotal: Option[Int] = None, numAnimalsAffected: Option[Int] = None, timepointDays: Option[Double] = None, timepointRelative: Option[Double] = None): FindingsAllQRow = {
    int4 :: termTop :: substId :: studyId :: source :: relevance :: observationStandarised :: observationVerbatim :: observationNormalised :: organStandarised :: organVerbatim :: organNormalised :: change :: dose :: sex :: standarisedSex :: normalisedSex :: averagefoldchange :: averagevalue :: unit :: timepoint :: timepointunit :: grade :: numAnimalsTotal :: numAnimalsAffected :: timepointDays :: timepointRelative :: HNil
  }
  /** GetResult implicit for fetching FindingsAllQRow objects using plain SQL queries */
  implicit def GetResultFindingsAllQRow(implicit e0: GR[Option[Int]], e1: GR[Option[String]], e2: GR[Option[scala.math.BigDecimal]], e3: GR[Option[Double]]): GR[FindingsAllQRow] = GR{
    prs => import prs._
    <<?[Int] :: <<?[String] :: <<?[String] :: <<?[String] :: <<?[String] :: <<?[String] :: <<?[String] :: <<?[String] :: <<?[String] :: <<?[String] :: <<?[String] :: <<?[String] :: <<?[String] :: <<?[scala.math.BigDecimal] :: <<?[String] :: <<?[String] :: <<?[String] :: <<?[scala.math.BigDecimal] :: <<?[scala.math.BigDecimal] :: <<?[String] :: <<?[Double] :: <<?[String] :: <<?[String] :: <<?[Int] :: <<?[Int] :: <<?[Double] :: <<?[Double] :: HNil
  }
  /** Table description of table findings_all_q. Objects of this class serve as prototypes for rows in queries. */
  class FindingsAllQ(_tableTag: Tag) extends Table[FindingsAllQRow](_tableTag, "findings_all_q") {
    def * = int4 :: termTop :: substId :: studyId :: source :: relevance :: observationStandarised :: observationVerbatim :: observationNormalised :: organStandarised :: organVerbatim :: organNormalised :: change :: dose :: sex :: standarisedSex :: normalisedSex :: averagefoldchange :: averagevalue :: unit :: timepoint :: timepointunit :: grade :: numAnimalsTotal :: numAnimalsAffected :: timepointDays :: timepointRelative :: HNil
    
    /** Database column int4 DBType(int4), Default(None) */
    val int4: Column[Option[Int]] = column[Option[Int]]("int4", O.Default(None))
    /** Database column term_top DBType(text), Length(2147483647,true), Default(None) */
    val termTop: Column[Option[String]] = column[Option[String]]("term_top", O.Length(2147483647,varying=true), O.Default(None))
    /** Database column subst_id DBType(varchar), Length(100,true), Default(None) */
    val substId: Column[Option[String]] = column[Option[String]]("subst_id", O.Length(100,varying=true), O.Default(None))
    /** Database column study_id DBType(varchar), Length(100,true), Default(None) */
    val studyId: Column[Option[String]] = column[Option[String]]("study_id", O.Length(100,varying=true), O.Default(None))
    /** Database column source DBType(varchar), Length(50,true), Default(None) */
    val source: Column[Option[String]] = column[Option[String]]("source", O.Length(50,varying=true), O.Default(None))
    /** Database column relevance DBType(varchar), Length(100,true), Default(None) */
    val relevance: Column[Option[String]] = column[Option[String]]("relevance", O.Length(100,varying=true), O.Default(None))
    /** Database column observation_standarised DBType(varchar), Length(1000,true), Default(None) */
    val observationStandarised: Column[Option[String]] = column[Option[String]]("observation_standarised", O.Length(1000,varying=true), O.Default(None))
    /** Database column observation_verbatim DBType(varchar), Length(1000,true), Default(None) */
    val observationVerbatim: Column[Option[String]] = column[Option[String]]("observation_verbatim", O.Length(1000,varying=true), O.Default(None))
    /** Database column observation_normalised DBType(varchar), Length(1000,true), Default(None) */
    val observationNormalised: Column[Option[String]] = column[Option[String]]("observation_normalised", O.Length(1000,varying=true), O.Default(None))
    /** Database column organ_standarised DBType(varchar), Length(1000,true), Default(None) */
    val organStandarised: Column[Option[String]] = column[Option[String]]("organ_standarised", O.Length(1000,varying=true), O.Default(None))
    /** Database column organ_verbatim DBType(varchar), Length(1000,true), Default(None) */
    val organVerbatim: Column[Option[String]] = column[Option[String]]("organ_verbatim", O.Length(1000,varying=true), O.Default(None))
    /** Database column organ_normalised DBType(varchar), Length(1000,true), Default(None) */
    val organNormalised: Column[Option[String]] = column[Option[String]]("organ_normalised", O.Length(1000,varying=true), O.Default(None))
    /** Database column change DBType(varchar), Length(1000,true), Default(None) */
    val change: Column[Option[String]] = column[Option[String]]("change", O.Length(1000,varying=true), O.Default(None))
    /** Database column dose DBType(numeric), Default(None) */
    val dose: Column[Option[scala.math.BigDecimal]] = column[Option[scala.math.BigDecimal]]("dose", O.Default(None))
    /** Database column sex DBType(varchar), Length(1000,true), Default(None) */
    val sex: Column[Option[String]] = column[Option[String]]("sex", O.Length(1000,varying=true), O.Default(None))
    /** Database column standarised_sex DBType(varchar), Length(1000,true), Default(None) */
    val standarisedSex: Column[Option[String]] = column[Option[String]]("standarised_sex", O.Length(1000,varying=true), O.Default(None))
    /** Database column normalised_sex DBType(varchar), Length(1000,true), Default(None) */
    val normalisedSex: Column[Option[String]] = column[Option[String]]("normalised_sex", O.Length(1000,varying=true), O.Default(None))
    /** Database column averagefoldchange DBType(numeric), Default(None) */
    val averagefoldchange: Column[Option[scala.math.BigDecimal]] = column[Option[scala.math.BigDecimal]]("averagefoldchange", O.Default(None))
    /** Database column averagevalue DBType(numeric), Default(None) */
    val averagevalue: Column[Option[scala.math.BigDecimal]] = column[Option[scala.math.BigDecimal]]("averagevalue", O.Default(None))
    /** Database column unit DBType(varchar), Length(1000,true), Default(None) */
    val unit: Column[Option[String]] = column[Option[String]]("unit", O.Length(1000,varying=true), O.Default(None))
    /** Database column timepoint DBType(float8), Default(None) */
    val timepoint: Column[Option[Double]] = column[Option[Double]]("timepoint", O.Default(None))
    /** Database column timepointunit DBType(varchar), Length(1000,true), Default(None) */
    val timepointunit: Column[Option[String]] = column[Option[String]]("timepointunit", O.Length(1000,varying=true), O.Default(None))
    /** Database column grade DBType(varchar), Length(1000,true), Default(None) */
    val grade: Column[Option[String]] = column[Option[String]]("grade", O.Length(1000,varying=true), O.Default(None))
    /** Database column num_animals_total DBType(int4), Default(None) */
    val numAnimalsTotal: Column[Option[Int]] = column[Option[Int]]("num_animals_total", O.Default(None))
    /** Database column num_animals_affected DBType(int4), Default(None) */
    val numAnimalsAffected: Column[Option[Int]] = column[Option[Int]]("num_animals_affected", O.Default(None))
    /** Database column timepoint_days DBType(float8), Default(None) */
    val timepointDays: Column[Option[Double]] = column[Option[Double]]("timepoint_days", O.Default(None))
    /** Database column timepoint_relative DBType(float8), Default(None) */
    val timepointRelative: Column[Option[Double]] = column[Option[Double]]("timepoint_relative", O.Default(None))
  }
  /** Collection-like TableQuery object for table FindingsAllQ */
  lazy val FindingsAllQ = new TableQuery(tag => new FindingsAllQ(tag))
  
  /** Entity class storing rows of table Generaltoxiceffects
   *  @param substId Database column subst_id DBType(varchar), Length(100,true), Default(None)
   *  @param studyId Database column study_id DBType(varchar), Length(100,true), Default(None)
   *  @param bodyweigth Database column bodyweigth DBType(numeric), Default(None)
   *  @param bodyweightrelevance Database column bodyweightrelevance DBType(varchar), Length(100,true), Default(None)
   *  @param bodyweightunit Database column bodyweightunit DBType(varchar), Length(100,true), Default(None)
   *  @param dosemgkg Database column dosemgkg DBType(numeric), Default(None)
   *  @param mortality Database column mortality DBType(numeric), Default(None)
   *  @param sd Database column sd DBType(numeric), Default(None)
   *  @param sex Database column sex DBType(varchar), Length(100,true), Default(None)
   *  @param standardisedbodyweight Database column standardisedbodyweight DBType(numeric), Default(None)
   *  @param standardisedsex Database column standardisedsex DBType(varchar), Length(100,true), Default(None)
   *  @param normalisedSex Database column normalised_sex DBType(varchar), Length(100,true), Default(None)
   *  @param timepoint Database column timepoint DBType(numeric), Default(None)
   *  @param timepointunit Database column timepointunit DBType(varchar), Length(100,true), Default(None) */
  case class GeneraltoxiceffectsRow(substId: Option[String] = None, studyId: Option[String] = None, bodyweigth: Option[scala.math.BigDecimal] = None, bodyweightrelevance: Option[String] = None, bodyweightunit: Option[String] = None, dosemgkg: Option[scala.math.BigDecimal] = None, mortality: Option[scala.math.BigDecimal] = None, sd: Option[scala.math.BigDecimal] = None, sex: Option[String] = None, standardisedbodyweight: Option[scala.math.BigDecimal] = None, standardisedsex: Option[String] = None, normalisedSex: Option[String] = None, timepoint: Option[scala.math.BigDecimal] = None, timepointunit: Option[String] = None)
  /** GetResult implicit for fetching GeneraltoxiceffectsRow objects using plain SQL queries */
  implicit def GetResultGeneraltoxiceffectsRow(implicit e0: GR[Option[String]], e1: GR[Option[scala.math.BigDecimal]]): GR[GeneraltoxiceffectsRow] = GR{
    prs => import prs._
    GeneraltoxiceffectsRow.tupled((<<?[String], <<?[String], <<?[scala.math.BigDecimal], <<?[String], <<?[String], <<?[scala.math.BigDecimal], <<?[scala.math.BigDecimal], <<?[scala.math.BigDecimal], <<?[String], <<?[scala.math.BigDecimal], <<?[String], <<?[String], <<?[scala.math.BigDecimal], <<?[String]))
  }
  /** Table description of table generaltoxiceffects. Objects of this class serve as prototypes for rows in queries. */
  class Generaltoxiceffects(_tableTag: Tag) extends Table[GeneraltoxiceffectsRow](_tableTag, "generaltoxiceffects") {
    def * = (substId, studyId, bodyweigth, bodyweightrelevance, bodyweightunit, dosemgkg, mortality, sd, sex, standardisedbodyweight, standardisedsex, normalisedSex, timepoint, timepointunit) <> (GeneraltoxiceffectsRow.tupled, GeneraltoxiceffectsRow.unapply)
    
    /** Database column subst_id DBType(varchar), Length(100,true), Default(None) */
    val substId: Column[Option[String]] = column[Option[String]]("subst_id", O.Length(100,varying=true), O.Default(None))
    /** Database column study_id DBType(varchar), Length(100,true), Default(None) */
    val studyId: Column[Option[String]] = column[Option[String]]("study_id", O.Length(100,varying=true), O.Default(None))
    /** Database column bodyweigth DBType(numeric), Default(None) */
    val bodyweigth: Column[Option[scala.math.BigDecimal]] = column[Option[scala.math.BigDecimal]]("bodyweigth", O.Default(None))
    /** Database column bodyweightrelevance DBType(varchar), Length(100,true), Default(None) */
    val bodyweightrelevance: Column[Option[String]] = column[Option[String]]("bodyweightrelevance", O.Length(100,varying=true), O.Default(None))
    /** Database column bodyweightunit DBType(varchar), Length(100,true), Default(None) */
    val bodyweightunit: Column[Option[String]] = column[Option[String]]("bodyweightunit", O.Length(100,varying=true), O.Default(None))
    /** Database column dosemgkg DBType(numeric), Default(None) */
    val dosemgkg: Column[Option[scala.math.BigDecimal]] = column[Option[scala.math.BigDecimal]]("dosemgkg", O.Default(None))
    /** Database column mortality DBType(numeric), Default(None) */
    val mortality: Column[Option[scala.math.BigDecimal]] = column[Option[scala.math.BigDecimal]]("mortality", O.Default(None))
    /** Database column sd DBType(numeric), Default(None) */
    val sd: Column[Option[scala.math.BigDecimal]] = column[Option[scala.math.BigDecimal]]("sd", O.Default(None))
    /** Database column sex DBType(varchar), Length(100,true), Default(None) */
    val sex: Column[Option[String]] = column[Option[String]]("sex", O.Length(100,varying=true), O.Default(None))
    /** Database column standardisedbodyweight DBType(numeric), Default(None) */
    val standardisedbodyweight: Column[Option[scala.math.BigDecimal]] = column[Option[scala.math.BigDecimal]]("standardisedbodyweight", O.Default(None))
    /** Database column standardisedsex DBType(varchar), Length(100,true), Default(None) */
    val standardisedsex: Column[Option[String]] = column[Option[String]]("standardisedsex", O.Length(100,varying=true), O.Default(None))
    /** Database column normalised_sex DBType(varchar), Length(100,true), Default(None) */
    val normalisedSex: Column[Option[String]] = column[Option[String]]("normalised_sex", O.Length(100,varying=true), O.Default(None))
    /** Database column timepoint DBType(numeric), Default(None) */
    val timepoint: Column[Option[scala.math.BigDecimal]] = column[Option[scala.math.BigDecimal]]("timepoint", O.Default(None))
    /** Database column timepointunit DBType(varchar), Length(100,true), Default(None) */
    val timepointunit: Column[Option[String]] = column[Option[String]]("timepointunit", O.Length(100,varying=true), O.Default(None))
    
    /** Foreign key referencing Study (database name generaltoxiceffects_subst_id_fkey) */
    lazy val studyFk = foreignKey("generaltoxiceffects_subst_id_fkey", (substId, studyId), Study)(r => (r.substId, r.studyId), onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.Cascade)
  }
  /** Collection-like TableQuery object for table Generaltoxiceffects */
  lazy val Generaltoxiceffects = new TableQuery(tag => new Generaltoxiceffects(tag))
  
  /** Entity class storing rows of table Grossnecropsy
   *  @param substId Database column subst_id DBType(varchar), Length(100,true), Default(None)
   *  @param studyId Database column study_id DBType(varchar), Length(100,true), Default(None)
   *  @param dosemgkg Database column dosemgkg DBType(numeric), Default(None)
   *  @param grade Database column grade DBType(varchar), Length(100,true), Default(None)
   *  @param grosspathologyfinding Database column grosspathologyfinding DBType(varchar), Length(255,true), Default(None)
   *  @param grosspathologyorganaffected Database column grosspathologyorganaffected DBType(varchar), Length(255,true), Default(None)
   *  @param numberofananimalsaffected Database column numberofananimalsaffected DBType(int4), Default(None)
   *  @param relevance Database column relevance DBType(varchar), Length(100,true), Default(None)
   *  @param sex Database column sex DBType(varchar), Length(100,true), Default(None)
   *  @param normalisedSex Database column normalised_sex DBType(varchar), Length(100,true), Default(None)
   *  @param standardisedgrosspathology Database column standardisedgrosspathology DBType(varchar), Length(255,true), Default(None)
   *  @param standardisedorgan Database column standardisedorgan DBType(varchar), Length(255,true), Default(None)
   *  @param standardisedsex Database column standardisedsex DBType(varchar), Length(255,true), Default(None)
   *  @param timepoint Database column timepoint DBType(numeric), Default(None)
   *  @param timepointunit Database column timepointunit DBType(varchar), Length(100,true), Default(None)
   *  @param totalnumberofanimals Database column totalnumberofanimals DBType(int4), Default(None) */
  case class GrossnecropsyRow(substId: Option[String] = None, studyId: Option[String] = None, dosemgkg: Option[scala.math.BigDecimal] = None, grade: Option[String] = None, grosspathologyfinding: Option[String] = None, grosspathologyorganaffected: Option[String] = None, numberofananimalsaffected: Option[Int] = None, relevance: Option[String] = None, sex: Option[String] = None, normalisedSex: Option[String] = None, standardisedgrosspathology: Option[String] = None, standardisedorgan: Option[String] = None, standardisedsex: Option[String] = None, timepoint: Option[scala.math.BigDecimal] = None, timepointunit: Option[String] = None, totalnumberofanimals: Option[Int] = None)
  /** GetResult implicit for fetching GrossnecropsyRow objects using plain SQL queries */
  implicit def GetResultGrossnecropsyRow(implicit e0: GR[Option[String]], e1: GR[Option[scala.math.BigDecimal]], e2: GR[Option[Int]]): GR[GrossnecropsyRow] = GR{
    prs => import prs._
    GrossnecropsyRow.tupled((<<?[String], <<?[String], <<?[scala.math.BigDecimal], <<?[String], <<?[String], <<?[String], <<?[Int], <<?[String], <<?[String], <<?[String], <<?[String], <<?[String], <<?[String], <<?[scala.math.BigDecimal], <<?[String], <<?[Int]))
  }
  /** Table description of table grossnecropsy. Objects of this class serve as prototypes for rows in queries. */
  class Grossnecropsy(_tableTag: Tag) extends Table[GrossnecropsyRow](_tableTag, "grossnecropsy") {
    def * = (substId, studyId, dosemgkg, grade, grosspathologyfinding, grosspathologyorganaffected, numberofananimalsaffected, relevance, sex, normalisedSex, standardisedgrosspathology, standardisedorgan, standardisedsex, timepoint, timepointunit, totalnumberofanimals) <> (GrossnecropsyRow.tupled, GrossnecropsyRow.unapply)
    
    /** Database column subst_id DBType(varchar), Length(100,true), Default(None) */
    val substId: Column[Option[String]] = column[Option[String]]("subst_id", O.Length(100,varying=true), O.Default(None))
    /** Database column study_id DBType(varchar), Length(100,true), Default(None) */
    val studyId: Column[Option[String]] = column[Option[String]]("study_id", O.Length(100,varying=true), O.Default(None))
    /** Database column dosemgkg DBType(numeric), Default(None) */
    val dosemgkg: Column[Option[scala.math.BigDecimal]] = column[Option[scala.math.BigDecimal]]("dosemgkg", O.Default(None))
    /** Database column grade DBType(varchar), Length(100,true), Default(None) */
    val grade: Column[Option[String]] = column[Option[String]]("grade", O.Length(100,varying=true), O.Default(None))
    /** Database column grosspathologyfinding DBType(varchar), Length(255,true), Default(None) */
    val grosspathologyfinding: Column[Option[String]] = column[Option[String]]("grosspathologyfinding", O.Length(255,varying=true), O.Default(None))
    /** Database column grosspathologyorganaffected DBType(varchar), Length(255,true), Default(None) */
    val grosspathologyorganaffected: Column[Option[String]] = column[Option[String]]("grosspathologyorganaffected", O.Length(255,varying=true), O.Default(None))
    /** Database column numberofananimalsaffected DBType(int4), Default(None) */
    val numberofananimalsaffected: Column[Option[Int]] = column[Option[Int]]("numberofananimalsaffected", O.Default(None))
    /** Database column relevance DBType(varchar), Length(100,true), Default(None) */
    val relevance: Column[Option[String]] = column[Option[String]]("relevance", O.Length(100,varying=true), O.Default(None))
    /** Database column sex DBType(varchar), Length(100,true), Default(None) */
    val sex: Column[Option[String]] = column[Option[String]]("sex", O.Length(100,varying=true), O.Default(None))
    /** Database column normalised_sex DBType(varchar), Length(100,true), Default(None) */
    val normalisedSex: Column[Option[String]] = column[Option[String]]("normalised_sex", O.Length(100,varying=true), O.Default(None))
    /** Database column standardisedgrosspathology DBType(varchar), Length(255,true), Default(None) */
    val standardisedgrosspathology: Column[Option[String]] = column[Option[String]]("standardisedgrosspathology", O.Length(255,varying=true), O.Default(None))
    /** Database column standardisedorgan DBType(varchar), Length(255,true), Default(None) */
    val standardisedorgan: Column[Option[String]] = column[Option[String]]("standardisedorgan", O.Length(255,varying=true), O.Default(None))
    /** Database column standardisedsex DBType(varchar), Length(255,true), Default(None) */
    val standardisedsex: Column[Option[String]] = column[Option[String]]("standardisedsex", O.Length(255,varying=true), O.Default(None))
    /** Database column timepoint DBType(numeric), Default(None) */
    val timepoint: Column[Option[scala.math.BigDecimal]] = column[Option[scala.math.BigDecimal]]("timepoint", O.Default(None))
    /** Database column timepointunit DBType(varchar), Length(100,true), Default(None) */
    val timepointunit: Column[Option[String]] = column[Option[String]]("timepointunit", O.Length(100,varying=true), O.Default(None))
    /** Database column totalnumberofanimals DBType(int4), Default(None) */
    val totalnumberofanimals: Column[Option[Int]] = column[Option[Int]]("totalnumberofanimals", O.Default(None))
    
    /** Foreign key referencing Study (database name grossnecropsy_subst_id_fkey) */
    lazy val studyFk = foreignKey("grossnecropsy_subst_id_fkey", (substId, studyId), Study)(r => (r.substId, r.studyId), onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.Cascade)
  }
  /** Collection-like TableQuery object for table Grossnecropsy */
  lazy val Grossnecropsy = new TableQuery(tag => new Grossnecropsy(tag))
  
  /** Entity class storing rows of table Hpfinding
   *  @param substId Database column subst_id DBType(varchar), Length(100,true), Default(None)
   *  @param studyId Database column study_id DBType(varchar), Length(100,true), Default(None)
   *  @param numAnimalsTotal Database column num_animals_total DBType(int4), Default(None)
   *  @param numAnimalsAffected Database column num_animals_affected DBType(int4), Default(None)
   *  @param dose Database column dose DBType(numeric), Default(None)
   *  @param sex Database column sex DBType(varchar), Length(100,true), Default(None)
   *  @param standarisedSex Database column standarised_sex DBType(varchar), Length(100,true), Default(None)
   *  @param normalisedSex Database column normalised_sex DBType(varchar), Length(100,true), Default(None)
   *  @param grade Database column grade DBType(varchar), Length(1000,true), Default(None)
   *  @param timepoint Database column timepoint DBType(int4), Default(None)
   *  @param timepointUnit Database column timepoint_unit DBType(varchar), Length(30,true), Default(None)
   *  @param relevance Database column relevance DBType(varchar), Length(1000,true), Default(None)
   *  @param histopathologyFinding Database column histopathology_finding DBType(varchar), Length(1000,true), Default(None)
   *  @param histopathologyFindingStandarised Database column histopathology_finding_standarised DBType(varchar), Length(1000,true), Default(None)
   *  @param histopathologyOrganAffected Database column histopathology_organ_affected DBType(varchar), Length(1000,true), Default(None)
   *  @param histopathologyOrganAffectedStandarised Database column histopathology_organ_affected_standarised DBType(varchar), Length(1000,true), Default(None)
   *  @param source Database column source DBType(varchar), Length(100,true), Default(None)
   *  @param histopathologyFindingStandarisedRevised Database column histopathology_finding_standarised_revised DBType(varchar), Length(1000,true), Default(None)
   *  @param histopathologyOrganAffectedStandarisedRevised Database column histopathology_organ_affected_standarised_revised DBType(varchar), Length(1000,true), Default(None) */
  case class HpfindingRow(substId: Option[String] = None, studyId: Option[String] = None, numAnimalsTotal: Option[Int] = None, numAnimalsAffected: Option[Int] = None, dose: Option[scala.math.BigDecimal] = None, sex: Option[String] = None, standarisedSex: Option[String] = None, normalisedSex: Option[String] = None, grade: Option[String] = None, timepoint: Option[Int] = None, timepointUnit: Option[String] = None, relevance: Option[String] = None, histopathologyFinding: Option[String] = None, histopathologyFindingStandarised: Option[String] = None, histopathologyOrganAffected: Option[String] = None, histopathologyOrganAffectedStandarised: Option[String] = None, source: Option[String] = None, histopathologyFindingStandarisedRevised: Option[String] = None, histopathologyOrganAffectedStandarisedRevised: Option[String] = None)
  /** GetResult implicit for fetching HpfindingRow objects using plain SQL queries */
  implicit def GetResultHpfindingRow(implicit e0: GR[Option[String]], e1: GR[Option[Int]], e2: GR[Option[scala.math.BigDecimal]]): GR[HpfindingRow] = GR{
    prs => import prs._
    HpfindingRow.tupled((<<?[String], <<?[String], <<?[Int], <<?[Int], <<?[scala.math.BigDecimal], <<?[String], <<?[String], <<?[String], <<?[String], <<?[Int], <<?[String], <<?[String], <<?[String], <<?[String], <<?[String], <<?[String], <<?[String], <<?[String], <<?[String]))
  }
  /** Table description of table hpfinding. Objects of this class serve as prototypes for rows in queries. */
  class Hpfinding(_tableTag: Tag) extends Table[HpfindingRow](_tableTag, "hpfinding") {
    def * = (substId, studyId, numAnimalsTotal, numAnimalsAffected, dose, sex, standarisedSex, normalisedSex, grade, timepoint, timepointUnit, relevance, histopathologyFinding, histopathologyFindingStandarised, histopathologyOrganAffected, histopathologyOrganAffectedStandarised, source, histopathologyFindingStandarisedRevised, histopathologyOrganAffectedStandarisedRevised) <> (HpfindingRow.tupled, HpfindingRow.unapply)
    
    /** Database column subst_id DBType(varchar), Length(100,true), Default(None) */
    val substId: Column[Option[String]] = column[Option[String]]("subst_id", O.Length(100,varying=true), O.Default(None))
    /** Database column study_id DBType(varchar), Length(100,true), Default(None) */
    val studyId: Column[Option[String]] = column[Option[String]]("study_id", O.Length(100,varying=true), O.Default(None))
    /** Database column num_animals_total DBType(int4), Default(None) */
    val numAnimalsTotal: Column[Option[Int]] = column[Option[Int]]("num_animals_total", O.Default(None))
    /** Database column num_animals_affected DBType(int4), Default(None) */
    val numAnimalsAffected: Column[Option[Int]] = column[Option[Int]]("num_animals_affected", O.Default(None))
    /** Database column dose DBType(numeric), Default(None) */
    val dose: Column[Option[scala.math.BigDecimal]] = column[Option[scala.math.BigDecimal]]("dose", O.Default(None))
    /** Database column sex DBType(varchar), Length(100,true), Default(None) */
    val sex: Column[Option[String]] = column[Option[String]]("sex", O.Length(100,varying=true), O.Default(None))
    /** Database column standarised_sex DBType(varchar), Length(100,true), Default(None) */
    val standarisedSex: Column[Option[String]] = column[Option[String]]("standarised_sex", O.Length(100,varying=true), O.Default(None))
    /** Database column normalised_sex DBType(varchar), Length(100,true), Default(None) */
    val normalisedSex: Column[Option[String]] = column[Option[String]]("normalised_sex", O.Length(100,varying=true), O.Default(None))
    /** Database column grade DBType(varchar), Length(1000,true), Default(None) */
    val grade: Column[Option[String]] = column[Option[String]]("grade", O.Length(1000,varying=true), O.Default(None))
    /** Database column timepoint DBType(int4), Default(None) */
    val timepoint: Column[Option[Int]] = column[Option[Int]]("timepoint", O.Default(None))
    /** Database column timepoint_unit DBType(varchar), Length(30,true), Default(None) */
    val timepointUnit: Column[Option[String]] = column[Option[String]]("timepoint_unit", O.Length(30,varying=true), O.Default(None))
    /** Database column relevance DBType(varchar), Length(1000,true), Default(None) */
    val relevance: Column[Option[String]] = column[Option[String]]("relevance", O.Length(1000,varying=true), O.Default(None))
    /** Database column histopathology_finding DBType(varchar), Length(1000,true), Default(None) */
    val histopathologyFinding: Column[Option[String]] = column[Option[String]]("histopathology_finding", O.Length(1000,varying=true), O.Default(None))
    /** Database column histopathology_finding_standarised DBType(varchar), Length(1000,true), Default(None) */
    val histopathologyFindingStandarised: Column[Option[String]] = column[Option[String]]("histopathology_finding_standarised", O.Length(1000,varying=true), O.Default(None))
    /** Database column histopathology_organ_affected DBType(varchar), Length(1000,true), Default(None) */
    val histopathologyOrganAffected: Column[Option[String]] = column[Option[String]]("histopathology_organ_affected", O.Length(1000,varying=true), O.Default(None))
    /** Database column histopathology_organ_affected_standarised DBType(varchar), Length(1000,true), Default(None) */
    val histopathologyOrganAffectedStandarised: Column[Option[String]] = column[Option[String]]("histopathology_organ_affected_standarised", O.Length(1000,varying=true), O.Default(None))
    /** Database column source DBType(varchar), Length(100,true), Default(None) */
    val source: Column[Option[String]] = column[Option[String]]("source", O.Length(100,varying=true), O.Default(None))
    /** Database column histopathology_finding_standarised_revised DBType(varchar), Length(1000,true), Default(None) */
    val histopathologyFindingStandarisedRevised: Column[Option[String]] = column[Option[String]]("histopathology_finding_standarised_revised", O.Length(1000,varying=true), O.Default(None))
    /** Database column histopathology_organ_affected_standarised_revised DBType(varchar), Length(1000,true), Default(None) */
    val histopathologyOrganAffectedStandarisedRevised: Column[Option[String]] = column[Option[String]]("histopathology_organ_affected_standarised_revised", O.Length(1000,varying=true), O.Default(None))
    
    /** Foreign key referencing Study (database name hpfinding_subst_id_fkey) */
    lazy val studyFk = foreignKey("hpfinding_subst_id_fkey", (substId, studyId), Study)(r => (r.substId, r.studyId), onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.Cascade)
  }
  /** Collection-like TableQuery object for table Hpfinding */
  lazy val Hpfinding = new TableQuery(tag => new Hpfinding(tag))
  
  /** Entity class storing rows of table HpfLevels
   *  @param level1 Database column level1 DBType(text), Length(2147483647,true), Default(None)
   *  @param level2 Database column level2 DBType(text), Length(2147483647,true), Default(None)
   *  @param level3 Database column level3 DBType(text), Length(2147483647,true), Default(None)
   *  @param level4 Database column level4 DBType(text), Length(2147483647,true), Default(None)
   *  @param level5 Database column level5 DBType(text), Length(2147483647,true), Default(None)
   *  @param level6 Database column level6 DBType(text), Length(2147483647,true), Default(None)
   *  @param level7 Database column level7 DBType(text), Length(2147483647,true), Default(None)
   *  @param level8 Database column level8 DBType(text), Length(2147483647,true), Default(None)
   *  @param childTerm Database column child_term DBType(text), Length(2147483647,true), Default(None)
   *  @param pathTerms Database column path_terms DBType(_text), Length(2147483647,false), Default(None) */
  case class HpfLevelsRow(level1: Option[String] = None, level2: Option[String] = None, level3: Option[String] = None, level4: Option[String] = None, level5: Option[String] = None, level6: Option[String] = None, level7: Option[String] = None, level8: Option[String] = None, childTerm: Option[String] = None, pathTerms: Option[String] = None)
  /** GetResult implicit for fetching HpfLevelsRow objects using plain SQL queries */
  implicit def GetResultHpfLevelsRow(implicit e0: GR[Option[String]]): GR[HpfLevelsRow] = GR{
    prs => import prs._
    HpfLevelsRow.tupled((<<?[String], <<?[String], <<?[String], <<?[String], <<?[String], <<?[String], <<?[String], <<?[String], <<?[String], <<?[String]))
  }
  /** Table description of table hpf_levels. Objects of this class serve as prototypes for rows in queries. */
  class HpfLevels(_tableTag: Tag) extends Table[HpfLevelsRow](_tableTag, "hpf_levels") {
    def * = (level1, level2, level3, level4, level5, level6, level7, level8, childTerm, pathTerms) <> (HpfLevelsRow.tupled, HpfLevelsRow.unapply)
    
    /** Database column level1 DBType(text), Length(2147483647,true), Default(None) */
    val level1: Column[Option[String]] = column[Option[String]]("level1", O.Length(2147483647,varying=true), O.Default(None))
    /** Database column level2 DBType(text), Length(2147483647,true), Default(None) */
    val level2: Column[Option[String]] = column[Option[String]]("level2", O.Length(2147483647,varying=true), O.Default(None))
    /** Database column level3 DBType(text), Length(2147483647,true), Default(None) */
    val level3: Column[Option[String]] = column[Option[String]]("level3", O.Length(2147483647,varying=true), O.Default(None))
    /** Database column level4 DBType(text), Length(2147483647,true), Default(None) */
    val level4: Column[Option[String]] = column[Option[String]]("level4", O.Length(2147483647,varying=true), O.Default(None))
    /** Database column level5 DBType(text), Length(2147483647,true), Default(None) */
    val level5: Column[Option[String]] = column[Option[String]]("level5", O.Length(2147483647,varying=true), O.Default(None))
    /** Database column level6 DBType(text), Length(2147483647,true), Default(None) */
    val level6: Column[Option[String]] = column[Option[String]]("level6", O.Length(2147483647,varying=true), O.Default(None))
    /** Database column level7 DBType(text), Length(2147483647,true), Default(None) */
    val level7: Column[Option[String]] = column[Option[String]]("level7", O.Length(2147483647,varying=true), O.Default(None))
    /** Database column level8 DBType(text), Length(2147483647,true), Default(None) */
    val level8: Column[Option[String]] = column[Option[String]]("level8", O.Length(2147483647,varying=true), O.Default(None))
    /** Database column child_term DBType(text), Length(2147483647,true), Default(None) */
    val childTerm: Column[Option[String]] = column[Option[String]]("child_term", O.Length(2147483647,varying=true), O.Default(None))
    /** Database column path_terms DBType(_text), Length(2147483647,false), Default(None) */
    val pathTerms: Column[Option[String]] = column[Option[String]]("path_terms", O.Length(2147483647,varying=false), O.Default(None))
  }
  /** Collection-like TableQuery object for table HpfLevels */
  lazy val HpfLevels = new TableQuery(tag => new HpfLevels(tag))
  
  /** Entity class storing rows of table HyperplasiaFindingsLevels
   *  @param substId Database column subst_id DBType(varchar), Length(100,true), Default(None)
   *  @param source Database column source DBType(varchar), Length(50,true), Default(None)
   *  @param relevance Database column relevance DBType(varchar), Length(100,true), Default(None)
   *  @param findingNormalised Database column finding_normalised DBType(varchar), Length(1000,true), Default(None)
   *  @param organNormalised Database column organ_normalised DBType(varchar), Length(1000,true), Default(None)
   *  @param change Database column change DBType(varchar), Length(1000,true), Default(None)
   *  @param dose Database column dose DBType(numeric), Default(None)
   *  @param cnt Database column cnt DBType(int4), Default(None)
   *  @param childTerm Database column child_term DBType(text), Length(2147483647,true), Default(None)
   *  @param level1 Database column level1 DBType(text), Length(2147483647,true), Default(None)
   *  @param level2 Database column level2 DBType(text), Length(2147483647,true), Default(None)
   *  @param level3 Database column level3 DBType(text), Length(2147483647,true), Default(None)
   *  @param level4 Database column level4 DBType(text), Length(2147483647,true), Default(None) */
  case class HyperplasiaFindingsLevelsRow(substId: Option[String] = None, source: Option[String] = None, relevance: Option[String] = None, findingNormalised: Option[String] = None, organNormalised: Option[String] = None, change: Option[String] = None, dose: Option[scala.math.BigDecimal] = None, cnt: Option[Int] = None, childTerm: Option[String] = None, level1: Option[String] = None, level2: Option[String] = None, level3: Option[String] = None, level4: Option[String] = None)
  /** GetResult implicit for fetching HyperplasiaFindingsLevelsRow objects using plain SQL queries */
  implicit def GetResultHyperplasiaFindingsLevelsRow(implicit e0: GR[Option[String]], e1: GR[Option[scala.math.BigDecimal]], e2: GR[Option[Int]]): GR[HyperplasiaFindingsLevelsRow] = GR{
    prs => import prs._
    HyperplasiaFindingsLevelsRow.tupled((<<?[String], <<?[String], <<?[String], <<?[String], <<?[String], <<?[String], <<?[scala.math.BigDecimal], <<?[Int], <<?[String], <<?[String], <<?[String], <<?[String], <<?[String]))
  }
  /** Table description of table hyperplasia_findings_levels. Objects of this class serve as prototypes for rows in queries. */
  class HyperplasiaFindingsLevels(_tableTag: Tag) extends Table[HyperplasiaFindingsLevelsRow](_tableTag, "hyperplasia_findings_levels") {
    def * = (substId, source, relevance, findingNormalised, organNormalised, change, dose, cnt, childTerm, level1, level2, level3, level4) <> (HyperplasiaFindingsLevelsRow.tupled, HyperplasiaFindingsLevelsRow.unapply)
    
    /** Database column subst_id DBType(varchar), Length(100,true), Default(None) */
    val substId: Column[Option[String]] = column[Option[String]]("subst_id", O.Length(100,varying=true), O.Default(None))
    /** Database column source DBType(varchar), Length(50,true), Default(None) */
    val source: Column[Option[String]] = column[Option[String]]("source", O.Length(50,varying=true), O.Default(None))
    /** Database column relevance DBType(varchar), Length(100,true), Default(None) */
    val relevance: Column[Option[String]] = column[Option[String]]("relevance", O.Length(100,varying=true), O.Default(None))
    /** Database column finding_normalised DBType(varchar), Length(1000,true), Default(None) */
    val findingNormalised: Column[Option[String]] = column[Option[String]]("finding_normalised", O.Length(1000,varying=true), O.Default(None))
    /** Database column organ_normalised DBType(varchar), Length(1000,true), Default(None) */
    val organNormalised: Column[Option[String]] = column[Option[String]]("organ_normalised", O.Length(1000,varying=true), O.Default(None))
    /** Database column change DBType(varchar), Length(1000,true), Default(None) */
    val change: Column[Option[String]] = column[Option[String]]("change", O.Length(1000,varying=true), O.Default(None))
    /** Database column dose DBType(numeric), Default(None) */
    val dose: Column[Option[scala.math.BigDecimal]] = column[Option[scala.math.BigDecimal]]("dose", O.Default(None))
    /** Database column cnt DBType(int4), Default(None) */
    val cnt: Column[Option[Int]] = column[Option[Int]]("cnt", O.Default(None))
    /** Database column child_term DBType(text), Length(2147483647,true), Default(None) */
    val childTerm: Column[Option[String]] = column[Option[String]]("child_term", O.Length(2147483647,varying=true), O.Default(None))
    /** Database column level1 DBType(text), Length(2147483647,true), Default(None) */
    val level1: Column[Option[String]] = column[Option[String]]("level1", O.Length(2147483647,varying=true), O.Default(None))
    /** Database column level2 DBType(text), Length(2147483647,true), Default(None) */
    val level2: Column[Option[String]] = column[Option[String]]("level2", O.Length(2147483647,varying=true), O.Default(None))
    /** Database column level3 DBType(text), Length(2147483647,true), Default(None) */
    val level3: Column[Option[String]] = column[Option[String]]("level3", O.Length(2147483647,varying=true), O.Default(None))
    /** Database column level4 DBType(text), Length(2147483647,true), Default(None) */
    val level4: Column[Option[String]] = column[Option[String]]("level4", O.Length(2147483647,varying=true), O.Default(None))
  }
  /** Collection-like TableQuery object for table HyperplasiaFindingsLevels */
  lazy val HyperplasiaFindingsLevels = new TableQuery(tag => new HyperplasiaFindingsLevels(tag))
  
  /** Entity class storing rows of table InputAdditionalfindings
   *  @param averagevalue Database column AverageValue DBType(varchar), Length(255,true), Default(None)
   *  @param dosemgkg Database column DoseMgKg DBType(varchar), Length(255,true), Default(None)
   *  @param grade Database column Grade DBType(varchar), Length(255,true), Default(None)
   *  @param numberofanimalsaffected Database column NumberOfAnimalsAffected DBType(varchar), Length(255,true), Default(None)
   *  @param parameter Database column Parameter DBType(varchar), Length(255,true), Default(None)
   *  @param relevance Database column Relevance DBType(varchar), Length(255,true), Default(None)
   *  @param substId Database column SUBST_ID DBType(varchar), Length(255,true), Default(None)
   *  @param sd Database column Sd DBType(varchar), Length(255,true), Default(None)
   *  @param sex Database column Sex DBType(varchar), Length(255,true), Default(None)
   *  @param standardisedsex Database column StandardisedSex DBType(varchar), Length(255,true), Default(None)
   *  @param timepostdosehrs Database column TimePostDoseHrs DBType(varchar), Length(255,true), Default(None)
   *  @param timepoint Database column Timepoint DBType(varchar), Length(255,true), Default(None)
   *  @param timepointunit Database column TimepointUnit DBType(varchar), Length(255,true), Default(None)
   *  @param totalnumberofanimals Database column TotalNumberOfAnimals DBType(varchar), Length(255,true), Default(None)
   *  @param unit Database column Unit DBType(varchar), Length(255,true), Default(None)
   *  @param studyId Database column study_id DBType(varchar), Length(255,true), Default(None) */
  case class InputAdditionalfindingsRow(averagevalue: Option[String] = None, dosemgkg: Option[String] = None, grade: Option[String] = None, numberofanimalsaffected: Option[String] = None, parameter: Option[String] = None, relevance: Option[String] = None, substId: Option[String] = None, sd: Option[String] = None, sex: Option[String] = None, standardisedsex: Option[String] = None, timepostdosehrs: Option[String] = None, timepoint: Option[String] = None, timepointunit: Option[String] = None, totalnumberofanimals: Option[String] = None, unit: Option[String] = None, studyId: Option[String] = None)
  /** GetResult implicit for fetching InputAdditionalfindingsRow objects using plain SQL queries */
  implicit def GetResultInputAdditionalfindingsRow(implicit e0: GR[Option[String]]): GR[InputAdditionalfindingsRow] = GR{
    prs => import prs._
    InputAdditionalfindingsRow.tupled((<<?[String], <<?[String], <<?[String], <<?[String], <<?[String], <<?[String], <<?[String], <<?[String], <<?[String], <<?[String], <<?[String], <<?[String], <<?[String], <<?[String], <<?[String], <<?[String]))
  }
  /** Table description of table input_AdditionalFindings. Objects of this class serve as prototypes for rows in queries. */
  class InputAdditionalfindings(_tableTag: Tag) extends Table[InputAdditionalfindingsRow](_tableTag, "input_AdditionalFindings") {
    def * = (averagevalue, dosemgkg, grade, numberofanimalsaffected, parameter, relevance, substId, sd, sex, standardisedsex, timepostdosehrs, timepoint, timepointunit, totalnumberofanimals, unit, studyId) <> (InputAdditionalfindingsRow.tupled, InputAdditionalfindingsRow.unapply)
    
    /** Database column AverageValue DBType(varchar), Length(255,true), Default(None) */
    val averagevalue: Column[Option[String]] = column[Option[String]]("AverageValue", O.Length(255,varying=true), O.Default(None))
    /** Database column DoseMgKg DBType(varchar), Length(255,true), Default(None) */
    val dosemgkg: Column[Option[String]] = column[Option[String]]("DoseMgKg", O.Length(255,varying=true), O.Default(None))
    /** Database column Grade DBType(varchar), Length(255,true), Default(None) */
    val grade: Column[Option[String]] = column[Option[String]]("Grade", O.Length(255,varying=true), O.Default(None))
    /** Database column NumberOfAnimalsAffected DBType(varchar), Length(255,true), Default(None) */
    val numberofanimalsaffected: Column[Option[String]] = column[Option[String]]("NumberOfAnimalsAffected", O.Length(255,varying=true), O.Default(None))
    /** Database column Parameter DBType(varchar), Length(255,true), Default(None) */
    val parameter: Column[Option[String]] = column[Option[String]]("Parameter", O.Length(255,varying=true), O.Default(None))
    /** Database column Relevance DBType(varchar), Length(255,true), Default(None) */
    val relevance: Column[Option[String]] = column[Option[String]]("Relevance", O.Length(255,varying=true), O.Default(None))
    /** Database column SUBST_ID DBType(varchar), Length(255,true), Default(None) */
    val substId: Column[Option[String]] = column[Option[String]]("SUBST_ID", O.Length(255,varying=true), O.Default(None))
    /** Database column Sd DBType(varchar), Length(255,true), Default(None) */
    val sd: Column[Option[String]] = column[Option[String]]("Sd", O.Length(255,varying=true), O.Default(None))
    /** Database column Sex DBType(varchar), Length(255,true), Default(None) */
    val sex: Column[Option[String]] = column[Option[String]]("Sex", O.Length(255,varying=true), O.Default(None))
    /** Database column StandardisedSex DBType(varchar), Length(255,true), Default(None) */
    val standardisedsex: Column[Option[String]] = column[Option[String]]("StandardisedSex", O.Length(255,varying=true), O.Default(None))
    /** Database column TimePostDoseHrs DBType(varchar), Length(255,true), Default(None) */
    val timepostdosehrs: Column[Option[String]] = column[Option[String]]("TimePostDoseHrs", O.Length(255,varying=true), O.Default(None))
    /** Database column Timepoint DBType(varchar), Length(255,true), Default(None) */
    val timepoint: Column[Option[String]] = column[Option[String]]("Timepoint", O.Length(255,varying=true), O.Default(None))
    /** Database column TimepointUnit DBType(varchar), Length(255,true), Default(None) */
    val timepointunit: Column[Option[String]] = column[Option[String]]("TimepointUnit", O.Length(255,varying=true), O.Default(None))
    /** Database column TotalNumberOfAnimals DBType(varchar), Length(255,true), Default(None) */
    val totalnumberofanimals: Column[Option[String]] = column[Option[String]]("TotalNumberOfAnimals", O.Length(255,varying=true), O.Default(None))
    /** Database column Unit DBType(varchar), Length(255,true), Default(None) */
    val unit: Column[Option[String]] = column[Option[String]]("Unit", O.Length(255,varying=true), O.Default(None))
    /** Database column study_id DBType(varchar), Length(255,true), Default(None) */
    val studyId: Column[Option[String]] = column[Option[String]]("study_id", O.Length(255,varying=true), O.Default(None))
  }
  /** Collection-like TableQuery object for table InputAdditionalfindings */
  lazy val InputAdditionalfindings = new TableQuery(tag => new InputAdditionalfindings(tag))
  
  /** Entity class storing rows of table InputAnimalspergrouppersex
   *  @param dosemgkg Database column DoseMgKg DBType(float8), Default(None)
   *  @param numberofanimals Database column NumberOfAnimals DBType(int4), Default(None)
   *  @param substId Database column SUBST_ID DBType(varchar), Length(255,true), Default(None)
   *  @param sex Database column Sex DBType(varchar), Length(255,true), Default(None)
   *  @param standardisedsex Database column StandardisedSex DBType(varchar), Length(255,true), Default(None)
   *  @param studyId Database column study_id DBType(int4), Default(None) */
  case class InputAnimalspergrouppersexRow(dosemgkg: Option[Double] = None, numberofanimals: Option[Int] = None, substId: Option[String] = None, sex: Option[String] = None, standardisedsex: Option[String] = None, studyId: Option[Int] = None)
  /** GetResult implicit for fetching InputAnimalspergrouppersexRow objects using plain SQL queries */
  implicit def GetResultInputAnimalspergrouppersexRow(implicit e0: GR[Option[Double]], e1: GR[Option[Int]], e2: GR[Option[String]]): GR[InputAnimalspergrouppersexRow] = GR{
    prs => import prs._
    InputAnimalspergrouppersexRow.tupled((<<?[Double], <<?[Int], <<?[String], <<?[String], <<?[String], <<?[Int]))
  }
  /** Table description of table input_AnimalsPerGroupPerSex. Objects of this class serve as prototypes for rows in queries. */
  class InputAnimalspergrouppersex(_tableTag: Tag) extends Table[InputAnimalspergrouppersexRow](_tableTag, "input_AnimalsPerGroupPerSex") {
    def * = (dosemgkg, numberofanimals, substId, sex, standardisedsex, studyId) <> (InputAnimalspergrouppersexRow.tupled, InputAnimalspergrouppersexRow.unapply)
    
    /** Database column DoseMgKg DBType(float8), Default(None) */
    val dosemgkg: Column[Option[Double]] = column[Option[Double]]("DoseMgKg", O.Default(None))
    /** Database column NumberOfAnimals DBType(int4), Default(None) */
    val numberofanimals: Column[Option[Int]] = column[Option[Int]]("NumberOfAnimals", O.Default(None))
    /** Database column SUBST_ID DBType(varchar), Length(255,true), Default(None) */
    val substId: Column[Option[String]] = column[Option[String]]("SUBST_ID", O.Length(255,varying=true), O.Default(None))
    /** Database column Sex DBType(varchar), Length(255,true), Default(None) */
    val sex: Column[Option[String]] = column[Option[String]]("Sex", O.Length(255,varying=true), O.Default(None))
    /** Database column StandardisedSex DBType(varchar), Length(255,true), Default(None) */
    val standardisedsex: Column[Option[String]] = column[Option[String]]("StandardisedSex", O.Length(255,varying=true), O.Default(None))
    /** Database column study_id DBType(int4), Default(None) */
    val studyId: Column[Option[Int]] = column[Option[Int]]("study_id", O.Default(None))
    
    /** Index over (numberofanimals) (database name NumberOfAnimals) */
    val index1 = index("NumberOfAnimals", numberofanimals)
    /** Index over (substId) (database name SUBST_ID) */
    val index2 = index("SUBST_ID", substId)
    /** Index over (studyId) (database name study_id) */
    val index3 = index("study_id", studyId)
  }
  /** Collection-like TableQuery object for table InputAnimalspergrouppersex */
  lazy val InputAnimalspergrouppersex = new TableQuery(tag => new InputAnimalspergrouppersex(tag))
  
  /** Entity class storing rows of table InputClinicalchemicalfindings
   *  @param averagefoldchange Database column AverageFoldChange DBType(varchar), Length(255,true), Default(None)
   *  @param averagevalue Database column AverageValue DBType(varchar), Length(255,true), Default(None)
   *  @param clinicalchemistryparameter Database column ClinicalChemistryParameter DBType(varchar), Length(255,true), Default(None)
   *  @param dosemgkg Database column DoseMgKg DBType(varchar), Length(255,true), Default(None)
   *  @param finding Database column Finding DBType(varchar), Length(255,true), Default(None)
   *  @param relevance Database column Relevance DBType(varchar), Length(255,true), Default(None)
   *  @param substId Database column SUBST_ID DBType(varchar), Length(255,true), Default(None)
   *  @param sd Database column Sd DBType(varchar), Length(255,true), Default(None)
   *  @param sex Database column Sex DBType(varchar), Length(255,true), Default(None)
   *  @param standardisedparameter Database column StandardisedParameter DBType(varchar), Length(255,true), Default(None)
   *  @param standardisedsex Database column StandardisedSex DBType(varchar), Length(255,true), Default(None)
   *  @param timepoint Database column Timepoint DBType(varchar), Length(255,true), Default(None)
   *  @param timepointunit Database column TimepointUnit DBType(varchar), Length(255,true), Default(None)
   *  @param unit Database column Unit DBType(varchar), Length(255,true), Default(None)
   *  @param studyId Database column study_id DBType(varchar), Length(255,true), Default(None) */
  case class InputClinicalchemicalfindingsRow(averagefoldchange: Option[String] = None, averagevalue: Option[String] = None, clinicalchemistryparameter: Option[String] = None, dosemgkg: Option[String] = None, finding: Option[String] = None, relevance: Option[String] = None, substId: Option[String] = None, sd: Option[String] = None, sex: Option[String] = None, standardisedparameter: Option[String] = None, standardisedsex: Option[String] = None, timepoint: Option[String] = None, timepointunit: Option[String] = None, unit: Option[String] = None, studyId: Option[String] = None)
  /** GetResult implicit for fetching InputClinicalchemicalfindingsRow objects using plain SQL queries */
  implicit def GetResultInputClinicalchemicalfindingsRow(implicit e0: GR[Option[String]]): GR[InputClinicalchemicalfindingsRow] = GR{
    prs => import prs._
    InputClinicalchemicalfindingsRow.tupled((<<?[String], <<?[String], <<?[String], <<?[String], <<?[String], <<?[String], <<?[String], <<?[String], <<?[String], <<?[String], <<?[String], <<?[String], <<?[String], <<?[String], <<?[String]))
  }
  /** Table description of table input_ClinicalChemicalFindings. Objects of this class serve as prototypes for rows in queries. */
  class InputClinicalchemicalfindings(_tableTag: Tag) extends Table[InputClinicalchemicalfindingsRow](_tableTag, "input_ClinicalChemicalFindings") {
    def * = (averagefoldchange, averagevalue, clinicalchemistryparameter, dosemgkg, finding, relevance, substId, sd, sex, standardisedparameter, standardisedsex, timepoint, timepointunit, unit, studyId) <> (InputClinicalchemicalfindingsRow.tupled, InputClinicalchemicalfindingsRow.unapply)
    
    /** Database column AverageFoldChange DBType(varchar), Length(255,true), Default(None) */
    val averagefoldchange: Column[Option[String]] = column[Option[String]]("AverageFoldChange", O.Length(255,varying=true), O.Default(None))
    /** Database column AverageValue DBType(varchar), Length(255,true), Default(None) */
    val averagevalue: Column[Option[String]] = column[Option[String]]("AverageValue", O.Length(255,varying=true), O.Default(None))
    /** Database column ClinicalChemistryParameter DBType(varchar), Length(255,true), Default(None) */
    val clinicalchemistryparameter: Column[Option[String]] = column[Option[String]]("ClinicalChemistryParameter", O.Length(255,varying=true), O.Default(None))
    /** Database column DoseMgKg DBType(varchar), Length(255,true), Default(None) */
    val dosemgkg: Column[Option[String]] = column[Option[String]]("DoseMgKg", O.Length(255,varying=true), O.Default(None))
    /** Database column Finding DBType(varchar), Length(255,true), Default(None) */
    val finding: Column[Option[String]] = column[Option[String]]("Finding", O.Length(255,varying=true), O.Default(None))
    /** Database column Relevance DBType(varchar), Length(255,true), Default(None) */
    val relevance: Column[Option[String]] = column[Option[String]]("Relevance", O.Length(255,varying=true), O.Default(None))
    /** Database column SUBST_ID DBType(varchar), Length(255,true), Default(None) */
    val substId: Column[Option[String]] = column[Option[String]]("SUBST_ID", O.Length(255,varying=true), O.Default(None))
    /** Database column Sd DBType(varchar), Length(255,true), Default(None) */
    val sd: Column[Option[String]] = column[Option[String]]("Sd", O.Length(255,varying=true), O.Default(None))
    /** Database column Sex DBType(varchar), Length(255,true), Default(None) */
    val sex: Column[Option[String]] = column[Option[String]]("Sex", O.Length(255,varying=true), O.Default(None))
    /** Database column StandardisedParameter DBType(varchar), Length(255,true), Default(None) */
    val standardisedparameter: Column[Option[String]] = column[Option[String]]("StandardisedParameter", O.Length(255,varying=true), O.Default(None))
    /** Database column StandardisedSex DBType(varchar), Length(255,true), Default(None) */
    val standardisedsex: Column[Option[String]] = column[Option[String]]("StandardisedSex", O.Length(255,varying=true), O.Default(None))
    /** Database column Timepoint DBType(varchar), Length(255,true), Default(None) */
    val timepoint: Column[Option[String]] = column[Option[String]]("Timepoint", O.Length(255,varying=true), O.Default(None))
    /** Database column TimepointUnit DBType(varchar), Length(255,true), Default(None) */
    val timepointunit: Column[Option[String]] = column[Option[String]]("TimepointUnit", O.Length(255,varying=true), O.Default(None))
    /** Database column Unit DBType(varchar), Length(255,true), Default(None) */
    val unit: Column[Option[String]] = column[Option[String]]("Unit", O.Length(255,varying=true), O.Default(None))
    /** Database column study_id DBType(varchar), Length(255,true), Default(None) */
    val studyId: Column[Option[String]] = column[Option[String]]("study_id", O.Length(255,varying=true), O.Default(None))
  }
  /** Collection-like TableQuery object for table InputClinicalchemicalfindings */
  lazy val InputClinicalchemicalfindings = new TableQuery(tag => new InputClinicalchemicalfindings(tag))
  
  /** Entity class storing rows of table InputClinicalhaematologicalfindings
   *  @param averagefoldchange Database column AverageFoldChange DBType(varchar), Length(255,true), Default(None)
   *  @param averagevalue Database column AverageValue DBType(varchar), Length(255,true), Default(None)
   *  @param clinicalhaematologyparameter Database column ClinicalHaematologyParameter DBType(varchar), Length(255,true), Default(None)
   *  @param dosemgkg Database column DoseMgKg DBType(varchar), Length(255,true), Default(None)
   *  @param finding Database column Finding DBType(varchar), Length(255,true), Default(None)
   *  @param relevance Database column Relevance DBType(varchar), Length(255,true), Default(None)
   *  @param substId Database column SUBST_ID DBType(varchar), Length(255,true), Default(None)
   *  @param sd Database column Sd DBType(varchar), Length(255,true), Default(None)
   *  @param sex Database column Sex DBType(varchar), Length(255,true), Default(None)
   *  @param standardisedparameter Database column StandardisedParameter DBType(varchar), Length(255,true), Default(None)
   *  @param standardisedsex Database column StandardisedSex DBType(varchar), Length(255,true), Default(None)
   *  @param timepoint Database column Timepoint DBType(varchar), Length(255,true), Default(None)
   *  @param timepointunit Database column TimepointUnit DBType(varchar), Length(255,true), Default(None)
   *  @param unit Database column Unit DBType(varchar), Length(255,true), Default(None)
   *  @param studyId Database column study_id DBType(varchar), Length(255,true), Default(None) */
  case class InputClinicalhaematologicalfindingsRow(averagefoldchange: Option[String] = None, averagevalue: Option[String] = None, clinicalhaematologyparameter: Option[String] = None, dosemgkg: Option[String] = None, finding: Option[String] = None, relevance: Option[String] = None, substId: Option[String] = None, sd: Option[String] = None, sex: Option[String] = None, standardisedparameter: Option[String] = None, standardisedsex: Option[String] = None, timepoint: Option[String] = None, timepointunit: Option[String] = None, unit: Option[String] = None, studyId: Option[String] = None)
  /** GetResult implicit for fetching InputClinicalhaematologicalfindingsRow objects using plain SQL queries */
  implicit def GetResultInputClinicalhaematologicalfindingsRow(implicit e0: GR[Option[String]]): GR[InputClinicalhaematologicalfindingsRow] = GR{
    prs => import prs._
    InputClinicalhaematologicalfindingsRow.tupled((<<?[String], <<?[String], <<?[String], <<?[String], <<?[String], <<?[String], <<?[String], <<?[String], <<?[String], <<?[String], <<?[String], <<?[String], <<?[String], <<?[String], <<?[String]))
  }
  /** Table description of table input_ClinicalHaematologicalFindings. Objects of this class serve as prototypes for rows in queries. */
  class InputClinicalhaematologicalfindings(_tableTag: Tag) extends Table[InputClinicalhaematologicalfindingsRow](_tableTag, "input_ClinicalHaematologicalFindings") {
    def * = (averagefoldchange, averagevalue, clinicalhaematologyparameter, dosemgkg, finding, relevance, substId, sd, sex, standardisedparameter, standardisedsex, timepoint, timepointunit, unit, studyId) <> (InputClinicalhaematologicalfindingsRow.tupled, InputClinicalhaematologicalfindingsRow.unapply)
    
    /** Database column AverageFoldChange DBType(varchar), Length(255,true), Default(None) */
    val averagefoldchange: Column[Option[String]] = column[Option[String]]("AverageFoldChange", O.Length(255,varying=true), O.Default(None))
    /** Database column AverageValue DBType(varchar), Length(255,true), Default(None) */
    val averagevalue: Column[Option[String]] = column[Option[String]]("AverageValue", O.Length(255,varying=true), O.Default(None))
    /** Database column ClinicalHaematologyParameter DBType(varchar), Length(255,true), Default(None) */
    val clinicalhaematologyparameter: Column[Option[String]] = column[Option[String]]("ClinicalHaematologyParameter", O.Length(255,varying=true), O.Default(None))
    /** Database column DoseMgKg DBType(varchar), Length(255,true), Default(None) */
    val dosemgkg: Column[Option[String]] = column[Option[String]]("DoseMgKg", O.Length(255,varying=true), O.Default(None))
    /** Database column Finding DBType(varchar), Length(255,true), Default(None) */
    val finding: Column[Option[String]] = column[Option[String]]("Finding", O.Length(255,varying=true), O.Default(None))
    /** Database column Relevance DBType(varchar), Length(255,true), Default(None) */
    val relevance: Column[Option[String]] = column[Option[String]]("Relevance", O.Length(255,varying=true), O.Default(None))
    /** Database column SUBST_ID DBType(varchar), Length(255,true), Default(None) */
    val substId: Column[Option[String]] = column[Option[String]]("SUBST_ID", O.Length(255,varying=true), O.Default(None))
    /** Database column Sd DBType(varchar), Length(255,true), Default(None) */
    val sd: Column[Option[String]] = column[Option[String]]("Sd", O.Length(255,varying=true), O.Default(None))
    /** Database column Sex DBType(varchar), Length(255,true), Default(None) */
    val sex: Column[Option[String]] = column[Option[String]]("Sex", O.Length(255,varying=true), O.Default(None))
    /** Database column StandardisedParameter DBType(varchar), Length(255,true), Default(None) */
    val standardisedparameter: Column[Option[String]] = column[Option[String]]("StandardisedParameter", O.Length(255,varying=true), O.Default(None))
    /** Database column StandardisedSex DBType(varchar), Length(255,true), Default(None) */
    val standardisedsex: Column[Option[String]] = column[Option[String]]("StandardisedSex", O.Length(255,varying=true), O.Default(None))
    /** Database column Timepoint DBType(varchar), Length(255,true), Default(None) */
    val timepoint: Column[Option[String]] = column[Option[String]]("Timepoint", O.Length(255,varying=true), O.Default(None))
    /** Database column TimepointUnit DBType(varchar), Length(255,true), Default(None) */
    val timepointunit: Column[Option[String]] = column[Option[String]]("TimepointUnit", O.Length(255,varying=true), O.Default(None))
    /** Database column Unit DBType(varchar), Length(255,true), Default(None) */
    val unit: Column[Option[String]] = column[Option[String]]("Unit", O.Length(255,varying=true), O.Default(None))
    /** Database column study_id DBType(varchar), Length(255,true), Default(None) */
    val studyId: Column[Option[String]] = column[Option[String]]("study_id", O.Length(255,varying=true), O.Default(None))
  }
  /** Collection-like TableQuery object for table InputClinicalhaematologicalfindings */
  lazy val InputClinicalhaematologicalfindings = new TableQuery(tag => new InputClinicalhaematologicalfindings(tag))
  
  /** Entity class storing rows of table InputClinicalhaemostasisfindings
   *  @param averagefoldchange Database column AverageFoldChange DBType(varchar), Length(255,true), Default(None)
   *  @param averagevalue Database column AverageValue DBType(varchar), Length(255,true), Default(None)
   *  @param clinicalhaemostasisparameter Database column ClinicalHaemostasisParameter DBType(varchar), Length(255,true), Default(None)
   *  @param dosemgkg Database column DoseMgKg DBType(varchar), Length(255,true), Default(None)
   *  @param finding Database column Finding DBType(varchar), Length(255,true), Default(None)
   *  @param relevance Database column Relevance DBType(varchar), Length(255,true), Default(None)
   *  @param substId Database column SUBST_ID DBType(varchar), Length(255,true), Default(None)
   *  @param sd Database column Sd DBType(varchar), Length(255,true), Default(None)
   *  @param sex Database column Sex DBType(varchar), Length(255,true), Default(None)
   *  @param standardisedparameter Database column StandardisedParameter DBType(varchar), Length(255,true), Default(None)
   *  @param standardisedsex Database column StandardisedSex DBType(varchar), Length(255,true), Default(None)
   *  @param timepoint Database column Timepoint DBType(varchar), Length(255,true), Default(None)
   *  @param timepointunit Database column TimepointUnit DBType(varchar), Length(255,true), Default(None)
   *  @param unit Database column Unit DBType(varchar), Length(255,true), Default(None)
   *  @param studyId Database column study_id DBType(varchar), Length(255,true), Default(None) */
  case class InputClinicalhaemostasisfindingsRow(averagefoldchange: Option[String] = None, averagevalue: Option[String] = None, clinicalhaemostasisparameter: Option[String] = None, dosemgkg: Option[String] = None, finding: Option[String] = None, relevance: Option[String] = None, substId: Option[String] = None, sd: Option[String] = None, sex: Option[String] = None, standardisedparameter: Option[String] = None, standardisedsex: Option[String] = None, timepoint: Option[String] = None, timepointunit: Option[String] = None, unit: Option[String] = None, studyId: Option[String] = None)
  /** GetResult implicit for fetching InputClinicalhaemostasisfindingsRow objects using plain SQL queries */
  implicit def GetResultInputClinicalhaemostasisfindingsRow(implicit e0: GR[Option[String]]): GR[InputClinicalhaemostasisfindingsRow] = GR{
    prs => import prs._
    InputClinicalhaemostasisfindingsRow.tupled((<<?[String], <<?[String], <<?[String], <<?[String], <<?[String], <<?[String], <<?[String], <<?[String], <<?[String], <<?[String], <<?[String], <<?[String], <<?[String], <<?[String], <<?[String]))
  }
  /** Table description of table input_ClinicalHaemostasisFindings. Objects of this class serve as prototypes for rows in queries. */
  class InputClinicalhaemostasisfindings(_tableTag: Tag) extends Table[InputClinicalhaemostasisfindingsRow](_tableTag, "input_ClinicalHaemostasisFindings") {
    def * = (averagefoldchange, averagevalue, clinicalhaemostasisparameter, dosemgkg, finding, relevance, substId, sd, sex, standardisedparameter, standardisedsex, timepoint, timepointunit, unit, studyId) <> (InputClinicalhaemostasisfindingsRow.tupled, InputClinicalhaemostasisfindingsRow.unapply)
    
    /** Database column AverageFoldChange DBType(varchar), Length(255,true), Default(None) */
    val averagefoldchange: Column[Option[String]] = column[Option[String]]("AverageFoldChange", O.Length(255,varying=true), O.Default(None))
    /** Database column AverageValue DBType(varchar), Length(255,true), Default(None) */
    val averagevalue: Column[Option[String]] = column[Option[String]]("AverageValue", O.Length(255,varying=true), O.Default(None))
    /** Database column ClinicalHaemostasisParameter DBType(varchar), Length(255,true), Default(None) */
    val clinicalhaemostasisparameter: Column[Option[String]] = column[Option[String]]("ClinicalHaemostasisParameter", O.Length(255,varying=true), O.Default(None))
    /** Database column DoseMgKg DBType(varchar), Length(255,true), Default(None) */
    val dosemgkg: Column[Option[String]] = column[Option[String]]("DoseMgKg", O.Length(255,varying=true), O.Default(None))
    /** Database column Finding DBType(varchar), Length(255,true), Default(None) */
    val finding: Column[Option[String]] = column[Option[String]]("Finding", O.Length(255,varying=true), O.Default(None))
    /** Database column Relevance DBType(varchar), Length(255,true), Default(None) */
    val relevance: Column[Option[String]] = column[Option[String]]("Relevance", O.Length(255,varying=true), O.Default(None))
    /** Database column SUBST_ID DBType(varchar), Length(255,true), Default(None) */
    val substId: Column[Option[String]] = column[Option[String]]("SUBST_ID", O.Length(255,varying=true), O.Default(None))
    /** Database column Sd DBType(varchar), Length(255,true), Default(None) */
    val sd: Column[Option[String]] = column[Option[String]]("Sd", O.Length(255,varying=true), O.Default(None))
    /** Database column Sex DBType(varchar), Length(255,true), Default(None) */
    val sex: Column[Option[String]] = column[Option[String]]("Sex", O.Length(255,varying=true), O.Default(None))
    /** Database column StandardisedParameter DBType(varchar), Length(255,true), Default(None) */
    val standardisedparameter: Column[Option[String]] = column[Option[String]]("StandardisedParameter", O.Length(255,varying=true), O.Default(None))
    /** Database column StandardisedSex DBType(varchar), Length(255,true), Default(None) */
    val standardisedsex: Column[Option[String]] = column[Option[String]]("StandardisedSex", O.Length(255,varying=true), O.Default(None))
    /** Database column Timepoint DBType(varchar), Length(255,true), Default(None) */
    val timepoint: Column[Option[String]] = column[Option[String]]("Timepoint", O.Length(255,varying=true), O.Default(None))
    /** Database column TimepointUnit DBType(varchar), Length(255,true), Default(None) */
    val timepointunit: Column[Option[String]] = column[Option[String]]("TimepointUnit", O.Length(255,varying=true), O.Default(None))
    /** Database column Unit DBType(varchar), Length(255,true), Default(None) */
    val unit: Column[Option[String]] = column[Option[String]]("Unit", O.Length(255,varying=true), O.Default(None))
    /** Database column study_id DBType(varchar), Length(255,true), Default(None) */
    val studyId: Column[Option[String]] = column[Option[String]]("study_id", O.Length(255,varying=true), O.Default(None))
  }
  /** Collection-like TableQuery object for table InputClinicalhaemostasisfindings */
  lazy val InputClinicalhaemostasisfindings = new TableQuery(tag => new InputClinicalhaemostasisfindings(tag))
  
  /** Entity class storing rows of table InputClinicalsigns
   *  @param clinicalsign Database column ClinicalSign DBType(varchar), Length(255,true), Default(None)
   *  @param dosemgkg Database column DoseMgKg DBType(varchar), Length(255,true), Default(None)
   *  @param grade Database column Grade DBType(varchar), Length(255,true), Default(None)
   *  @param numberofanimalsaffected Database column NumberOfAnimalsAffected DBType(varchar), Length(255,true), Default(None)
   *  @param relevance Database column Relevance DBType(varchar), Length(255,true), Default(None)
   *  @param substId Database column SUBST_ID DBType(varchar), Length(255,true), Default(None)
   *  @param sex Database column Sex DBType(varchar), Length(255,true), Default(None)
   *  @param standardisedsex Database column StandardisedSex DBType(varchar), Length(255,true), Default(None)
   *  @param timepoint Database column Timepoint DBType(varchar), Length(255,true), Default(None)
   *  @param timepointunit Database column TimepointUnit DBType(varchar), Length(255,true), Default(None)
   *  @param totalnumberofanimals Database column TotalNumberOfAnimals DBType(varchar), Length(255,true), Default(None)
   *  @param studyId Database column study_id DBType(varchar), Length(255,true), Default(None) */
  case class InputClinicalsignsRow(clinicalsign: Option[String] = None, dosemgkg: Option[String] = None, grade: Option[String] = None, numberofanimalsaffected: Option[String] = None, relevance: Option[String] = None, substId: Option[String] = None, sex: Option[String] = None, standardisedsex: Option[String] = None, timepoint: Option[String] = None, timepointunit: Option[String] = None, totalnumberofanimals: Option[String] = None, studyId: Option[String] = None)
  /** GetResult implicit for fetching InputClinicalsignsRow objects using plain SQL queries */
  implicit def GetResultInputClinicalsignsRow(implicit e0: GR[Option[String]]): GR[InputClinicalsignsRow] = GR{
    prs => import prs._
    InputClinicalsignsRow.tupled((<<?[String], <<?[String], <<?[String], <<?[String], <<?[String], <<?[String], <<?[String], <<?[String], <<?[String], <<?[String], <<?[String], <<?[String]))
  }
  /** Table description of table input_ClinicalSigns. Objects of this class serve as prototypes for rows in queries. */
  class InputClinicalsigns(_tableTag: Tag) extends Table[InputClinicalsignsRow](_tableTag, "input_ClinicalSigns") {
    def * = (clinicalsign, dosemgkg, grade, numberofanimalsaffected, relevance, substId, sex, standardisedsex, timepoint, timepointunit, totalnumberofanimals, studyId) <> (InputClinicalsignsRow.tupled, InputClinicalsignsRow.unapply)
    
    /** Database column ClinicalSign DBType(varchar), Length(255,true), Default(None) */
    val clinicalsign: Column[Option[String]] = column[Option[String]]("ClinicalSign", O.Length(255,varying=true), O.Default(None))
    /** Database column DoseMgKg DBType(varchar), Length(255,true), Default(None) */
    val dosemgkg: Column[Option[String]] = column[Option[String]]("DoseMgKg", O.Length(255,varying=true), O.Default(None))
    /** Database column Grade DBType(varchar), Length(255,true), Default(None) */
    val grade: Column[Option[String]] = column[Option[String]]("Grade", O.Length(255,varying=true), O.Default(None))
    /** Database column NumberOfAnimalsAffected DBType(varchar), Length(255,true), Default(None) */
    val numberofanimalsaffected: Column[Option[String]] = column[Option[String]]("NumberOfAnimalsAffected", O.Length(255,varying=true), O.Default(None))
    /** Database column Relevance DBType(varchar), Length(255,true), Default(None) */
    val relevance: Column[Option[String]] = column[Option[String]]("Relevance", O.Length(255,varying=true), O.Default(None))
    /** Database column SUBST_ID DBType(varchar), Length(255,true), Default(None) */
    val substId: Column[Option[String]] = column[Option[String]]("SUBST_ID", O.Length(255,varying=true), O.Default(None))
    /** Database column Sex DBType(varchar), Length(255,true), Default(None) */
    val sex: Column[Option[String]] = column[Option[String]]("Sex", O.Length(255,varying=true), O.Default(None))
    /** Database column StandardisedSex DBType(varchar), Length(255,true), Default(None) */
    val standardisedsex: Column[Option[String]] = column[Option[String]]("StandardisedSex", O.Length(255,varying=true), O.Default(None))
    /** Database column Timepoint DBType(varchar), Length(255,true), Default(None) */
    val timepoint: Column[Option[String]] = column[Option[String]]("Timepoint", O.Length(255,varying=true), O.Default(None))
    /** Database column TimepointUnit DBType(varchar), Length(255,true), Default(None) */
    val timepointunit: Column[Option[String]] = column[Option[String]]("TimepointUnit", O.Length(255,varying=true), O.Default(None))
    /** Database column TotalNumberOfAnimals DBType(varchar), Length(255,true), Default(None) */
    val totalnumberofanimals: Column[Option[String]] = column[Option[String]]("TotalNumberOfAnimals", O.Length(255,varying=true), O.Default(None))
    /** Database column study_id DBType(varchar), Length(255,true), Default(None) */
    val studyId: Column[Option[String]] = column[Option[String]]("study_id", O.Length(255,varying=true), O.Default(None))
    
    /** Index over (numberofanimalsaffected) (database name NumberOfAnimalsAffected) */
    val index1 = index("NumberOfAnimalsAffected", numberofanimalsaffected)
  }
  /** Collection-like TableQuery object for table InputClinicalsigns */
  lazy val InputClinicalsigns = new TableQuery(tag => new InputClinicalsigns(tag))
  
  /** Entity class storing rows of table InputEffectlevels
   *  @param effectleveltype Database column EffectLevelType DBType(varchar), Length(255,true), Default(None)
   *  @param effectlevelunit Database column EffectLevelUnit DBType(varchar), Length(255,true), Default(None)
   *  @param effectlevelvalue Database column EffectLevelValue DBType(varchar), Length(255,true), Default(None)
   *  @param substId Database column SUBST_ID DBType(varchar), Length(255,true), Default(None)
   *  @param standardisedeffectlevel Database column StandardisedEffectLevel DBType(varchar), Length(255,true), Default(None)
   *  @param studyId Database column study_id DBType(varchar), Length(255,true), Default(None) */
  case class InputEffectlevelsRow(effectleveltype: Option[String] = None, effectlevelunit: Option[String] = None, effectlevelvalue: Option[String] = None, substId: Option[String] = None, standardisedeffectlevel: Option[String] = None, studyId: Option[String] = None)
  /** GetResult implicit for fetching InputEffectlevelsRow objects using plain SQL queries */
  implicit def GetResultInputEffectlevelsRow(implicit e0: GR[Option[String]]): GR[InputEffectlevelsRow] = GR{
    prs => import prs._
    InputEffectlevelsRow.tupled((<<?[String], <<?[String], <<?[String], <<?[String], <<?[String], <<?[String]))
  }
  /** Table description of table input_EffectLevels. Objects of this class serve as prototypes for rows in queries. */
  class InputEffectlevels(_tableTag: Tag) extends Table[InputEffectlevelsRow](_tableTag, "input_EffectLevels") {
    def * = (effectleveltype, effectlevelunit, effectlevelvalue, substId, standardisedeffectlevel, studyId) <> (InputEffectlevelsRow.tupled, InputEffectlevelsRow.unapply)
    
    /** Database column EffectLevelType DBType(varchar), Length(255,true), Default(None) */
    val effectleveltype: Column[Option[String]] = column[Option[String]]("EffectLevelType", O.Length(255,varying=true), O.Default(None))
    /** Database column EffectLevelUnit DBType(varchar), Length(255,true), Default(None) */
    val effectlevelunit: Column[Option[String]] = column[Option[String]]("EffectLevelUnit", O.Length(255,varying=true), O.Default(None))
    /** Database column EffectLevelValue DBType(varchar), Length(255,true), Default(None) */
    val effectlevelvalue: Column[Option[String]] = column[Option[String]]("EffectLevelValue", O.Length(255,varying=true), O.Default(None))
    /** Database column SUBST_ID DBType(varchar), Length(255,true), Default(None) */
    val substId: Column[Option[String]] = column[Option[String]]("SUBST_ID", O.Length(255,varying=true), O.Default(None))
    /** Database column StandardisedEffectLevel DBType(varchar), Length(255,true), Default(None) */
    val standardisedeffectlevel: Column[Option[String]] = column[Option[String]]("StandardisedEffectLevel", O.Length(255,varying=true), O.Default(None))
    /** Database column study_id DBType(varchar), Length(255,true), Default(None) */
    val studyId: Column[Option[String]] = column[Option[String]]("study_id", O.Length(255,varying=true), O.Default(None))
  }
  /** Collection-like TableQuery object for table InputEffectlevels */
  lazy val InputEffectlevels = new TableQuery(tag => new InputEffectlevels(tag))
  
  /** Entity class storing rows of table InputFreetextstudydesign
   *  @param header Database column Header DBType(varchar), Length(255,true), Default(None)
   *  @param substId Database column SUBST_ID DBType(varchar), Length(255,true), Default(None)
   *  @param text Database column Text DBType(text), Length(2147483647,true), Default(None)
   *  @param texttype Database column TextType DBType(varchar), Length(255,true), Default(None)
   *  @param studyId Database column study_id DBType(varchar), Length(255,true), Default(None) */
  case class InputFreetextstudydesignRow(header: Option[String] = None, substId: Option[String] = None, text: Option[String] = None, texttype: Option[String] = None, studyId: Option[String] = None)
  /** GetResult implicit for fetching InputFreetextstudydesignRow objects using plain SQL queries */
  implicit def GetResultInputFreetextstudydesignRow(implicit e0: GR[Option[String]]): GR[InputFreetextstudydesignRow] = GR{
    prs => import prs._
    InputFreetextstudydesignRow.tupled((<<?[String], <<?[String], <<?[String], <<?[String], <<?[String]))
  }
  /** Table description of table input_FreeTextStudyDesign. Objects of this class serve as prototypes for rows in queries. */
  class InputFreetextstudydesign(_tableTag: Tag) extends Table[InputFreetextstudydesignRow](_tableTag, "input_FreeTextStudyDesign") {
    def * = (header, substId, text, texttype, studyId) <> (InputFreetextstudydesignRow.tupled, InputFreetextstudydesignRow.unapply)
    
    /** Database column Header DBType(varchar), Length(255,true), Default(None) */
    val header: Column[Option[String]] = column[Option[String]]("Header", O.Length(255,varying=true), O.Default(None))
    /** Database column SUBST_ID DBType(varchar), Length(255,true), Default(None) */
    val substId: Column[Option[String]] = column[Option[String]]("SUBST_ID", O.Length(255,varying=true), O.Default(None))
    /** Database column Text DBType(text), Length(2147483647,true), Default(None) */
    val text: Column[Option[String]] = column[Option[String]]("Text", O.Length(2147483647,varying=true), O.Default(None))
    /** Database column TextType DBType(varchar), Length(255,true), Default(None) */
    val texttype: Column[Option[String]] = column[Option[String]]("TextType", O.Length(255,varying=true), O.Default(None))
    /** Database column study_id DBType(varchar), Length(255,true), Default(None) */
    val studyId: Column[Option[String]] = column[Option[String]]("study_id", O.Length(255,varying=true), O.Default(None))
  }
  /** Collection-like TableQuery object for table InputFreetextstudydesign */
  lazy val InputFreetextstudydesign = new TableQuery(tag => new InputFreetextstudydesign(tag))
  
  /** Entity class storing rows of table InputGeneraltoxiceffects
   *  @param bodyweight Database column Bodyweight DBType(varchar), Length(255,true), Default(None)
   *  @param bodyweightrelevance Database column BodyweightRelevance DBType(varchar), Length(255,true), Default(None)
   *  @param bodyweightunit Database column BodyweightUnit DBType(varchar), Length(255,true), Default(None)
   *  @param dosemgkg Database column DoseMgKg DBType(varchar), Length(255,true), Default(None)
   *  @param mortality Database column Mortality DBType(varchar), Length(255,true), Default(None)
   *  @param substId Database column SUBST_ID DBType(varchar), Length(255,true), Default(None)
   *  @param sd Database column Sd DBType(varchar), Length(255,true), Default(None)
   *  @param sex Database column Sex DBType(varchar), Length(255,true), Default(None)
   *  @param standardisedbodyweight Database column StandardisedBodyweight DBType(varchar), Length(255,true), Default(None)
   *  @param standardisedsex Database column StandardisedSex DBType(varchar), Length(255,true), Default(None)
   *  @param timepoint Database column Timepoint DBType(varchar), Length(255,true), Default(None)
   *  @param timepointunit Database column TimepointUnit DBType(varchar), Length(255,true), Default(None)
   *  @param studyId Database column study_id DBType(varchar), Length(255,true), Default(None) */
  case class InputGeneraltoxiceffectsRow(bodyweight: Option[String] = None, bodyweightrelevance: Option[String] = None, bodyweightunit: Option[String] = None, dosemgkg: Option[String] = None, mortality: Option[String] = None, substId: Option[String] = None, sd: Option[String] = None, sex: Option[String] = None, standardisedbodyweight: Option[String] = None, standardisedsex: Option[String] = None, timepoint: Option[String] = None, timepointunit: Option[String] = None, studyId: Option[String] = None)
  /** GetResult implicit for fetching InputGeneraltoxiceffectsRow objects using plain SQL queries */
  implicit def GetResultInputGeneraltoxiceffectsRow(implicit e0: GR[Option[String]]): GR[InputGeneraltoxiceffectsRow] = GR{
    prs => import prs._
    InputGeneraltoxiceffectsRow.tupled((<<?[String], <<?[String], <<?[String], <<?[String], <<?[String], <<?[String], <<?[String], <<?[String], <<?[String], <<?[String], <<?[String], <<?[String], <<?[String]))
  }
  /** Table description of table input_GeneralToxicEffects. Objects of this class serve as prototypes for rows in queries. */
  class InputGeneraltoxiceffects(_tableTag: Tag) extends Table[InputGeneraltoxiceffectsRow](_tableTag, "input_GeneralToxicEffects") {
    def * = (bodyweight, bodyweightrelevance, bodyweightunit, dosemgkg, mortality, substId, sd, sex, standardisedbodyweight, standardisedsex, timepoint, timepointunit, studyId) <> (InputGeneraltoxiceffectsRow.tupled, InputGeneraltoxiceffectsRow.unapply)
    
    /** Database column Bodyweight DBType(varchar), Length(255,true), Default(None) */
    val bodyweight: Column[Option[String]] = column[Option[String]]("Bodyweight", O.Length(255,varying=true), O.Default(None))
    /** Database column BodyweightRelevance DBType(varchar), Length(255,true), Default(None) */
    val bodyweightrelevance: Column[Option[String]] = column[Option[String]]("BodyweightRelevance", O.Length(255,varying=true), O.Default(None))
    /** Database column BodyweightUnit DBType(varchar), Length(255,true), Default(None) */
    val bodyweightunit: Column[Option[String]] = column[Option[String]]("BodyweightUnit", O.Length(255,varying=true), O.Default(None))
    /** Database column DoseMgKg DBType(varchar), Length(255,true), Default(None) */
    val dosemgkg: Column[Option[String]] = column[Option[String]]("DoseMgKg", O.Length(255,varying=true), O.Default(None))
    /** Database column Mortality DBType(varchar), Length(255,true), Default(None) */
    val mortality: Column[Option[String]] = column[Option[String]]("Mortality", O.Length(255,varying=true), O.Default(None))
    /** Database column SUBST_ID DBType(varchar), Length(255,true), Default(None) */
    val substId: Column[Option[String]] = column[Option[String]]("SUBST_ID", O.Length(255,varying=true), O.Default(None))
    /** Database column Sd DBType(varchar), Length(255,true), Default(None) */
    val sd: Column[Option[String]] = column[Option[String]]("Sd", O.Length(255,varying=true), O.Default(None))
    /** Database column Sex DBType(varchar), Length(255,true), Default(None) */
    val sex: Column[Option[String]] = column[Option[String]]("Sex", O.Length(255,varying=true), O.Default(None))
    /** Database column StandardisedBodyweight DBType(varchar), Length(255,true), Default(None) */
    val standardisedbodyweight: Column[Option[String]] = column[Option[String]]("StandardisedBodyweight", O.Length(255,varying=true), O.Default(None))
    /** Database column StandardisedSex DBType(varchar), Length(255,true), Default(None) */
    val standardisedsex: Column[Option[String]] = column[Option[String]]("StandardisedSex", O.Length(255,varying=true), O.Default(None))
    /** Database column Timepoint DBType(varchar), Length(255,true), Default(None) */
    val timepoint: Column[Option[String]] = column[Option[String]]("Timepoint", O.Length(255,varying=true), O.Default(None))
    /** Database column TimepointUnit DBType(varchar), Length(255,true), Default(None) */
    val timepointunit: Column[Option[String]] = column[Option[String]]("TimepointUnit", O.Length(255,varying=true), O.Default(None))
    /** Database column study_id DBType(varchar), Length(255,true), Default(None) */
    val studyId: Column[Option[String]] = column[Option[String]]("study_id", O.Length(255,varying=true), O.Default(None))
  }
  /** Collection-like TableQuery object for table InputGeneraltoxiceffects */
  lazy val InputGeneraltoxiceffects = new TableQuery(tag => new InputGeneraltoxiceffects(tag))
  
  /** Entity class storing rows of table InputGrossnecropsy
   *  @param dosemgkg Database column DoseMgKg DBType(varchar), Length(255,true), Default(None)
   *  @param grade Database column Grade DBType(varchar), Length(255,true), Default(None)
   *  @param grosspathologyfinding Database column GrossPathologyFinding DBType(varchar), Length(255,true), Default(None)
   *  @param grosspathologyorganaffected Database column GrossPathologyOrganAffected DBType(varchar), Length(255,true), Default(None)
   *  @param numberofanimalsaffected Database column NumberOfAnimalsAffected DBType(varchar), Length(255,true), Default(None)
   *  @param relevance Database column Relevance DBType(varchar), Length(255,true), Default(None)
   *  @param substId Database column SUBST_ID DBType(varchar), Length(255,true), Default(None)
   *  @param sex Database column Sex DBType(varchar), Length(255,true), Default(None)
   *  @param standardisedgrosspathology Database column StandardisedGrossPathology DBType(varchar), Length(255,true), Default(None)
   *  @param standardisedorgan Database column StandardisedOrgan DBType(varchar), Length(255,true), Default(None)
   *  @param standardisedsex Database column StandardisedSex DBType(varchar), Length(255,true), Default(None)
   *  @param timepoint Database column Timepoint DBType(varchar), Length(255,true), Default(None)
   *  @param timepointunit Database column TimepointUnit DBType(varchar), Length(255,true), Default(None)
   *  @param totalnumberofanimals Database column TotalNumberOfAnimals DBType(varchar), Length(255,true), Default(None)
   *  @param studyId Database column study_id DBType(varchar), Length(255,true), Default(None) */
  case class InputGrossnecropsyRow(dosemgkg: Option[String] = None, grade: Option[String] = None, grosspathologyfinding: Option[String] = None, grosspathologyorganaffected: Option[String] = None, numberofanimalsaffected: Option[String] = None, relevance: Option[String] = None, substId: Option[String] = None, sex: Option[String] = None, standardisedgrosspathology: Option[String] = None, standardisedorgan: Option[String] = None, standardisedsex: Option[String] = None, timepoint: Option[String] = None, timepointunit: Option[String] = None, totalnumberofanimals: Option[String] = None, studyId: Option[String] = None)
  /** GetResult implicit for fetching InputGrossnecropsyRow objects using plain SQL queries */
  implicit def GetResultInputGrossnecropsyRow(implicit e0: GR[Option[String]]): GR[InputGrossnecropsyRow] = GR{
    prs => import prs._
    InputGrossnecropsyRow.tupled((<<?[String], <<?[String], <<?[String], <<?[String], <<?[String], <<?[String], <<?[String], <<?[String], <<?[String], <<?[String], <<?[String], <<?[String], <<?[String], <<?[String], <<?[String]))
  }
  /** Table description of table input_GrossNecropsy. Objects of this class serve as prototypes for rows in queries. */
  class InputGrossnecropsy(_tableTag: Tag) extends Table[InputGrossnecropsyRow](_tableTag, "input_GrossNecropsy") {
    def * = (dosemgkg, grade, grosspathologyfinding, grosspathologyorganaffected, numberofanimalsaffected, relevance, substId, sex, standardisedgrosspathology, standardisedorgan, standardisedsex, timepoint, timepointunit, totalnumberofanimals, studyId) <> (InputGrossnecropsyRow.tupled, InputGrossnecropsyRow.unapply)
    
    /** Database column DoseMgKg DBType(varchar), Length(255,true), Default(None) */
    val dosemgkg: Column[Option[String]] = column[Option[String]]("DoseMgKg", O.Length(255,varying=true), O.Default(None))
    /** Database column Grade DBType(varchar), Length(255,true), Default(None) */
    val grade: Column[Option[String]] = column[Option[String]]("Grade", O.Length(255,varying=true), O.Default(None))
    /** Database column GrossPathologyFinding DBType(varchar), Length(255,true), Default(None) */
    val grosspathologyfinding: Column[Option[String]] = column[Option[String]]("GrossPathologyFinding", O.Length(255,varying=true), O.Default(None))
    /** Database column GrossPathologyOrganAffected DBType(varchar), Length(255,true), Default(None) */
    val grosspathologyorganaffected: Column[Option[String]] = column[Option[String]]("GrossPathologyOrganAffected", O.Length(255,varying=true), O.Default(None))
    /** Database column NumberOfAnimalsAffected DBType(varchar), Length(255,true), Default(None) */
    val numberofanimalsaffected: Column[Option[String]] = column[Option[String]]("NumberOfAnimalsAffected", O.Length(255,varying=true), O.Default(None))
    /** Database column Relevance DBType(varchar), Length(255,true), Default(None) */
    val relevance: Column[Option[String]] = column[Option[String]]("Relevance", O.Length(255,varying=true), O.Default(None))
    /** Database column SUBST_ID DBType(varchar), Length(255,true), Default(None) */
    val substId: Column[Option[String]] = column[Option[String]]("SUBST_ID", O.Length(255,varying=true), O.Default(None))
    /** Database column Sex DBType(varchar), Length(255,true), Default(None) */
    val sex: Column[Option[String]] = column[Option[String]]("Sex", O.Length(255,varying=true), O.Default(None))
    /** Database column StandardisedGrossPathology DBType(varchar), Length(255,true), Default(None) */
    val standardisedgrosspathology: Column[Option[String]] = column[Option[String]]("StandardisedGrossPathology", O.Length(255,varying=true), O.Default(None))
    /** Database column StandardisedOrgan DBType(varchar), Length(255,true), Default(None) */
    val standardisedorgan: Column[Option[String]] = column[Option[String]]("StandardisedOrgan", O.Length(255,varying=true), O.Default(None))
    /** Database column StandardisedSex DBType(varchar), Length(255,true), Default(None) */
    val standardisedsex: Column[Option[String]] = column[Option[String]]("StandardisedSex", O.Length(255,varying=true), O.Default(None))
    /** Database column Timepoint DBType(varchar), Length(255,true), Default(None) */
    val timepoint: Column[Option[String]] = column[Option[String]]("Timepoint", O.Length(255,varying=true), O.Default(None))
    /** Database column TimepointUnit DBType(varchar), Length(255,true), Default(None) */
    val timepointunit: Column[Option[String]] = column[Option[String]]("TimepointUnit", O.Length(255,varying=true), O.Default(None))
    /** Database column TotalNumberOfAnimals DBType(varchar), Length(255,true), Default(None) */
    val totalnumberofanimals: Column[Option[String]] = column[Option[String]]("TotalNumberOfAnimals", O.Length(255,varying=true), O.Default(None))
    /** Database column study_id DBType(varchar), Length(255,true), Default(None) */
    val studyId: Column[Option[String]] = column[Option[String]]("study_id", O.Length(255,varying=true), O.Default(None))
  }
  /** Collection-like TableQuery object for table InputGrossnecropsy */
  lazy val InputGrossnecropsy = new TableQuery(tag => new InputGrossnecropsy(tag))
  
  /** Entity class storing rows of table InputHistopathologicalfindings
   *  @param dosemgkg Database column DoseMgKg DBType(float8), Default(None)
   *  @param grade Database column Grade DBType(varchar), Length(255,true), Default(None)
   *  @param histopathologyfinding Database column HistopathologyFinding DBType(varchar), Length(255,true), Default(None)
   *  @param histopathologyorganaffected Database column HistopathologyOrganAffected DBType(varchar), Length(255,true), Default(None)
   *  @param numberofanimalsaffected Database column NumberOfAnimalsAffected DBType(varchar), Length(255,true), Default(None)
   *  @param relevance Database column Relevance DBType(varchar), Length(255,true), Default(None)
   *  @param substId Database column SUBST_ID DBType(varchar), Length(255,true), Default(None)
   *  @param sex Database column Sex DBType(varchar), Length(255,true), Default(None)
   *  @param standardisedorgan Database column StandardisedOrgan DBType(varchar), Length(255,true), Default(None)
   *  @param standardisedpathology Database column StandardisedPathology DBType(varchar), Length(255,true), Default(None)
   *  @param standardisedsex Database column StandardisedSex DBType(varchar), Length(255,true), Default(None)
   *  @param timepoint Database column Timepoint DBType(varchar), Length(255,true), Default(None)
   *  @param timepointunit Database column TimepointUnit DBType(varchar), Length(255,true), Default(None)
   *  @param totalnumberofanimals Database column TotalNumberOfAnimals DBType(varchar), Length(255,true), Default(None)
   *  @param studyId Database column study_id DBType(varchar), Length(255,true), Default(None) */
  case class InputHistopathologicalfindingsRow(dosemgkg: Option[Double] = None, grade: Option[String] = None, histopathologyfinding: Option[String] = None, histopathologyorganaffected: Option[String] = None, numberofanimalsaffected: Option[String] = None, relevance: Option[String] = None, substId: Option[String] = None, sex: Option[String] = None, standardisedorgan: Option[String] = None, standardisedpathology: Option[String] = None, standardisedsex: Option[String] = None, timepoint: Option[String] = None, timepointunit: Option[String] = None, totalnumberofanimals: Option[String] = None, studyId: Option[String] = None)
  /** GetResult implicit for fetching InputHistopathologicalfindingsRow objects using plain SQL queries */
  implicit def GetResultInputHistopathologicalfindingsRow(implicit e0: GR[Option[Double]], e1: GR[Option[String]]): GR[InputHistopathologicalfindingsRow] = GR{
    prs => import prs._
    InputHistopathologicalfindingsRow.tupled((<<?[Double], <<?[String], <<?[String], <<?[String], <<?[String], <<?[String], <<?[String], <<?[String], <<?[String], <<?[String], <<?[String], <<?[String], <<?[String], <<?[String], <<?[String]))
  }
  /** Table description of table input_HistopathologicalFindings. Objects of this class serve as prototypes for rows in queries. */
  class InputHistopathologicalfindings(_tableTag: Tag) extends Table[InputHistopathologicalfindingsRow](_tableTag, "input_HistopathologicalFindings") {
    def * = (dosemgkg, grade, histopathologyfinding, histopathologyorganaffected, numberofanimalsaffected, relevance, substId, sex, standardisedorgan, standardisedpathology, standardisedsex, timepoint, timepointunit, totalnumberofanimals, studyId) <> (InputHistopathologicalfindingsRow.tupled, InputHistopathologicalfindingsRow.unapply)
    
    /** Database column DoseMgKg DBType(float8), Default(None) */
    val dosemgkg: Column[Option[Double]] = column[Option[Double]]("DoseMgKg", O.Default(None))
    /** Database column Grade DBType(varchar), Length(255,true), Default(None) */
    val grade: Column[Option[String]] = column[Option[String]]("Grade", O.Length(255,varying=true), O.Default(None))
    /** Database column HistopathologyFinding DBType(varchar), Length(255,true), Default(None) */
    val histopathologyfinding: Column[Option[String]] = column[Option[String]]("HistopathologyFinding", O.Length(255,varying=true), O.Default(None))
    /** Database column HistopathologyOrganAffected DBType(varchar), Length(255,true), Default(None) */
    val histopathologyorganaffected: Column[Option[String]] = column[Option[String]]("HistopathologyOrganAffected", O.Length(255,varying=true), O.Default(None))
    /** Database column NumberOfAnimalsAffected DBType(varchar), Length(255,true), Default(None) */
    val numberofanimalsaffected: Column[Option[String]] = column[Option[String]]("NumberOfAnimalsAffected", O.Length(255,varying=true), O.Default(None))
    /** Database column Relevance DBType(varchar), Length(255,true), Default(None) */
    val relevance: Column[Option[String]] = column[Option[String]]("Relevance", O.Length(255,varying=true), O.Default(None))
    /** Database column SUBST_ID DBType(varchar), Length(255,true), Default(None) */
    val substId: Column[Option[String]] = column[Option[String]]("SUBST_ID", O.Length(255,varying=true), O.Default(None))
    /** Database column Sex DBType(varchar), Length(255,true), Default(None) */
    val sex: Column[Option[String]] = column[Option[String]]("Sex", O.Length(255,varying=true), O.Default(None))
    /** Database column StandardisedOrgan DBType(varchar), Length(255,true), Default(None) */
    val standardisedorgan: Column[Option[String]] = column[Option[String]]("StandardisedOrgan", O.Length(255,varying=true), O.Default(None))
    /** Database column StandardisedPathology DBType(varchar), Length(255,true), Default(None) */
    val standardisedpathology: Column[Option[String]] = column[Option[String]]("StandardisedPathology", O.Length(255,varying=true), O.Default(None))
    /** Database column StandardisedSex DBType(varchar), Length(255,true), Default(None) */
    val standardisedsex: Column[Option[String]] = column[Option[String]]("StandardisedSex", O.Length(255,varying=true), O.Default(None))
    /** Database column Timepoint DBType(varchar), Length(255,true), Default(None) */
    val timepoint: Column[Option[String]] = column[Option[String]]("Timepoint", O.Length(255,varying=true), O.Default(None))
    /** Database column TimepointUnit DBType(varchar), Length(255,true), Default(None) */
    val timepointunit: Column[Option[String]] = column[Option[String]]("TimepointUnit", O.Length(255,varying=true), O.Default(None))
    /** Database column TotalNumberOfAnimals DBType(varchar), Length(255,true), Default(None) */
    val totalnumberofanimals: Column[Option[String]] = column[Option[String]]("TotalNumberOfAnimals", O.Length(255,varying=true), O.Default(None))
    /** Database column study_id DBType(varchar), Length(255,true), Default(None) */
    val studyId: Column[Option[String]] = column[Option[String]]("study_id", O.Length(255,varying=true), O.Default(None))
  }
  /** Collection-like TableQuery object for table InputHistopathologicalfindings */
  lazy val InputHistopathologicalfindings = new TableQuery(tag => new InputHistopathologicalfindings(tag))
  
  /** Entity class storing rows of table InputOntoEtoxOntologyRelationships
   *  @param id Database column ID DBType(serial), AutoInc, PrimaryKey
   *  @param ontologyTermId Database column ONTOLOGY_TERM_ID DBType(varchar), Length(255,true), Default(None)
   *  @param relationship Database column RELATIONSHIP DBType(varchar), Length(255,true), Default(None)
   *  @param relatedOntologyTermId Database column RELATED_ONTOLOGY_TERM_ID DBType(varchar), Length(255,true), Default(None)
   *  @param relationshipStatus Database column RELATIONSHIP_STATUS DBType(varchar), Length(255,true), Default(None) */
  case class InputOntoEtoxOntologyRelationshipsRow(id: Int, ontologyTermId: Option[String] = None, relationship: Option[String] = None, relatedOntologyTermId: Option[String] = None, relationshipStatus: Option[String] = None)
  /** GetResult implicit for fetching InputOntoEtoxOntologyRelationshipsRow objects using plain SQL queries */
  implicit def GetResultInputOntoEtoxOntologyRelationshipsRow(implicit e0: GR[Int], e1: GR[Option[String]]): GR[InputOntoEtoxOntologyRelationshipsRow] = GR{
    prs => import prs._
    InputOntoEtoxOntologyRelationshipsRow.tupled((<<[Int], <<?[String], <<?[String], <<?[String], <<?[String]))
  }
  /** Table description of table input_onto_etox_ontology_relationships. Objects of this class serve as prototypes for rows in queries. */
  class InputOntoEtoxOntologyRelationships(_tableTag: Tag) extends Table[InputOntoEtoxOntologyRelationshipsRow](_tableTag, "input_onto_etox_ontology_relationships") {
    def * = (id, ontologyTermId, relationship, relatedOntologyTermId, relationshipStatus) <> (InputOntoEtoxOntologyRelationshipsRow.tupled, InputOntoEtoxOntologyRelationshipsRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (id.?, ontologyTermId, relationship, relatedOntologyTermId, relationshipStatus).shaped.<>({r=>import r._; _1.map(_=> InputOntoEtoxOntologyRelationshipsRow.tupled((_1.get, _2, _3, _4, _5)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))
    
    /** Database column ID DBType(serial), AutoInc, PrimaryKey */
    val id: Column[Int] = column[Int]("ID", O.AutoInc, O.PrimaryKey)
    /** Database column ONTOLOGY_TERM_ID DBType(varchar), Length(255,true), Default(None) */
    val ontologyTermId: Column[Option[String]] = column[Option[String]]("ONTOLOGY_TERM_ID", O.Length(255,varying=true), O.Default(None))
    /** Database column RELATIONSHIP DBType(varchar), Length(255,true), Default(None) */
    val relationship: Column[Option[String]] = column[Option[String]]("RELATIONSHIP", O.Length(255,varying=true), O.Default(None))
    /** Database column RELATED_ONTOLOGY_TERM_ID DBType(varchar), Length(255,true), Default(None) */
    val relatedOntologyTermId: Column[Option[String]] = column[Option[String]]("RELATED_ONTOLOGY_TERM_ID", O.Length(255,varying=true), O.Default(None))
    /** Database column RELATIONSHIP_STATUS DBType(varchar), Length(255,true), Default(None) */
    val relationshipStatus: Column[Option[String]] = column[Option[String]]("RELATIONSHIP_STATUS", O.Length(255,varying=true), O.Default(None))
  }
  /** Collection-like TableQuery object for table InputOntoEtoxOntologyRelationships */
  lazy val InputOntoEtoxOntologyRelationships = new TableQuery(tag => new InputOntoEtoxOntologyRelationships(tag))
  
  /** Entity class storing rows of table InputOntoEtoxOntologyTerms
   *  @param ontologyTermId Database column ONTOLOGY_TERM_ID DBType(varchar), Length(255,true), Default(None)
   *  @param ontologyName Database column ONTOLOGY_NAME DBType(varchar), Length(255,true), Default(None)
   *  @param termName Database column TERM_NAME DBType(varchar), Length(255,true), Default(None)
   *  @param termStatus Database column TERM_STATUS DBType(varchar), Length(255,true), Default(None) */
  case class InputOntoEtoxOntologyTermsRow(ontologyTermId: Option[String] = None, ontologyName: Option[String] = None, termName: Option[String] = None, termStatus: Option[String] = None)
  /** GetResult implicit for fetching InputOntoEtoxOntologyTermsRow objects using plain SQL queries */
  implicit def GetResultInputOntoEtoxOntologyTermsRow(implicit e0: GR[Option[String]]): GR[InputOntoEtoxOntologyTermsRow] = GR{
    prs => import prs._
    InputOntoEtoxOntologyTermsRow.tupled((<<?[String], <<?[String], <<?[String], <<?[String]))
  }
  /** Table description of table input_onto_etox_ontology_terms. Objects of this class serve as prototypes for rows in queries. */
  class InputOntoEtoxOntologyTerms(_tableTag: Tag) extends Table[InputOntoEtoxOntologyTermsRow](_tableTag, "input_onto_etox_ontology_terms") {
    def * = (ontologyTermId, ontologyName, termName, termStatus) <> (InputOntoEtoxOntologyTermsRow.tupled, InputOntoEtoxOntologyTermsRow.unapply)
    
    /** Database column ONTOLOGY_TERM_ID DBType(varchar), Length(255,true), Default(None) */
    val ontologyTermId: Column[Option[String]] = column[Option[String]]("ONTOLOGY_TERM_ID", O.Length(255,varying=true), O.Default(None))
    /** Database column ONTOLOGY_NAME DBType(varchar), Length(255,true), Default(None) */
    val ontologyName: Column[Option[String]] = column[Option[String]]("ONTOLOGY_NAME", O.Length(255,varying=true), O.Default(None))
    /** Database column TERM_NAME DBType(varchar), Length(255,true), Default(None) */
    val termName: Column[Option[String]] = column[Option[String]]("TERM_NAME", O.Length(255,varying=true), O.Default(None))
    /** Database column TERM_STATUS DBType(varchar), Length(255,true), Default(None) */
    val termStatus: Column[Option[String]] = column[Option[String]]("TERM_STATUS", O.Length(255,varying=true), O.Default(None))
  }
  /** Collection-like TableQuery object for table InputOntoEtoxOntologyTerms */
  lazy val InputOntoEtoxOntologyTerms = new TableQuery(tag => new InputOntoEtoxOntologyTerms(tag))
  
  /** Entity class storing rows of table InputOntoVxSynonyms
   *  @param id Database column ID DBType(serial), AutoInc, PrimaryKey
   *  @param synonymDomain Database column SYNONYM_DOMAIN DBType(varchar), Length(255,true), Default(None)
   *  @param synonymContext Database column SYNONYM_CONTEXT DBType(varchar), Length(255,true), Default(None)
   *  @param vxTable Database column VX_TABLE DBType(varchar), Length(255,true), Default(None)
   *  @param vxColumn Database column VX_COLUMN DBType(varchar), Length(255,true), Default(None)
   *  @param vxValue Database column VX_VALUE DBType(text), Length(2147483647,true), Default(None)
   *  @param ontologyTermId Database column ONTOLOGY_TERM_ID DBType(varchar), Length(255,true), Default(None)
   *  @param synonymScope Database column SYNONYM_SCOPE DBType(varchar), Length(255,true), Default(None)
   *  @param synonymStatus Database column SYNONYM_STATUS DBType(varchar), Length(255,true), Default(None) */
  case class InputOntoVxSynonymsRow(id: Int, synonymDomain: Option[String] = None, synonymContext: Option[String] = None, vxTable: Option[String] = None, vxColumn: Option[String] = None, vxValue: Option[String] = None, ontologyTermId: Option[String] = None, synonymScope: Option[String] = None, synonymStatus: Option[String] = None)
  /** GetResult implicit for fetching InputOntoVxSynonymsRow objects using plain SQL queries */
  implicit def GetResultInputOntoVxSynonymsRow(implicit e0: GR[Int], e1: GR[Option[String]]): GR[InputOntoVxSynonymsRow] = GR{
    prs => import prs._
    InputOntoVxSynonymsRow.tupled((<<[Int], <<?[String], <<?[String], <<?[String], <<?[String], <<?[String], <<?[String], <<?[String], <<?[String]))
  }
  /** Table description of table input_onto_vx_synonyms. Objects of this class serve as prototypes for rows in queries. */
  class InputOntoVxSynonyms(_tableTag: Tag) extends Table[InputOntoVxSynonymsRow](_tableTag, "input_onto_vx_synonyms") {
    def * = (id, synonymDomain, synonymContext, vxTable, vxColumn, vxValue, ontologyTermId, synonymScope, synonymStatus) <> (InputOntoVxSynonymsRow.tupled, InputOntoVxSynonymsRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (id.?, synonymDomain, synonymContext, vxTable, vxColumn, vxValue, ontologyTermId, synonymScope, synonymStatus).shaped.<>({r=>import r._; _1.map(_=> InputOntoVxSynonymsRow.tupled((_1.get, _2, _3, _4, _5, _6, _7, _8, _9)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))
    
    /** Database column ID DBType(serial), AutoInc, PrimaryKey */
    val id: Column[Int] = column[Int]("ID", O.AutoInc, O.PrimaryKey)
    /** Database column SYNONYM_DOMAIN DBType(varchar), Length(255,true), Default(None) */
    val synonymDomain: Column[Option[String]] = column[Option[String]]("SYNONYM_DOMAIN", O.Length(255,varying=true), O.Default(None))
    /** Database column SYNONYM_CONTEXT DBType(varchar), Length(255,true), Default(None) */
    val synonymContext: Column[Option[String]] = column[Option[String]]("SYNONYM_CONTEXT", O.Length(255,varying=true), O.Default(None))
    /** Database column VX_TABLE DBType(varchar), Length(255,true), Default(None) */
    val vxTable: Column[Option[String]] = column[Option[String]]("VX_TABLE", O.Length(255,varying=true), O.Default(None))
    /** Database column VX_COLUMN DBType(varchar), Length(255,true), Default(None) */
    val vxColumn: Column[Option[String]] = column[Option[String]]("VX_COLUMN", O.Length(255,varying=true), O.Default(None))
    /** Database column VX_VALUE DBType(text), Length(2147483647,true), Default(None) */
    val vxValue: Column[Option[String]] = column[Option[String]]("VX_VALUE", O.Length(2147483647,varying=true), O.Default(None))
    /** Database column ONTOLOGY_TERM_ID DBType(varchar), Length(255,true), Default(None) */
    val ontologyTermId: Column[Option[String]] = column[Option[String]]("ONTOLOGY_TERM_ID", O.Length(255,varying=true), O.Default(None))
    /** Database column SYNONYM_SCOPE DBType(varchar), Length(255,true), Default(None) */
    val synonymScope: Column[Option[String]] = column[Option[String]]("SYNONYM_SCOPE", O.Length(255,varying=true), O.Default(None))
    /** Database column SYNONYM_STATUS DBType(varchar), Length(255,true), Default(None) */
    val synonymStatus: Column[Option[String]] = column[Option[String]]("SYNONYM_STATUS", O.Length(255,varying=true), O.Default(None))
    
    /** Index over (vxValue) (database name ix_vx_value) */
    val index1 = index("ix_vx_value", vxValue)
  }
  /** Collection-like TableQuery object for table InputOntoVxSynonyms */
  lazy val InputOntoVxSynonyms = new TableQuery(tag => new InputOntoVxSynonyms(tag))
  
  /** Entity class storing rows of table InputOrganweights
   *  @param averagefoldchange Database column AverageFoldChange DBType(varchar), Length(255,true), Default(None)
   *  @param averageweight Database column AverageWeight DBType(varchar), Length(255,true), Default(None)
   *  @param dosemgkg Database column DoseMgKg DBType(float8), Default(None)
   *  @param finding Database column Finding DBType(varchar), Length(255,true), Default(None)
   *  @param organweighed Database column OrganWeighed DBType(varchar), Length(255,true), Default(None)
   *  @param relevance Database column Relevance DBType(varchar), Length(255,true), Default(None)
   *  @param substId Database column SUBST_ID DBType(varchar), Length(255,true), Default(None)
   *  @param sd Database column Sd DBType(varchar), Length(255,true), Default(None)
   *  @param sex Database column Sex DBType(varchar), Length(255,true), Default(None)
   *  @param standardisedorgan Database column StandardisedOrgan DBType(varchar), Length(255,true), Default(None)
   *  @param standardisedsex Database column StandardisedSex DBType(varchar), Length(255,true), Default(None)
   *  @param timepoint Database column Timepoint DBType(int4), Default(None)
   *  @param timepointunit Database column TimepointUnit DBType(varchar), Length(255,true), Default(None)
   *  @param unit Database column Unit DBType(varchar), Length(255,true), Default(None)
   *  @param studyId Database column study_id DBType(int4), Default(None) */
  case class InputOrganweightsRow(averagefoldchange: Option[String] = None, averageweight: Option[String] = None, dosemgkg: Option[Double] = None, finding: Option[String] = None, organweighed: Option[String] = None, relevance: Option[String] = None, substId: Option[String] = None, sd: Option[String] = None, sex: Option[String] = None, standardisedorgan: Option[String] = None, standardisedsex: Option[String] = None, timepoint: Option[Int] = None, timepointunit: Option[String] = None, unit: Option[String] = None, studyId: Option[Int] = None)
  /** GetResult implicit for fetching InputOrganweightsRow objects using plain SQL queries */
  implicit def GetResultInputOrganweightsRow(implicit e0: GR[Option[String]], e1: GR[Option[Double]], e2: GR[Option[Int]]): GR[InputOrganweightsRow] = GR{
    prs => import prs._
    InputOrganweightsRow.tupled((<<?[String], <<?[String], <<?[Double], <<?[String], <<?[String], <<?[String], <<?[String], <<?[String], <<?[String], <<?[String], <<?[String], <<?[Int], <<?[String], <<?[String], <<?[Int]))
  }
  /** Table description of table input_OrganWeights. Objects of this class serve as prototypes for rows in queries. */
  class InputOrganweights(_tableTag: Tag) extends Table[InputOrganweightsRow](_tableTag, "input_OrganWeights") {
    def * = (averagefoldchange, averageweight, dosemgkg, finding, organweighed, relevance, substId, sd, sex, standardisedorgan, standardisedsex, timepoint, timepointunit, unit, studyId) <> (InputOrganweightsRow.tupled, InputOrganweightsRow.unapply)
    
    /** Database column AverageFoldChange DBType(varchar), Length(255,true), Default(None) */
    val averagefoldchange: Column[Option[String]] = column[Option[String]]("AverageFoldChange", O.Length(255,varying=true), O.Default(None))
    /** Database column AverageWeight DBType(varchar), Length(255,true), Default(None) */
    val averageweight: Column[Option[String]] = column[Option[String]]("AverageWeight", O.Length(255,varying=true), O.Default(None))
    /** Database column DoseMgKg DBType(float8), Default(None) */
    val dosemgkg: Column[Option[Double]] = column[Option[Double]]("DoseMgKg", O.Default(None))
    /** Database column Finding DBType(varchar), Length(255,true), Default(None) */
    val finding: Column[Option[String]] = column[Option[String]]("Finding", O.Length(255,varying=true), O.Default(None))
    /** Database column OrganWeighed DBType(varchar), Length(255,true), Default(None) */
    val organweighed: Column[Option[String]] = column[Option[String]]("OrganWeighed", O.Length(255,varying=true), O.Default(None))
    /** Database column Relevance DBType(varchar), Length(255,true), Default(None) */
    val relevance: Column[Option[String]] = column[Option[String]]("Relevance", O.Length(255,varying=true), O.Default(None))
    /** Database column SUBST_ID DBType(varchar), Length(255,true), Default(None) */
    val substId: Column[Option[String]] = column[Option[String]]("SUBST_ID", O.Length(255,varying=true), O.Default(None))
    /** Database column Sd DBType(varchar), Length(255,true), Default(None) */
    val sd: Column[Option[String]] = column[Option[String]]("Sd", O.Length(255,varying=true), O.Default(None))
    /** Database column Sex DBType(varchar), Length(255,true), Default(None) */
    val sex: Column[Option[String]] = column[Option[String]]("Sex", O.Length(255,varying=true), O.Default(None))
    /** Database column StandardisedOrgan DBType(varchar), Length(255,true), Default(None) */
    val standardisedorgan: Column[Option[String]] = column[Option[String]]("StandardisedOrgan", O.Length(255,varying=true), O.Default(None))
    /** Database column StandardisedSex DBType(varchar), Length(255,true), Default(None) */
    val standardisedsex: Column[Option[String]] = column[Option[String]]("StandardisedSex", O.Length(255,varying=true), O.Default(None))
    /** Database column Timepoint DBType(int4), Default(None) */
    val timepoint: Column[Option[Int]] = column[Option[Int]]("Timepoint", O.Default(None))
    /** Database column TimepointUnit DBType(varchar), Length(255,true), Default(None) */
    val timepointunit: Column[Option[String]] = column[Option[String]]("TimepointUnit", O.Length(255,varying=true), O.Default(None))
    /** Database column Unit DBType(varchar), Length(255,true), Default(None) */
    val unit: Column[Option[String]] = column[Option[String]]("Unit", O.Length(255,varying=true), O.Default(None))
    /** Database column study_id DBType(int4), Default(None) */
    val studyId: Column[Option[Int]] = column[Option[Int]]("study_id", O.Default(None))
  }
  /** Collection-like TableQuery object for table InputOrganweights */
  lazy val InputOrganweights = new TableQuery(tag => new InputOrganweights(tag))
  
  /** Entity class storing rows of table InputStructures
   *  @param casnumber Database column CasNumber DBType(varchar), Length(255,true), Default(None)
   *  @param commonname Database column CommonName DBType(varchar), Length(255,true), Default(None)
   *  @param companiessubstanceid Database column CompaniesSubstanceId DBType(varchar), Length(255,true), Default(None)
   *  @param databasesubstanceid Database column DatabaseSubstanceId DBType(varchar), Length(255,true), Default(None)
   *  @param inchi Database column Inchi DBType(text), Length(2147483647,true), Default(None)
   *  @param molecularformula Database column MolecularFormula DBType(varchar), Length(255,true), Default(None)
   *  @param molecularweight Database column MolecularWeight DBType(varchar), Length(255,true), Default(None)
   *  @param pharmacologicalaction Database column PharmacologicalAction DBType(varchar), Length(255,true), Default(None)
   *  @param smiles Database column Smiles DBType(varchar), Length(255,true), Default(None)
   *  @param substancestatus Database column SubstanceStatus DBType(varchar), Length(255,true), Default(None)
   *  @param viticlegacyrecno Database column ViticLegacyRecno DBType(varchar), Length(255,true), Default(None) */
  case class InputStructuresRow(casnumber: Option[String] = None, commonname: Option[String] = None, companiessubstanceid: Option[String] = None, databasesubstanceid: Option[String] = None, inchi: Option[String] = None, molecularformula: Option[String] = None, molecularweight: Option[String] = None, pharmacologicalaction: Option[String] = None, smiles: Option[String] = None, substancestatus: Option[String] = None, viticlegacyrecno: Option[String] = None)
  /** GetResult implicit for fetching InputStructuresRow objects using plain SQL queries */
  implicit def GetResultInputStructuresRow(implicit e0: GR[Option[String]]): GR[InputStructuresRow] = GR{
    prs => import prs._
    InputStructuresRow.tupled((<<?[String], <<?[String], <<?[String], <<?[String], <<?[String], <<?[String], <<?[String], <<?[String], <<?[String], <<?[String], <<?[String]))
  }
  /** Table description of table input_Structures. Objects of this class serve as prototypes for rows in queries. */
  class InputStructures(_tableTag: Tag) extends Table[InputStructuresRow](_tableTag, "input_Structures") {
    def * = (casnumber, commonname, companiessubstanceid, databasesubstanceid, inchi, molecularformula, molecularweight, pharmacologicalaction, smiles, substancestatus, viticlegacyrecno) <> (InputStructuresRow.tupled, InputStructuresRow.unapply)
    
    /** Database column CasNumber DBType(varchar), Length(255,true), Default(None) */
    val casnumber: Column[Option[String]] = column[Option[String]]("CasNumber", O.Length(255,varying=true), O.Default(None))
    /** Database column CommonName DBType(varchar), Length(255,true), Default(None) */
    val commonname: Column[Option[String]] = column[Option[String]]("CommonName", O.Length(255,varying=true), O.Default(None))
    /** Database column CompaniesSubstanceId DBType(varchar), Length(255,true), Default(None) */
    val companiessubstanceid: Column[Option[String]] = column[Option[String]]("CompaniesSubstanceId", O.Length(255,varying=true), O.Default(None))
    /** Database column DatabaseSubstanceId DBType(varchar), Length(255,true), Default(None) */
    val databasesubstanceid: Column[Option[String]] = column[Option[String]]("DatabaseSubstanceId", O.Length(255,varying=true), O.Default(None))
    /** Database column Inchi DBType(text), Length(2147483647,true), Default(None) */
    val inchi: Column[Option[String]] = column[Option[String]]("Inchi", O.Length(2147483647,varying=true), O.Default(None))
    /** Database column MolecularFormula DBType(varchar), Length(255,true), Default(None) */
    val molecularformula: Column[Option[String]] = column[Option[String]]("MolecularFormula", O.Length(255,varying=true), O.Default(None))
    /** Database column MolecularWeight DBType(varchar), Length(255,true), Default(None) */
    val molecularweight: Column[Option[String]] = column[Option[String]]("MolecularWeight", O.Length(255,varying=true), O.Default(None))
    /** Database column PharmacologicalAction DBType(varchar), Length(255,true), Default(None) */
    val pharmacologicalaction: Column[Option[String]] = column[Option[String]]("PharmacologicalAction", O.Length(255,varying=true), O.Default(None))
    /** Database column Smiles DBType(varchar), Length(255,true), Default(None) */
    val smiles: Column[Option[String]] = column[Option[String]]("Smiles", O.Length(255,varying=true), O.Default(None))
    /** Database column SubstanceStatus DBType(varchar), Length(255,true), Default(None) */
    val substancestatus: Column[Option[String]] = column[Option[String]]("SubstanceStatus", O.Length(255,varying=true), O.Default(None))
    /** Database column ViticLegacyRecno DBType(varchar), Length(255,true), Default(None) */
    val viticlegacyrecno: Column[Option[String]] = column[Option[String]]("ViticLegacyRecno", O.Length(255,varying=true), O.Default(None))
    
    /** Index over (companiessubstanceid) (database name CompaniesSubstanceId) */
    val index1 = index("CompaniesSubstanceId", companiessubstanceid)
    /** Index over (databasesubstanceid) (database name DatabaseSubstanceId) */
    val index2 = index("DatabaseSubstanceId", databasesubstanceid)
  }
  /** Collection-like TableQuery object for table InputStructures */
  lazy val InputStructures = new TableQuery(tag => new InputStructures(tag))
  
  /** Row type of table InputStudies */
  type InputStudiesRow = HCons[Option[String],HCons[Option[String],HCons[Option[String],HCons[Option[String],HCons[Option[String],HCons[Option[String],HCons[Option[String],HCons[Option[String],HCons[Option[String],HCons[Option[String],HCons[Option[String],HCons[Option[String],HCons[Option[String],HCons[Option[String],HCons[Option[String],HCons[Option[String],HCons[Option[String],HCons[Option[String],HCons[Option[String],HCons[Option[String],HCons[Option[String],HCons[Option[String],HCons[Option[String],HCons[Option[String],HNil]]]]]]]]]]]]]]]]]]]]]]]]
  /** Constructor for InputStudiesRow providing default values if available in the database schema. */
  def InputStudiesRow(administrationroute: Option[String] = None, ageatstartoftreatment: Option[String] = None, ageunit: Option[String] = None, dosage: Option[String] = None, dosegivenisfor: Option[String] = None, exposureperioddays: Option[String] = None, individualanimaldata: Option[String] = None, recordstatus: Option[String] = None, recoveryperioddays: Option[String] = None, reportnumber: Option[String] = None, substId: Option[String] = None, sex: Option[String] = None, sourcecompany: Option[String] = None, species: Option[String] = None, standardisedadministrationroute: Option[String] = None, standardisedage: Option[String] = None, standardisedsex: Option[String] = None, standardisedspecies: Option[String] = None, standardisedstrain: Option[String] = None, strain: Option[String] = None, studyqualityassessment: Option[String] = None, vehicle: Option[String] = None, yearofstudy: Option[String] = None, studyId: Option[String] = None): InputStudiesRow = {
    administrationroute :: ageatstartoftreatment :: ageunit :: dosage :: dosegivenisfor :: exposureperioddays :: individualanimaldata :: recordstatus :: recoveryperioddays :: reportnumber :: substId :: sex :: sourcecompany :: species :: standardisedadministrationroute :: standardisedage :: standardisedsex :: standardisedspecies :: standardisedstrain :: strain :: studyqualityassessment :: vehicle :: yearofstudy :: studyId :: HNil
  }
  /** GetResult implicit for fetching InputStudiesRow objects using plain SQL queries */
  implicit def GetResultInputStudiesRow(implicit e0: GR[Option[String]]): GR[InputStudiesRow] = GR{
    prs => import prs._
    <<?[String] :: <<?[String] :: <<?[String] :: <<?[String] :: <<?[String] :: <<?[String] :: <<?[String] :: <<?[String] :: <<?[String] :: <<?[String] :: <<?[String] :: <<?[String] :: <<?[String] :: <<?[String] :: <<?[String] :: <<?[String] :: <<?[String] :: <<?[String] :: <<?[String] :: <<?[String] :: <<?[String] :: <<?[String] :: <<?[String] :: <<?[String] :: HNil
  }
  /** Table description of table input_Studies. Objects of this class serve as prototypes for rows in queries. */
  class InputStudies(_tableTag: Tag) extends Table[InputStudiesRow](_tableTag, "input_Studies") {
    def * = administrationroute :: ageatstartoftreatment :: ageunit :: dosage :: dosegivenisfor :: exposureperioddays :: individualanimaldata :: recordstatus :: recoveryperioddays :: reportnumber :: substId :: sex :: sourcecompany :: species :: standardisedadministrationroute :: standardisedage :: standardisedsex :: standardisedspecies :: standardisedstrain :: strain :: studyqualityassessment :: vehicle :: yearofstudy :: studyId :: HNil
    
    /** Database column AdministrationRoute DBType(varchar), Length(255,true), Default(None) */
    val administrationroute: Column[Option[String]] = column[Option[String]]("AdministrationRoute", O.Length(255,varying=true), O.Default(None))
    /** Database column AgeAtStartOfTreatment DBType(varchar), Length(255,true), Default(None) */
    val ageatstartoftreatment: Column[Option[String]] = column[Option[String]]("AgeAtStartOfTreatment", O.Length(255,varying=true), O.Default(None))
    /** Database column AgeUnit DBType(varchar), Length(255,true), Default(None) */
    val ageunit: Column[Option[String]] = column[Option[String]]("AgeUnit", O.Length(255,varying=true), O.Default(None))
    /** Database column Dosage DBType(text), Length(2147483647,true), Default(None) */
    val dosage: Column[Option[String]] = column[Option[String]]("Dosage", O.Length(2147483647,varying=true), O.Default(None))
    /** Database column DoseGivenIsFor DBType(varchar), Length(255,true), Default(None) */
    val dosegivenisfor: Column[Option[String]] = column[Option[String]]("DoseGivenIsFor", O.Length(255,varying=true), O.Default(None))
    /** Database column ExposurePeriodDays DBType(varchar), Length(255,true), Default(None) */
    val exposureperioddays: Column[Option[String]] = column[Option[String]]("ExposurePeriodDays", O.Length(255,varying=true), O.Default(None))
    /** Database column IndividualAnimalData DBType(varchar), Length(255,true), Default(None) */
    val individualanimaldata: Column[Option[String]] = column[Option[String]]("IndividualAnimalData", O.Length(255,varying=true), O.Default(None))
    /** Database column RecordStatus DBType(varchar), Length(255,true), Default(None) */
    val recordstatus: Column[Option[String]] = column[Option[String]]("RecordStatus", O.Length(255,varying=true), O.Default(None))
    /** Database column RecoveryPeriodDays DBType(varchar), Length(255,true), Default(None) */
    val recoveryperioddays: Column[Option[String]] = column[Option[String]]("RecoveryPeriodDays", O.Length(255,varying=true), O.Default(None))
    /** Database column ReportNumber DBType(varchar), Length(255,true), Default(None) */
    val reportnumber: Column[Option[String]] = column[Option[String]]("ReportNumber", O.Length(255,varying=true), O.Default(None))
    /** Database column SUBST_ID DBType(varchar), Length(255,true), Default(None) */
    val substId: Column[Option[String]] = column[Option[String]]("SUBST_ID", O.Length(255,varying=true), O.Default(None))
    /** Database column Sex DBType(varchar), Length(255,true), Default(None) */
    val sex: Column[Option[String]] = column[Option[String]]("Sex", O.Length(255,varying=true), O.Default(None))
    /** Database column SourceCompany DBType(varchar), Length(255,true), Default(None) */
    val sourcecompany: Column[Option[String]] = column[Option[String]]("SourceCompany", O.Length(255,varying=true), O.Default(None))
    /** Database column Species DBType(varchar), Length(255,true), Default(None) */
    val species: Column[Option[String]] = column[Option[String]]("Species", O.Length(255,varying=true), O.Default(None))
    /** Database column StandardisedAdministrationRoute DBType(varchar), Length(255,true), Default(None) */
    val standardisedadministrationroute: Column[Option[String]] = column[Option[String]]("StandardisedAdministrationRoute", O.Length(255,varying=true), O.Default(None))
    /** Database column StandardisedAge DBType(varchar), Length(255,true), Default(None) */
    val standardisedage: Column[Option[String]] = column[Option[String]]("StandardisedAge", O.Length(255,varying=true), O.Default(None))
    /** Database column StandardisedSex DBType(varchar), Length(255,true), Default(None) */
    val standardisedsex: Column[Option[String]] = column[Option[String]]("StandardisedSex", O.Length(255,varying=true), O.Default(None))
    /** Database column StandardisedSpecies DBType(varchar), Length(255,true), Default(None) */
    val standardisedspecies: Column[Option[String]] = column[Option[String]]("StandardisedSpecies", O.Length(255,varying=true), O.Default(None))
    /** Database column StandardisedStrain DBType(varchar), Length(255,true), Default(None) */
    val standardisedstrain: Column[Option[String]] = column[Option[String]]("StandardisedStrain", O.Length(255,varying=true), O.Default(None))
    /** Database column Strain DBType(varchar), Length(255,true), Default(None) */
    val strain: Column[Option[String]] = column[Option[String]]("Strain", O.Length(255,varying=true), O.Default(None))
    /** Database column StudyQualityAssessment DBType(varchar), Length(255,true), Default(None) */
    val studyqualityassessment: Column[Option[String]] = column[Option[String]]("StudyQualityAssessment", O.Length(255,varying=true), O.Default(None))
    /** Database column Vehicle DBType(varchar), Length(255,true), Default(None) */
    val vehicle: Column[Option[String]] = column[Option[String]]("Vehicle", O.Length(255,varying=true), O.Default(None))
    /** Database column YearOfStudy DBType(varchar), Length(255,true), Default(None) */
    val yearofstudy: Column[Option[String]] = column[Option[String]]("YearOfStudy", O.Length(255,varying=true), O.Default(None))
    /** Database column study_id DBType(varchar), Length(255,true), Default(None) */
    val studyId: Column[Option[String]] = column[Option[String]]("study_id", O.Length(255,varying=true), O.Default(None))
  }
  /** Collection-like TableQuery object for table InputStudies */
  lazy val InputStudies = new TableQuery(tag => new InputStudies(tag))
  
  /** Entity class storing rows of table InputSubstances
   *  @param dbdescription Database column DBDescription DBType(varchar), Length(255,true), Default(None)
   *  @param dbname Database column DBName DBType(varchar), Length(255,true), Default(None)
   *  @param dbversion Database column DBVersion DBType(varchar), Length(255,true), Default(None)
   *  @param queryText Database column Query_Text DBType(text), Length(2147483647,true), Default(None)
   *  @param substId Database column SUBST_ID DBType(varchar), Length(255,true), Default(None)
   *  @param softwareversion Database column SoftwareVersion DBType(varchar), Length(255,true), Default(None)
   *  @param cdkTitle Database column cdk_Title DBType(varchar), Length(255,true), Default(None) */
  case class InputSubstancesRow(dbdescription: Option[String] = None, dbname: Option[String] = None, dbversion: Option[String] = None, queryText: Option[String] = None, substId: Option[String] = None, softwareversion: Option[String] = None, cdkTitle: Option[String] = None)
  /** GetResult implicit for fetching InputSubstancesRow objects using plain SQL queries */
  implicit def GetResultInputSubstancesRow(implicit e0: GR[Option[String]]): GR[InputSubstancesRow] = GR{
    prs => import prs._
    InputSubstancesRow.tupled((<<?[String], <<?[String], <<?[String], <<?[String], <<?[String], <<?[String], <<?[String]))
  }
  /** Table description of table input_Substances. Objects of this class serve as prototypes for rows in queries. */
  class InputSubstances(_tableTag: Tag) extends Table[InputSubstancesRow](_tableTag, "input_Substances") {
    def * = (dbdescription, dbname, dbversion, queryText, substId, softwareversion, cdkTitle) <> (InputSubstancesRow.tupled, InputSubstancesRow.unapply)
    
    /** Database column DBDescription DBType(varchar), Length(255,true), Default(None) */
    val dbdescription: Column[Option[String]] = column[Option[String]]("DBDescription", O.Length(255,varying=true), O.Default(None))
    /** Database column DBName DBType(varchar), Length(255,true), Default(None) */
    val dbname: Column[Option[String]] = column[Option[String]]("DBName", O.Length(255,varying=true), O.Default(None))
    /** Database column DBVersion DBType(varchar), Length(255,true), Default(None) */
    val dbversion: Column[Option[String]] = column[Option[String]]("DBVersion", O.Length(255,varying=true), O.Default(None))
    /** Database column Query_Text DBType(text), Length(2147483647,true), Default(None) */
    val queryText: Column[Option[String]] = column[Option[String]]("Query_Text", O.Length(2147483647,varying=true), O.Default(None))
    /** Database column SUBST_ID DBType(varchar), Length(255,true), Default(None) */
    val substId: Column[Option[String]] = column[Option[String]]("SUBST_ID", O.Length(255,varying=true), O.Default(None))
    /** Database column SoftwareVersion DBType(varchar), Length(255,true), Default(None) */
    val softwareversion: Column[Option[String]] = column[Option[String]]("SoftwareVersion", O.Length(255,varying=true), O.Default(None))
    /** Database column cdk_Title DBType(varchar), Length(255,true), Default(None) */
    val cdkTitle: Column[Option[String]] = column[Option[String]]("cdk_Title", O.Length(255,varying=true), O.Default(None))
  }
  /** Collection-like TableQuery object for table InputSubstances */
  lazy val InputSubstances = new TableQuery(tag => new InputSubstances(tag))
  
  /** Entity class storing rows of table InputToxicokinetics
   *  @param dosemgkg Database column DoseMgKg DBType(varchar), Length(255,true), Default(None)
   *  @param substId Database column SUBST_ID DBType(varchar), Length(255,true), Default(None)
   *  @param sd Database column Sd DBType(varchar), Length(255,true), Default(None)
   *  @param sex Database column Sex DBType(varchar), Length(255,true), Default(None)
   *  @param standardisedsex Database column StandardisedSex DBType(varchar), Length(255,true), Default(None)
   *  @param standardisedtoxicokineticsparameter Database column StandardisedToxicokineticsParameter DBType(varchar), Length(255,true), Default(None)
   *  @param substanceid Database column SubstanceId DBType(varchar), Length(255,true), Default(None)
   *  @param timepostdosehrs Database column TimePostDoseHrs DBType(varchar), Length(255,true), Default(None)
   *  @param timepoint Database column Timepoint DBType(varchar), Length(255,true), Default(None)
   *  @param timepointunit Database column TimepointUnit DBType(varchar), Length(255,true), Default(None)
   *  @param toxicokineticparameter Database column ToxicokineticParameter DBType(varchar), Length(255,true), Default(None)
   *  @param unit Database column Unit DBType(varchar), Length(255,true), Default(None)
   *  @param value Database column Value DBType(varchar), Length(255,true), Default(None)
   *  @param studyId Database column study_id DBType(varchar), Length(255,true), Default(None) */
  case class InputToxicokineticsRow(dosemgkg: Option[String] = None, substId: Option[String] = None, sd: Option[String] = None, sex: Option[String] = None, standardisedsex: Option[String] = None, standardisedtoxicokineticsparameter: Option[String] = None, substanceid: Option[String] = None, timepostdosehrs: Option[String] = None, timepoint: Option[String] = None, timepointunit: Option[String] = None, toxicokineticparameter: Option[String] = None, unit: Option[String] = None, value: Option[String] = None, studyId: Option[String] = None)
  /** GetResult implicit for fetching InputToxicokineticsRow objects using plain SQL queries */
  implicit def GetResultInputToxicokineticsRow(implicit e0: GR[Option[String]]): GR[InputToxicokineticsRow] = GR{
    prs => import prs._
    InputToxicokineticsRow.tupled((<<?[String], <<?[String], <<?[String], <<?[String], <<?[String], <<?[String], <<?[String], <<?[String], <<?[String], <<?[String], <<?[String], <<?[String], <<?[String], <<?[String]))
  }
  /** Table description of table input_Toxicokinetics. Objects of this class serve as prototypes for rows in queries. */
  class InputToxicokinetics(_tableTag: Tag) extends Table[InputToxicokineticsRow](_tableTag, "input_Toxicokinetics") {
    def * = (dosemgkg, substId, sd, sex, standardisedsex, standardisedtoxicokineticsparameter, substanceid, timepostdosehrs, timepoint, timepointunit, toxicokineticparameter, unit, value, studyId) <> (InputToxicokineticsRow.tupled, InputToxicokineticsRow.unapply)
    
    /** Database column DoseMgKg DBType(varchar), Length(255,true), Default(None) */
    val dosemgkg: Column[Option[String]] = column[Option[String]]("DoseMgKg", O.Length(255,varying=true), O.Default(None))
    /** Database column SUBST_ID DBType(varchar), Length(255,true), Default(None) */
    val substId: Column[Option[String]] = column[Option[String]]("SUBST_ID", O.Length(255,varying=true), O.Default(None))
    /** Database column Sd DBType(varchar), Length(255,true), Default(None) */
    val sd: Column[Option[String]] = column[Option[String]]("Sd", O.Length(255,varying=true), O.Default(None))
    /** Database column Sex DBType(varchar), Length(255,true), Default(None) */
    val sex: Column[Option[String]] = column[Option[String]]("Sex", O.Length(255,varying=true), O.Default(None))
    /** Database column StandardisedSex DBType(varchar), Length(255,true), Default(None) */
    val standardisedsex: Column[Option[String]] = column[Option[String]]("StandardisedSex", O.Length(255,varying=true), O.Default(None))
    /** Database column StandardisedToxicokineticsParameter DBType(varchar), Length(255,true), Default(None) */
    val standardisedtoxicokineticsparameter: Column[Option[String]] = column[Option[String]]("StandardisedToxicokineticsParameter", O.Length(255,varying=true), O.Default(None))
    /** Database column SubstanceId DBType(varchar), Length(255,true), Default(None) */
    val substanceid: Column[Option[String]] = column[Option[String]]("SubstanceId", O.Length(255,varying=true), O.Default(None))
    /** Database column TimePostDoseHrs DBType(varchar), Length(255,true), Default(None) */
    val timepostdosehrs: Column[Option[String]] = column[Option[String]]("TimePostDoseHrs", O.Length(255,varying=true), O.Default(None))
    /** Database column Timepoint DBType(varchar), Length(255,true), Default(None) */
    val timepoint: Column[Option[String]] = column[Option[String]]("Timepoint", O.Length(255,varying=true), O.Default(None))
    /** Database column TimepointUnit DBType(varchar), Length(255,true), Default(None) */
    val timepointunit: Column[Option[String]] = column[Option[String]]("TimepointUnit", O.Length(255,varying=true), O.Default(None))
    /** Database column ToxicokineticParameter DBType(varchar), Length(255,true), Default(None) */
    val toxicokineticparameter: Column[Option[String]] = column[Option[String]]("ToxicokineticParameter", O.Length(255,varying=true), O.Default(None))
    /** Database column Unit DBType(varchar), Length(255,true), Default(None) */
    val unit: Column[Option[String]] = column[Option[String]]("Unit", O.Length(255,varying=true), O.Default(None))
    /** Database column Value DBType(varchar), Length(255,true), Default(None) */
    val value: Column[Option[String]] = column[Option[String]]("Value", O.Length(255,varying=true), O.Default(None))
    /** Database column study_id DBType(varchar), Length(255,true), Default(None) */
    val studyId: Column[Option[String]] = column[Option[String]]("study_id", O.Length(255,varying=true), O.Default(None))
    
    /** Index over (substanceid) (database name SubstanceId) */
    val index1 = index("SubstanceId", substanceid)
  }
  /** Collection-like TableQuery object for table InputToxicokinetics */
  lazy val InputToxicokinetics = new TableQuery(tag => new InputToxicokinetics(tag))
  
  /** Entity class storing rows of table InputUrinalysisfindings
   *  @param averagefoldchange Database column AverageFoldChange DBType(varchar), Length(255,true), Default(None)
   *  @param averagevalue Database column AverageValue DBType(varchar), Length(255,true), Default(None)
   *  @param dosemgkg Database column DoseMgKg DBType(varchar), Length(255,true), Default(None)
   *  @param finding Database column Finding DBType(varchar), Length(255,true), Default(None)
   *  @param relevance Database column Relevance DBType(varchar), Length(255,true), Default(None)
   *  @param substId Database column SUBST_ID DBType(varchar), Length(255,true), Default(None)
   *  @param sd Database column Sd DBType(varchar), Length(255,true), Default(None)
   *  @param sex Database column Sex DBType(varchar), Length(255,true), Default(None)
   *  @param standardisedparameter Database column StandardisedParameter DBType(varchar), Length(255,true), Default(None)
   *  @param standardisedsex Database column StandardisedSex DBType(varchar), Length(255,true), Default(None)
   *  @param timepoint Database column Timepoint DBType(varchar), Length(255,true), Default(None)
   *  @param timepointunit Database column TimepointUnit DBType(varchar), Length(255,true), Default(None)
   *  @param unit Database column Unit DBType(varchar), Length(255,true), Default(None)
   *  @param urinalysisparameter Database column UrinalysisParameter DBType(varchar), Length(255,true), Default(None)
   *  @param studyId Database column study_id DBType(varchar), Length(255,true), Default(None) */
  case class InputUrinalysisfindingsRow(averagefoldchange: Option[String] = None, averagevalue: Option[String] = None, dosemgkg: Option[String] = None, finding: Option[String] = None, relevance: Option[String] = None, substId: Option[String] = None, sd: Option[String] = None, sex: Option[String] = None, standardisedparameter: Option[String] = None, standardisedsex: Option[String] = None, timepoint: Option[String] = None, timepointunit: Option[String] = None, unit: Option[String] = None, urinalysisparameter: Option[String] = None, studyId: Option[String] = None)
  /** GetResult implicit for fetching InputUrinalysisfindingsRow objects using plain SQL queries */
  implicit def GetResultInputUrinalysisfindingsRow(implicit e0: GR[Option[String]]): GR[InputUrinalysisfindingsRow] = GR{
    prs => import prs._
    InputUrinalysisfindingsRow.tupled((<<?[String], <<?[String], <<?[String], <<?[String], <<?[String], <<?[String], <<?[String], <<?[String], <<?[String], <<?[String], <<?[String], <<?[String], <<?[String], <<?[String], <<?[String]))
  }
  /** Table description of table input_UrinalysisFindings. Objects of this class serve as prototypes for rows in queries. */
  class InputUrinalysisfindings(_tableTag: Tag) extends Table[InputUrinalysisfindingsRow](_tableTag, "input_UrinalysisFindings") {
    def * = (averagefoldchange, averagevalue, dosemgkg, finding, relevance, substId, sd, sex, standardisedparameter, standardisedsex, timepoint, timepointunit, unit, urinalysisparameter, studyId) <> (InputUrinalysisfindingsRow.tupled, InputUrinalysisfindingsRow.unapply)
    
    /** Database column AverageFoldChange DBType(varchar), Length(255,true), Default(None) */
    val averagefoldchange: Column[Option[String]] = column[Option[String]]("AverageFoldChange", O.Length(255,varying=true), O.Default(None))
    /** Database column AverageValue DBType(varchar), Length(255,true), Default(None) */
    val averagevalue: Column[Option[String]] = column[Option[String]]("AverageValue", O.Length(255,varying=true), O.Default(None))
    /** Database column DoseMgKg DBType(varchar), Length(255,true), Default(None) */
    val dosemgkg: Column[Option[String]] = column[Option[String]]("DoseMgKg", O.Length(255,varying=true), O.Default(None))
    /** Database column Finding DBType(varchar), Length(255,true), Default(None) */
    val finding: Column[Option[String]] = column[Option[String]]("Finding", O.Length(255,varying=true), O.Default(None))
    /** Database column Relevance DBType(varchar), Length(255,true), Default(None) */
    val relevance: Column[Option[String]] = column[Option[String]]("Relevance", O.Length(255,varying=true), O.Default(None))
    /** Database column SUBST_ID DBType(varchar), Length(255,true), Default(None) */
    val substId: Column[Option[String]] = column[Option[String]]("SUBST_ID", O.Length(255,varying=true), O.Default(None))
    /** Database column Sd DBType(varchar), Length(255,true), Default(None) */
    val sd: Column[Option[String]] = column[Option[String]]("Sd", O.Length(255,varying=true), O.Default(None))
    /** Database column Sex DBType(varchar), Length(255,true), Default(None) */
    val sex: Column[Option[String]] = column[Option[String]]("Sex", O.Length(255,varying=true), O.Default(None))
    /** Database column StandardisedParameter DBType(varchar), Length(255,true), Default(None) */
    val standardisedparameter: Column[Option[String]] = column[Option[String]]("StandardisedParameter", O.Length(255,varying=true), O.Default(None))
    /** Database column StandardisedSex DBType(varchar), Length(255,true), Default(None) */
    val standardisedsex: Column[Option[String]] = column[Option[String]]("StandardisedSex", O.Length(255,varying=true), O.Default(None))
    /** Database column Timepoint DBType(varchar), Length(255,true), Default(None) */
    val timepoint: Column[Option[String]] = column[Option[String]]("Timepoint", O.Length(255,varying=true), O.Default(None))
    /** Database column TimepointUnit DBType(varchar), Length(255,true), Default(None) */
    val timepointunit: Column[Option[String]] = column[Option[String]]("TimepointUnit", O.Length(255,varying=true), O.Default(None))
    /** Database column Unit DBType(varchar), Length(255,true), Default(None) */
    val unit: Column[Option[String]] = column[Option[String]]("Unit", O.Length(255,varying=true), O.Default(None))
    /** Database column UrinalysisParameter DBType(varchar), Length(255,true), Default(None) */
    val urinalysisparameter: Column[Option[String]] = column[Option[String]]("UrinalysisParameter", O.Length(255,varying=true), O.Default(None))
    /** Database column study_id DBType(varchar), Length(255,true), Default(None) */
    val studyId: Column[Option[String]] = column[Option[String]]("study_id", O.Length(255,varying=true), O.Default(None))
  }
  /** Collection-like TableQuery object for table InputUrinalysisfindings */
  lazy val InputUrinalysisfindings = new TableQuery(tag => new InputUrinalysisfindings(tag))
  
  /** Entity class storing rows of table OntoTermsRelations
   *  @param childId Database column child_id DBType(varchar), Length(255,true), Default(None)
   *  @param childTerm Database column child_term DBType(varchar), Length(255,true), Default(None)
   *  @param parentId Database column parent_id DBType(varchar), Length(255,true), Default(None)
   *  @param relationship Database column RELATIONSHIP DBType(varchar), Length(255,true), Default(None)
   *  @param parentTerm Database column parent_term DBType(varchar), Length(255,true), Default(None) */
  case class OntoTermsRelationsRow(childId: Option[String] = None, childTerm: Option[String] = None, parentId: Option[String] = None, relationship: Option[String] = None, parentTerm: Option[String] = None)
  /** GetResult implicit for fetching OntoTermsRelationsRow objects using plain SQL queries */
  implicit def GetResultOntoTermsRelationsRow(implicit e0: GR[Option[String]]): GR[OntoTermsRelationsRow] = GR{
    prs => import prs._
    OntoTermsRelationsRow.tupled((<<?[String], <<?[String], <<?[String], <<?[String], <<?[String]))
  }
  /** Table description of table onto_terms_relations. Objects of this class serve as prototypes for rows in queries. */
  class OntoTermsRelations(_tableTag: Tag) extends Table[OntoTermsRelationsRow](_tableTag, "onto_terms_relations") {
    def * = (childId, childTerm, parentId, relationship, parentTerm) <> (OntoTermsRelationsRow.tupled, OntoTermsRelationsRow.unapply)
    
    /** Database column child_id DBType(varchar), Length(255,true), Default(None) */
    val childId: Column[Option[String]] = column[Option[String]]("child_id", O.Length(255,varying=true), O.Default(None))
    /** Database column child_term DBType(varchar), Length(255,true), Default(None) */
    val childTerm: Column[Option[String]] = column[Option[String]]("child_term", O.Length(255,varying=true), O.Default(None))
    /** Database column parent_id DBType(varchar), Length(255,true), Default(None) */
    val parentId: Column[Option[String]] = column[Option[String]]("parent_id", O.Length(255,varying=true), O.Default(None))
    /** Database column RELATIONSHIP DBType(varchar), Length(255,true), Default(None) */
    val relationship: Column[Option[String]] = column[Option[String]]("RELATIONSHIP", O.Length(255,varying=true), O.Default(None))
    /** Database column parent_term DBType(varchar), Length(255,true), Default(None) */
    val parentTerm: Column[Option[String]] = column[Option[String]]("parent_term", O.Length(255,varying=true), O.Default(None))
  }
  /** Collection-like TableQuery object for table OntoTermsRelations */
  lazy val OntoTermsRelations = new TableQuery(tag => new OntoTermsRelations(tag))
  
  /** Entity class storing rows of table Organweights
   *  @param substId Database column subst_id DBType(varchar), Length(100,true), Default(None)
   *  @param studyId Database column study_id DBType(varchar), Length(100,true), Default(None)
   *  @param averagefoldchange Database column averagefoldchange DBType(numeric), Default(None)
   *  @param averageweight Database column averageweight DBType(numeric), Default(None)
   *  @param dosemgkg Database column dosemgkg DBType(numeric), Default(None)
   *  @param finding Database column finding DBType(varchar), Length(100,true), Default(None)
   *  @param organweighted Database column organweighted DBType(varchar), Length(100,true), Default(None)
   *  @param relevance Database column relevance DBType(varchar), Length(100,true), Default(None)
   *  @param sd Database column sd DBType(numeric), Default(None)
   *  @param sex Database column sex DBType(varchar), Length(100,true), Default(None)
   *  @param standardisedorgan Database column standardisedorgan DBType(varchar), Length(100,true), Default(None)
   *  @param normalisedOrgan Database column normalised_organ DBType(varchar), Length(100,true), Default(None)
   *  @param standardisedsex Database column standardisedsex DBType(varchar), Length(100,true), Default(None)
   *  @param normalisedSex Database column normalised_sex DBType(varchar), Length(100,true), Default(None)
   *  @param timepoint Database column timepoint DBType(int4), Default(None)
   *  @param timepointunit Database column timepointunit DBType(varchar), Length(100,true), Default(None)
   *  @param unit Database column unit DBType(varchar), Length(1000,true), Default(None) */
  case class OrganweightsRow(substId: Option[String] = None, studyId: Option[String] = None, averagefoldchange: Option[scala.math.BigDecimal] = None, averageweight: Option[scala.math.BigDecimal] = None, dosemgkg: Option[scala.math.BigDecimal] = None, finding: Option[String] = None, organweighted: Option[String] = None, relevance: Option[String] = None, sd: Option[scala.math.BigDecimal] = None, sex: Option[String] = None, standardisedorgan: Option[String] = None, normalisedOrgan: Option[String] = None, standardisedsex: Option[String] = None, normalisedSex: Option[String] = None, timepoint: Option[Int] = None, timepointunit: Option[String] = None, unit: Option[String] = None)
  /** GetResult implicit for fetching OrganweightsRow objects using plain SQL queries */
  implicit def GetResultOrganweightsRow(implicit e0: GR[Option[String]], e1: GR[Option[scala.math.BigDecimal]], e2: GR[Option[Int]]): GR[OrganweightsRow] = GR{
    prs => import prs._
    OrganweightsRow.tupled((<<?[String], <<?[String], <<?[scala.math.BigDecimal], <<?[scala.math.BigDecimal], <<?[scala.math.BigDecimal], <<?[String], <<?[String], <<?[String], <<?[scala.math.BigDecimal], <<?[String], <<?[String], <<?[String], <<?[String], <<?[String], <<?[Int], <<?[String], <<?[String]))
  }
  /** Table description of table organweights. Objects of this class serve as prototypes for rows in queries. */
  class Organweights(_tableTag: Tag) extends Table[OrganweightsRow](_tableTag, "organweights") {
    def * = (substId, studyId, averagefoldchange, averageweight, dosemgkg, finding, organweighted, relevance, sd, sex, standardisedorgan, normalisedOrgan, standardisedsex, normalisedSex, timepoint, timepointunit, unit) <> (OrganweightsRow.tupled, OrganweightsRow.unapply)
    
    /** Database column subst_id DBType(varchar), Length(100,true), Default(None) */
    val substId: Column[Option[String]] = column[Option[String]]("subst_id", O.Length(100,varying=true), O.Default(None))
    /** Database column study_id DBType(varchar), Length(100,true), Default(None) */
    val studyId: Column[Option[String]] = column[Option[String]]("study_id", O.Length(100,varying=true), O.Default(None))
    /** Database column averagefoldchange DBType(numeric), Default(None) */
    val averagefoldchange: Column[Option[scala.math.BigDecimal]] = column[Option[scala.math.BigDecimal]]("averagefoldchange", O.Default(None))
    /** Database column averageweight DBType(numeric), Default(None) */
    val averageweight: Column[Option[scala.math.BigDecimal]] = column[Option[scala.math.BigDecimal]]("averageweight", O.Default(None))
    /** Database column dosemgkg DBType(numeric), Default(None) */
    val dosemgkg: Column[Option[scala.math.BigDecimal]] = column[Option[scala.math.BigDecimal]]("dosemgkg", O.Default(None))
    /** Database column finding DBType(varchar), Length(100,true), Default(None) */
    val finding: Column[Option[String]] = column[Option[String]]("finding", O.Length(100,varying=true), O.Default(None))
    /** Database column organweighted DBType(varchar), Length(100,true), Default(None) */
    val organweighted: Column[Option[String]] = column[Option[String]]("organweighted", O.Length(100,varying=true), O.Default(None))
    /** Database column relevance DBType(varchar), Length(100,true), Default(None) */
    val relevance: Column[Option[String]] = column[Option[String]]("relevance", O.Length(100,varying=true), O.Default(None))
    /** Database column sd DBType(numeric), Default(None) */
    val sd: Column[Option[scala.math.BigDecimal]] = column[Option[scala.math.BigDecimal]]("sd", O.Default(None))
    /** Database column sex DBType(varchar), Length(100,true), Default(None) */
    val sex: Column[Option[String]] = column[Option[String]]("sex", O.Length(100,varying=true), O.Default(None))
    /** Database column standardisedorgan DBType(varchar), Length(100,true), Default(None) */
    val standardisedorgan: Column[Option[String]] = column[Option[String]]("standardisedorgan", O.Length(100,varying=true), O.Default(None))
    /** Database column normalised_organ DBType(varchar), Length(100,true), Default(None) */
    val normalisedOrgan: Column[Option[String]] = column[Option[String]]("normalised_organ", O.Length(100,varying=true), O.Default(None))
    /** Database column standardisedsex DBType(varchar), Length(100,true), Default(None) */
    val standardisedsex: Column[Option[String]] = column[Option[String]]("standardisedsex", O.Length(100,varying=true), O.Default(None))
    /** Database column normalised_sex DBType(varchar), Length(100,true), Default(None) */
    val normalisedSex: Column[Option[String]] = column[Option[String]]("normalised_sex", O.Length(100,varying=true), O.Default(None))
    /** Database column timepoint DBType(int4), Default(None) */
    val timepoint: Column[Option[Int]] = column[Option[Int]]("timepoint", O.Default(None))
    /** Database column timepointunit DBType(varchar), Length(100,true), Default(None) */
    val timepointunit: Column[Option[String]] = column[Option[String]]("timepointunit", O.Length(100,varying=true), O.Default(None))
    /** Database column unit DBType(varchar), Length(1000,true), Default(None) */
    val unit: Column[Option[String]] = column[Option[String]]("unit", O.Length(1000,varying=true), O.Default(None))
    
    /** Foreign key referencing Study (database name organweights_subst_id_fkey) */
    lazy val studyFk = foreignKey("organweights_subst_id_fkey", (substId, studyId), Study)(r => (r.substId, r.studyId), onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.Cascade)
  }
  /** Collection-like TableQuery object for table Organweights */
  lazy val Organweights = new TableQuery(tag => new Organweights(tag))
  
  /** Row type of table Study */
  type StudyRow = HCons[Option[String],HCons[Option[String],HCons[Option[String],HCons[Option[String],HCons[Option[String],HCons[Option[String],HCons[Option[String],HCons[Option[String],HCons[Option[String],HCons[Option[String],HCons[Option[String],HCons[Option[String],HCons[Option[String],HCons[Option[String],HCons[Option[scala.math.BigDecimal],HCons[Option[String],HCons[Option[String],HCons[Option[String],HCons[Option[String],HCons[Option[Int],HCons[Option[String],HCons[Option[String],HCons[Option[scala.math.BigDecimal],HCons[Option[Int],HCons[Option[String],HNil]]]]]]]]]]]]]]]]]]]]]]]]]
  /** Constructor for StudyRow providing default values if available in the database schema. */
  def StudyRow(substId: Option[String] = None, studyId: Option[String] = None, strain: Option[String] = None, normalisedStrain: Option[String] = None, sex: Option[String] = None, normalisedSex: Option[String] = None, administrationRoute: Option[String] = None, normalisedAdministrationRoute: Option[String] = None, species: Option[String] = None, normalisedSpecies: Option[String] = None, standarisedSpecies: Option[String] = None, reportNumber: Option[String] = None, recordStatus: Option[String] = None, yearOfStudy: Option[String] = None, standarisedAge: Option[scala.math.BigDecimal] = None, ageUnit: Option[String] = None, studyQualityAssesment: Option[String] = None, dosage: Option[String] = None, sourceCompany: Option[String] = None, recoveryPeriodDays: Option[Int] = None, individualAnimalData: Option[String] = None, vehicle: Option[String] = None, ageOfStartOfTreatment: Option[scala.math.BigDecimal] = None, exposurePeriodDays: Option[Int] = None, source: Option[String] = None): StudyRow = {
    substId :: studyId :: strain :: normalisedStrain :: sex :: normalisedSex :: administrationRoute :: normalisedAdministrationRoute :: species :: normalisedSpecies :: standarisedSpecies :: reportNumber :: recordStatus :: yearOfStudy :: standarisedAge :: ageUnit :: studyQualityAssesment :: dosage :: sourceCompany :: recoveryPeriodDays :: individualAnimalData :: vehicle :: ageOfStartOfTreatment :: exposurePeriodDays :: source :: HNil
  }
  /** GetResult implicit for fetching StudyRow objects using plain SQL queries */
  implicit def GetResultStudyRow(implicit e0: GR[Option[String]], e1: GR[Option[scala.math.BigDecimal]], e2: GR[Option[Int]]): GR[StudyRow] = GR{
    prs => import prs._
    <<?[String] :: <<?[String] :: <<?[String] :: <<?[String] :: <<?[String] :: <<?[String] :: <<?[String] :: <<?[String] :: <<?[String] :: <<?[String] :: <<?[String] :: <<?[String] :: <<?[String] :: <<?[String] :: <<?[scala.math.BigDecimal] :: <<?[String] :: <<?[String] :: <<?[String] :: <<?[String] :: <<?[Int] :: <<?[String] :: <<?[String] :: <<?[scala.math.BigDecimal] :: <<?[Int] :: <<?[String] :: HNil
  }
  /** Table description of table study. Objects of this class serve as prototypes for rows in queries. */
  class Study(_tableTag: Tag) extends Table[StudyRow](_tableTag, "study") {
    def * = substId :: studyId :: strain :: normalisedStrain :: sex :: normalisedSex :: administrationRoute :: normalisedAdministrationRoute :: species :: normalisedSpecies :: standarisedSpecies :: reportNumber :: recordStatus :: yearOfStudy :: standarisedAge :: ageUnit :: studyQualityAssesment :: dosage :: sourceCompany :: recoveryPeriodDays :: individualAnimalData :: vehicle :: ageOfStartOfTreatment :: exposurePeriodDays :: source :: HNil
    
    /** Database column subst_id DBType(varchar), Length(100,true), Default(None) */
    val substId: Column[Option[String]] = column[Option[String]]("subst_id", O.Length(100,varying=true), O.Default(None))
    /** Database column study_id DBType(varchar), Length(100,true), Default(None) */
    val studyId: Column[Option[String]] = column[Option[String]]("study_id", O.Length(100,varying=true), O.Default(None))
    /** Database column strain DBType(varchar), Length(1000,true), Default(None) */
    val strain: Column[Option[String]] = column[Option[String]]("strain", O.Length(1000,varying=true), O.Default(None))
    /** Database column normalised_strain DBType(varchar), Length(1000,true), Default(None) */
    val normalisedStrain: Column[Option[String]] = column[Option[String]]("normalised_strain", O.Length(1000,varying=true), O.Default(None))
    /** Database column sex DBType(varchar), Length(100,true), Default(None) */
    val sex: Column[Option[String]] = column[Option[String]]("sex", O.Length(100,varying=true), O.Default(None))
    /** Database column normalised_sex DBType(varchar), Length(100,true), Default(None) */
    val normalisedSex: Column[Option[String]] = column[Option[String]]("normalised_sex", O.Length(100,varying=true), O.Default(None))
    /** Database column administration_route DBType(varchar), Length(100,true), Default(None) */
    val administrationRoute: Column[Option[String]] = column[Option[String]]("administration_route", O.Length(100,varying=true), O.Default(None))
    /** Database column normalised_administration_route DBType(varchar), Length(100,true), Default(None) */
    val normalisedAdministrationRoute: Column[Option[String]] = column[Option[String]]("normalised_administration_route", O.Length(100,varying=true), O.Default(None))
    /** Database column species DBType(varchar), Length(100,true), Default(None) */
    val species: Column[Option[String]] = column[Option[String]]("species", O.Length(100,varying=true), O.Default(None))
    /** Database column normalised_species DBType(varchar), Length(100,true), Default(None) */
    val normalisedSpecies: Column[Option[String]] = column[Option[String]]("normalised_species", O.Length(100,varying=true), O.Default(None))
    /** Database column standarised_species DBType(varchar), Length(100,true), Default(None) */
    val standarisedSpecies: Column[Option[String]] = column[Option[String]]("standarised_species", O.Length(100,varying=true), O.Default(None))
    /** Database column report_number DBType(varchar), Length(100,true), Default(None) */
    val reportNumber: Column[Option[String]] = column[Option[String]]("report_number", O.Length(100,varying=true), O.Default(None))
    /** Database column record_status DBType(varchar), Length(100,true), Default(None) */
    val recordStatus: Column[Option[String]] = column[Option[String]]("record_status", O.Length(100,varying=true), O.Default(None))
    /** Database column year_of_study DBType(varchar), Length(100,true), Default(None) */
    val yearOfStudy: Column[Option[String]] = column[Option[String]]("year_of_study", O.Length(100,varying=true), O.Default(None))
    /** Database column standarised_age DBType(numeric), Default(None) */
    val standarisedAge: Column[Option[scala.math.BigDecimal]] = column[Option[scala.math.BigDecimal]]("standarised_age", O.Default(None))
    /** Database column age_unit DBType(varchar), Length(100,true), Default(None) */
    val ageUnit: Column[Option[String]] = column[Option[String]]("age_unit", O.Length(100,varying=true), O.Default(None))
    /** Database column study_quality_assesment DBType(varchar), Length(100,true), Default(None) */
    val studyQualityAssesment: Column[Option[String]] = column[Option[String]]("study_quality_assesment", O.Length(100,varying=true), O.Default(None))
    /** Database column dosage DBType(varchar), Length(1000,true), Default(None) */
    val dosage: Column[Option[String]] = column[Option[String]]("dosage", O.Length(1000,varying=true), O.Default(None))
    /** Database column source_company DBType(varchar), Length(100,true), Default(None) */
    val sourceCompany: Column[Option[String]] = column[Option[String]]("source_company", O.Length(100,varying=true), O.Default(None))
    /** Database column recovery_period_days DBType(int4), Default(None) */
    val recoveryPeriodDays: Column[Option[Int]] = column[Option[Int]]("recovery_period_days", O.Default(None))
    /** Database column individual_animal_data DBType(varchar), Length(100,true), Default(None) */
    val individualAnimalData: Column[Option[String]] = column[Option[String]]("individual_animal_data", O.Length(100,varying=true), O.Default(None))
    /** Database column vehicle DBType(varchar), Length(1000,true), Default(None) */
    val vehicle: Column[Option[String]] = column[Option[String]]("vehicle", O.Length(1000,varying=true), O.Default(None))
    /** Database column age_of_start_of_treatment DBType(numeric), Default(None) */
    val ageOfStartOfTreatment: Column[Option[scala.math.BigDecimal]] = column[Option[scala.math.BigDecimal]]("age_of_start_of_treatment", O.Default(None))
    /** Database column exposure_period_days DBType(int4), Default(None) */
    val exposurePeriodDays: Column[Option[Int]] = column[Option[Int]]("exposure_period_days", O.Default(None))
    /** Database column source DBType(varchar), Length(100,true), Default(None) */
    val source: Column[Option[String]] = column[Option[String]]("source", O.Length(100,varying=true), O.Default(None))
    
    /** Foreign key referencing Compounds (database name study_subst_id_fkey) */
    lazy val compoundsFk = foreignKey("study_subst_id_fkey", substId :: HNil, Compounds)(r => r.substId :: HNil, onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.Cascade)
    
    /** Uniqueness Index over (substId,studyId) (database name study_subst_id_study_id_key) */
    val index1 = index("study_subst_id_study_id_key", substId :: studyId :: HNil, unique=true)
  }
  /** Collection-like TableQuery object for table Study */
  lazy val Study = new TableQuery(tag => new Study(tag))
  
  /** Entity class storing rows of table StudyRangeTime
   *  @param studyId Database column study_id DBType(varchar), Length(100,true), Default(None)
   *  @param minTimepoint Database column min_timepoint DBType(float8), Default(None)
   *  @param maxTimepoint Database column max_timepoint DBType(float8), Default(None) */
  case class StudyRangeTimeRow(studyId: Option[String] = None, minTimepoint: Option[Double] = None, maxTimepoint: Option[Double] = None)
  /** GetResult implicit for fetching StudyRangeTimeRow objects using plain SQL queries */
  implicit def GetResultStudyRangeTimeRow(implicit e0: GR[Option[String]], e1: GR[Option[Double]]): GR[StudyRangeTimeRow] = GR{
    prs => import prs._
    StudyRangeTimeRow.tupled((<<?[String], <<?[Double], <<?[Double]))
  }
  /** Table description of table study_range_time. Objects of this class serve as prototypes for rows in queries. */
  class StudyRangeTime(_tableTag: Tag) extends Table[StudyRangeTimeRow](_tableTag, "study_range_time") {
    def * = (studyId, minTimepoint, maxTimepoint) <> (StudyRangeTimeRow.tupled, StudyRangeTimeRow.unapply)
    
    /** Database column study_id DBType(varchar), Length(100,true), Default(None) */
    val studyId: Column[Option[String]] = column[Option[String]]("study_id", O.Length(100,varying=true), O.Default(None))
    /** Database column min_timepoint DBType(float8), Default(None) */
    val minTimepoint: Column[Option[Double]] = column[Option[Double]]("min_timepoint", O.Default(None))
    /** Database column max_timepoint DBType(float8), Default(None) */
    val maxTimepoint: Column[Option[Double]] = column[Option[Double]]("max_timepoint", O.Default(None))
  }
  /** Collection-like TableQuery object for table StudyRangeTime */
  lazy val StudyRangeTime = new TableQuery(tag => new StudyRangeTime(tag))
  
  /** Entity class storing rows of table TimepointUnitsMapping
   *  @param timepointunit Database column timepointunit DBType(varchar), Length(1000,true), Default(None)
   *  @param timepointunitDays Database column timepointunit_days DBType(float8), Default(None) */
  case class TimepointUnitsMappingRow(timepointunit: Option[String] = None, timepointunitDays: Option[Double] = None)
  /** GetResult implicit for fetching TimepointUnitsMappingRow objects using plain SQL queries */
  implicit def GetResultTimepointUnitsMappingRow(implicit e0: GR[Option[String]], e1: GR[Option[Double]]): GR[TimepointUnitsMappingRow] = GR{
    prs => import prs._
    TimepointUnitsMappingRow.tupled((<<?[String], <<?[Double]))
  }
  /** Table description of table timepoint_units_mapping. Objects of this class serve as prototypes for rows in queries. */
  class TimepointUnitsMapping(_tableTag: Tag) extends Table[TimepointUnitsMappingRow](_tableTag, "timepoint_units_mapping") {
    def * = (timepointunit, timepointunitDays) <> (TimepointUnitsMappingRow.tupled, TimepointUnitsMappingRow.unapply)
    
    /** Database column timepointunit DBType(varchar), Length(1000,true), Default(None) */
    val timepointunit: Column[Option[String]] = column[Option[String]]("timepointunit", O.Length(1000,varying=true), O.Default(None))
    /** Database column timepointunit_days DBType(float8), Default(None) */
    val timepointunitDays: Column[Option[Double]] = column[Option[Double]]("timepointunit_days", O.Default(None))
  }
  /** Collection-like TableQuery object for table TimepointUnitsMapping */
  lazy val TimepointUnitsMapping = new TableQuery(tag => new TimepointUnitsMapping(tag))
  
  /** Entity class storing rows of table Urianalysisfindings
   *  @param substId Database column subst_id DBType(varchar), Length(100,true), Default(None)
   *  @param studyId Database column study_id DBType(varchar), Length(100,true), Default(None)
   *  @param averagefoldchange Database column averagefoldchange DBType(numeric), Default(None)
   *  @param averagevalue Database column averagevalue DBType(numeric), Default(None)
   *  @param dosemgkg Database column dosemgkg DBType(numeric), Default(None)
   *  @param finding Database column finding DBType(varchar), Length(100,true), Default(None)
   *  @param relevance Database column relevance DBType(varchar), Length(100,true), Default(None)
   *  @param sd Database column sd DBType(numeric), Default(None)
   *  @param sex Database column sex DBType(varchar), Length(100,true), Default(None)
   *  @param normalisedSex Database column normalised_sex DBType(varchar), Length(100,true), Default(None)
   *  @param standardisedsex Database column standardisedsex DBType(varchar), Length(100,true), Default(None)
   *  @param urinalysisparameter Database column urinalysisparameter DBType(varchar), Length(500,true), Default(None)
   *  @param standardisedparameter Database column standardisedparameter DBType(varchar), Length(500,true), Default(None)
   *  @param normalisedUrinalysisparameter Database column normalised_urinalysisparameter DBType(varchar), Length(500,true), Default(None)
   *  @param timepoint Database column timepoint DBType(numeric), Default(None)
   *  @param timepointunit Database column timepointunit DBType(varchar), Length(100,true), Default(None)
   *  @param unit Database column unit DBType(varchar), Length(1000,true), Default(None) */
  case class UrianalysisfindingsRow(substId: Option[String] = None, studyId: Option[String] = None, averagefoldchange: Option[scala.math.BigDecimal] = None, averagevalue: Option[scala.math.BigDecimal] = None, dosemgkg: Option[scala.math.BigDecimal] = None, finding: Option[String] = None, relevance: Option[String] = None, sd: Option[scala.math.BigDecimal] = None, sex: Option[String] = None, normalisedSex: Option[String] = None, standardisedsex: Option[String] = None, urinalysisparameter: Option[String] = None, standardisedparameter: Option[String] = None, normalisedUrinalysisparameter: Option[String] = None, timepoint: Option[scala.math.BigDecimal] = None, timepointunit: Option[String] = None, unit: Option[String] = None)
  /** GetResult implicit for fetching UrianalysisfindingsRow objects using plain SQL queries */
  implicit def GetResultUrianalysisfindingsRow(implicit e0: GR[Option[String]], e1: GR[Option[scala.math.BigDecimal]]): GR[UrianalysisfindingsRow] = GR{
    prs => import prs._
    UrianalysisfindingsRow.tupled((<<?[String], <<?[String], <<?[scala.math.BigDecimal], <<?[scala.math.BigDecimal], <<?[scala.math.BigDecimal], <<?[String], <<?[String], <<?[scala.math.BigDecimal], <<?[String], <<?[String], <<?[String], <<?[String], <<?[String], <<?[String], <<?[scala.math.BigDecimal], <<?[String], <<?[String]))
  }
  /** Table description of table urianalysisfindings. Objects of this class serve as prototypes for rows in queries. */
  class Urianalysisfindings(_tableTag: Tag) extends Table[UrianalysisfindingsRow](_tableTag, "urianalysisfindings") {
    def * = (substId, studyId, averagefoldchange, averagevalue, dosemgkg, finding, relevance, sd, sex, normalisedSex, standardisedsex, urinalysisparameter, standardisedparameter, normalisedUrinalysisparameter, timepoint, timepointunit, unit) <> (UrianalysisfindingsRow.tupled, UrianalysisfindingsRow.unapply)
    
    /** Database column subst_id DBType(varchar), Length(100,true), Default(None) */
    val substId: Column[Option[String]] = column[Option[String]]("subst_id", O.Length(100,varying=true), O.Default(None))
    /** Database column study_id DBType(varchar), Length(100,true), Default(None) */
    val studyId: Column[Option[String]] = column[Option[String]]("study_id", O.Length(100,varying=true), O.Default(None))
    /** Database column averagefoldchange DBType(numeric), Default(None) */
    val averagefoldchange: Column[Option[scala.math.BigDecimal]] = column[Option[scala.math.BigDecimal]]("averagefoldchange", O.Default(None))
    /** Database column averagevalue DBType(numeric), Default(None) */
    val averagevalue: Column[Option[scala.math.BigDecimal]] = column[Option[scala.math.BigDecimal]]("averagevalue", O.Default(None))
    /** Database column dosemgkg DBType(numeric), Default(None) */
    val dosemgkg: Column[Option[scala.math.BigDecimal]] = column[Option[scala.math.BigDecimal]]("dosemgkg", O.Default(None))
    /** Database column finding DBType(varchar), Length(100,true), Default(None) */
    val finding: Column[Option[String]] = column[Option[String]]("finding", O.Length(100,varying=true), O.Default(None))
    /** Database column relevance DBType(varchar), Length(100,true), Default(None) */
    val relevance: Column[Option[String]] = column[Option[String]]("relevance", O.Length(100,varying=true), O.Default(None))
    /** Database column sd DBType(numeric), Default(None) */
    val sd: Column[Option[scala.math.BigDecimal]] = column[Option[scala.math.BigDecimal]]("sd", O.Default(None))
    /** Database column sex DBType(varchar), Length(100,true), Default(None) */
    val sex: Column[Option[String]] = column[Option[String]]("sex", O.Length(100,varying=true), O.Default(None))
    /** Database column normalised_sex DBType(varchar), Length(100,true), Default(None) */
    val normalisedSex: Column[Option[String]] = column[Option[String]]("normalised_sex", O.Length(100,varying=true), O.Default(None))
    /** Database column standardisedsex DBType(varchar), Length(100,true), Default(None) */
    val standardisedsex: Column[Option[String]] = column[Option[String]]("standardisedsex", O.Length(100,varying=true), O.Default(None))
    /** Database column urinalysisparameter DBType(varchar), Length(500,true), Default(None) */
    val urinalysisparameter: Column[Option[String]] = column[Option[String]]("urinalysisparameter", O.Length(500,varying=true), O.Default(None))
    /** Database column standardisedparameter DBType(varchar), Length(500,true), Default(None) */
    val standardisedparameter: Column[Option[String]] = column[Option[String]]("standardisedparameter", O.Length(500,varying=true), O.Default(None))
    /** Database column normalised_urinalysisparameter DBType(varchar), Length(500,true), Default(None) */
    val normalisedUrinalysisparameter: Column[Option[String]] = column[Option[String]]("normalised_urinalysisparameter", O.Length(500,varying=true), O.Default(None))
    /** Database column timepoint DBType(numeric), Default(None) */
    val timepoint: Column[Option[scala.math.BigDecimal]] = column[Option[scala.math.BigDecimal]]("timepoint", O.Default(None))
    /** Database column timepointunit DBType(varchar), Length(100,true), Default(None) */
    val timepointunit: Column[Option[String]] = column[Option[String]]("timepointunit", O.Length(100,varying=true), O.Default(None))
    /** Database column unit DBType(varchar), Length(1000,true), Default(None) */
    val unit: Column[Option[String]] = column[Option[String]]("unit", O.Length(1000,varying=true), O.Default(None))
    
    /** Foreign key referencing Study (database name urianalysisfindings_subst_id_fkey) */
    lazy val studyFk = foreignKey("urianalysisfindings_subst_id_fkey", (substId, studyId), Study)(r => (r.substId, r.studyId), onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.Cascade)
  }
  /** Collection-like TableQuery object for table Urianalysisfindings */
  lazy val Urianalysisfindings = new TableQuery(tag => new Urianalysisfindings(tag))
}