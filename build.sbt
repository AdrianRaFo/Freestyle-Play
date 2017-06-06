name := """free-play"""
organization := "AdrianRaFo"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)
addCompilerPlugin("org.scalamacros" % "paradise" % "2.1.0" cross CrossVersion.full)

scalaVersion := "2.11.11"

libraryDependencies ++= Seq(
  guice,
  "com.typesafe.play" %% "play-json"           % "2.6.0-RC2",
  "io.frees"          %% "freestyle"           % "0.2.0",
  "io.frees"          %% "freestyle-http-play" % "0.2.0")
