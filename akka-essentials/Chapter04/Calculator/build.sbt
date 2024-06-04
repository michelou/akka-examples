name := "Calculator"

version := "1.0"

scalaVersion := "2.13.14"

resolvers += "Akka library repository".at("https://repo.akka.io/maven")

libraryDependencies ++= Seq(
  "com.typesafe" % "config" % "1.4.2",
  "com.typesafe.akka" % "akka-actor_2.13" % "2.9.3"
)
