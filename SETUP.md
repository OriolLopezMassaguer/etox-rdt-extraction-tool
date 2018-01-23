Setup:
- Source code. Clone this github repo: https://github.com/phi-grib/etox-rdt-extraction-tool
- Database import:
    connect to postgres using psql
	create database "vitic_2016_1"
	logout
	import database using psql:
		 psql --host=gea --username=postgres vitic_2016_1 < vitic_2018.sql
			- gea: database_server
			- vitic2016_1: database name
			- enter password when asked
- edit config file: conf/application.conf
	etoxdb.url="jdbc:postgresql://gea/vitic_2016_1"
		database server (gea in this example)
		database name (usually vitic_2016_1
	etoxdb.user=postgres
	etoxdb.password=XXXXXXXXXX

