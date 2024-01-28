name := "Receptionist"

version := "1.0"

scalaVersion := "2.13.12"

// https://mvnrepository.com/artifact/com.typesafe.akka/akka-actor-typed
// https://mvnrepository.com/artifact/ch.qos.logback/logback-classic
// https://mvnrepository.com/artifact/org.scalatest/scalatest
lazy val akkaVersion = "2.8.5"
lazy val configVersion = "1.4.2"
lazy val logbackVersion = "1.4.14"
lazy val scalatestVersion = "3.2.17"

libraryDependencies ++= Seq(
  "ch.qos.logback" % "logback-classic" % logbackVersion,
  "com.typesafe" % "config" % configVersion,
  "com.typesafe.akka" %% "akka-actor-typed" % akkaVersion,
  // test
  "com.typesafe.akka" %% "akka-actor-testkit-typed" % akkaVersion % Test,
  "org.scalatest" %% "scalatest" % scalatestVersion % Test
)
