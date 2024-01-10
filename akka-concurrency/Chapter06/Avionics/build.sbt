name := "Avionics"

version := "1.0"

scalaVersion := "2.13.12"

libraryDependencies ++= Seq(
  "com.typesafe" % "config" % "1.4.2",
  "com.typesafe.akka" %% "akka-actor" % "2.8.5",
  // test
  "com.typesafe.akka" %% "akka-testkit" % "2.8.5" % "test",
  "org.scalatest" %% "scalatest" % "3.2.17" % "test"
  
)

packageOptions ++= Seq(Package.ManifestAttributes(
  ("Specification-Vendor", "Derek Wyatt"),
  ("Implementation-Vendor", "Derek Wyatt"),
  ("Implementation-Title", "Akka Concurrency, Derek Wyatt"),
  ("Implementation-Vendor-Id", "B00D67E1LI")
))
