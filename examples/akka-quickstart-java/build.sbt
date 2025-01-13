name := "akka-quickstart-java"

version := "1.0"

scalaVersion := "2.13.15"

lazy val akkaVersion = "2.10.0"
lazy val configVersion = "1.4.3"
lazy val logbackVersion = "1.5.16"

libraryDependencies ++= Seq(
  "ch.qos.logback" % "logback-classic" % logbackVersion,
  "com.typesafe" % "config" % configVersion,
  "com.typesafe.akka" %% "akka-actor-typed" % akkaVersion,
  "com.typesafe.akka" %% "akka-actor-testkit-typed" % akkaVersion,
  // test
  "com.novocode" % "junit-interface" % "0.11" % Test,
  "junit" % "junit" % "4.13.2" % Test
)
