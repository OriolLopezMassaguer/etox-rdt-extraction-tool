# Running extractions

* You can run extractions already developed:
    * The source code is located at:
    * /phi/users/oriol/traspaso/etox_RDT_tool/etox-rdt-extraction-tool/src/main/scala/models/extractions

	* Extraction examples are located in package  models.extractions
	* You can execute extractions using:
	    * scala> models.extractions.example1_HPF_liver.extract
		* scala> models.extractions.example2_CCF_transaminases.extract
		* scala> models.extractions.example3_Organ_weights.extract
		* scala> example4_HPF_clusters.extract
	* Extracted data is stored in folders (path relative to /phi/users/oriol/traspaso/etox_RDT_tool/etox-rdt-extraction-tool in the example)
		* /phi/users/oriol/traspaso/etox_RDT_tool/data/example1
		* /phi/users/oriol/traspaso/etox_RDT_tool/data/example2
		* /phi/users/oriol/traspaso/etox_RDT_tool/data/example3
		* /phi/users/oriol/traspaso/etox_RDT_tool/data/example4

# Extraction API
* The extractions defined are based on the API call:
	models.etox_reports.Observations_querys.getEndpoints_v3

* def getEndpoints_v3(
    * observationSource:
            * one of the type of findings
            * "ClinicalChemicalFinding"
            * "UrianalysisFinding"
            * "HistopathologicalFinding"
            * "ClinicalHaematologicalFinding"
            * "OrganWeights"
    * observations:
	    * list of observations (can be qualitative "necrosis" or quatitative "ALT")
               * see below for possible values "How to obtain possible filtering values"
                * Optional: If not passed or empty list is passed we don't filter by kinds of observations (only from the source above)
        * admin_routes:
		        * see below for possible values "How to obtain possible filtering values"
		        * Optional: If not passed or empty list is passed we don't filter by admin_routes
    * species:
	    * list of species
		    * Optional: If not passed or empty list is passed we don't filter by species
    * relevanceFiltering:
        * If true only "Treatment related" findings are extracted
		* Optional: if not provided true is assumed
    * changes:
        * Only appies to quantitative findings: "ClinicalChemicalFinding"	"UrianalysisFinding" "ClinicalHaematologicalFinding" "OrganWeights"
		* Possible values: "Increased" or "Decreased"
		* Optional: if not provided we assume "Increased"
    * exposure_period:
		* Possible values:
		* Some(min_days,max_days) -> only studies between >=min_days and <=max_days
		* None -> no filtering by exposure period
    * organs:
		* list of organs
		* Optional: If not passed or empty list is passed we don't filter by organs
    * sex_list:
		* list of sex
		* Optional: If not passed or empty list is passed we don't filter by sex
    * dropstructure:
		* true by default. If true only the id but not the structure is obtained.
		* Structures are stored in "compounds" table
    * patterns:
		* Dictionary to group findings by clusters: cluster -> list of findings
		* Scala type is: Map[String, List[String]]
		* See example 4 for how to pass clusters from plain text files
    * conditions_tag:
		* Tag to specify the conditions of the endpoints obtained:
		* usually: species_tag + "_" + routes_tag + "_" + exposure_tag
    * patterntag: additional tag for clusters
    * path: path to generate output files
    * aggregation:
	* Aggregation mode.
	    * If not specified we assume Observations_querys.LOAEL
	    * Other possible values are:
            * Observations_querys.Existence (qualitative 1 if finding found 0 if no finding found)
            * Observations_querys.LOAEL (minimal dose at wich finding is found)
            * Observations_querys.MaxFoldChange (not used, data lacking)
            * Observations_querys.NumFindings (num of findings row count)
            * Observations_querys.NumFindingsDistinct (num of distinct findings row count without duplicate findings)
        * Output:
		    * 3 dataframes (tuple of 3 dataframes )are generated and exported to 3 TSV files.
		        * unpivoted + unagregated data: Data extracted according to filtering conditions + inferred data in case of HPF findings
		        * unpivoted data: Data extracted according to filtering conditions + inferred data in case of HPF findings + aggregated at finding/compound level
		        * data qsar like (pivoted + aggregated): Data extracted according to filtering conditions + inferred data in case of HPF findings + aggregated at finding/compound level + pivoted (endpoints (findings or clusters) are transferred to columns



# How to obtain possible filtering values

* From scala console execute:

    * scala> models.etox_reports.Observations_filtering_values.filteringValues("OrganWeights")
	* scala> models.etox_reports.Observations_filtering_values.filteringValues("UrianalysisFindingsFindings")
    * Other possible values are:
        *	Findings_Types
        *	OrganWeights
        *	UrianalysisFindingsFindings
        *	ClinicalHaematologicalFinding
        *	HistopathologicalFinding
        *	ClinicalChemicalFinding
        *	NormalisedSex
        *	NormalisedAdminRoute
        *	NormalisedSpecies
        *	OrgansNormalied

    * the values can also be exported to files:
	    * scala> models.etox_reports.Observations_filtering_values.export_filtering_values(path_where_exporting_is_done)
