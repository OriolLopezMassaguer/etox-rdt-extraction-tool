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
