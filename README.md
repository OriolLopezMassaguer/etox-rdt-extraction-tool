Setup:
- clone this github repo: https://github.com/phi-grib/etox-rdt-extraction-tool
- database: import database.sql
	connect to postgres using psql
	create database "vitic2016_1"
	logout
	import database using psql:
		 psql --host=gea --username=postgres vitic2016_1 < vitic_2016_1_20180111.sql
			- gea: database_server
			- vitic2016_1: database name
			- enter password when asked
- edit config file: conf/application.conf
	etoxdb.url="jdbc:postgresql://gea/vitic2016_1" 
		database server (gea in this example)
		database name (usually vitic2016_1
	etoxdb.user=postgres
	etoxdb.password=postgres

Statring Scala console / Eclipse
- configure environment: 
	in /phi/users/oriol/traspaso
- execute:
	source environment_dev.sh

- Start eclipse:
	eclipse&
	
- Caveats: maybe is not a good idea to share the eclipse workspace
    - You can create a per user works

- Start sbt tool:
	from /phi/users/oriol/traspaso/etox_RDT_tool/etox-rdt-extraction-tool
	from unix prompt run "sbt"
		Simple build tool scala (full reference https://www.scala-sbt.org/0.13/docs/index.html)
		Very complex tool, we only use basic functions
	from sbt start scala console
	sbt> console
	
- Executing code:
	from scala console
	scala>

Performing extractions:

- You can run extractions already developed:
	The source code is located at:
		/phi/users/oriol/traspaso/etox_RDT_tool/etox-rdt-extraction-tool/src/main/scala/models/extractions

	Extraction examples are located in package  models.extractions
	You can execute extractions using:
		scala> models.extractions.example1_HPF_liver.extract 
		scala> models.extractions.example2_CCF_transaminases.extract
		scala> models.extractions.example3_Organ_weights.extract
		scala> example4_HPF_clusters.extract
	Extracted data is stored in folders (path relative to /phi/users/oriol/traspaso/etox_RDT_tool/etox-rdt-extraction-tool in the example)
		/phi/users/oriol/traspaso/etox_RDT_tool/data/example1
		/phi/users/oriol/traspaso/etox_RDT_tool/data/example2
		/phi/users/oriol/traspaso/etox_RDT_tool/data/example3
		/phi/users/oriol/traspaso/etox_RDT_tool/data/example4
- Extraction API: (to be completed

	The extractions defined are based on the API call:
	models.etox_reports.Observations_querys.getEndpoints_v3

	def getEndpoints_v3(
	    observationSource: 
		one of the type of findings
		"ClinicalChemicalFinding"
		"UrianalysisFinding"
		"HistopathologicalFinding"
		"ClinicalHaematologicalFinding"
		"OrganWeights"
	    observations: 
		list of observations (can be qualitative "necrosis" or quatitative "ALT")
		from ...
		Optional: If not passed or empty list is passed we don't filter by kinds of observations (only from the source above)
	    admin_routes: 
		list of administration routes from ...
		Optional: If not passed or empty list is passed we don't filter by admin_routes
	    species: 
		list of species
		Optional: If not passed or empty list is passed we don't filter by species
	    relevanceFiltering: 
		If true only "Treatment related" findings are extracted
		Optional: if not provided true is assumed
	    changes: 
		Only appies to quantitative findings: "ClinicalChemicalFinding"	"UrianalysisFinding" "ClinicalHaematologicalFinding" "OrganWeights"
		Possible values: "Increased" or "Decreased"
		Optional: if not provided we assume "Increased"
	    exposure_period:
		Possible values: 
			Some(min_days,max_days) -> only studies between >=min_days and <=max_days 
			None -> no filtering by exposure period
	    organs: 
		list of organs
		Optional: If not passed or empty list is passed we don't filter by organs
	    sex_list:
		list of sex
		Optional: If not passed or empty list is passed we don't filter by sex
	    dropstructure:
		true by default. If true only the id but not the structure is obtained.
		Structures are stored in "compounds" table
	    patterns: 
		Dictionary to group findings by clusters: cluster -> list of findings
		Scala type is: Map[String, List[String]] 
		See example 4 for how to pass clusters from plain text files
	    conditions_tag: 
		Tag to specify the conditions of the endpoints obtained:
		usually: species_tag + "_" + routes_tag + "_" + exposure_tag
	    patterntag: 
		additional tag for clusters
	    path:
		path to generate output files
	    aggregation:
		Aggregation mode.
		If not specified we assume 
                    Observations_querys.LOAEL
		Other possible values are:
			Observations_querys.Existence (qualitative 1 if finding found 0 if no finding found)
			Observations_querys.LOAEL (minimal dose at wich finding is found)
			Observations_querys.MaxFoldChange (not used, data lacking)
			Observations_querys.NumFindings (num of findings row count)
			Observations_querys.NumFindingsDistinct (num of distinct findings row count without duplicate findings)
	Output:
		3 dataframes are generated and exported to 3 TSV files.
		unpivoted + unagregated data: Data extracted according to filtering conditions + inferred data in case of HPF findings
		unpivoted data: Data extracted according to filtering conditions + inferred data in case of HPF findings + aggregated at finding/compound level
		data qsar like (pivoted + aggregated): Data extracted according to filtering conditions + inferred data in case of HPF findings + aggregated at finding/compound level + pivoted (endpoints (findings or clusters) are transferred to columns

- How to obtain possible filtering values:

  From scala console execute:

	scala> models.etox_reports.Observations_filtering_values.filteringValues("OrganWeights")
	scala> models.etox_reports.Observations_filtering_values.filteringValues("UrianalysisFindingsFindings")
	 Other possible values are:

		Findings_Types

		OrganWeights
		UrianalysisFindingsFindings
		ClinicalHaematologicalFinding
		HistopathologicalFinding
		ClinicalChemicalFinding
		
		NormalisedSex
		NormalisedAdminRoute
		NormalisedSpecies
		OrgansNormalied

	the values can also be exporte to files:

	scala> models.etox_reports.Observations_filtering_values.export_filtering_values(path_where_exporting_is_done)


- Internal RDT
	Data model main entities
		compounds->studies->findings_all
		Compounds: Structure etc
		Studies: Experimental conditions: species, admin route, exposure period etc.
		findings_all: Unified findings table. Stores different quantitative and qualitative findings in a unified way:
					finding types: obatainde by "select distinct source from findings_all"
						'ClinicalChemicalFinding'
						'UrianalysisFinding'
						'HistopathologicalFinding'
						'ClinicalHaematologicalFinding'
						'OrganWeights'
		Important note: findings_all only contains facts without inferences. To complete HPF findings inference must be applied. But other findings are didn't need inference.
	Ontologies:
		SQL inference:
			Execute from pgadmin or psql the following query
			psql> select * from label_params('liver')
				A table with full recurvive expansion
			psql> select * from label_params('necrosis')
				A table with full recurvive expansion
		Scala console
			"liver" expansion
			scala> models.ontologies.Ontologies_DB.expandTerm("liver")
			res1: List[String] = List(hepatic duct intrahepatic part, liver middle lobe, liver, zone i, liver lobe, liver left medial lobe, liver perisinusoidal space, intralobular bile duct, intrahepatic part of right hepatic duct, liver sinusoid, liver lobule, liver left lateral lobe, liver parenchyma, liver quadrate lobe, liver bare area, portal triad, liver papillary process, bile duct intrahepatic part, interlobular bile duct, intrahepatic part of left hepatic duct, liver left lobe, liver right lobe, liver, zone ii, intrahepatic bile duct epithelium, liver, zone iii, liver, bile canaliculus, liver caudate lobe, liver acinus, portal lobule)
			
			"necrosis" expansion
			scala> models.ontologies.Ontologies_DB.expandTerm("necrosis")
			res3: List[String] = List(necrosis, hepatocellular, necrosis, squamous epithelium, necrosis, leydig cell, necrosis, respiratory epithelium, necrosis, zonal, diffus, necrosis, myocardiocytes, necrosis, testicular, necrosis, lymphocytes, necrosis, adnexal, necrosis, soft tissue, necrosis, zonal, single cell necrosis, necrosis, renal tubules, necrosis, olfactory epithelium, necrosis, myofiber, necrosis, zonal, periportal, necrosis, zonal, midzonal, necrosis, tubular, necrosis, endometrial, necrosis, focal/multifocal, necrosis, epidermal, necrosis, myometrial, necrosis, mucosa, necrosis, papillary, single cell necrosis, epithelial, cell debris, luminal, necrosis, fibrinoid, necrosis, testis, necrosis, neuronal, necrosis, zonal, centrilobular, fat necrosis, necrosis, necrosis, epithelial)



TO BE DOCUMENTED:
	data model
	data import
	full ontology expansion
