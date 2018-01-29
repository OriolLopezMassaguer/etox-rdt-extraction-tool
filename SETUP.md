Setup:
* Source code
    * Clone this github repo: https://github.com/phi-grib/etox-rdt-extraction-tool
* Database import:
    * connect to postgres using psql
    * create database "vitic_2016_1"
    * logout
    * import database using psql:
        * psql --host=gea --username=postgres vitic_2016_1 < vitic_2018.sql
			* where
		        * gea: database_server
                * vitic2016_1: database name (previously created)
			    * enter password when asked
        * file located in /phi/users/oriol/traspaso/etox_RDT_tool
	* contains the full database dump with confidential data and can not be uploaded to gihub
* Setup database connection form app:
    * edit config file: conf/application.conf
    * etoxdb.url="jdbc:postgresql://gea/vitic_2016_1"
        * database server (gea in this example)
	    * database name (usually vitic_2016_1
	    * etoxdb.user=postgres
	    * etoxdb.password=XXXXXXXXXX

