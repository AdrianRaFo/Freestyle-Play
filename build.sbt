name := """free-play"""
organization := "AdrianRaFo"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)
addCompilerPlugin(
  "org.scalamacros" % "paradise" % "2.1.0" cross CrossVersion.full)

scalaVersion := "2.11.11"

libraryDependencies += filters
libraryDependencies += guice
libraryDependencies += "io.frees" %% "freestyle" % "0.2.0"
libraryDependencies += "io.frees" %% "freestyle-http-play" % "0.2.0"
libraryDependencies += "org.scalatestplus.play" %% "scalatestplus-play" % "2.0.0" % Test

// Adds additional packages into Twirl
//TwirlKeys.templateImports += "AdrianRaFo.controllers._"

// Adds additional packages into conf/routes
// play.sbt.routes.RoutesKeys.routesImport += "AdrianRaFo.binders._"
