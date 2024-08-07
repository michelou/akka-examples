name := "Avionics"

version := "1.0"

scalaVersion := "2.13.14"

libraryDependencies ++= Seq(
  "com.typesafe" % "config" % "1.4.3",
  "com.typesafe.akka" %% "akka-actor" % "2.9.4"
)

packageOptions ++= Seq(Package.ManifestAttributes(
  ("Specification-Vendor", "Derek Wyatt"),
  ("Implementation-Vendor", "Derek Wyatt"),
  ("Implementation-Title", "Akka Concurrency, Derek Wyatt"),
  ("Implementation-Vendor-Id", "B00D67E1LI")
))
