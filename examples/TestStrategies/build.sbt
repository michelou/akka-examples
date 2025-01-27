name := "TestStrategies"

version := "1.0"

scalaVersion := "2.13.16"

// https://mvnrepository.com/artifact/com.typesafe.akka/akka-actor-typed
// https://mvnrepository.com/artifact/com.typesafe/config
// https://mvnrepository.com/artifact/ch.qos.logback/logback-classic
// https://mvnrepository.com/artifact/org.scalatest/scalatest
lazy val akkaVersion = "2.10.0"
lazy val configVersion = "1.4.3"
lazy val logbackVersion = "1.5.16"
lazy val scalatestVersion = "3.2.19"

resolvers += "Akka library repository".at("https://repo.akka.io/maven")

libraryDependencies ++= Seq(
  "com.typesafe" % "config" % configVersion,
  "com.typesafe.akka" %% "akka-actor-typed" % akkaVersion,
  // test scope
  "ch.qos.logback" % "logback-classic" % logbackVersion,
  "com.typesafe.akka" %% "akka-actor-testkit-typed" % akkaVersion % Test,
  "org.scalatest" %% "scalatest" % scalatestVersion % Test
)
