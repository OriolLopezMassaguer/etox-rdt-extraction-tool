CREATE TABLE ipie_compounds
(
  name character varying(100),
  smiles character varying(1000),  
  cas character varying(1000),    
  m mol
)
WITH (
  OIDS=FALSE
);
ALTER TABLE compounds
	OWNER TO postgres;
	
  ALTER TABLE compounds
   ADD COLUMN bfp bfp;
  ALTER TABLE ipie_compounds
   ADD COLUMN bfp bfp;


   update compounds
   set bfp=morganbv_fp(m);
   
   update ipie_compounds
   set bfp=morganbv_fp(m);

insert into ipie_compounds values ('Anastrozole','CC(C)(C#N)c1cc(cc(c1)C(C)(C)C#N)Cn2cncn2','120511-73-1');
insert into ipie_compounds values ('Aripiprazole','c1cc(c(c(c1)Cl)Cl)N2CCN(CC2)CCCCOc3ccc4c(c3)NC(=O)CC4','129722-12-9 ');
insert into ipie_compounds values ('Atorvastatin','CC(C)c1c(c(c(n1CC[C@H](C[C@H](CC(=O)O)O)O)c2ccc(cc2)F)c3ccccc3)/C(=N/c4ccccc4)/O','134523-00-5 ');
insert into ipie_compounds values ('Bicalutamide','CC(CS(=O)(=O)c1ccc(cc1)F)(C(=O)Nc2ccc(c(c2)C(F)(F)F)C#N)O','90357-06-5 ');
insert into ipie_compounds values ('Ciprofloxacin','c1c2c(cc(c1F)N3CCNCC3)n(cc(c2=O)C(=O)O)C4CC4','85721-33-1');
insert into ipie_compounds values ('Clotrimazole','c1ccc(cc1)C(c2ccccc2)(c3ccccc3Cl)n4ccnc4','23593-75-1');
insert into ipie_compounds values ('Diazepam','CN1c2ccc(cc2C(=NCC1=O)c3ccccc3)Cl','439-14-5');
insert into ipie_compounds values ('Esomeprazole','CC1=CN=C(C(=C1OC)C)C[S@](=O)C2=NC3=C(N2)C=CC(=C3)OC','217087-09-7');
insert into ipie_compounds values ('Felodipine','CCOC(=O)C1=C(NC(=C(C1c2cccc(c2Cl)Cl)C(=O)OC)C)C','72509-76-3');
insert into ipie_compounds values ('Gefitinib','COc1cc2c(cc1OCCCN3CCOCC3)c(ncn2)Nc4ccc(c(c4)Cl)F','184475-35-2');
insert into ipie_compounds values ('Iopromide','CN(CC(CO)O)C(=O)c1c(c(c(c(c1I)NC(=O)COC)I)C(=O)NCC(CO)O)I','73334-07-3');
insert into ipie_compounds values ('Isoconazole','c1cc(c(c(c1)Cl)COC(Cn2ccnc2)c3ccc(cc3Cl)Cl)Cl','24168-96-5');
insert into ipie_compounds values ('Metformin','CN(C)C(=N)NC(=N)N','657-24-9');
insert into ipie_compounds values ('Miconazole','c1cc(c(cc1Cl)Cl)COC(Cn2ccnc2)c3ccc(cc3Cl)Cl','22916-47-8');
insert into ipie_compounds values ('Quetiapine','c1ccc2c(c1)C(=Nc3ccccc3S2)N4CCN(CC4)CCOCCO','111974-69-7');
insert into ipie_compounds values ('Rosiglitazone','CN(CCOc1ccc(cc1)CC2C(=O)NC(=O)S2)c3ccccn3','155141-29-0');
insert into ipie_compounds values ('Sorafenib','CNC(=O)c1cc(ccn1)Oc2ccc(cc2)NC(=O)Nc3ccc(c(c3)C(F)(F)F)Cl','284461-73-0');
insert into ipie_compounds values ('Sulfamethoxazole','Cc1cc(no1)NS(=O)(=O)c2ccc(cc2)N','723-46-6');
insert into ipie_compounds values ('Vandetanib','CN1CCC(CC1)COc2cc3c(cc2OC)c(ncn3)Nc4ccc(cc4F)Br','443913-73-3');
insert into ipie_compounds values ('Ziprasidone','c1ccc2c(c1)c(ns2)N3CCN(CC3)CCc4cc5c(cc4Cl)NC(=O)C5','138982-67-9');

update ipie_compounds 
set m=smiles::mol 


create table ipie_etox_overlap_compounds as 
	select c.*,i.name 
	from compounds c, ipie_compounds i 
	where c.m@=i.m
union 
	select c.*,'Atorvastatin' name
	from compounds c
	where subst_id='GGA-SAD-0048'

select * from ipie_etox_overlap_compounds order by name

create table ipie_summary_studies_by_specie as 
select c.name,c.subst_id,s.normalised_species, count(*) 
from ipie_etox_overlap_compounds c, study s 
where c.subst_id =s.subst_id
group by c.name,c.subst_id,s.normalised_species
order by name,normalised_species


create table ipie_summary_studies_by_specie_finding_type as 
select c.name,c.subst_id,s.normalised_species,f.source, count(*) 
from ipie_etox_overlap_compounds c, study s,findings_all f 
where c.subst_id =s.subst_id and 
 s.subst_id = f.subst_id and s.study_id=f.study_id
group by c.name,c.subst_id,s.normalised_species,f.source 
order by name,normalised_species,f.source

select * from ipie_compounds where name not in (select name from ipie_etox_overlap_compounds)


select c.smiles,i.smiles
from compounds c, (select * from ipie_compounds where name not in (select name from ipie_etox_overlap_compounds)) i 
where c.bfp%i.bfp

select * from compounds where subst_id='GGA-SAD-0048'

select 'C1=CC=CC2N(C3CC3)C=CCC1=2'::mol


select *, m@>'C1=CC=CC2N(C3CC3)C=CCC1=2'::mol 
from compounds