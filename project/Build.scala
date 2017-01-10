import sbt._
import Keys._
import com.typesafe.sbt.SbtNativePackager.autoImport._

object ApplicationBuild extends Build {

  val appName = "etox_invivo_extraction_tool"
  val appVersion = "1.0"

  val appDependencies = List(
    "commons-codec" % "commons-codec" % "1.10",
    "com.typesafe.slick" %% "slick" % "2.1.0",
    "com.typesafe.slick" %% "slick-codegen" % "2.1.0",
    "org.slf4j" % "slf4j-nop" % "1.6.4",
    "postgresql" % "postgresql" % "9.1-901.jdbc4",
    "org.apache.poi" % "poi" % "3.10.1",
    "com.github.jbellis" % "jamm" % "0.3.0",
    "org.scalaz" %% "scalaz-core" % "7.1.0",
    "com.github.scopt" %% "scopt" % "3.3.0",
    "org.json4s" %% "json4s-jackson" % "3.2.10",
    "net.databinder" %% "unfiltered-filter" % "0.8.3",
    "net.databinder" %% "unfiltered-jetty" % "0.8.3",
    "com.quantifind" %% "sumac" % "0.3.0",
    "org.apache.commons" % "commons-math3" % "3.4.1",
    "commons-io" % "commons-io" % "2.4",
    "com.github.OriolLopezMassaguer" %% "dataframe" % "1.2.2-SNAPSHOT" classifier "assembly")

  val main = Project(appName, file(".")).settings(
    version := appVersion,
    scalaVersion := "2.11.8",
    libraryDependencies ++= appDependencies,
    maxErrors := 5,   
    resolvers ++= Seq("ASF Snapshots" at "http://repository.apache.org/snapshots/",
      "Sonatype OSS Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots",
      Resolver.sonatypeRepo("public"),
      Resolver.sonatypeRepo("snapshots"),
      Resolver.sonatypeRepo("releases")))

  scalacOptions ++= Seq("-unchecked", "-deprecation", "-feature")

  scalacOptions in (Compile, doc) ++= Seq("-unchecked", "-deprecation", "-diagrams", "-implicits", "-skip-packages", "samples")

  //slick <<= slickCodeGenTask,
  //sourceGenerators in Compile <+= slickCodeGenTask)

  lazy val slick = TaskKey[Seq[File]]("gen-tables")
  lazy val slickCodeGenTask = (sourceManaged, dependencyClasspath in Compile, runner in Compile, streams) map { (dir, cp, r, s) =>
    val outputDir = (dir / "slick").getPath // place generated files in sbt's managed sources folder
    val url = "jdbc:postgresql://localhost/vitic2015_1" // connection info for a pre-populated throw-away, in-memory db for this demo, which is freshly initialized on every run
    val jdbcDriver = "org.postgresql.Driver"
    val slickDriver = "scala.slick.driver.PostgresDriver"
    val user = "postgres"
    val password = "postgres"
    val pkg = "demo"
    toError(r.run("scala.slick.codegen.SourceCodeGenerator", cp.files, Array(slickDriver, jdbcDriver, url, outputDir, pkg, user, password), s.log))
    //toError(r.run("scala.slick.model.codegen.SourceCodeGenerator", cp.files, Array(slickDriver, jdbcDriver, url, outputDir, pkg,user,password), s.log))
    val fname = outputDir + "/vitic_2015_1/Tables.scala"
    Seq(file(fname))

  }
}



