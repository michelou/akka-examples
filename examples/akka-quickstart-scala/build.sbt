name := "akka-quickstart-scala"

version := "1.0"

scalaVersion := "2.13.6"

lazy val akkaVersion = "2.6.15"
lazy val logbackVersion = "1.2.5"
lazy val scalatestVersion = "3.2.9"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor-typed" % akkaVersion,
  "com.typesafe.akka" %% "akka-actor-testkit-typed" % akkaVersion % Test,
  "ch.qos.logback" % "logback-classic" % logbackVersion,
  "org.scalatest" %% "scalatest" % scalatestVersion % Test
)
