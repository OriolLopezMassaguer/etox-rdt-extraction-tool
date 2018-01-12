drop table findings_summary;

create table findings_summary as

select count(*) num_findings,
x2."study_id",
x2."source", x2."relevance","normalised_administration_route",x4."normalised_species",x4."exposure_period_days",(x4."exposure_period_days" /7) +1 as week_exposure
from "compounds" x3, "study" x4, "findings_all" x2 
where 
	(x3."smiles" is not null) 
	and (x4."subst_id" = x3."subst_id") 
			
	and (x4."study_id" = x2."study_id") and (x4."subst_id" = x2."subst_id")
	and (x3."smiles" is not null)
group by x2."study_id",x2."source", x2."relevance","normalised_administration_route",x4."normalised_species",x4."exposure_period_days",(x4."exposure_period_days" /7) +1  

