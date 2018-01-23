Starting Scala console / Eclipse
- configure environment:
    - in /phi/users/oriol/traspaso
    - execute:
	    source environment_dev.sh
- Start eclipse:
	- using eclipse&
    - Caveats: It is not a good idea to share the eclipse workspace
        - You should create a per user workspace
        - create an empty folder to store the workspace
        - Inside eclipse
            - Switch workspace: File> Switch Workspace
            - Select the previously created folder
        - importing project in eclipse
            - start sbt from from project root
            - sbt> eclipse
            - wait until sbt creates eclipse files
            - from eclipse import the project
            - In eclipse:
                - File> Import
                - Select General\Existing project in workspace
                - Select the project source code folder
    - See https://github.com/sbt/sbteclipse
    - See http://scala-ide.org/docs/current-user-doc/gettingstarted/index.html


Executing Scala code interactively

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
- After changing code you can exit the console using:
    scala>:quit
- and reentr the console to use the change code after the recompilation:
    sbt> console



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
