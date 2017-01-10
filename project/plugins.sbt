// Comment to get more information during initialization
logLevel := Level.Warn

// The Typesafe repository 
resolvers += "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/"
//resolvers += "ASF Snapshots" at "http://repository.apache.org/snapshots/"



// Use the Play sbt plugin for Play projects
//addSbtPlugin("com.typesafe.play" % "sbt-plugin" % "2.3.10")
addSbtPlugin("net.virtual-void" % "sbt-dependency-graph" % "0.8.2")
addSbtPlugin("com.typesafe.sbteclipse" % "sbteclipse-plugin" % "5.0.1")
addSbtPlugin("com.typesafe.sbt" % "sbt-native-packager" % "1.1.4")
addSbtPlugin("org.scoverage" % "sbt-scoverage" % "1.5.0")
addSbtPlugin("org.scalastyle" %% "scalastyle-sbt-plugin" % "0.8.0")

