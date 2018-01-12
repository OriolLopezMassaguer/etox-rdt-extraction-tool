--drop table public.hpfinding cascade;
--drop table public.ccfinding cascade;
-- drop table public.study cascade;
-- drop table public.compounds cascade;

create extension rdkit;

create table public.compounds
( 
   compound_id serial PRIMARY KEY,
   m mol,
   smiles varchar(1000),--Smiles
   subst_id varchar(100),--SUBST_ID
   query_text varchar(100),--Query_Text
   db_version varchar(100),--DB Version
   software_version varchar(100),--Software Version
   cdk_title varchar(100),--cdk:Title
   db_description varchar(100),--DB Description
   db_name varchar(100),--DB Name
   database_substance_id varchar(100),--Database Substance Id
   inchi varchar(1000),--Inchi
   substance_status varchar(100),--Substance Status
   cas_number varchar(100),--Cas Number
   common_name varchar(1000),--Common Name
   molecular_weight varchar(100),--Molecular Weight
   pharmacological_action varchar(1000),--Pharmacological Action
   vitic_legacy_recno varchar(100),--Vitic Legacy Recno
   molecular_formula varchar(1000),--Molecular Formula
   img_base64 varchar(500000),
   source varchar(100),
   UNIQUE (subst_id)
);

--drop table public.study;
CREATE TABLE public.study
(
   subst_id varchar(100),--SUBST_ID
   study_id varchar(100),--   
   strain varchar(1000),--Strain
   normalised_strain varchar(1000),--Strain via ontologies
   sex varchar(100),--Sex
   normalised_sex varchar(100),--Sex via ontologies
   administration_route varchar(100),--Administration Route
   normalised_administration_route varchar(100),--Administration Route via ontologies
   species varchar(100),--Species
   normalised_species varchar(100),--Species via ontologies
   standarised_species varchar(100),--Standardised Species     
   report_number varchar(100),--Report Number
   record_status varchar(100),--Record Status
   year_of_study varchar(100),--Year Of Study
   standarised_age numeric(10,3),--Standardised Age
   age_unit varchar(100),--Age Unit
   study_quality_assesment varchar(100),--Study Quality Assessment
   dosage varchar(1000),--Dosage
   source_company varchar(100),--Source (Company)   
   recovery_period_days integer,--Recovery Period (Days)
   individual_animal_data varchar(100),--Individual Animal Data
   vehicle varchar(1000),--Vehicle
   age_of_start_of_treatment numeric(10,3),--Age At Start Of Treatment
   exposure_period_days integer,--Exposure Period (Days)
      source varchar(100),
   UNIQUE (subst_id,study_id),
   FOREIGN KEY (subst_id) references compounds (subst_id) ON DELETE CASCADE
);


CREATE TABLE hpfinding
(
  subst_id character varying(100),
  study_id character varying(100),
  num_animals_total integer,
  num_animals_affected integer,
  dose numeric(10,2),
  sex character varying(100),  
  standarised_sex character varying(100), 
  normalised_sex character varying(100),
  grade character varying(1000),
  timepoint integer,
  timepoint_unit character varying(30),
  relevance character varying(1000),
  histopathology_finding character varying(1000),
  histopathology_finding_standarised character varying(1000),
  histopathology_organ_affected character varying(1000),
  histopathology_organ_affected_standarised character varying(1000),
  source character varying(100),
  histopathology_finding_standarised_revised character varying(1000),
  histopathology_organ_affected_standarised_revised character varying(1000),
  standarised_grade character varying(1000),
  CONSTRAINT hpfinding_subst_id_fkey FOREIGN KEY (subst_id, study_id)
      REFERENCES study (subst_id, study_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE
)
WITH (
  OIDS=FALSE
);
ALTER TABLE hpfinding
  OWNER TO postgres;

drop table ccfinding;
CREATE UNLOGGED TABLE ccfinding
(
  subst_id character varying(100),
  study_id character varying(100),
  dose numeric(10,2),
  sex character varying(100),
  standarised_sex character varying(100), 
  normalised_sex character varying(100),
  timepoint float,
  timepoint_unit character varying(30),
  relevance character varying(1000),
  sd numeric(10,2),
  clinical_chemistry_parameter character varying(1000),
  standarised_parameter character varying(1000),
  average_fold_change numeric(10,2),
  finding character varying(1000),
  average_value numeric(10,2),
  unit character varying(1000),  
  standarised_parameter_revised character varying(1000),
  standarised_value numeric(10,2),
  CONSTRAINT ccfinding_subst_id_fkey FOREIGN KEY (subst_id, study_id)
      REFERENCES study (subst_id, study_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE
)
WITH (
  OIDS=FALSE
);
ALTER TABLE ccfinding
  OWNER TO postgres;

-- drop table effeclevels;
CREATE TABLE public.effectlevels
(
   subst_id varchar(100),--SUBST_ID
   study_id varchar(100),--
   effectleveltype varchar(1000),
   effectlevelunit varchar(1000),
   effectlevelvalue numeric(10,2),   
   standardisedeffectlevel varchar(1000),
   FOREIGN KEY (subst_id,study_id) references study (subst_id,study_id) ON DELETE CASCADE
);

-- drop table generaltoxiceffects;
CREATE TABLE public.generaltoxiceffects 
(
   subst_id varchar(100),--SUBST_ID
   study_id varchar(100),--
   bodyweigth numeric(10,2),
   bodyweightrelevance varchar(100),
   bodyweightunit varchar(100),
   dosemgkg numeric(10,2),
   mortality numeric(10,2),
   sd numeric(10,2),
   sex varchar(100),
   standardisedbodyweight numeric(10,2),
   standardisedsex varchar(100),
   normalised_sex varchar(100),
   timepoint numeric(10,2),
   timepointunit varchar(100),  
   FOREIGN KEY (subst_id,study_id) references study (subst_id,study_id) ON DELETE CASCADE
);

CREATE TABLE public.grossnecropsy 
(
   subst_id varchar(100),--SUBST_ID
   study_id varchar(100),--
   dosemgkg numeric(10,3),
   grade varchar(100),
   grosspathologyfinding varchar(255),
   grosspathologyorganaffected varchar(255),
   numberofananimalsaffected integer,
   relevance varchar(100),
   sex varchar(100),
   normalised_sex varchar(100),
   standardisedgrosspathology varchar(255),
   standardisedorgan varchar(255),
   standardisedsex varchar(255),
   timepoint numeric(10,2),
   timepointunit varchar(100),
   totalnumberofanimals integer, 
   FOREIGN KEY (subst_id,study_id) references study (subst_id,study_id) ON DELETE CASCADE
);
drop table  public.organweights;
CREATE UNLOGGED TABLE public.organweights
(
   subst_id varchar(100),--SUBST_ID
   study_id varchar(100),--
   averagefoldchange numeric(10,3),
   averageweight numeric(10,3),
   dosemgkg numeric(10,3),
   finding varchar(100),
   organweighted varchar(100),
   relevance varchar(100),
   sd numeric(10,2),
   sex varchar(100),
   standardisedorgan varchar(100),
   normalised_organ varchar(100),
   standardisedsex varchar(100),
   normalised_sex varchar(100),
   timepoint integer,
   timepointunit varchar(100),
   unit varchar(1000),
   FOREIGN KEY (subst_id,study_id) references study (subst_id,study_id) ON DELETE CASCADE
);

drop table public.clinicalhaematologicalfindings ;
CREATE UNLOGGED TABLE public.clinicalhaematologicalfindings 
(
   subst_id varchar(100),--SUBST_ID
   study_id varchar(100),--
   averagefoldchange numeric(10,3),
   averagevalue numeric(20,3),
   clinicalhaematologyparameter varchar(500),
   standardisedparameter varchar(500),
   normalised_clinicalhaematologyparameter varchar(500),   
   dosemgkg numeric(10,3),
   finding varchar(100),   
   relevance varchar(100),
   sd numeric(10,2),
   sex varchar(100),
   normalised_sex varchar(100),
   standardisedsex varchar(100),      
   timepoint numeric(10,2),
   timepointunit varchar(100),
   unit varchar(1000),
   FOREIGN KEY (subst_id,study_id) references study (subst_id,study_id) ON DELETE CASCADE
);

drop table public.urianalysisfindings ;

CREATE UNLOGGED TABLE public.urianalysisfindings 
(
   subst_id varchar(100),--SUBST_ID
   study_id varchar(100),--
   averagefoldchange numeric(10,3),
   averagevalue numeric(20,3),
   dosemgkg numeric(10,3),
   finding varchar(100),  
   relevance varchar(100),     
   sd numeric(10,2),
   sex varchar(100),   
   normalised_sex varchar(100),
   standardisedsex varchar(100),      
   urinalysisparameter character varying(500),
   standardisedparameter  character varying(500),
   normalised_urinalysisparameter character varying(500),
   timepoint numeric(10,2),
   timepointunit varchar(100),
   unit varchar(1000),
   FOREIGN KEY (subst_id,study_id) references study (subst_id,study_id) ON DELETE CASCADE
);

--drop function convert_to_integer;

CREATE OR REPLACE FUNCTION convert_to_numeric(v_input text)
RETURNS numeric AS $$
DECLARE v_int_value numeric DEFAULT NULL;
BEGIN
    BEGIN
        v_int_value := v_input::numeric;
    EXCEPTION WHEN OTHERS THEN
        RAISE NOTICE 'Invalid integer value: "%".  Returning NULL.', v_input;
        RETURN NULL;
    END;
RETURN v_int_value;
END;
$$ LANGUAGE plpgsql;

