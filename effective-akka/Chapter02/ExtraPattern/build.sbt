name := "ExtraPattern"

version := "1.0"

scalaVersion := "2.13.8"

val configVersion = "1.4.2"
val akkaVersion = "2.6.19"

libraryDependencies ++= Seq(
  "com.typesafe" % "config" % configVersion,
  "com.typesafe.akka" %% "akka-actor" % akkaVersion,
  "com.typesafe.akka" %% "akka-testkit" % akkaVersion % Test
)
