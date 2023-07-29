name := "ParentChildren"

version := "1.0"

scalaVersion := "2.13.11"

lazy val akkaVersion = "2.8.3"
lazy val configVersion = "1.4.2"
lazy val logbackVersion = "1.4.8"
lazy val scalatestVersion = "3.2.16"

libraryDependencies ++= Seq(
  "ch.qos.logback" % "logback-classic" % logbackVersion,
  "com.typesafe" % "config" % configVersion,
  "com.typesafe.akka" %% "akka-actor-typed" % akkaVersion,
  // test
  "com.typesafe.akka" %% "akka-actor-testkit-typed" % akkaVersion % Test,
  "org.scalatest" %% "scalatest" % scalatestVersion % Test
)
