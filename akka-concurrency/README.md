# <span id="top">Book <i>Akka Concurrency</i></span> <span style="size:30%;"><a href="../README.md">⬆</a></span>

<table style="font-family:Helvetica,Arial;font-size:14px;line-height:1.6;">
  <tr>
  <td style="border:0;padding:0 10px 0 0;min-width:120px;"><a href="https://akka.io/"><img src="../docs/images/akka.svg" width="120" alt="Akka project"/></a></td>
  <td style="border:0;padding:0;vertical-align:text-top;">Directory <a href="."><strong><code>akka-concurrency\</code></strong></a> contains <a href="https://akka.io/" alt="Akka">Akka</a> examples presented in Wyatt's book <a href="https://www.artima.com/shop/akka_concurrency"><i>Akka Concurrency</i></a> (<a href="https://www.artima.com/books/" rel="external" title="Artima">Artima</a>, 2013).<br/>It also includes different build scripts for experimenting with <a href="https://akka.io/" alt="Akka">Akka</a> on a Windows machine.</td>
  </tr>
</table>


Code examples are written in [Scala] and can be built/run with the following tools:

| Build&nbsp;tool | Configuration file | Parent&nbsp;file | Environment(s) |
|:----------------|:-------------------|:----------------|:---------------|
| [**`ant.bat`**][apache_ant_cli] | [`build.xml`](./Chapter05/BadShakespearean/build.xml) | [`build.xml`](./build.xml) | Any <sup><b>a)</b></sup> |
| [**`make.exe`**][make_cli] | [`Makefile`](./Chapter05/BadShakespearean/Makefile) | [`Makefile.inc`](./Makefile.inc) | Any |
| [**`mvn.cmd`**][apache_maven_cli] | [`pom.xml`](./Chapter05/BadShakespearean/pom.xml) | [`pom.xml`](./pom.xml) | Any |
| [**`sbt.bat`**][sbt_cli] | [`build.sbt`](./Chapter05/BadShakespearean/build.sbt) | &nbsp;        | Any |
| [**`build.bat`**](./Chapter05/BadShakespearean/build.bat) | *none*             |  &nbsp;        | Windows only |
<div style="font-size:80%;">
<sup><b>a)</b></sup> Here "Any" means "tested on Windows, Cygwin, MSYS2 and UNIX".<br/>&nbsp;
</div>

> **:mag_right:** Some text quotes from Wyatt's book :
> - ***Section 4.1*** One of the excellent things you can do with actors is to add behaviour to an algorithm by inserting actors into the message flow.
> - ***Section 5.1*** When you send a message to an actor, you’re only sending it to its ActorRef; you never get to interact with the actor in any direct way.
> - ***Section 5.5*** Akka makes it impossible to even construct an actor, or any derivation thereof.

## <span id="badshakespearean">`BadShakespearean`</span>

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

## <span id="avionics">`Avionics`</span>

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

## <span id="avionics_event">`AvionicsEvent`</span>

The code example adds the Scala source file [`EventSource.scala`](./Chapter05/AvionicsEvent//src/main/scala/zzz/akka/avionics/EventSource.scala); see paragraph **Getting updates from the altimeter** in section 5.5 of Wyatt's book for more details.

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
<li><a href="https://doc.akka.io/docs/akka/current/project/migration-guide-2.5.x-2.6.x.html">Migration Guide 2.5.x to 2.6.x</a></li>
<li><a href="https://doc.akka.io/docs/akka/2.5.32/project/migration-guide-2.4.x-2.5.x.html">Migration Guide 2.4.x to 2.5.x</a></li>
<li><a href="https://doc.akka.io/docs/akka/2.4/project/migration-guide-2.3.x-2.4.x.html">Migration Guide 2.3.x to 2.4.x</a></li>
</ul>
</dd></dl>

***

*[mics](https://lampwww.epfl.ch/~michelou/)/February 2022* [**&#9650;**](#top)
<span id="bottom">&nbsp;</span>

<!-- link refs -->

[apache_ant_cli]: https://ant.apache.org/manual/running.html
[apache_maven_cli]: https://maven.apache.org/ref/current/maven-embedder/cli.html
[sbt_cli]: https://www.scala-sbt.org/1.x/docs/Command-Line-Reference.html
[scala]: https://www.scala-lang-org/
[make_cli]: https://ftp.gnu.org/old-gnu/Manuals/make-3.79.1/html_node/make_86.html
