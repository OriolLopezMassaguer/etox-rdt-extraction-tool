CREATE OR REPLACE FUNCTION convert_to_numeric(v_input text)
RETURNS numeric AS $$
DECLARE v_int_value numeric DEFAULT NULL;
BEGIN
    BEGIN
        v_int_value := v_input::numeric;
    EXCEPTION WHEN OTHERS THEN
        RAISE NOTICE 'Invalid integer value: "%".  Returning NULL.', v_input;
        RETURN null;
    END;
RETURN v_int_value;
END;
$$ LANGUAGE plpgsql;


select 
	ccf.subst_id,
	ccf.study_id,
	cast(ccf.dose as numeric(10,2)),
	ccf.sex,
	cast(ccf.timepoint as integer),
	ccf.timepoint_unit,
	ccf.relevance,
	cast(ccf.sd as numeric(10,2)),
	ccf.clinical_chemistry_parameter,--Clinical Chemistry Parameter
	ccf.standarised_parameter ,--Standardised Parameter
	cast(ccf.average_fold_change as numeric(10,2)),--Average Fold Change
	ccf.finding,--Finding,
	ccf.unit,
	ccf.average_value,
	convert_to_numeric(ccf.average_value) 
from input_ccfinding ccf 
where convert_to_numeric(ccf.average_value) is null and ccf.average_value is not null