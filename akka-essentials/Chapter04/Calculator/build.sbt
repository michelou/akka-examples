name := "Calculator"

version := "1.0"

scalaVersion := "2.13.16"

resolvers += "Akka library repository".at("https://repo.akka.io/maven")

libraryDependencies ++= Seq(
  "com.typesafe" % "config" % "1.4.3",
  "com.typesafe.akka" % "akka-actor_2.13" % "2.10.1"
)
