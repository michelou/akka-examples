name := "TestStrategies"

version := "1.0"

scalaVersion := "2.13.14"

lazy val akkaVersion = "2.9.3"
lazy val configVersion = "1.4.3"
lazy val logbackVersion = "1.2.11"
lazy val scalatestVersion = "3.2.18"

resolvers += "Akka library repository".at("https://repo.akka.io/maven")

libraryDependencies ++= Seq(
  "com.typesafe" % "config" % configVersion,
  "com.typesafe.akka" %% "akka-actor-typed" % akkaVersion,
  // test scope
  "ch.qos.logback" % "logback-classic" % logbackVersion,
  "com.typesafe.akka" %% "akka-actor-testkit-typed" % akkaVersion % Test,
  "org.scalatest" %% "scalatest" % scalatestVersion % Test
)
