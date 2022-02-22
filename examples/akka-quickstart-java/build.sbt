name := "akka-quickstart-java"

version := "1.0"

scalaVersion := "2.13.8"

lazy val akkaVersion = "2.6.18"
lazy val logbackVersion = "1.2.10"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor-typed" % akkaVersion,
  "com.typesafe.akka" %% "akka-actor-testkit-typed" % akkaVersion,
  "ch.qos.logback" % "logback-classic" % logbackVersion,
  "junit" % "junit" % "4.13.2" % Test,
  "com.novocode" % "junit-interface" % "0.11" % Test
)
