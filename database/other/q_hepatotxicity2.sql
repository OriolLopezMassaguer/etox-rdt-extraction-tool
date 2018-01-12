drop table hptx_substs;
create table hptx_substs as 
select distinct subst_id
from "hpfinding" x6 
where 	(x6."relevance" = 'Treatment Related')
 and histopathology_organ_affected_standarised like 'Liver'

select * from hptx_ccf_for_hptx_tr

create table hptx_ccf_for_hptx_tr as 
select distinct 
	c.subst_id,
	c.smiles,
	c.substance_status,
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
where c.subst_id in (select subst_id from hptx_substs) 
and c.subst_id=s.subst_id and s.subst_id=ccf.subst_id and s.study_id=ccf.study_id 
and ccf.relevance = 'Treatment Related'
union
select distinct 
	c.subst_id,
	c.smiles,
	c.substance_status,
  0 as hptx,
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
where c.subst_id not in (select subst_id from hptx_substs) 
and c.subst_id=s.subst_id and s.subst_id=ccf.subst_id and s.study_id=ccf.study_id 
and ccf.relevance = 'Treatment Related'

select * from hptx_hpf_for_hptx_tr

create table hptx_hpf_for_hptx_tr as 
select distinct
	c.subst_id,
	c.smiles,
	c.substance_status,
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
where c.subst_id in (select subst_id from hptx_substs) 
and c.subst_id=s.subst_id 
and s.subst_id=hpf.subst_id and s.study_id=hpf.study_id 
and hpf.relevance = 'Treatment Related'
union
select distinct
	c.subst_id,
	c.smiles,
	c.substance_status,
 0 as hptx,	
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
where c.subst_id not in (select subst_id from hptx_substs) 
and c.subst_id=s.subst_id 
and s.subst_id=hpf.subst_id and s.study_id=hpf.study_id 
and hpf.relevance = 'Treatment Related'


select count(*) ,hptx 
from hptx_hpf_for_hptx_tr
group by hptx

select count(*) ,hptx 
from hptx_ccf_for_hptx_tr
group by hptx