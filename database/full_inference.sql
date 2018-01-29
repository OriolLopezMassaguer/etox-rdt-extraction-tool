-- Full ontologies inference
create table infer_all_organs as select child_term,parent_term,super_parent from label_params('anatomical entity'); 

create table infer_all_morph_changes as select child_term,parent_term,super_parent from label_params('morphologic change'); 

create table infer_hpf_organs as
	select distinct
		findings_all.*, 
		infer_all_organs.parent_term inferred_organ
	from findings_all left join infer_all_organs on findings_all.organ_normalised=infer_all_organs.child_term
	where source ='HistopathologicalFinding';


create table infer_hpf_organs_morph_changes as
select distinct
	infer_hpf_organs.*,
	infer_all_morph_changes.parent_term inferred_observation 
from infer_hpf_organs left join infer_all_morph_changes on infer_hpf_organs.observation_normalised=infer_all_morph_changes.child_term;



select count(*) from infer_hpf_organs;

select count(*) from infer_hpf_organs_morph_changes;