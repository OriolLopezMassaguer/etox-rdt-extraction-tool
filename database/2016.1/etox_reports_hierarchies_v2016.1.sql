drop  table onto_terms_relations cascade;

create table onto_terms_relations as 
SELECT  
	rel."ONTOLOGY_TERM_ID" child_id,
	(select "TERM_NAME" from  input_onto_etox_ontology_terms where "ONTOLOGY_TERM_ID" = rel."ONTOLOGY_TERM_ID") as child_term,
	rel."RELATED_ONTOLOGY_TERM_ID" parent_id, 
	rel."RELATIONSHIP",
	(select "TERM_NAME"  from  input_onto_etox_ontology_terms where "ONTOLOGY_TERM_ID" = rel."RELATED_ONTOLOGY_TERM_ID") as parent_term
FROM input_onto_etox_ontology_relationships  rel


CREATE OR REPLACE FUNCTION label_params(IN term text)
  RETURNS TABLE(level integer, path integer[], path_terms text[], child_id character varying, child_term text, parent_id character varying, parent_term text, super_parent text) AS
$BODY$
WITH RECURSIVE included_terms(level,path, path_terms ,child_id, child_term, parent_id, parent_term) AS (
		    select 
			2 as level,
			array[1,2] AS path,
			ARRAY[parent_term,child_term]::text[] AS path_terms,
			child_id, child_term::text, 
			parent_id, parent_term::text
		    from onto_terms_relations 
		    where onto_terms_relations.parent_term=$1
		  UNION ALL  
		    select 
			level+1 as level,
			(it.path || level+1) path,
			array_append(it.path_terms,cast(t.child_term as text)) AS path_terms,
			t.child_id, t.child_term::text,
			t.parent_id, t.parent_term::text
		    from onto_terms_relations t, included_terms it
		    where t.parent_id=it.child_id 	    
		  )
		select distinct 
		    1 as level,
		    array[1] AS path,
		    ARRAY[parent_term]::text[] AS path_terms,
		    child_id,
		    child_term::text child_term, 
		    parent_id,
		    parent_term::text,
		    parent_term::text super_parent_term
		from onto_terms_relations 
		where onto_terms_relations.parent_term=$1
	union all
		SELECT  
			included_terms.level,
			path, 
			path_terms,
			child_id,
			child_term,
			parent_id, 
			parent_term,
			path_terms[1]::text super_parent
		FROM included_terms
$BODY$
  LANGUAGE sql VOLATILE
  COST 100
  ROWS 1000;
ALTER FUNCTION label_params(text)
  OWNER TO postgres;


select * from label_params('liver')
-- drop view HPF_hierarchy;
-- 
-- drop  table onto_terms_relations cascade;

-- create or replace view HPF_hierarchy as	
-- 	select distinct 1,
-- 		    array[1] AS path,
-- 		    ARRAY[parent_term]::text[] AS path_terms,
-- 		    child_id, parent_term::text child_term, 
-- 		    parent_id, parent_term::text
-- 		    from onto_terms_relations 
-- 		   where onto_terms_relations.parent_term='morphologic change'
-- 	union all
-- 	select * from HPF_hierarchy_prev;
-- 
-- 	create or replace view HPF_hierarchy_prev as	
-- 		WITH RECURSIVE included_terms(level,path, path_terms ,child_id, child_term, parent_id, parent_term) AS (
-- 		    select 2,
-- 		    array[1,2] AS path,
-- 		    ARRAY[parent_term,child_term]::text[] AS path_terms,
-- 		    child_id, child_term::text, 
-- 		    parent_id, parent_term::text
-- 		    from onto_terms_relations 
-- 		    where --parent_id='MA:0003000'  
-- 		       onto_terms_relations.parent_term='morphologic change'
-- 		  UNION ALL  
-- 		    select 
-- 			level+1,
-- 			(it.path || level+1) path,
-- 			array_append(it.path_terms,cast(t.child_term as text)) AS path_terms,
-- 			t.child_id, t.child_term::text,
-- 			t.parent_id, t.parent_term::text
-- 		    from onto_terms_relations t, included_terms it
-- 		    where t.parent_id=it.child_id 	    
-- 		  )
-- 		SELECT  * 
-- 		FROM included_terms
-- 		order by path;
-- 
-- create or replace view organ_hierarchy as	
-- 	select distinct 1,
-- 		    array[1] AS path,
-- 		    ARRAY[parent_term]::text[] AS path_terms,
-- 		    child_id, parent_term::text child_term, 
-- 		    parent_id, parent_term::text
-- 		    from onto_terms_relations 
-- 		   where onto_terms_relations.parent_term='morphologic change'
-- 	union all
-- 	select * from HPF_hierarchy_prev;
-- 
-- 	create or replace view HPF_hierarchy_prev as	
-- 		WITH RECURSIVE included_terms(level,path, path_terms ,child_id, child_term, parent_id, parent_term) AS (
-- 		    select 2,
-- 		    array[1,2] AS path,
-- 		    ARRAY[parent_term,child_term]::text[] AS path_terms,
-- 		    child_id, child_term::text, 
-- 		    parent_id, parent_term::text
-- 		    from onto_terms_relations 
-- 		    where --parent_id='MA:0003000'  
-- 		       onto_terms_relations.parent_term='morphologic change'
-- 		  UNION ALL  
-- 		    select 
-- 			level+1,
-- 			(it.path || level+1) path,
-- 			array_append(it.path_terms,cast(t.child_term as text)) AS path_terms,
-- 			t.child_id, t.child_term::text,
-- 			t.parent_id, t.parent_term::text
-- 		    from onto_terms_relations t, included_terms it
-- 		    where t.parent_id=it.child_id 	    
-- 		  )
-- 		SELECT  * 
-- 		FROM included_terms
-- 		order by path;		
-- 
-- drop table hpf_levels;
-- 
-- create table hpf_levels as
-- select distinct 
-- 	HPF_hierarchy.path_terms[1] level1,
-- 	HPF_hierarchy.path_terms[2] level2,
-- 	HPF_hierarchy.path_terms[3] level3,
-- 	HPF_hierarchy.path_terms[4] level4,
-- 	HPF_hierarchy.path_terms[5] level5,
-- 	HPF_hierarchy.path_terms[6] level6,
-- 	HPF_hierarchy.path_terms[7] level7,
-- 	HPF_hierarchy.path_terms[8] level8,
-- 	HPF_hierarchy.child_term
-- from HPF_hierarchy 
-- 
-- drop table all_findings_levels;
-- create table all_findings_levels as
-- select 
-- 	find.*, lev.* 
-- from findings_all_grouped find left join hpf_levels lev on (find.finding_normalised= lev.child_term)


