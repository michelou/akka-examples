# <span id="top">Book <i>Effective Akka</i></span> <span style="size:30%;"><a href="../README.md">⬆</a></span>

<table style="font-family:Helvetica,Arial;line-height:1.6;">
  <tr>
  <td style="border:0;padding:0 10px 0 0;min-width:100px;"><a href="https://akka.io/"><img style="border:0;" src="../docs/images/akka.svg" width="100" alt="Akka project"/></a></td>
  <td style="border:0;padding:0;vertical-align:text-top;">Directory <strong><code>effective-akka\</code></strong> contains <a href="https://akka.io/" alt="Akka">Akka</a> code examples from Allen's book <a href="https://www.oreilly.com/library/view/effective-akka/9781449360061/"><i>Effective Akka</i></a> (O'Reilly, 2013).
  </td>
  </tr>
</table>

Code examples presented below can be built/run with the following tools:

| Build&nbsp;tool | Build&nbsp;file | Parent&nbsp;file | Environment(s) |
|:----------------|:----------------|:----------------|:---------------|
| [**`ant.bat`**][apache_ant_cli] | [`build.xml`](./Chapter02/ExtraPattern/build.xml) | [`build.xml`](./build.xml) | Any <sup><b>a)</b></sup> |
| [**`gradle.bat`**][gradle_cli] | [`build.gradle`](./Chapter02/ExtraPattern/build.gradle) | [`common.gradle`](./common.gradle) | Any |
| [**`make.exe`**][make_cli] | [`Makefile`](./Chapter02/ExtraPattern/Makefile) | [`Makefile.inc`](./Makefile.inc) | Any |
| [**`mvn.cmd`**][apache_maven_cli] | [`pom.xml`](./Chapter02/ExtraPattern/pom.xml) | [`pom.xml`](./pom.xml) | Any |
| [**`sbt.bat`**][sbt_cli] | [`build.sbt`](./Chapter02/ExtraPattern/build.sbt) | &nbsp;        | Any |
| [**`cmd.exe`**][cmd_cli] | [`build.bat`](./Chapter02/ExtraPattern/build.bat) |  &nbsp;        | Windows only |
| [**`bash.exe`**][bash_cli] | [`build.sh`](./Chapter02/CameoPattern/build.sh) | | Any |
<div style="font-size:80%;">
<sup><b>a)</b></sup> Here "Any" means "tested on Windows, Cygwin, MSYS2 and UNIX".<br/>&nbsp;
</div>

### <span id="extra_pattern">`ExtraPattern` Example</span>

Example `ExtraPattern` is the same [Akka] application written in [Scala].

It has the following directory structure :

<pre style="font-size:80%;">
<b>&gt; <a href="">tree</a> /a /f . | <a href="">findstr</a> /v /b [A-Z]</b>
|   <a href="./Chapter02/ExtraPattern/build.bat">build.bat</a>
|   <a href="./Chapter02/ExtraPattern/build.gradle">build.gradle</a>
|   <a href="./Chapter02/ExtraPattern/build.sbt">build.sbt</a>
|   <a href="./Chapter02/ExtraPattern/build.sh">build.sh</a>
|   <a href="./Chapter02/ExtraPattern/build.xml">build.xml</a>
|   <a href="./Chapter02/ExtraPattern/gradle.properties">gradle.properties</a>
|   <a href="./Chapter02/ExtraPattern/Makefile">Makefile</a>
|   <a href="./Chapter02/ExtraPattern/pom.xml">pom.xml</a>
+---project
|       <a href="./Chapter02/ExtraPattern/project/build.properties">build.properties</a>
\---src
    +---main
    |   +---resources
    |   |       <a href="./Chapter02/ExtraPattern/src/main/resources/application.conf">application.conf</a>
    |   |       <a href="./Chapter02/ExtraPattern/src/main/resources/logback.xml">logback.xml</a>
    |   \---scala
    |       \---org
    |           \---jamieallen
    |               \---effectiveakka
    |                   |   <a href="./Chapter02/ExtraPattern/src/main/scala/org/jamieallen/effectiveakka/ExtraPattern.scala">ExtraPattern.scala</a>
    |                   +---common
    |                   |   <a href="./Chapter02/ExtraPattern/src/main/scala/org/jamieallen/effectiveakka/common/Common.scala">Common.scala</a>
    |                   \---pattern
    |                       \---extra
    |                               <a href="./Chapter02/ExtraPattern/src/main/scala/org/jamieallen/effectiveakka/pattern/extra/AccountBalanceRetriever.scala">AccountBalanceRetriever.scala</a>
    |                               <a href="./Chapter02/ExtraPattern/src/main/scala/org/jamieallen/effectiveakka/pattern/extra/CheckingAccountsActor.scala">CheckingAccountsActor.scala</a>
    |                               <a href="./Chapter02/ExtraPattern/src/main/scala/org/jamieallen/effectiveakka/pattern/extra/MoneyMarketAccountsActor.scala">MoneyMarketAccountsActor.scala</a>
    |                               <a href="./Chapter02/ExtraPattern/src/main/scala/org/jamieallen/effectiveakka/pattern/extra/SavingsAccountsActor.scala">SavingsAccountsActor.scala</a>
    \---test
        \---scala
            \---org
                \---jamieallen
                    \---effectiveakka
                        \---pattern
                            \---extra
                                    <a href="./Chapter02/ExtraPattern/src/test/scala/org/jamieallen/effectiveakka/pattern/extra/ExtraSpec.scala">ExtraSpec.scala</a>
                                    <a href="./Chapter02/ExtraPattern/src/test/scala/org/jamieallen/effectiveakka/pattern/extra/ProxyStubs.scala">ProxyStubs.scala</a>
</pre>

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
&nbsp;
[ExtraPattern-akka.actor.default-dispatcher-7] INFO akka.actor.CoordinatedShutdown - Running CoordinatedShutdown with reason [ActorSystemTerminateReason]
</pre>

Bash script [**`build.sh`**](./Chapter02/ExtraPattern/build.sh) works in the same way from a Bash shell prompt :

<pre style="font-size:80%;">
<b>&gt; sh <a href="./Chapter02/ExtraPattern/build.sh">build.sh</a> -verbose run</b>
Compile 6 Scala source files to directory "target/classes"
Execute Scala main class "org.jamieallen.effectiveakka.ExtraPattern"
CheckingAccounts   : Received GetCustomerAccountBalances for ID: 2
MoneyMarketAccounts: Received GetCustomerAccountBalances for ID: 1
SavingsAccounts    : Received GetCustomerAccountBalances for ID: 1
MoneyMarketAccounts: Received GetCustomerAccountBalances for ID: 2
CheckingAccounts   : Received GetCustomerAccountBalances for ID: 1
SavingsAccounts    : Received GetCustomerAccountBalances for ID: 2
[INFO] [akkaDeadLetter][02/09/2023 21:16:47.622] [ExtraPattern-akka.actor.default-dispatcher-7] [akka://ExtraPattern/deadLetters] Message [org.jamieallen.effectiveakka.common.AccountBalances] from Actor[akka://ExtraPattern/user/extra-retriever/$b#1994845275] to Actor[akka://ExtraPattern/deadLetters] was not delivered. [1] dead letters encountered. If this is not an expected behavior then Actor[akka://ExtraPattern/deadLetters] may have terminated unexpectedly. This logging can be turned off or adjusted with configuration settings 'akka.log-dead-letters' and 'akka.log-dead-letters-during-shutdown'.
[INFO] [akkaDeadLetter][02/09/2023 21:16:47.622] [ExtraPattern-akka.actor.default-dispatcher-7] [akka://ExtraPattern/deadLetters] Message [org.jamieallen.effectiveakka.common.AccountBalances] from Actor[akka://ExtraPattern/user/extra-retriever/$a#1147423554] to Actor[akka://ExtraPattern/deadLetters] was not delivered. [2] dead letters encountered. If this is not an expected behavior then Actor[akka://ExtraPattern/deadLetters] may have terminated unexpectedly. This logging can be turned off or adjusted with configuration settings 'akka.log-dead-letters' and 'akka.log-dead-letters-during-shutdown'.
>>> Press ENTER to exit <<<
&nbsp;
[INFO] [02/09/2023 21:16:50.363] [main] [CoordinatedShutdown(akka://ExtraPattern)] Running CoordinatedShutdown with reason [ActorSystemTerminateReason]
</pre>

### <span id="cameo_pattern">`CameoPattern` Example</span> [**&#x25B4;**](#top)

Batch file [**`build.bat`**](./Chapter02/CameoPattern/build.bat) matches what the user would run from the command prompt (use option **`-debug`** to see the execution details):

<pre style="font-size:80%;">
<b>&gt; <a href="./Chapter02/CameoPattern/build.bat">build</a> -verbose run</b>
Compile 7 Scala source files to directory "target\classes"
Execute Scala main class "org.jamieallen.effectiveakka.CameoPattern"
[CameoPattern-akka.actor.default-dispatcher-4] INFO akka.event.slf4j.Slf4jLogger - Slf4jLogger started
SavingsAccounts    : Received GetCustomerAccountBalances for ID: 1
MoneyMarketAccounts: Received GetCustomerAccountBalances for ID: 1
CheckingAccounts   : Received GetCustomerAccountBalances for ID: 1
SavingsAccounts    : Received GetCustomerAccountBalances for ID: 2
MoneyMarketAccounts: Received GetCustomerAccountBalances for ID: 2
CheckingAccounts   : Received GetCustomerAccountBalances for ID: 2
[CameoPattern-akka.actor.default-dispatcher-10] INFO akka.actor.DeadLetterActorRef -  akkaDeadLetter Message [org.jamieallen.effectiveakka.common.AccountBalances] from Actor[akka://CameoPattern/user/extra-retriever/cameo-message-handler-1#-946328889] to Actor[akka://CameoPattern/deadLetters] was not delivered. [1] dead letters encountered. If this is not an expected behavior then Actor[akka://CameoPattern/deadLetters] may have terminated unexpectedly. This logging can be turned off or adjusted with configuration settings 'akka.log-dead-letters' and 'akka.log-dead-letters-during-shutdown'.
[CameoPattern-akka.actor.default-dispatcher-10] INFO akka.actor.DeadLetterActorRef -  akkaDeadLetter Message [org.jamieallen.effectiveakka.common.AccountBalances] from Actor[akka://CameoPattern/user/extra-retriever/cameo-message-handler-2#162633198] to Actor[akka://CameoPattern/deadLetters] was not delivered. [2] dead letters encountered. If this is not an expected behavior then Actor[akka://CameoPattern/deadLetters] may have terminated unexpectedly. This logging can be turned off or adjusted with configuration settings 'akka.log-dead-letters' and 'akka.log-dead-letters-during-shutdown'.
>>> Press ENTER to exit <<<
&nbsp;
[CameoPattern-akka.actor.default-dispatcher-10] INFO akka.actor.CoordinatedShutdown - Running CoordinatedShutdown with reason [ActorSystemTerminateReason]
</pre>

Bash script [**`build.sh`**](./Chapter02/CameoPattern/build.sh) works in the same way from a Bash shell prompt :

<pre style="font-size:80%;">
<b>&gt; sh <a href=".//Chapter02/CameoPattern/build.sh">build.sh</a> -verbose run</b>
Compile 7 Scala source files to directory "target/classes"
Execute Scala main class "org.jamieallen.effectiveakka.CameoPattern"
SavingsAccounts    : Received GetCustomerAccountBalances for ID: 1
CheckingAccounts   : Received GetCustomerAccountBalances for ID: 1
MoneyMarketAccounts: Received GetCustomerAccountBalances for ID: 1
CheckingAccounts   : Received GetCustomerAccountBalances for ID: 2
SavingsAccounts    : Received GetCustomerAccountBalances for ID: 2
MoneyMarketAccounts: Received GetCustomerAccountBalances for ID: 2
[INFO] [akkaDeadLetter][02/09/2023 20:59:36.085] [CameoPattern-akka.actor.default-dispatcher-9] [akka://CameoPattern/deadLetters] Message [org.jamieallen.effectiveakka.common.AccountBalances] from Actor[akka://CameoPattern/user/extra-retriever/cameo-message-handler-2#900144580] to Actor[akka://CameoPattern/deadLetters] was not delivered. [1] dead letters encountered. If this is not an expected behavior then Actor[akka://CameoPattern/deadLetters] may have terminated unexpectedly. This logging can be turned off or adjusted with configuration settings 'akka.log-dead-letters' and 'akka.log-dead-letters-during-shutdown'.
[INFO] [akkaDeadLetter][02/09/2023 20:59:36.085] [CameoPattern-akka.actor.default-dispatcher-9] [akka://CameoPattern/deadLetters] Message [org.jamieallen.effectiveakka.common.AccountBalances] from Actor[akka://CameoPattern/user/extra-retriever/cameo-message-handler-1#-203518532] to Actor[akka://CameoPattern/deadLetters] was not delivered. [2] dead letters encountered. If this is not an expected behavior then Actor[akka://CameoPattern/deadLetters] may have terminated unexpectedly. This logging can be turned off or adjusted with configuration settings 'akka.log-dead-letters' and 'akka.log-dead-letters-during-shutdown'.
>>> Press ENTER to exit <<<
&nbsp;
[INFO] [02/09/2023 20:59:44.364] [main] [CoordinatedShutdown(akka://CameoPattern)] Running CoordinatedShutdown with reason [ActorSystemTerminateReason]
</pre>

***

*[mics](https://lampwww.epfl.ch/~michelou/)/March 2025* [**&#9650;**](#top)
<span id="bottom">&nbsp;</span>

<!-- link refs -->

[akka]: https://akka.io/
[apache_ant_cli]: https://ant.apache.org/manual/running.html
[apache_maven_cli]: https://maven.apache.org/ref/current/maven-embedder/cli.html
[bash_cli]: https://www.man7.org/linux/man-pages/man1/bash.1.html
[book_allen]: https://www.oreilly.com/library/view/effective-akka/9781449360061/
[cmd_cli]: https://learn.microsoft.com/en-us/windows-server/administration/windows-commands/cmd
[gradle_cli]: https://docs.gradle.org/current/userguide/command_line_interface.html
[sbt_cli]: https://www.scala-sbt.org/1.x/docs/Command-Line-Reference.html
[make_cli]: https://ftp.gnu.org/old-gnu/Manuals/make-3.79.1/html_node/make_86.html
[scala]: https://www.scala-lang.org/
