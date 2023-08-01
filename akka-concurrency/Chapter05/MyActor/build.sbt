// Definitions common to all subprojects
ThisBuild / organization := "github.com/michelou"
ThisBuild / scalaVersion := "2.13.11"
ThisBuild / version      := "1.0"

lazy val akkaVersion = "2.8.3"

lazy val root = (project in file("."))
  .settings(
    name := "MyActor",
    libraryDependencies ++= Seq(
      "com.typesafe" % "config" % "1.4.2",
      "com.typesafe.akka" %% "akka-actor" % akkaVersion,
      // Test libraries
      "com.novocode" % "junit-interface" % "0.11" % Test,
      "com.typesafe.akka" %% "akka-testkit" % akkaVersion % Test
    ),
    Test / parallelExecution := false
  )
