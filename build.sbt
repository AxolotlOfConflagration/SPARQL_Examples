import Dependencies._

ThisBuild / scalaVersion := "2.13.1"
ThisBuild / version := "0.1.0-SNAPSHOT"
ThisBuild / organization := "com.example"
ThisBuild / organizationName := "example"

lazy val root = (project in file("."))
  .settings(
    name := "SPARQL_examples",
    libraryDependencies += scalaTest % Test,
    libraryDependencies += "org.apache.jena" % "apache-jena-libs" % "3.13.1" pomOnly(),
    libraryDependencies += "org.slf4j" % "slf4j-api" % "1.7.29",
    libraryDependencies += "ch.qos.logback" % "logback-classic" % "1.1.3" % Runtime
  )

// See https://www.scala-sbt.org/1.x/docs/Using-Sonatype.html for instructions on how to publish to Sonatype.
