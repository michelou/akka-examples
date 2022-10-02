name := "akka-quickstart-scala"

version := "1.0"

scalaVersion := "2.13.9"

lazy val akkaVersion = "2.6.20"
lazy val configVersion = "1.4.2"
lazy val logbackVersion = "1.4.1"
lazy val scalatestVersion = "3.2.14"

libraryDependencies ++= Seq(
  "ch.qos.logback" % "logback-classic" % logbackVersion,
  "com.typesafe" % "config" % configVersion,
  "com.typesafe.akka" %% "akka-actor-typed" % akkaVersion,
  // test
  "com.typesafe.akka" %% "akka-actor-testkit-typed" % akkaVersion % Test,
  "org.scalatest" %% "scalatest" % scalatestVersion % Test
)
