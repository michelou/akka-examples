name := "ExtraPattern"

version := "1.0"

scalaVersion := "2.13.8"

val akkaVersion = "2.6.18"

libraryDependencies ++= Seq(
  "com.typesafe" % "config" % "1.4.2",
  "com.typesafe.akka" %% "akka-actor" % akkaVersion,
  "com.typesafe.akka" %% "akka-testkit" % akkaVersion % Test
)
