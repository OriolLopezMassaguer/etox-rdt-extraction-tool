SELECT distinct relevance  FROM ccfindiNg
SELECT distinct initcap(relevance)  FROM ccfindiNg

update ccfinding
set relevance=initcap(relevance)


SELECT distinct finding  FROM ccfindiNg

update ccfinding
set finding='Increased' where finding='Increase'
update ccfinding
set finding='Decreased' where finding='Decrease'


SELECT distinct sex  FROM ccfindiNg

update ccfindiNg set sex=initcap(sex)



SELECT distinct relevance  FROM hpfinding
update hpfinding
set relevance=initcap(relevance)

SELECT distinct sex  FROM hpfinding

update hpfinding set sex=initcap(sex)
