-- select  source, count(*) 
-- from findings_all 
-- group by source 
--drop table findings_all;



CREATE UNLOGGED TABLE findings_all 
(
  subst_id character varying(100),
  study_id character varying(100),
  source character varying(50),
  relevance character varying(100),
  observation_standarised character varying(1000),
  observation_verbatim character varying(1000),
  observation_normalised  character varying(1000),
  organ_standarised character varying(1000),
  organ_verbatim character varying(1000),
  organ_normalised character varying(1000),
  change character varying(1000),
  dose numeric(10,2),
  sex character varying(1000),
  standarised_sex character varying(1000),
  normalised_sex character varying(1000),
  averagefoldchange numeric(10,2),
  averagevalue numeric(10,2),
  unit character varying(1000),
  timepoint float,
  timepointunit character varying(1000),
  grade character varying(1000),
  num_animals_total integer,
  num_animals_affected integer,
  CONSTRAINT findings_all_subst_id_fkey FOREIGN KEY (subst_id, study_id)
      REFERENCES study (subst_id, study_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE
)
WITH (
  OIDS=FALSE
);
ALTER TABLE findings_all
  OWNER TO postgres;
  

CREATE UNLOGGED TABLE findings_all_q
(
  int4 integer,
  term_top text,
  subst_id character varying(100),
  dose numeric(10,2)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE findings_all_q
  OWNER TO postgres;



insert into findings_all
(
  subst_id,
  study_id,
  source,
  relevance,
  observation_standarised,
  observation_verbatim,
  observation_normalised,
  organ_standarised,
  organ_verbatim,
  organ_normalised,
  change,
  dose,
  sex,
  standarised_sex,
  normalised_sex,
  averagefoldchange,
  averagevalue,
  unit,
  timepoint,
  timepointunit,
  grade,
  num_animals_total,
  num_animals_affected
)
select
  subst_id,
  study_id,
  'HistopathologicalFinding' source,
  relevance relevance,
  histopathology_finding_standarised observation_standarised,
  histopathology_finding  observation_verbatim,
  histopathology_finding_standarised_revised observation_normalised,
  histopathology_organ_affected_standarised organ_standarised,
  histopathology_organ_affected organ_verbatim,
  histopathology_organ_affected_standarised_revised organ_normalised,
  'NA' as change,
  dose dose,
  sex,
  standarised_sex,
  normalised_sex,
  null averagefoldchange,
  null averagevalue,
  'NA' unit,
  timepoint,
  timepoint_unit,
  grade,
  num_animals_total,
  num_animals_affected
from hpfinding hpf;

insert into findings_all
(
  subst_id,
  study_id,
  source,
  relevance,
  observation_standarised,
  observation_verbatim,
  observation_normalised,
  organ_standarised,
  organ_verbatim,
  organ_normalised,
  change,
  dose,
  standarised_sex,
  normalised_sex,
  averagefoldchange,
  averagevalue,
  unit,
  timepoint,
  timepointunit,
  grade,
  num_animals_total,
  num_animals_affected
)
select
  subst_id,
  study_id,
  'ClinicalChemicalFinding' source,
  relevance,
  standarised_parameter,
  clinical_chemistry_parameter,
  standarised_parameter_revised,
  'NA' organ_standarised,
  'NA' organ_verbatim,
  'NA' organ_normalised,
  finding as change,
  dose,
  standarised_sex,
  normalised_sex,
  average_fold_change,
  average_value,
  unit,
  timepoint,
  timepoint_unit,
  'NA' grade,
  null num_animals_total,
  null num_animals_affected
from ccfinding;


insert into findings_all
(
  subst_id,
  study_id,
  source,
  relevance,
  observation_standarised,
  observation_verbatim,
  observation_normalised,
  organ_standarised,
  organ_verbatim,
  organ_normalised,
  change,
  dose,
  standarised_sex,
  normalised_sex,
  averagefoldchange,
  averagevalue,
  unit,
  timepoint,
  timepointunit,
  grade,
  num_animals_total,
  num_animals_affected
)
select
  subst_id,
  study_id,
  'ClinicalHaematologicalFinding' source,
  relevance,
  standardisedparameter,
  clinicalhaematologyparameter,
  normalised_clinicalhaematologyparameter,
  'NA' organ_standarised,
  'NA' organ_verbatim,
  'NA' organ_normalised,
  finding as change,
  dosemgkg dose,
  standardisedsex,
  normalised_sex,
  averagefoldchange,
  averagevalue,
  unit,
  timepoint,
  timepointunit,
  'NA' grade,
  null num_animals_total,
  null num_animals_affected
from clinicalhaematologicalfindings;



insert into findings_all
(
  subst_id,
  study_id,
  source,
  relevance,
  observation_standarised,
  observation_verbatim,
  observation_normalised,
  organ_standarised,
  organ_verbatim,
  organ_normalised,
  change,
  dose,
  standarised_sex,
  normalised_sex,
  averagefoldchange,
  averagevalue,
  unit,
  timepoint,
  timepointunit,
  grade,
  num_animals_total,
  num_animals_affected
)
select
  subst_id,
  study_id,
  'UrianalysisFinding' source,
  relevance,
  standardisedparameter,
  urinalysisparameter,
  normalised_urinalysisparameter,
  'NA' organ_standarised,
  'NA' organ_verbatim,
  'NA' organ_normalised,
  finding as change,
  dosemgkg dose,
  standardisedsex,
  normalised_sex,
  averagefoldchange,
  averagevalue,
  unit,
  timepoint,
  timepointunit,
  'NA' grade,
  null num_animals_total,
  null num_animals_affected
from urianalysisfindings;




insert into findings_all
(
  subst_id,
  study_id,
  source,
  relevance,
  observation_standarised,
  observation_verbatim,
  observation_normalised,
  organ_standarised,
  organ_verbatim,
  organ_normalised,
  change,
  dose,
  standarised_sex,
  normalised_sex,
  averagefoldchange,
  averagevalue,
  unit,
  timepoint,
  timepointunit,
  grade,
  num_animals_total,
  num_animals_affected
)
select
  subst_id,
  study_id,
  'OrganWeights' source,
  relevance,
  standardisedorgan organ_standarised,
  organweighted organ_verbatim,
  normalised_organ organ_normalised,
  'NA',
  'NA',
  'NA',
  finding as change,
  dosemgkg dose,
  standardisedsex,
  normalised_sex,
  averagefoldchange,
  averageweight,
  unit,
  timepoint,
  timepointunit,
  'NA' grade,
  null num_animals_total,
  null num_animals_affected
from organweights;


-- 
-- drop table findings_all_grouped;
-- CREATE TABLE findings_all_grouped
-- (
--   subst_id character varying(100),
--   source character varying(50),
--   relevance character varying(100),
--   observation_normalised  character varying(1000),
--   organ_normalised character varying(1000),
--   change character varying(1000),
--   dose numeric(10,2),
--   cnt integer,
--   flag integer
-- )
-- WITH (
--   OIDS=FALSE
-- );
-- ALTER TABLE findings_all
--   OWNER TO postgres;
-- 
-- insert into findings_all_grouped 
-- select 
-- 	subst_id,source,relevance,observation_normalised,organ_normalised,change,
-- 	min(dose),
-- 	count(*) cnt,
-- 	case  when count(*) > 0 then 1 else 0 end flag
-- from findings_all fa
-- group by subst_id,source,relevance,observation_normalised,organ_normalised,change;



-- 
-- insert into findings_all
-- (out
--   subst_id,
--   study_id,
--   source,
--   relevance,
--   finding_standarised,
--   finding_verbatim,
--   finding_normalised,
--   organ_standarised,
--   organ_verbatim,
--   organ_normalised,
--   change,
--   dose
-- )
-- select
--   subst_id,
--   study_id,
--   'GeneralToxicEffects' source,
--   bodyweightrelevance relevance,
--   'NA',
--   'NA',
--   'NA',
--   standardisedorgan organ_standarised,
--   organweighted organ_verbatim,
--   normalised_organ organ_normalised,
--   finding as change,
--   dosemgkg dose
-- from generaltoxiceffects;

