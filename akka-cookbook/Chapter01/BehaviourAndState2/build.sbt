name := "BehaviourAndState2"

version := "1.0"

scalaVersion := "2.13.15"

resolvers += "Akka library repository".at("https://repo.akka.io/maven")

libraryDependencies ++= Seq(
  "com.typesafe" % "config" % "1.4.3",
  "com.typesafe.akka" %% "akka-actor" % "2.10.0"
)
