name := "akka-quickstart-java"

version := "1.0"

scalaVersion := "2.13.14"

lazy val akkaVersion = "2.9.5"
lazy val configVersion = "1.4.3"
lazy val logbackVersion = "1.5.7"

libraryDependencies ++= Seq(
  "ch.qos.logback" % "logback-classic" % logbackVersion,
  "com.typesafe" % "config" % configVersion,
  "com.typesafe.akka" %% "akka-actor-typed" % akkaVersion,
  "com.typesafe.akka" %% "akka-actor-testkit-typed" % akkaVersion,
  // test
  "com.novocode" % "junit-interface" % "0.11" % Test,
  "junit" % "junit" % "4.13.2" % Test
)
