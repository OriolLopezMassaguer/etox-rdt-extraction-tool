-- Full ontologies inference
create table infer_findings_all_complete as
select findings_all where organ_normalised is not null and observation_normalised is not null;

drop table infer_all_organs;
create table infer_all_organs as select child_term,parent_term,super_parent from label_params('anatomical entity');

drop table infer_all_morph_changes;
create table infer_all_morph_changes as select child_term,parent_term,super_parent from label_params('morphologic change');

drop table infer_hpf_organs;
create table infer_hpf_organs as
	select distinct
		findings_all.*, 
		infer_all_organs.parent_term inferred_organ
	from findings_all left join infer_all_organs on findings_all.organ_normalised=infer_all_organs.child_term
	where source ='HistopathologicalFinding';

drop table infer_hpf_organs_morph_changes;
create table infer_hpf_organs_morph_changes as
select distinct
	infer_hpf_organs.*,
	infer_all_morph_changes.parent_term inferred_observation 
from infer_hpf_organs left join infer_all_morph_changes on infer_hpf_organs.observation_normalised=infer_all_morph_changes.child_term;

select count(*) from infer_hpf_organs_morph_changes where inferred_organ =organ_normalised;
select count(*) from infer_hpf_organs_morph_changes where inferred_observation =observation_normalised;

select * from infer_hpf_organs_morph_changes where inferred_observation is null limit 10;


select count(*) from infer_hpf_organs;

select count(*) from infer_hpf_organs_morph_changes;