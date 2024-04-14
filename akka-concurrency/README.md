# <span id="top">Book <i>Akka Concurrency</i></span> <span style="size:30%;"><a href="../README.md">⬆</a></span>

<table style="font-family:Helvetica,Arial;line-height:1.6;">
  <tr>
  <td style="border:0;padding:0 10px 0 0;min-width:120px;"><a href="https://akka.io/" rel="external"><img src="../docs/images/akka.svg" width="120" alt="Akka project"/></a></td>
  <td style="border:0;padding:0;vertical-align:text-top;">Directory <a href="."><strong><code>akka-concurrency\</code></strong></a> contains <a href="https://akka.io/" rel="external">Akka</a> examples presented in Wyatt's book <a href="https://www.artima.com/shop/akka_concurrency" rel="external"><i>Akka Concurrency</i></a> (<a href="https://www.artima.com/books/" rel="external" title="Artima">Artima</a>, 2013).<br/>It also includes different build scripts for experimenting with <a href="https://akka.io/" rel="external">Akka</a> on a Windows machine.</td>
  </tr>
</table>


Code examples are written in [Scala] and can be built/run with the following tools:

| Build&nbsp;tool | Build&nbsp;file | Parent&nbsp;file | Environment(s) |
|:----------------|:----------------|:-----------------|:---------------|
| [**`ant.bat`**][apache_ant_cli] | [`build.xml`](./Chapter05/BadShakespearean/build.xml) | [`build.xml`](./build.xml) | Any <sup><b>a)</b></sup> |
| [**`gradle.bat`**][gradle_cli] | [`build.gradle`](./Chapter05/BadShakespearean/build.gradle) | [`common.gradle`](./common.gradle) | Any |
| [**`make.exe`**][make_cli] | [`Makefile`](./Chapter05/BadShakespearean/Makefile) | [`Makefile.inc`](./Makefile.inc) | Any |
| [**`mvn.cmd`**][apache_maven_cli] | [`pom.xml`](./Chapter05/BadShakespearean/pom.xml) | [`pom.xml`](./pom.xml) | Any |
| [**`sbt.bat`**][sbt_cli] | [`build.sbt`](./Chapter05/BadShakespearean/build.sbt) | &nbsp;        | Any |
| [**`cmd.exe`**][cmd_cli] | [`build.bat`](./Chapter05/BadShakespearean/build.bat)   |  &nbsp;        | Windows only |
<div style="font-size:80%;">
<sup><b>a)</b></sup> Here "Any" means "tested on Windows, Cygwin, MSYS2 and UNIX".<br/>&nbsp;
</div>

> **:mag_right:** Some text quotes from Wyatt's book :
> - ***Section 4.1*** One of the excellent things you can do with actors is to add behaviour to an algorithm by inserting actors into the message flow.
> - ***Section 5.1*** When you send a message to an actor, you’re only sending it to its ActorRef; you never get to interact with the actor in any direct way.
> - ***Section 5.5*** Akka makes it impossible to even construct an actor, or any derivation thereof.

## <span id="ch05">Chapter 5</span> [**&#x25B4;**](#top)

### <span id="ch05_badshakespearean">`BadShakespearean` Example</span>

Code example [`BadShakespearean`](./Chapter05/BadShakespearean/) is organized as follows :

<pre style="font-size:80%;">
<b>&gt; <a href="https://learn.microsoft.com/en-us/windows-server/administration/windows-commands/tree" rel="external">tree</a> /f /a . | <a href="https://learn.microsoft.com/en-us/windows-server/administration/windows-commands/findstr" rel="external">findstr</a> /v /b [A-Z]</b>
|   <a href="./Chapter05/BadShakespearean/build.bat">build.bat</a>
|   <a href="./Chapter05/BadShakespearean/build.gradle">build.gradle</a>
|   <a href="./Chapter05/BadShakespearean/build.sbt">build.sbt</a>
|   <a href="./Chapter05/BadShakespearean/build.xml">build.xml</a>
|   <a href="./Chapter05/BadShakespearean/gradle.properties">gradle.properties</a>
|   <a href="./Chapter05/BadShakespearean/Makefile">Makefile</a>
|   <a href="./Chapter05/BadShakespearean/pom.xml">pom.xml</a>
+---project
|       <a href="./Chapter05/BadShakespearean/project/build.properties">build.properties</a>
\---src
    \---main
        +---resources
        |       <a href="./Chapter05/BadShakespearean/src/main/resources/application.conf">application.conf</a>
        \---scala
            \---zzz
                \---akka
                    \---investigation
                            <a href="./Chapter05/BadShakespearean/src/main/scala/zzz/akka/investigation/BadShakespeareanActor.scala">BadShakespeareanActor.scala</a>
                            <a href="./Chapter05/BadShakespearean/src/main/scala/zzz/akka/investigation/BadShakespeareanMain.scala">BadShakespeareanMain.scala</a>
</pre>

Batch file [**`build.bat`**](./Chapter05/BadShakespearean/build.bat) matches what a user would run from the command prompt (use option **`-debug`** to see the execution details):

<pre style="font-size:80%;">
<b>&gt; <a href="Chapter05/BadShakespearean/build.bat">build</a> -verbose run</b>
Compile 1 Scala source file to directory "target\classes"
[BadShakespearean-...-4] INFO akka.event.slf4j.Slf4jLogger - Slf4jLogger started
Me: Good Morning
Him: Forsooth 'tis the 'morn, but mourneth for thou doest I do!
Me: You're terrible
Him: Yup
</pre>

> **:mag_right:** Commands [**`ant.bat`**][apache_ant_cli] ([`build.xml`](./Chapter05/BadShakespearean/build.xml)) [**`make`**][make_cli] ([`Makefile`](./Chapter05/BadShakespearean/Makefile)), [**`mvn`**][apache_maven_cli] ([`pom.xml`](./Chapter05/BadShakespearean/pom.xml)) and [**`sbt`**][sbt_cli] ([`build.sbt`](./Chapter05/BadShakespearean/build.sbt)) produce the same output; for instance :
> <pre style="font-size:80%;">
> <b>&gt; <a href="https://www.gnu.org/software/make/manual/html_node/Running.html">make</a> -s run</b>
> Me: Good Morning
> Him: Forsooth 'tis the 'morn, but mourneth for thou doest I do!
> Me: You're terrible
> Him: Yup
> [INFO] [02/21/2022 15:52:44.964] [main] [CoordinatedShutdown(akka://BadShakespearean)] Running CoordinatedShutdown with reason [ActorSystemTerminateReason]
> </pre>

### <span id="ch05_avionics">`Avionics` Example</span>

Code example [`Avionics`](./Chapter05/Avionics/) is composed of the 4 source files [`Altimeter.scala`](./Chapter05/Avionics/src/main/scala/zzz/akka/avionics/Altimeter.scala), [`Avionics.scala`](./Chapter05/Avionics/src/main/scala/zzz/akka/avionics/Avionics.scala), [`ControlSurfaces.scala`](./Chapter05/Avionics/src/main/scala/zzz/akka/avionics/ControlSurfaces.scala) and [`Plane.scala`](./Chapter05/Avionics/src/main/scala/zzz/akka/avionics/Plane.scala).

Batch file [**`build.bat`**](./Chapter05/Avionics/build.bat) matches what a user would run from the command prompt (use option **`-debug`** to see the execution details):

<pre style="font-size:80%;">
<b>&gt; <a href="./Chapter05/Avionics/build.bat">build</a> -verbose run</b>
Compile 4 Scala source files to directory "target\classes"
[PlaneSimulation-...-5] INFO akka.event.slf4j.Slf4jLogger - Slf4jLogger started
[PlaneSimulation-...-6] INFO zzz.akka.avionics.Plane - Plane giving control.
[PlaneSimulation-...-6] INFO zzz.akka.avionics.Altimeter - Altimeter changed rate of climb to 5000.0.
[PlaneSimulation-...-6] INFO zzz.akka.avionics.Altimeter - Altimeter changed rate of climb to 0.0.
[PlaneSimulation-...-6] INFO zzz.akka.avionics.Altimeter - Altimeter changed rate of climb to 2500.0.
[PlaneSimulation-...-6] INFO zzz.akka.avionics.Altimeter - Altimeter changed rate of climb to 0.0.
[PlaneSimulation-...-5] INFO akka.actor.CoordinatedShutdown - Running CoordinatedShutdown with reason [ActorSystemTerminateReason]
</pre>

### <span id="ch05_avionics_event">`AvionicsEvent` Example</span>

This example adds the Scala source file [`EventSource.scala`](./Chapter05/AvionicsEvent//src/main/scala/zzz/akka/avionics/EventSource.scala) to the previous example (see paragraph **Getting updates from the altimeter** in section 5.5 of Wyatt's book for more details).

Batch file [**`build.bat`**](./Chapter05/AvionicsEvent/build.bat) matches what a user would run from the command prompt (use option **`-debug`** to see the execution details):

<pre style="font-size:80%;">
<b>&gt; <a href="./Chapter05/AvionicsEvent/build.bat">build</a> -verbose run</b>
Compile 5 Scala source files to directory "target\classes"
[PlaneSimulation-...-4] INFO akka.event.slf4j.Slf4jLogger - Slf4jLogger started
[PlaneSimulation-...-6] INFO zzz.akka.avionics.Plane - Plane giving control.
[PlaneSimulation-...-4] INFO zzz.akka.avionics.Plane - Altitude is now: 0.0
[PlaneSimulation-...-4] INFO zzz.akka.avionics.Plane - Altitude is now: 0.0
[PlaneSimulation-...-4] INFO zzz.akka.avionics.Altimeter - Altimeter changed rate of climb to 5000.0.
[PlaneSimulation-...-6] INFO zzz.akka.avionics.Plane - Altitude is now: 8.416666666666666
[PlaneSimulation-...-6] INFO zzz.akka.avionics.Plane - Altitude is now: 16.75
[PlaneSimulation-...-4] INFO zzz.akka.avionics.Plane - Altitude is now: 25.083333333333336
[PlaneSimulation-...-6] INFO zzz.akka.avionics.Plane - Altitude is now: 33.41666666666667
[PlaneSimulation-...-6] INFO zzz.akka.avionics.Plane - Altitude is now: 41.833333333333336
[PlaneSimulation-...-4] INFO zzz.akka.avionics.Plane - Altitude is now: 50.16666666666667
[PlaneSimulation-...-6] INFO zzz.akka.avionics.Plane - Altitude is now: 59.833333333333336
[PlaneSimulation-...-4] INFO zzz.akka.avionics.Plane - Altitude is now: 66.91666666666667
[PlaneSimulation-...-6] INFO zzz.akka.avionics.Altimeter - Altimeter changed rate of climb to 0.0.
[PlaneSimulation-...-4] INFO zzz.akka.avionics.Plane - Altitude is now: 66.91666666666667
[...]
[PlaneSimulation-...-5] INFO zzz.akka.avionics.Plane - Altitude is now: 66.91666666666667
[PlaneSimulation-...-5] INFO zzz.akka.avionics.Altimeter - Altimeter changed rate of climb to 2500.0.
[PlaneSimulation-...-8] INFO zzz.akka.avionics.Plane - Altitude is now: 71.125
[...]
[PlaneSimulation-...-7] INFO zzz.akka.avionics.Altimeter - Altimeter changed rate of climb to 0.0.
[PlaneSimulation-...-7] INFO zzz.akka.avionics.Plane - Altitude is now: 108.75000000000001
[...]
[PlaneSimulation-...-7] INFO akka.actor.CoordinatedShutdown - Running CoordinatedShutdown with reason [ActorSystemTerminateReason]
</pre>

## <span id="ch06">Chapter 6</span> [**&#x25B4;**](#top)

### <span id="ch06_avionics">`Avionics` Example</span>

This example adds a [ScalaTest] suite to example [`AvionicsEvent`](#ch05_avionics_event) of Chapter 5. The test source file is [`TestEventSource.scala`](./Chapter06/Avionics/src/test/scala/zzz/akka/avionics/TestEventSource.scala) <sup id="anchor_03">[3](#footnote_03)</sup>.

Batch file [**`build.bat`**](./Chapter05/Avionics/build.bat) matches what a user would run from the command prompt (use option **`-debug`** to see the execution details):

<pre style="font-size:80%;">
<b>&gt; <a href="./Chapter06/Avionics/build.bat">build</a> -verbose clean test</b>
Delete directory "target"
Compile 5 Scala source files to directory "target\classes"
Compile 1 Scala test source file to directory "target\test-classes"
Execute ScalaTest suite "zzz.akka.avionics.EventSourceSpec"
[EventSourceSpec-akka.actor.default-dispatcher-4] INFO akka.event.slf4j.Slf4jLogger - Slf4jLogger started
Run starting. Expected test count is: 3
EventSourceSpec:
EventSource
- should allow us to register a listener
- should allow us to unregister a listener
- should send the event to our test actor
[EventSourceSpec-akka.actor.default-dispatcher-4] INFO akka.actor.CoordinatedShutdown - Running CoordinatedShutdown with reason [ActorSystemTerminateReason]
Run completed in 994 milliseconds.
Total number of tests run: 3
Suites: completed 1, aborted 0
Tests: succeeded 3, failed 0, canceled 0, ignored 0, pending 0
All tests passed.
</pre>

## <span id="ch07">Chapter 7</span> [**&#x25B4;**](#top)

*WIP*

## <span id="footnotes">Footnotes</span>

<span id="footnote_01">[1]</span> ***Source code updates*** [↩](#anchor_01)

<dl><dd>
We have updated several deprecated code in the original examples of <a href="https://www.artima.com/shop/akka_concurrency">Wyatt's book</a>:
</dd>
<dd>
<table style="font-size:90%;">
<tr>
<th>Original source code</th>
<th>Updated source code</th>
</tr>
<tr>
<td>(&le;2.6) <code>akka.actor.Scheduler.<a href="https://doc.akka.io/api/akka/2.6/akka/actor/Scheduler.html#schedule(initialDelay:scala.concurrent.duration.FiniteDuration,interval:scala.concurrent.duration.FiniteDuration,runnable:Runnable)(implicitexecutor:scala.concurrent.ExecutionContext):akka.actor.Cancellable">schedule</a></code></td>
<td><code>akka.actor.Scheduler.<a href="https://doc.akka.io/api/akka/2.6/akka/actor/Scheduler.html#scheduleAtFixedRate(initialDelay:java.time.Duration,interval:java.time.Duration,receiver:akka.actor.ActorRef,message:Any,executor:scala.concurrent.ExecutionContext,sender:akka.actor.ActorRef):akka.actor.Cancellable">scheduleAtFixedRate</a></code></td>
</tr>
<tr style="padding:0;">
<td>(&le;2.4) <code>system.<a href="https://doc.akka.io/docs/akka/2.4/project/migration-guide-2.3.x-2.4.x.html#Actor_system_shutdown">shutdown()</a></code></td>
<td><code>system.<a href="https://doc.akka.io/api/akka/current/akka/actor/ActorSystem.html#terminate():scala.concurrent.Future[akka.actor.Terminated]">terminate()</a</code></td>
</tr>
</table>
See the online documentation for further informations: 
<ul>
<li><a href="https://doc.akka.io/docs/akka/current/project/migration-guide-2.8.x-2.9.x.html">Migration Guide 2.8.x to 2.9.x</a></li>
<li><a href="https://doc.akka.io/docs/akka/current/project/migration-guide-2.7.x-2.8.x.html">Migration Guide 2.7.x to 2.8.x</a></li>
<li><a href="https://doc.akka.io/docs/akka/current/project/migration-guide-2.6.x-2.7.x.html" rel="external">Migration Guide 2.6.x to 2.7.x</a></li>
<li><a href="https://doc.akka.io/docs/akka/current/project/migration-guide-2.5.x-2.6.x.html" rel="external">Migration Guide 2.5.x to 2.6.x</a></li>
<li><a href="https://doc.akka.io/docs/akka/2.5.32/project/migration-guide-2.4.x-2.5.x.html" rel="external">Migration Guide 2.4.x to 2.5.x</a></li>
<li><a href="https://doc.akka.io/docs/akka/2.4/project/migration-guide-2.3.x-2.4.x.html" rel="external">Migration Guide 2.3.x to 2.4.x</a></li>
</ul>
</dd></dl>

<span id="footnote_02">[2]</span> ***Dead Letter Office*** [↩](#anchor_02)

<dl><dd>
When the sender of a message is <code>null</code>, <a href="https://akka.io/" rel="external">Akka</a> attaches a default sender called the <i>dead letter office</i>, which is a single actor instance per <a href="https://doc.akka.io/docs/akka/current/general/actor-systems.html" rel="external">ActorSystem</a> and can be accessed directly from the <a href="https://doc.akka.io/japi/akka/current/akka/actor/ActorSystem.html"><code>akka.actor.ActorSystem</code></a> via method <a href="https://doc.akka.io/japi/akka/current/akka/actor/ActorSystem.html#deadLetters()" rel="external"><code>deadLetters</code></a>.
</dd></dl>

<span id="footnote_03">[3]</span> ***ScalaTest API 3.2 Removals*** [↩](#anchor_03)

<dl><dd>
The <a href="https://www.scalatest.org/release_notes/3.2.0"><i>ScalaTest/Scalactic 3.2.0 Release Notes</i></a> announces many <b><i>deprecation expirations</i></b>, a consequence of the new ScalaTest 3.2 modularization (and the associated artifact reorganization).
</dd>
<dd>
Code examples from Wyatt's book <a href="https://www.artima.com/shop/akka_concurrency"><i>Akka Concurrency</i></a> are thus impacted by those removals, for instance :
</dd>
<dd>
<table>
<tr>
  <th>removed in 3.20</th>
  <th>new&nbsp;name</th>
</tr>
<tr>
  <td><code>org.scalatest.MustMatchers</code></td>
  <td><code>org.scalatest.matchers.must.Matchers</code></td>
</tr>
<tr>
  <td><code>org.scalatest.WordSpec</code></td>
  <td><code>org.scalatest.wordspec.AnyWordSpec</code></td></tr>
</table>
</dd></dl>

***

*[mics](https://lampwww.epfl.ch/~michelou/)/April 2024* [**&#9650;**](#top)
<span id="bottom">&nbsp;</span>

<!-- link refs -->

[akka]: https://akka.io/
[apache_ant_cli]: https://ant.apache.org/manual/running.html
[apache_maven_cli]: https://maven.apache.org/ref/current/maven-embedder/cli.html
[cmd_cli]: https://learn.microsoft.com/en-us/windows-server/administration/windows-commands/cmd
[gradle_cli]: https://docs.gradle.org/current/userguide/command_line_interface.html
[sbt_cli]: https://www.scala-sbt.org/1.x/docs/Command-Line-Reference.html
[scala]: https://www.scala-lang-org/
[scalatest]: https://www.scalatest.org/
[make_cli]: https://ftp.gnu.org/old-gnu/Manuals/make-3.79.1/html_node/make_86.html
