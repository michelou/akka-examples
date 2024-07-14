// Definitions common to all subprojects
ThisBuild / organization := "github.com/michelou"
ThisBuild / scalaVersion := "2.13.14"
ThisBuild / version      := "1.0"

lazy val configVersion = "1.4.3"
lazy val akkaVersion = "2.9.4"

lazy val root = (project in file("."))
  .settings(
    name := "MyActor",
    libraryDependencies ++= Seq(
      "com.typesafe" % "config" % configVersion,
      "com.typesafe.akka" %% "akka-actor" % akkaVersion,
      // Test libraries
      "com.novocode" % "junit-interface" % "0.11" % Test,
      "com.typesafe.akka" %% "akka-testkit" % akkaVersion % Test
    ),
    Test / parallelExecution := false
  )
