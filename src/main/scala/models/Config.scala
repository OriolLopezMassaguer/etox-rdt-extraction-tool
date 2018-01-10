package models

import com.typesafe.config._

object Config {

  val cf = ConfigFactory.parseFile(new java.io.File("conf/application.conf"))
  val home_path = cf.getString("home.path")
  println("Home path: " + home_path)
  val dbURLetox = cf.getString("etoxdb.url")
  val dbUseretox = cf.getString("etoxdb.user")
  val dbPasswordetox = cf.getString("etoxdb.password")
  val data_path = cf.getString("homedata.path")

}
