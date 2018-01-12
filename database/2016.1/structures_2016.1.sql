create extension rdkit;

select * from compounds

update compounds
set  
 m=mol_from_smiles(smiles::cstring);

update compounds
set 
 inchikey=mol_inchikey(m);

 ----

truncate table external_compounds;

update external_compounds 
set molecule=mol_from_smiles(smiles::cstring);

--select * from external_compounds where smiles like 'CC1=C(NN=C%'

--select mol_from_smiles('CC%'::cstring)

update external_compounds
set smiles= 'OC(=O)c1ccc(cc1)N\2N\C(NC2=C\3C=CC=CC3=O)=C/4C=CC=CC4=O'
where id='82';

update external_compounds
set smiles= 'CC\1=C(CC(O)=O)c2cc(F)ccc2C1=C\c3ccc(cc3)[S](C)=O'
where id='119';

update external_compounds
set smiles= 'CC1=C(N\N=C\2C=CC=C(C2=O)c3cccc(c3)C(O)=O)C(=O)N(N1)c4ccc(C)c(C)c4'
where id='85';


update external_compounds
set inchikey_part=left(inchikey,25);

update compounds
set inchikey_part=left(inchikey,25);


----

select * 
from compounds inner join external_compounds  on compounds.inchikey_part = external_compounds.inchikey_part


select subst_id
from compounds inner join external_compounds  on compounds.inchikey_part = external_compounds.inchikey_part



select subst_id,source,count(*)
from findings_all where subst_id in (select subst_id from compounds inner join external_compounds  on compounds.inchikey_part = external_compounds.inchikey_part)
group by subst_id,source


select * from findings_all where subst_id='GGA-SAD-0351'
select * from findings_all where subst_id='AZ_GGA_200001874'



