name := "ExtraPattern"
description := "Code example from Allen's book Effective Akka (O'Reilly, 2013)"

version := "1.0"

scalaVersion := "2.13.18"

scalacOptions ++= Seq(Opts.compile.deprecation)

val akkaVersion = "2.10.20"
val configVersion = "1.4.3"

val akkaAccessToken = settingKey[String]("Akka")
akkaAccessToken := {
  def parseYaml(file: File): String =
    val lines = IO.readLines(file)
    val pattern = "resolver: \"https://repo.akka.io/(.*)/secure.*\""
    val token = pattern.r.findFirstMatchIn((lines.filter(_.matches(pattern)) :+ "").head).map(_.group(1))
    token.getOrElse("")
  val cacheFile = new File(Path.userHome.absolutePath, ".akka" + java.io.File.separator + "cache.yaml")
  val key = parseYaml(cacheFile)
  key
}

resolvers ++= Seq(
  "akka" at s"https://repo.akka.io/${akkaAccessToken.value}/secure"
)

libraryDependencies ++= Seq(
  // https://mvnrepository.com/artifact/com.typesafe/config
  "com.typesafe" % "config" % configVersion,
  // https://mvnrepository.com/artifact/com.typesafe.akka/akka-actor
  "com.typesafe.akka" %% "akka-actor" % akkaVersion,
  "com.typesafe.akka" %% "akka-testkit" % akkaVersion % Test
)
