select f.*, organs.parent_term inferred_organ
from findings_all f, all_organs organs
 where source ='HistopathologicalFinding' 
 and relevance='Treatment related'
 and f.organ_normalised=organs.child_term


 create table all_organs as select child_term,parent_term,super_parent from label_params('anatomical entity') 

  create table all_changes as select child_term,parent_term,super_parent from label_params('morphologic change') 

--- findinds RAW
select count(*) from ( select f.*
from findings_all f
 where source ='HistopathologicalFinding' 
 --and relevance='Treatment related'
 ) result

 select count(*) from ( select f.*
from findings_all f
 where source ='HistopathologicalFinding' 
 ) result

-- TR findings + inferrred organs

 select count(*) from
 (select findings_all.*, all_organs.parent_term inferred_organ
from findings_all left join all_organs on findings_all.organ_normalised=all_organs.child_term
 where source ='HistopathologicalFinding' 
 --and relevance='Treatment related'
 ) result

  select count(*) from
 (select findings_all.*, all_organs.parent_term inferred_organ
from findings_all left join all_organs on findings_all.organ_normalised=all_organs.child_term
 where source ='HistopathologicalFinding' 
 and relevance='Treatment related'
 ) result

drop table inferred_tr_organs;
 create table inferred_organs as
 (select findings_all.*, all_organs.parent_term inferred_organ
from findings_all left join all_organs on findings_all.organ_normalised=all_organs.child_term
 where source ='HistopathologicalFinding' 
 --and relevance='Treatment related'
 )

 select count(*) from inferred_tr_organs

-- TR findings + inferred organs + inferred observations
  select count(*) from
 (select inferred_tr_organs.*,all_changes.parent_term inferred_observation 
from inferred_tr_organs left join all_changes on inferred_tr_organs.observation_normalised=all_changes.child_term) result


create table inferred_tr_organs_obs as 
 (select inferred_organs.*,all_changes.parent_term inferred_observation 
from inferred_organs left join all_changes on inferred_organs.observation_normalised=all_changes.child_term)

select count(*) from (select inferred_organs.*,all_changes.parent_term inferred_observation 
from inferred_organs left join all_changes on inferred_organs.observation_normalised=all_changes.child_term
 where  relevance='Treatment related') cnt;

select count(*) from (select inferred_organs.*,all_changes.parent_term inferred_observation 
from inferred_organs left join all_changes on inferred_organs.observation_normalised=all_changes.child_term
) cnt;


select * from inferred_tr_organs where observation_normalised not in (select child_term from all_changes)
limit 100

select * from label_params('adipose tissue')
  
