# <span id="top">Maven Packages</span> <span style="size:25%;"><a href="README.md">↩</a></span>

<table style="font-family:Helvetica,Arial;line-height:1.6;">
  <tr>
  <td style="border:0;padding:0 10px 0 0;min-width:120px;"><a href="https://akka.io/" rel="external"><img style="border:0;" src="./docs/images/akka.svg" width="120" alt="Akka project"/></a></td>
  <td style="border:0;padding:0;vertical-align:text-top;">This document presents the <a href="https://mvnrepository.com/" rel="external">Maven</a> packages our projects depend on.
  </td>
  </tr>
</table>

We also install the following [Maven][maven_repository] packages : 

- [Akka Actor 2.8](https://mvnrepository.com/artifact/com.typesafe.akka/akka-actor) ([*release notes*][akka_relnotes])
- [Akka Actor Typed 2.8](https://mvnrepository.com/artifact/com.typesafe.akka/akka-actor-typed) ([*release notes*][akka_relnotes])
- [Akka Testkit 2.8](https://mvnrepository.com/artifact/com.typesafe.akka/akka-testkit) ([*release notes*][akka_relnotes])
- [Config 1.4][config_download] ([*release notes*][config_relnotes])
- [ScalaTest 3.2][scalatest_download] ([*release notes*][scalatest_relnotes])

Depending on the build tool the above packages are stored locally in two different locations :

| Build&nbsp;tool   | Local&nbsp;repository           |
|:-----------------------|:--------------------------------|
| [`gradle.bat`][gradle_cli], [`mvn.cmd`][mvn_cli], [`sbt.bat`][sbt_cli] | `%USERPROFILE%\.m2\repository\` | 
| [`ant.bat`][ant_cli] ([Ivy][ivy_lib] library)    | `%USERPROFILE%\.ivy2\cache\`    |

*WIP*

***

*[mics](https://lampwww.epfl.ch/~michelou/)/July 2023* [**&#9650;**](#top)
<span id="bottom">&nbsp;</span>

<!-- link refs -->

[akka_relnotes]: https://github.com/akka/akka/releases
[ant_cli]: https://ant.apache.org/
[config_download]: https://mvnrepository.com/artifact/com.typesafe/config
[config_relnotes]: https://github.com/lightbend/config/blob/main/NEWS.md
[gradle_cli]: https://docs.gradle.org/current/userguide/declaring_repositories.html
[ivy_lib]: https://ant.apache.org/ivy/
[maven_repository]: https://mvnrepository.com/
[mvn_cli]: https://maven.apache.org/ref/3.9.0/maven-embedder/cli.html
[sbt_cli]: https://www.scala-sbt.org/1.x/docs/Resolvers.html
[scalatest_download]: https://mvnrepository.com/artifact/org.scalatest/scalatest
[scalatest_relnotes]: https://github.com/scalatest/scalatest/releases/tag/release-3.2.16
