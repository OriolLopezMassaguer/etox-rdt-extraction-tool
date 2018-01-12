select distinct
	gte."SUBST_ID" subst_id,
	gte.study_id,
	cast(gte."Bodyweight" as numeric(10,2)) bodyweightrelevance, 
	gte."BodyweightRelevance" bodyweightrelevance,
	gte."BodyweightUnit" bodyweightunit,
	cast(gte."DoseMgKg"  as numeric(10,2)) dosemgkg,
	gte."Mortality" ,
	cast(gte."Sd" as numeric(10,2)) sd,
	gte."Sex" sex,
	cast(gte."StandardisedBodyweight" as numeric(10,2)) standardisedbodyweight,
	gte. "StandardisedSex",	
	"Timepoint",
	gte."TimepointUnit" timepointunit
from "input_GeneralToxicEffects" gte  
where "Mortality"  like '%.%'
or "Timepoint"  like '%.%'


select distinct
	gte."SUBST_ID" subst_id

from "input_GeneralToxicEffects" gte  
where "Mortality"  like '%.%'
or "Timepoint"  like '%.%'