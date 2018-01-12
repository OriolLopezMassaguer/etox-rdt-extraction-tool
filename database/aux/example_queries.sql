

SELECT count(*) FROm compounds where substance_status='eTox confidential'
SELECT count(*) FROm compounds where substance_status='eTox non-confidential'

SELECT * from compounds where substance_status='eTox non-confidential'
SELECT * from compounds where substance_status='eTox confidential'

-- query busqueda substructura
select * 
from etox_reports.public.compounds 
where m@>'c1ccccc1'::mol


-- studies para un compuesto
select x2."subst_id", x2."smiles", x2."molecular_weight", x3."study_id", x3."source_company" 
from "compounds" x2, "input_study" x3 
where (x2."substance_status" = 'eTox non-confidential') and (x2."subst_id" = x3."subst_id") 
order by x2."subst_id", x3."study_id" 
-- top 20 Histopat findings

select x2.x3, x2.x4 from 
	(select x5.x3 as x3, x5.x4 as x4 from 
		(select x6."histopathology_finding_standarised" as x3, count(1) as x4 
		from "hpfinding" x6 
		where 
			(x6."relevance" = 'Treatment related') and (x6."histopathology_finding_standarised" is not null)
	 	group by x6."histopathology_finding_standarised") x5 
 order by x5.x4 desc limit 20) x2


-- Compuestos con algun HP finding TR en top 20


select x2."subst_id", x2."smiles", x2."molecular_weight", x3."study_id", x3."source_company", x4."histopathology_finding_standarised" 
from "compounds" x2, "input_study" x3, "hpfinding" x4, 
(select x5.x6 as x6, x5.x7 as x7 from 
(select x8."histopathology_finding_standarised" as x6, count(1) as x7 
from "hpfinding" x8 
where (x8."relevance" = 'Treatment related') and (x8."histopathology_finding_standarised" is not null) 
group by x8."histopathology_finding_standarised") x5 order by x5.x7 desc limit 20) x9 
where (((x2."substance_status" = 'eTox non-confidential') and (x2."subst_id" = x3."subst_id")) and ((x3."subst_id" = x4."subst_id") and (x3."study_id" = x4."study_id"))) and (x4."histopathology_finding_standarised" = x9.x6)

-- Billirubinemia findings
select x2."subst_id", x2."study_id", x2."dose", x2."sex", x2."timepoint", x2."relevance", x2."timepoint_unit",
 x2."sd", x2."clinical_chemistry_parameter", x2."standarised_parameter", x2."average_fold_change", x2."finding", x2."average_value", x2."unit" 
 from "ccfinding" x2 where (x2."relevance" = 'Treatment related') and (x2."standarised_parameter" = 'Bilirubin')

-- Compounds transaminases findings intersection bilirrubinemia findings

select x2."subst_id", x2."study_id", x2."dose", x2."sex", x2."timepoint", x2."relevance", x2."timepoint_unit", x2."sd", x2."clinical_chemistry_parameter", 
x2."standarised_parameter", x2."average_fold_change", x2."finding", x2."average_value", x2."unit"
 from "ccfinding" x2, "ccfinding" x3 
 where (((x2."standarised_parameter" = 'Alkaline Phosphatase') or (x2."standarised_parameter" = 'Aspartate Aminotransferase')) or (x2."standarised_parameter" = 'Alanine Aminotransferase')) 
 and (((x3."relevance" = 'Treatment related') and (x3."standarised_parameter" = 'Bilirubin')) and (x2."subst_id" = x3."subst_id"))


