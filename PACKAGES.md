# <span id="top">Maven Packages</span> <span style="size:25%;"><a href="README.md">↩</a></span>

<table style="font-family:Helvetica,Arial;line-height:1.6;">
  <tr>
  <td style="border:0;padding:0 10px 0 0;min-width:120px;"><a href="https://akka.io/" rel="external"><img style="border:0;" src="./docs/images/akka.svg" width="120" alt="Akka project"/></a></td>
  <td style="border:0;padding:0;vertical-align:text-top;">This document presents the <a href="https://mvnrepository.com/" rel="external">Maven</a> packages our projects depend on.
  </td>
  </tr>
</table>

The [Akka] tookit is distributed as [Maven][maven_repository] packages : 

- [Akka Actor 2.9][akka_actor] ([*release notes*][akka_relnotes])
- [Akka Actor Typed 2.9][akka_actor_typed] ([*release notes*][akka_relnotes])
- [Akka Testkit 2.9][akka_testkit] ([*release notes*][akka_relnotes])
- [Akka Testkit Typed 2.9][akka_testkit_typed] ([*release notes*][akka_relnotes])

The [Apache Pekko][pekko] tookit is also distributed as [Maven][maven_repository] packages :

- [Pekko Actor 1.0][pekko_actor] ([*release notes*][pekko_relnotes])
- [Pekko Actor Typed 1.0][pekko_actor_typed] ([*release notes*][pekko_relnotes])
- [Pekko Testkit 1.0][pekko_testkit] ([*release notes*][pekko_relnotes])
- [Pekko Testkit Typed 1.0][pekko_testkit_typed] ([*release notes*][pekko_relnotes])
<!--
- [Pekko HTTP 1.0][pekko_http]
- [Pekko Stream 1.0][pekko_stream]
-->
Our projects further depend on the following [Maven][maven_repository] packages :

- [Config 1.4][config_download] ([*release notes*][config_relnotes])
- [ScalaTest 3.2][scalatest_download] ([*release notes*][scalatest_relnotes])

> **Note**: [Maven][maven_repository] packages are stored locally in two different locations depending on the build tool:
>
>| Build&nbsp;tool   | Local&nbsp;repository           |
>|:------------------|:--------------------------------|
>| [**`ant.bat`**][ant_cli] ([Ivy][ivy_lib] library)    | [`%USERPROFILE%`](https://learn.microsoft.com/en-us/windows/deployment/usmt/usmt-recognized-environment-variables#variables-that-are-recognized-only-in-the-user-context)`\.ivy2\cache\`    |
>| [**`gradle.bat`**][gradle_cli] | [`%USERPROFILE%`](https://learn.microsoft.com/en-us/windows/deployment/usmt/usmt-recognized-environment-variables#variables-that-are-recognized-only-in-the-user-context)`.m2\repository\` | 
>| [**`mvn.cmd`**][mvn_cli] | [`%USERPROFILE%`](https://learn.microsoft.com/en-us/windows/deployment/usmt/usmt-recognized-environment-variables#variables-that-are-recognized-only-in-the-user-context)`\.m2\repository\` | 
>| [**`sbt.bat`**][sbt_cli] | [`%USERPROFILE%`](https://learn.microsoft.com/en-us/windows/deployment/usmt/usmt-recognized-environment-variables#variables-that-are-recognized-only-in-the-user-context)`\.m2\repository\` | 

*WIP*

***

*[mics](https://lampwww.epfl.ch/~michelou/)/June 2024* [**&#9650;**](#top)
<span id="bottom">&nbsp;</span>

<!-- link refs -->

[akka]: https://akka.io/
[akka_actor]: https://mvnrepository.com/artifact/com.typesafe.akka/akka-actor
[akka_actor_typed]: https://mvnrepository.com/artifact/com.typesafe.akka/akka-actor-typed
[akka_relnotes]: https://github.com/akka/akka/releases/tag/v2.9.0
[akka_testkit]: https://mvnrepository.com/artifact/com.typesafe.akka/akka-testkit
[akka_testkit_typed]: https://mvnrepository.com/artifact/com.typesafe.akka/akka-actor-testkit-typed
[ant_cli]: https://ant.apache.org/
[config_download]: https://mvnrepository.com/artifact/com.typesafe/config
[config_relnotes]: https://github.com/lightbend/config/blob/main/NEWS.md
[gradle_cli]: https://docs.gradle.org/current/userguide/declaring_repositories.html
[ivy_lib]: https://ant.apache.org/ivy/
[maven_repository]: https://mvnrepository.com/
[mvn_cli]: https://maven.apache.org/ref/3.9.0/maven-embedder/cli.html
[pekko]: https://pekko.apache.org/what-is-pekko.html
[pekko_actor]: https://mvnrepository.com/artifact/org.apache.pekko/pekko-actor
[pekko_actor_typed]: https://mvnrepository.com/artifact/org.apache.pekko/pekko-actor-typed
[pekko_http]: https://mvnrepository.com/artifact/org.apache.pekko/pekko-http
[pekko_relnotes]: https://pekko.apache.org/docs/pekko/current/release-notes/
[pekko_stream]: https://mvnrepository.com/artifact/org.apache.pekko/pekko-stream
[pekko_testkit]: hhttps://mvnrepository.com/artifact/org.apache.pekko/pekko-testkit
[pekko_testkit_typed]: https://mvnrepository.com/artifact/org.apache.pekko/pekko-actor-testkit-typed
[sbt_cli]: https://www.scala-sbt.org/1.x/docs/Resolvers.html
[scalatest_download]: https://mvnrepository.com/artifact/org.scalatest/scalatest
[scalatest_relnotes]: https://github.com/scalatest/scalatest/releases/tag/release-3.2.17
