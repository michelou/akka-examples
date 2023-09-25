name := "akka-quickstart-java"

version := "1.0"

scalaVersion := "2.13.12"

lazy val akkaVersion = "2.8.5"
lazy val configVersion = "1.4.2"
lazy val logbackVersion = "1.4.11"

libraryDependencies ++= Seq(
  "ch.qos.logback" % "logback-classic" % logbackVersion,
  "com.typesafe" % "config" % configVersion,
  "com.typesafe.akka" %% "akka-actor-typed" % akkaVersion,
  "com.typesafe.akka" %% "akka-actor-testkit-typed" % akkaVersion,
  // test
  "com.novocode" % "junit-interface" % "0.11" % Test,
  "junit" % "junit" % "4.13.2" % Test
)
