name := "ExtraPattern"
description := "Code example from Allen's book Effective Akka (O'Reilly, 2013)"

version := "1.0"

scalaVersion := "2.13.12"

scalacOptions ++= Seq(Opts.compile.deprecation)

val akkaVersion = "2.8.5"
val configVersion = "1.4.2"

libraryDependencies ++= Seq(
  // https://mvnrepository.com/artifact/com.typesafe/config
  "com.typesafe" % "config" % configVersion,
  // https://mvnrepository.com/artifact/com.typesafe.akka/akka-actor
  "com.typesafe.akka" %% "akka-actor" % akkaVersion,
  "com.typesafe.akka" %% "akka-slf4j" % akkaVersion,
  "com.typesafe.akka" %% "akka-testkit" % akkaVersion % Test
)
