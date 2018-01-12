SELECT 'Normalized' norm,source,count(*) 

  FROM findings_all
  where finding_normalised is not null
  group by source
union all
  SELECT 'Non Normalized' norm,source,count(*) 

  FROM findings_all
  
  group by source
  
