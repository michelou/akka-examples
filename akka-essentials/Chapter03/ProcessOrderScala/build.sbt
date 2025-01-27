name := "ProcessOrder"

version := "1.0"

scalaVersion := "2.13.16"

// https://mvnrepository.com/artifact/com.typesafe/config
// https://mvnrepository.com/artifact/com.typesafe.akka/akka-actor
libraryDependencies ++= Seq(
  "com.typesafe" % "config" % "1.4.3",
  "com.typesafe.akka" %% "akka-actor" % "2.10.0"
)
