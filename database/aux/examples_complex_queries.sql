SELECT count(*) FROM histopathological_findings;

SELECT count(*) FROM clinical_chemical_findings;

SELECT length(img_base64) FROM compounds ode

select count(*) from etox_reports.public.compounds 

select * from etox_reports.public.compounds 
where m@>'c1cccc2c1nncc2'::mol ;

select count(*) from etox_reports.public.compounds 
where m@>'c1ccccc1' ;


----Q1 organ=Adrenal & finding=Hyperplasia

select * from etox_reports.public.hpfinding 
where histopathology_organ_affected ='Adrenal'  
and histopathology_finding like '%hyperplasia%'

----Q2 organ=Liver or kidney & relevance=TR & specie=(Mice,Mouse)

select stu.*,hpf.* 
from etox_reports.public.hpfinding hpf, etox_reports.public.study stu 
where 
hpf.histopathology_organ_affected in ('Liver','Kidney') and 
hpf.relevance = 'Treatment related' and
stu.species in ('Mice','Mouse') and
stu.study_id = hpf.study_id and
stu.subst_id = hpf.subst_id 

select distinct species from study order by species


select x2."subst_id", x2."smiles", x2."molecular_weight", x3."study_id", x3."source_company", x4."histopathology_finding_standarised" 
from "compounds" x2, "input_study" x3, "hpfinding" x4 where ((x2."subst_id" = x3."subst_id")) 
and (((((x3."subst_id" = x4."subst_id") and (x3."study_id" = x4."study_id")) and (x3."species" = 'Mouse')) 
and (x4."histopathology_organ_affected" = 'Liver')) and (x4."relevance" = 'Treatment related'))


----Q3 organ=Liver & finding="No*"

select * from etox_reports.public.hpfinding 
where histopathology_organ_affected ='Liver'  
and histopathology_finding like 'No%'


----Q4 organ=Liver & finding="No*"

select * from etox_reports.public.hpfinding 
where histopathology_organ_affected ='Liver'  
and (histopathology_finding like 'No%' or histopathology_finding like 'Without%')