--- we obtain all compounds with a "Treatment related" Histipathological finding in the "Liver"
drop table liver_hpf_tr_substs;
create table liver_hpf_tr_substs as 
select 
	distinct subst_id
from 
	"hpfinding" x6 
where 
	(x6."relevance" = 'Treatment Related') and histopathology_organ_affected_standarised like 'Liver';

-- select count(*) from liver_hpf_tr_substs

drop table liver_hpf_tr_ccfindings;
create table liver_hpf_tr_ccfindings as 
select distinct 
	c.subst_id,
	c.smiles,
	c.inchi,
	c.substance_status,
	c.common_name,
	c.cas_number,
   1 as hptx,
  s.strain,
  s.report_number,
  s.standarised_species,
  s.age_unit,
  s.study_quality_assesment,
  s.source_company,
  s.administration_route,
  s.vehicle,
  ccf.dose,
  ccf.sex,
  ccf.timepoint,
  ccf.timepoint_unit,
  ccf.relevance,
  ccf.sd,
  ccf.clinical_chemistry_parameter,
  ccf.standarised_parameter,
  ccf.average_fold_change,
  ccf.finding,
  ccf.average_value,
  ccf.unit 
from ccfinding ccf, study s , compounds c
where c.subst_id in (select subst_id from liver_hpf_tr_substs) 
and c.subst_id=s.subst_id and s.subst_id=ccf.subst_id and s.study_id=ccf.study_id;

-- We obtain all HTP Treatmenent related findinds
-- for the compounds "TR" & "Liver" & HPF (tagged 1) (query1)
-- for the other compounds (tagged0)
drop table liver_hpf_tr_hpfindings;
create table liver_hpf_tr_hpfindings as 
select distinct
	c.subst_id,
	c.smiles,
	c.inchi,
	c.substance_status,
	c.common_name,
	c.cas_number,
 1 as hptx,	
  s.strain,
  s.report_number,
  s.standarised_species,
  s.age_unit,
  s.study_quality_assesment,
  s.source_company,
  s.administration_route,
  s.vehicle,
  hpf.num_animals_total,
  hpf.num_animals_affected,
  hpf.dose,
  hpf.sex,
  hpf.grade,
  hpf.timepoint,
  hpf.timepoint_unit,
  hpf.relevance,
  hpf.histopathology_finding,
  hpf.histopathology_finding_standarised,
  hpf.histopathology_organ_affected,
  hpf.histopathology_organ_affected_standarised 
from hpfinding hpf, study s , compounds c
where c.subst_id in (select subst_id from liver_hpf_tr_substs)  
and c.subst_id=s.subst_id 
and s.subst_id=hpf.subst_id and s.study_id=hpf.study_id;


drop table liver_hpf_tr_gtefindings;
create table liver_hpf_tr_gtefindings as 
select distinct
	c.subst_id,
	c.smiles,
	c.inchi,
	c.substance_status,
		c.common_name,
	c.cas_number,
 1 as hptx,	
  s.strain,
  s.report_number,
  s.standarised_species,
  s.age_unit,
  s.study_quality_assesment,
  s.source_company,
  s.administration_route,
  s.vehicle,
  gte.bodyweigth,
  gte.bodyweightrelevance,
  gte.bodyweightunit,
  gte.dosemgkg, 
  gte.mortality,
  gte.sd,
  gte.sex, 
  gte.standardisedbodyweight,
  gte.standardisedsex, 
  gte.timepoint,
  gte.timepointunit 
from generaltoxiceffects gte, study s , compounds c
where c.subst_id in (select subst_id from liver_hpf_tr_substs)  
and c.subst_id=s.subst_id 
and s.subst_id=gte.subst_id and s.study_id=gte.study_id;

drop table liver_hpf_tr_gnefindings;
create table liver_hpf_tr_gnefindings as 
select distinct
	c.subst_id,
	c.smiles,
	c.inchi,
	c.substance_status,
		c.common_name,
	c.cas_number,
 1 as hptx,	
  s.strain,
  s.report_number,
  s.standarised_species,
  s.age_unit,
  s.study_quality_assesment,
  s.source_company,
  s.administration_route,
  s.vehicle,
  gne.dosemgkg,
  gne.grade,
  gne.grosspathologyfinding,
  gne.grosspathologyorganaffected,
  gne.numberofananimalsaffected,
  gne.relevance,
  gne.sex,
  gne.standardisedgrosspathology,
  gne.standardisedorgan,
  gne.standardisedsex,
  gne.timepoint,
  gne.timepointunit,
  gne.totalnumberofanimals  
from grossnecropsy gne, study s , compounds c
where c.subst_id in (select subst_id from liver_hpf_tr_substs)  
and c.subst_id=s.subst_id 
and s.subst_id=gne.subst_id and s.study_id=gne.study_id;


drop table liver_hpf_tr_effectfindings;
create table liver_hpf_tr_effectfindings as 
select distinct
	c.subst_id,
	c.smiles,
	c.inchi,
	c.substance_status,
		c.common_name,
	c.cas_number,
 1 as hptx,	
  s.strain,
  s.report_number,
  s.standarised_species,
  s.age_unit,
  s.study_quality_assesment,
  s.source_company,
  s.administration_route,
  s.vehicle,
  elev.effectleveltype,
  elev.effectlevelunit,
  elev.effectlevelvalue 
from effectlevels elev, study s , compounds c
where c.subst_id in (select subst_id from liver_hpf_tr_substs)  
and c.subst_id=s.subst_id 
and s.subst_id=elev.subst_id and s.study_id=elev.study_id;

drop table liver_hpf_tr_organweightsfindings;
create table liver_hpf_tr_organweightsfindings as 
select distinct
	c.subst_id,
	c.smiles,
	c.substance_status,
		c.common_name,
	c.cas_number,
 1 as hptx,	
  s.strain,
  s.report_number,
  s.standarised_species,
  s.age_unit,
  s.study_quality_assesment,
  s.source_company,
  s.administration_route,
  s.vehicle,  
  ow.averagefoldchange,
  ow.averageweight,
  ow.dosemgkg,
  ow.finding,
  ow.organweighted,
  ow.relevance,
  ow.sd,
  ow.sex,
  ow.standardisedorgan,
  ow.standardisedsex,
  ow.timepoint,
  ow.timepointunit,
  ow.unit
from organweights ow, study s , compounds c
where c.subst_id in (select subst_id from liver_hpf_tr_substs)  
and c.subst_id = s.subst_id 
and s.subst_id = ow.subst_id and s.study_id = ow.study_id;




