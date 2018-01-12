truncate table public.compounds cascade;

insert into public.compounds 
	(smiles, subst_id, query_text, db_version,software_version,   cdk_title, db_description, db_name, 
	database_substance_id,  inchi,substance_status, cas_number, common_name,  molecular_weight, pharmacological_action,   
	vitic_legacy_recno, molecular_formula,img_base64,source) 
select distinct 
	istruc."Smiles" smiles, isubs."SUBST_ID" subst_id, NULL query_text, isubs."DBVersion" db_version, isubs."SoftwareVersion" software_version,  
	nullif('',trim("cdk:Title")) cdk_title, isubs."DBDescription" db_description, isubs."DBName" db_name, 
	istruc."DatabaseSubstanceId" database_substance_id, istruc."Inchi" inchi, istruc."SubstanceStatus" substance_status,
	istruc."CasNumber" cas_number, istruc."CommonName" common_name, istruc."MolecularWeight" molecular_weight, istruc."PharmacologicalAction" pharmacological_action, 
	--istruc.vitic_legacy_recno, 
	NULL vitic_legacy_no,	istruc."MolecularFormula",NULL img,NULL source   
from "input_Substances" isubs, "input_Structures" istruc 
where isubs."SUBST_ID" = istruc."DatabaseSubstanceId";
--and istruc."Smiles" not in ('Clc1ccc2c([H]C=C2C2=CCN(CCC3C[H]c4ccccc34)CC2)c1','CS(=O)(=O)[H]CC1CCC([H]c2nc-3c(CCSc4ccccc-34)s2)CC1');

--- molecule 1 correction
--select * from public.compounds 
--where smiles = 'Clc1ccc2c([H]C=C2C2=CCN(CCC3C[H]c4ccccc34)CC2)c1'

--update public.compounds 
--set smiles = 'Clc1ccc2C(=CNc2c1)C1=CCN(CCC2CNc3ccccc23)CC1' 
--where smiles = 'Clc1ccc2c([H]C=C2C2=CCN(CCC3C[H]c4ccccc34)CC2)c1' 

--- molecule 2 correction
--select * from public.compounds 
--where smiles = 'CS(=O)(=O)[H]CC1CCC([H]c2nc-3c(CCSc4ccccc-34)s2)CC1'

--update public.compounds 
--set smiles = 'CS(=O)(=O)NCC1CCC(CC1)Nc1nc-2c(CCSc3ccccc-23)s1' 
--where smiles = 'CS(=O)(=O)[H]CC1CCC([H]c2nc-3c(CCSc4ccccc-34)s2)CC1' 

------

--update public.compounds 
--set m=smiles::mol;
--where smiles not in ('Clc1ccc2c([H]C=C2C2=CCN(CCC3C[H]c4ccccc34)CC2)c1','CS(=O)(=O)[H]CC1CCC([H]c2nc-3c(CCSc4ccccc-34)s2)CC1');


truncate table public.study cascade;

insert into study 
         (subst_id, 
         study_id, 
         strain, 
         normalised_strain,
         normalised_sex,
         normalised_administration_route,
         normalised_species,
         report_number, 
         standarised_species, record_status,   year_of_study, standarised_age, age_unit,
           study_quality_assesment,dosage ,source_company,   sex, recovery_period_days,species, individual_animal_data, administration_route, vehicle,
           age_of_start_of_treatment, exposure_period_days,source ) 
select distinct 
       	 s."SUBST_ID" subst_id, s.study_id, s."Strain" strain, 

	 (select distinct "TERM_NAME"
      from input_onto_vx_synonyms 
      inner join input_onto_etox_ontology_terms using("ONTOLOGY_TERM_ID") 
      where "VX_VALUE" = s."Strain"
      and "SYNONYM_DOMAIN" = 'strain'
      and "SYNONYM_CONTEXT" = 'study design') 
      normalised_strain,
      
	(select distinct "TERM_NAME"
      from input_onto_vx_synonyms 
      inner join input_onto_etox_ontology_terms using("ONTOLOGY_TERM_ID") 
      where "VX_VALUE" = s."Sex"
      and "SYNONYM_DOMAIN" = 'sex'
      and "SYNONYM_CONTEXT" = 'study design')
           normalised_sex,

           	(select distinct "TERM_NAME"
      from input_onto_vx_synonyms 
      inner join input_onto_etox_ontology_terms using("ONTOLOGY_TERM_ID") 
      where "VX_VALUE" = s."AdministrationRoute" 
      and "SYNONYM_DOMAIN" = 'administration route'
      and "SYNONYM_CONTEXT" = 'study design')
           normalised_administration_route,

	(select distinct "TERM_NAME"
      from input_onto_vx_synonyms 
      inner join input_onto_etox_ontology_terms using("ONTOLOGY_TERM_ID") 
      where "VX_VALUE" = s."Species" 
      and "SYNONYM_DOMAIN" = 'species'
      and "SYNONYM_CONTEXT" = 'study design')
           normalised_species,

           
       	 s."ReportNumber" report_number, 
       	 s."StandardisedSpecies" standarised_species, s."RecordStatus" record_status, s."YearOfStudy" year_of_study, 
       	 cast(s."StandardisedAge" as numeric(10,3)) standarised_age, s."AgeUnit" age_unit, 
       	 s."StudyQualityAssessment" study_quality_assesment, s."Dosage" dosage, s."SourceCompany" source_company, 
       	 s."Sex" sex, cast(s."RecoveryPeriodDays"  as integer) recovery_period_days, s."Species" species, s."IndividualAnimalData" individual_animal_data,
       	 s."AdministrationRoute" administration_route, s."Vehicle" vehicle, cast(s."AgeAtStartOfTreatment" as numeric(10,3)) age_of_start_of_treatment, 
         cast(s."ExposurePeriodDays" as integer) exposure_period_days,null source 
from "input_Studies" s;


insert into public.hpfinding 
 (
   subst_id,
   study_id,
   num_animals_total,
   num_animals_affected,
   dose,
   sex,
   standarised_sex,
   normalised_sex,
   grade,
   timepoint,
   timepoint_unit,
   relevance,
   histopathology_finding,
   histopathology_finding_standarised,
   histopathology_organ_affected,
   histopathology_organ_affected_standarised,
   histopathology_finding_standarised_revised,
   histopathology_organ_affected_standarised_revised,
   standarised_grade
)
select 
	hpf."SUBST_ID" subst_id,
	hpf.study_id,
	cast(hpf."TotalNumberOfAnimals" as integer) num_animals_total,
	cast(hpf."NumberOfAnimalsAffected" as integer) num_animals_affected,
	cast(hpf."DoseMgKg" as numeric(10,2)) dose,
	hpf."Sex" sex,
	hpf."StandardisedSex" standarised_sex,
	(select distinct "TERM_NAME"
	      from input_onto_vx_synonyms 
	      inner join input_onto_etox_ontology_terms using("ONTOLOGY_TERM_ID") 
	      where "VX_VALUE" = hpf."Sex"
	      and "SYNONYM_DOMAIN" = 'sex'
	      and "SYNONYM_CONTEXT" = 'microscopic finding')
        normalised_sex,

	
	hpf."Grade" grade,
	cast(hpf."Timepoint" as integer) timepoint,
	hpf."TimepointUnit" timepoint_unit,
	hpf."Relevance" relevance,
	hpf."HistopathologyFinding" histopathology_finding,
	hpf."StandardisedPathology" standarised_pathology,
	hpf."HistopathologyOrganAffected" histopathology_organ_affected,
	hpf."StandardisedOrgan" histopathology_organ_affected_standarised,

	(select distinct "TERM_NAME"
	      from input_onto_vx_synonyms 
	      inner join input_onto_etox_ontology_terms using("ONTOLOGY_TERM_ID") 
	      where "VX_VALUE" = hpf."HistopathologyFinding"
	      and "SYNONYM_DOMAIN" = 'microscopic finding'
	      and "SYNONYM_CONTEXT" = 'microscopic finding')
	histopathology_finding_standarised_revised,
	
	(select distinct "TERM_NAME"
	      from input_onto_vx_synonyms 
	      inner join input_onto_etox_ontology_terms using("ONTOLOGY_TERM_ID") 
	      where "VX_VALUE" = hpf."HistopathologyOrganAffected"
	      and "SYNONYM_DOMAIN" = 'organ tissue'
	      and "SYNONYM_CONTEXT" = 'microscopic finding')
	histopathology_organ_affected_standarised_revised,
	hpf."StandardisedGrade" 
	
from "input_HistopathologicalFindings" hpf;

insert into public.ccfinding 
 (
   subst_id,
   study_id,
   dose,
   sex,
   normalised_sex,
   standarised_sex,
   timepoint,
   timepoint_unit,
   relevance,
   sd,
   clinical_chemistry_parameter,--Clinical Chemistry Parameter
   standarised_parameter,--Standardised Parameter
   average_fold_change,--Average Fold Change
   finding,--Finding
   average_value,--Average Value
   unit,--Unit 
   standarised_parameter_revised
   --standarised_value
)
select 
	ccf."SUBST_ID" subst_id,
	ccf.study_id,
	convert_to_numeric(ccf."DoseMgKg") dose,
	ccf."Sex" sex,
	ccf."StandardisedSex" standarised_sex,
	(select distinct "TERM_NAME"
	      from input_onto_vx_synonyms 
	      inner join input_onto_etox_ontology_terms using("ONTOLOGY_TERM_ID") 
	      where "VX_VALUE" = ccf."Sex"
	      and "SYNONYM_DOMAIN" = 'sex'
	      and "SYNONYM_CONTEXT" = 'clinical chemistry')
        normalised_sex,
	
	convert_to_numeric(ccf."Timepoint") timepoint,
	ccf."TimepointUnit" timepoint_unit,
	ccf."Relevance" relevance,
	convert_to_numeric(ccf."Sd") sd,
	ccf."ClinicalChemistryParameter" clinical_chemistry_parameter,--Clinical Chemistry Parameter
	ccf."StandardisedParameter" standarised_parameter ,--Standardised Parameter
	convert_to_numeric(ccf."AverageFoldChange") average_fold_change,--Average Fold Change
	ccf."Finding" finding,--Finding
	convert_to_numeric(ccf."AverageValue") average_value,--Average Value
	ccf."Unit" unit,--Unit  
	(select distinct "TERM_NAME"
	      from input_onto_vx_synonyms 
	      inner join input_onto_etox_ontology_terms using("ONTOLOGY_TERM_ID") 
	      where "VX_VALUE" = ccf."ClinicalChemistryParameter"
	      and "SYNONYM_DOMAIN" = 'laboratory test'
	      and "SYNONYM_CONTEXT" = 'clinical chemistry')
	standarised_parameter_revised

from "input_ClinicalChemicalFindings" ccf;


insert into public.effectlevels
 (
   subst_id,
   study_id,
   effectleveltype,
   effectlevelunit,
   effectlevelvalue,
   standardisedeffectlevel
)
select distinct
	elevels."SUBST_ID" subst_id,
	elevels.study_id,
	elevels."EffectLevelType",
	elevels."EffectLevelUnit",
	cast(elevels."EffectLevelValue" as numeric(10,2)),
	"StandardisedEffectLevel"  
from "input_EffectLevels" elevels;

truncate table public.generaltoxiceffects;
insert into public.generaltoxiceffects
 (
   subst_id,
   study_id,
   bodyweigth,
   bodyweightrelevance,
   bodyweightunit,
   dosemgkg,
   mortality,
   sd,
   sex,
   standardisedbodyweight,
   standardisedsex,
   timepoint,
   timepointunit,
   normalised_sex
)
select distinct
	gte."SUBST_ID" subst_id,
	gte.study_id,
	cast(gte."Bodyweight" as numeric(10,2)) bodyweightrelevance, 
	gte."BodyweightRelevance" bodyweightrelevance,
	gte."BodyweightUnit" bodyweightunit,
	cast(gte."DoseMgKg"  as numeric(10,2)) dosemgkg,
	cast(gte."Mortality" as numeric(10,2)) mortality,
	cast(gte."Sd" as numeric(10,2)) sd,
	gte."Sex" sex,
	cast(gte."StandardisedBodyweight" as numeric(10,2)) standardisedbodyweight,
	gte. "StandardisedSex",	
	cast(gte."Timepoint" as numeric(10,2)) timepoint,
	gte."TimepointUnit" timepointunit,
	
	(select distinct "TERM_NAME"
	      from input_onto_vx_synonyms 
	      inner join input_onto_etox_ontology_terms using("ONTOLOGY_TERM_ID") 
	      where "VX_VALUE" = gte."Sex"
	      and "SYNONYM_DOMAIN" = 'sex'
	      and "SYNONYM_CONTEXT" = 'general toxic effect') normalised_sex
from "input_GeneralToxicEffects" gte;  


truncate table public.grossnecropsy;
insert into public.grossnecropsy 
 (
   subst_id,
   study_id,
   dosemgkg,
   grade,
   grosspathologyfinding,
   grosspathologyorganaffected,
   numberofananimalsaffected,
   relevance,
   sex,
   standardisedgrosspathology,
   standardisedorgan,
   standardisedsex,
   timepoint,
   timepointunit,
   totalnumberofanimals   
)
select distinct
	gne."SUBST_ID" subst_id,
	gne.study_id,
	cast(gne."DoseMgKg" as numeric(10,3)) dosemgkg,
	gne."Grade" grade,
	gne."GrossPathologyFinding" grosspathologyfinding,	
	gne."GrossPathologyOrganAffected" grosspathologyorganaffected,	
	cast(gne."NumberOfAnimalsAffected" as integer) numberofananimalsaffected,
	gne."Relevance" relevance,
	gne."Sex" sex,
	gne."StandardisedGrossPathology" sex,
	gne."StandardisedOrgan" sex,
	gne."StandardisedSex" sex,
	cast(gne."Timepoint" as  numeric(10,2)) timepoint,
	gne."TimepointUnit" timepointunit,	
	cast(gne."TotalNumberOfAnimals" as integer) totalnumberofanimals
from "input_GrossNecropsy" gne;

truncate table public.organweights;
insert into public.organweights 
(
   subst_id,
   study_id,
   averagefoldchange,
   averageweight,
   dosemgkg,
   finding,
   organweighted,
   relevance,
   sd,
   sex,
   standardisedorgan,
   normalised_organ,
   standardisedsex,
   normalised_sex,
   timepoint,
   timepointunit,
   unit
)
select 
	ow."SUBST_ID" subst_id,
	ow.study_id,
	cast(ow."AverageFoldChange"  as numeric(10,3)) averagefoldchange,
	cast(ow."AverageWeight" as numeric(10,3)) averageweight,
	cast(ow."DoseMgKg" as numeric(10,3)) dosemgkg,
	ow."Finding" finding,
	ow."OrganWeighed" organweighted,
	ow."Relevance" relevance,	
	cast(ow."Sd" as numeric(10,2)) sd,
        ow."Sex" sex,
	ow."StandardisedOrgan" standarisedorgan,
	
	(select distinct "TERM_NAME"
	      from input_onto_vx_synonyms 
	      inner join input_onto_etox_ontology_terms using("ONTOLOGY_TERM_ID") 
	      where "VX_VALUE" = ow."OrganWeighed"
	      and "SYNONYM_DOMAIN" = 'organ tissue'
	      and "SYNONYM_CONTEXT" = 'organ measurement')
        normalised_organ,
	
	ow."StandardisedSex" standarisedsex,

	(select distinct "TERM_NAME"
	      from input_onto_vx_synonyms 
	      inner join input_onto_etox_ontology_terms using("ONTOLOGY_TERM_ID") 
	      where "VX_VALUE" = ow."Sex"
	      and "SYNONYM_DOMAIN" = 'sex' 
	      and "SYNONYM_CONTEXT" = 'organ measurement')
        normalised_sex,

	
	cast(ow."Timepoint" as  numeric(10,2)) timepoint,
	ow."TimepointUnit" timepointunit,	
	ow."Unit" unit
from "input_OrganWeights" ow;
-- 
-- select distinct chf."AverageFoldChange"
-- from "input_ClinicalHaematologicalFindings" chf
-- where chf."AverageFoldChange"='AverageFoldChange'
-- order by chf."AverageFoldChange"
-- 
-- delete from "input_ClinicalHaematologicalFindings" chf 
-- where chf."AverageFoldChange"='AverageFoldChange'

truncate table public.clinicalhaematologicalfindings;
insert into public.clinicalhaematologicalfindings 
(
   subst_id,
   study_id,
   averagefoldchange,
   averagevalue,
   clinicalhaematologyparameter,
   standardisedparameter,
   normalised_clinicalhaematologyparameter,   
   dosemgkg,
   finding,   
   relevance,
   sd,
   sex,
   normalised_sex,
   standardisedsex,      
   timepoint,
   timepointunit,
   unit
)
select 
	chf."SUBST_ID" subst_id,
	chf.study_id,
	cast(chf."AverageFoldChange"  as numeric(10,3)) averagefoldchange,
	cast(chf."AverageValue" as numeric(20,3)) averageweight,	
	chf."ClinicalHaematologyParameter" clinicalhaematologyparameter,	
	chf."StandardisedParameter" standardisedparameter,
	
	(select distinct "TERM_NAME"
	      from input_onto_vx_synonyms 
	      inner join input_onto_etox_ontology_terms using("ONTOLOGY_TERM_ID") 
	      where "VX_VALUE" = chf."ClinicalHaematologyParameter"
	      and "SYNONYM_DOMAIN" = 'laboratory test' 
	      and "SYNONYM_CONTEXT" = 'clinical haematology')
	normalised_clinicalhaematologyparameter,   
	
	cast(chf."DoseMgKg" as numeric(10,3)) dosemgkg,
	chf."Finding" finding,
	chf."Relevance" relevance,
	cast(chf."Sd" as numeric(10,2)) sd,
	chf."Sex" sex,

	(select distinct "TERM_NAME"
	      from input_onto_vx_synonyms 
	      inner join input_onto_etox_ontology_terms using("ONTOLOGY_TERM_ID") 
	      where "VX_VALUE" = chf."Sex"
	      and "SYNONYM_DOMAIN" = 'sex' 
	      and "SYNONYM_CONTEXT" = 'clinical haematology')
	normalised_sex,

	chf."StandardisedSex" standardisedsex,      
	cast(chf."Timepoint" as numeric(10,2)) timepoint,
	chf."TimepointUnit" timepointunit,
	chf."Unit" unit
	
from "input_ClinicalHaematologicalFindings" chf;


truncate table public.urianalysisfindings;
insert into public.urianalysisfindings 
(
   subst_id,
   study_id,
   averagefoldchange,
   averagevalue,
   
   urinalysisparameter,
   standardisedparameter,
   normalised_urinalysisparameter,

   dosemgkg,
   finding,   
   relevance,
   sd,
   sex,
   normalised_sex,
   standardisedsex, 
        
   timepoint,
   timepointunit,
   unit
)
select 
	chf."SUBST_ID" subst_id,
	chf.study_id,
	cast(chf."AverageFoldChange"  as numeric(10,3)) averagefoldchange,
	cast(chf."AverageValue" as numeric(20,3)) averageweight,	
	
	chf."UrinalysisParameter" urinalysisparameter,	
	chf."StandardisedParameter" standardisedparameter,
	
	(select distinct "TERM_NAME"
	      from input_onto_vx_synonyms 
	      inner join input_onto_etox_ontology_terms using("ONTOLOGY_TERM_ID") 
	      where "VX_VALUE" = chf."UrinalysisParameter"
	      and "SYNONYM_DOMAIN" = 'laboratory test' 
	      and "SYNONYM_CONTEXT" = 'urinalysis')
	normalised_urinalysisparameter,   
	
	cast(chf."DoseMgKg" as numeric(10,3)) dosemgkg,
	chf."Finding" finding,
	chf."Relevance" relevance,
	cast(chf."Sd" as numeric(10,2)) sd,
	chf."Sex" sex,

	(select distinct "TERM_NAME"
	      from input_onto_vx_synonyms 
	      inner join input_onto_etox_ontology_terms using("ONTOLOGY_TERM_ID") 
	      where "VX_VALUE" = chf."Sex"
	      and "SYNONYM_DOMAIN" = 'sex' 
	      and "SYNONYM_CONTEXT" = 'urinalysis')
	normalised_sex,

	chf."StandardisedSex" standardisedsex,      
	cast(chf."Timepoint" as numeric(10,2)) timepoint,
	chf."TimepointUnit" timepointunit,
	chf."Unit" unit
	
from "input_UrinalysisFindings" chf;


