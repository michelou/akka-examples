# <span id="top">Book <i>Effective Akka</i></span> <span style="size:30%;"><a href="../README.md">⬆</a></span>

<table style="font-family:Helvetica,Arial;font-size:14px;line-height:1.6;">
  <tr>
  <td style="border:0;padding:0 10px 0 0;min-width:100px;"><a href="https://akka.io/"><img style="border:0;" src="../docs/images/akka.svg" width="100" alt="Akka project"/></a></td>
  <td style="border:0;padding:0;vertical-align:text-top;">Directory <strong><code>effective-akka\</code></strong> contains <a href="https://akka.io/" alt="Akka">Akka</a> code examples from Allen's book <a href="https://www.oreilly.com/library/view/effective-akka/9781449360061/"><i>Effective Akka</i></a> (O'Reilly, 2013).
  </td>
  </tr>
</table>

Code examples presented below can be built/run with the following tools:

| Build&nbsp;tool | Configuration file | Parent&nbsp;file | Environment(s) |
|:----------------|:-------------------|:----------------|:---------------|
| [**`ant.bat`**][apache_ant_cli] | [`build.xml`](./Chapter02/ExtraPattern/build.xml) | [`build.xml`](./build.xml) | Any <sup><b>a)</b></sup> |
| [**`gradle.bat`**][gradle_cli] | [`build.gradle`](./Chapter02/ExtraPattern/build.gradle) | [`common.gradle`](./common.gradle) | Any |
| [**`make.exe`**][make_cli] | [`Makefile`](./Chapter02/ExtraPattern/Makefile) | [`Makefile.inc`](./Makefile.inc) | Any |
| [**`mvn.cmd`**][apache_maven_cli] | [`pom.xml`](./Chapter02/ExtraPattern/pom.xml) | [`pom.xml`](./pom.xml) | Any |
| [**`sbt.bat`**][sbt_cli] | [`build.sbt`](./Chapter02/ExtraPattern/build.sbt) | &nbsp;        | Any |
| [**`build.bat`**](./Chapter02/ExtraPattern/build.bat) | *none*             |  &nbsp;        | Windows only |
<div style="font-size:80%;">
<sup><b>a)</b></sup> Here "Any" means "tested on Windows, Cygwin, MSYS2 and UNIX".
</div>

### <span id="extra_pattern">`ExtraPattern`</span>

Code example `ExtraPattern` is the same [Akka] application written in [Scala] (e.g. [`ExtraPattern.scala`](./Chapter02/ExtraPattern/src/main/scala/org/jamieallen/effectiveakka/ExtraPattern.scala), [`AccountBalanceRetriever.scala`](./Chapter02/ExtraPattern/src/main/scala/org/jamieallen/effectiveakka/pattern/extra/AccountBalanceRetriever.scala)).

Batch file [**`build.bat`**](./Chapter02/ExtraPattern/build.bat) matches what the user would run from the command prompt (use option **`-debug`** to see the execution details):

<pre style="font-size:80%;">
<b>&gt; <a href="./Chapter02/ExtraPattern/build.bat">build</a> -verbose run</b>
Compile 6 Scala source files to directory "target\classes"
[ExtraPattern-akka.actor.default-dispatcher-4] INFO akka.event.slf4j.Slf4jLogger - Slf4jLogger started
CheckingAccounts   : Received GetCustomerAccountBalances for ID: 1
MoneyMarketAccounts: Received GetCustomerAccountBalances for ID: 1
SavingsAccounts    : Received GetCustomerAccountBalances for ID: 1
CheckingAccounts   : Received GetCustomerAccountBalances for ID: 2
MoneyMarketAccounts: Received GetCustomerAccountBalances for ID: 2
SavingsAccounts    : Received GetCustomerAccountBalances for ID: 2
>>> Press ENTER to exit <<<

[ExtraPattern-akka.actor.default-dispatcher-7] INFO akka.actor.CoordinatedShutdown - Running CoordinatedShutdown with reason [ActorSystemTerminateReason]
</pre>

*WIP*

### <span id="cameo_pattern">`CameoPattern`</span>

*WIP*

***

*[mics](https://lampwww.epfl.ch/~michelou/)/June 2022* [**&#9650;**](#top)
<span id="bottom">&nbsp;</span>

<!-- link refs -->

[akka]: https://akka.io/
[apache_ant_cli]: https://ant.apache.org/manual/running.html
[apache_maven_cli]: https://maven.apache.org/ref/current/maven-embedder/cli.html
[book_allen]: https://www.oreilly.com/library/view/effective-akka/9781449360061/
[gradle_cli]: https://docs.gradle.org/current/userguide/command_line_interface.html
[sbt_cli]: https://www.scala-sbt.org/1.x/docs/Command-Line-Reference.html
[make_cli]: https://ftp.gnu.org/old-gnu/Manuals/make-3.79.1/html_node/make_86.html
[scala]: https://www.scala-lang.org/
