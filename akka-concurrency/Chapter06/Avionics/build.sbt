name := "Avionics"

version := "1.0"

scalaVersion := "2.13.14"

resolvers += "Akka library repository".at("https://repo.akka.io/maven")

libraryDependencies ++= Seq(
  "com.typesafe" % "config" % "1.4.3",
  "com.typesafe.akka" %% "akka-actor" % "2.9.4",
  // test
  "com.typesafe.akka" %% "akka-testkit" % "2.9.4" % "test",
  "org.scalatest" %% "scalatest" % "3.2.19" % "test"
  
)

packageOptions ++= Seq(Package.ManifestAttributes(
  ("Specification-Vendor", "Derek Wyatt"),
  ("Implementation-Vendor", "Derek Wyatt"),
  ("Implementation-Title", "Akka Concurrency, Derek Wyatt"),
  ("Implementation-Vendor-Id", "B00D67E1LI")
))
