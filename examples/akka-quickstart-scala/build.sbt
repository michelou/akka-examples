name := "akka-quickstart-scala"

version := "1.0"

scalaVersion := "2.13.15"

lazy val akkaVersion = "2.10.0"
lazy val configVersion = "1.4.2"
lazy val logbackVersion = "1.5.16"
lazy val scalatestVersion = "3.2.19"

libraryDependencies ++= Seq(
  "ch.qos.logback" % "logback-classic" % logbackVersion,
  "com.typesafe" % "config" % configVersion,
  "com.typesafe.akka" %% "akka-actor-typed" % akkaVersion,
  // test
  "com.typesafe.akka" %% "akka-actor-testkit-typed" % akkaVersion % Test,
  "org.scalatest" %% "scalatest" % scalatestVersion % Test
)
