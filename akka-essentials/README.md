# <span id="top">Book <i>Akka Essentials</i></span> <span style="size:30%;"><a href="../README.md">⬆</a></span>

<table style="font-family:Helvetica,Arial;font-size:14px;line-height:1.6;">
  <tr>
  <td style="border:0;padding:0 10px 0 0;min-width:100px;"><a href="https://akka.io/"><img style="border:0;" src="../docs/images/akka.svg" width="100" alt="Akka project"/></a></td>
  <td style="border:0;padding:0;vertical-align:text-top;">Directory <strong><code>akka-essentials\</code></strong> contains <a href="https://akka.io/" alt="Akka">Akka</a> code examples from Gupta's book <a href="https://www.packtpub.com/product/akka-essentials/9781849518284"><i>Akka Essentials</i></a> (Packt, 2012).<br/>It also includes different build scripts for experimenting with <a href="https://akka.io/" alt="Akka">Akka</a> on a Windows machine.
  </td>
  </tr>
</table>

The code examples presented below are written both in Java and in [Scala] and can be built/run with the following tools:

| Build&nbsp;tool | Configuration file | Parent&nbsp;file | Environment(s) |
|:----------------|:-------------------|:-----------------|:---------------|
| [**`ant.bat`**][apache_ant_cli] | [`build.xml`](./Chapter02/FirstAkkaApplication/build.xml)| [`build.xml`](./build.xml) | Any <sup><b>a)</b></sup> |
| [**`gradle.bat`**][gradle_cli] | [`build.gradle`](./Chapter02/FirstAkkaApplication/build.gradle) | [`common.gradle`](./common.gradle) | Any |
| [**`make.exe`**][make_cli] | [`Makefile`](./Chapter02/FirstAkkaApplication/Makefile) | [`Makefile.inc`](./Makefile.inc) | Any |
| [**`mvn.cmd`**][apache_maven_cli] | [`pom.xml`](./Chapter02/FirstAkkaApplication/pom.xml) | [`pom.xml`](./pom.xml) | Any |
| [**`sbt.bat`**][sbt_cli] | [`build.sbt`](./Chapter02/FirstAkkaApplication/build.sbt) | &nbsp;        | Any |
| [**`build.bat`**](./Chapter02/FirstAkkaApplication/build.bat) | *none*             |  &nbsp;        | Windows only |
<div style="font-size:80%;">
<sup><b>a)</b></sup> Here "Any" means "tested on Windows, Cygwin, MSYS2 and UNIX".
</div>

## <span id="first_scala">`FirstAkkaApplication`</span>

Code example `FirstAkkaApplication`<sup id="anchor_01">[1](#footnote_01)</sup> is an [Akka] application written in Java (e.g. [`MapReduceApplication.java`](./Chapter02/FirstAkkaApplication/src/main/java/akka/first/app/mapreduce/MapReduceApplication.java), [`MasterActor.java`](./Chapter02/FirstAkkaApplication/src/main/java/akka/first/app/mapreduce/actors/MasterActor.java)<sup id="anchor_02">[2](#footnote_02)</sup>).

Batch file [**`build.bat`**](./Chapter02/FirstAkkaApplication/build.bat) matches what the user would run from the command prompt (use option **`-debug`** to see the execution details):

<pre style="font-size:80%;">
<b>&gt; <a href="./Chapter02/FirstAkkaApplication/build.bat">build</a> -verbose run</b>
Compile 9 Java source files to directory "target\classes"
[MapReduceApp-akka.actor.default-dispatcher-4] INFO akka.event.slf4j.Slf4jLogger - Slf4jLogger started
{over=1, quick=1, belong=1, lazy=1, best=1, man's=1, brown=1, fox=2, fell=1, tried=1, same=1, friend=1, family=1, dog=4, jump=1}
</pre>

Commands [**`ant.bat`**][apache_ant_cli] ([`build.xml`](./Chapter02/FirstAkkaApplication/build.xml)), [**`gradle.bat`**][gradle_cli] ([`build.gradle`](./Chapter02/FirstAkkaApplication/build.gradle)), [**`make`**][make_cli] ([`Makefile`](./Chapter02/FirstAkkaApplication/Makefile)), [**`mvn`**][apache_maven_cli] ([`pom.xml`](./Chapter02/FirstAkkaApplication/pom.xml)) and [**`sbt`**][sbt_cli] ([`build.sbt`](./Chapter02/FirstAkkaApplication/build.sbt)) produce the same output; for instance :

<pre style="font-size:80%;">
<b>&gt; <a href="https://www.gnu.org/software/make/manual/html_node/Running.html">make</a> -s run</b>
{over=1, quick=1, belong=1, lazy=1, best=1, man's=1, brown=1, fox=2, fell=1, tried=1, same=1, friend=1, family=1, dog=4, jump=1}
</pre>

> **:mag_right:** `FirstAkkaApplicationScala` is the same [Akka] application written in [Scala] (e.g. [`MapReduceApplication.scala`](./Chapter02/FirstAkkaApplicationScala/src/main/scala/akka/first/app/mapreduce/MapReduceApplication.scala), [`MasterActor.scala`](./Chapter02/FirstAkkaApplicationScala/src/main/scala/akka/first/app/mapreduce/actors/MasterActor.scala)). For instance [**`build.bat`**](./Chapter02/FirstAkkaApplicationScala/build.bat) generates the following output:
> &nbsp;
> <pre style="font-size:80%;">
> <b>&gt; <a href="./Chapter02/FirstAkkaAppicationScala/build.bat">build</a> run</b>
> [MapReduceApp-akka.actor.default-dispatcher-4] INFO akka.event.slf4j.Slf4jLogger - Slf4jLogger started
> HashMap(over -> 1, quick -> 1, belong -> 1, lazy -> 1, best -> 1, man's -> 1, brown -> 1, fox -> 2, fell -> 1, tried -> 1, same -> 1, friend -> 1, family -> 1, dog -> 4, jump -> 1)
> </pre>

## <span id="process_order">`ProcessOrder`</span>

Code example `ProcessOrder` is an [Akka] application written in Java (e.g. [`ProcessOrderApp.java`](./Chapter03/ProcessOrder/src/main/java/ProcessOrderApp.java), [`ProcessOrderActor.java`](./Chapter03/ProcessOrder/src/main/java/actors/ProcessOrderActor.java)).

Batch file [**`build.bat`**](./Chapter03/ProcessOrder/build.bat) matches what the user would run from the command prompt (use option **`-debug`** to see the execution details):

<pre style="font-size:80%;">
<b>&gt; <a href="./Chapter03/ProcessOrder/build.bat">build</a> -verbose run</b>
Compile 8 Java source files to directory "target\classes"
[ProcessOrder-akka.actor.default-dispatcher-5] INFO akka.event.slf4j.Slf4jLogger - Slf4jLogger started
OrderActor: userId=1
OrderAggregate: akka.actor.Status$Failure
</pre>

> **:mag_right:** Code example `ProcessOrderScala` is the same [Akka] application written in [Scala] (e.g. [`MapReduceApplication.scala`](./Chapter03/ProcessOrderScala/src/main/scala/akka/first/app/mapreduce/MapReduceApplication.scala), [`MasterActor.scala`](./Chapter03/ProcessOrderScala/src/main/scala/akka/first/app/mapreduce/actors/MasterActor.scala)). For instance [**`build.bat`**](./Chapter03/ProcessOrderScala/build.bat) generates the following output :
> &nbsp;
> <pre style="font-size:80%;">
> <b>&gt; <a href="./Chapter03/ProcessOrderScala/build.bat">build</a> run</b>
> ...
> </pre>

## <span id="ping_pong">`PingPong`</span>

Code example `PingPong` is an [Akka] application written in Java (e.g. [`PingPongApp.java`](./Chapter03/PingPong/src/main/java/PingPongApp.java), [`PingPongActor.java`](./Chapter03/PingPong/src/main/java/PingPongActor.java)).

Batch file [**`build.bat`**](./Chapter03/PingPong/build.bat) matches what the user would run from the command prompt (use option **`-debug`** to see the execution details):

<pre style="font-size:80%;">
<b>&gt; <a href="./Chapter03/PingPong/build.bat">build</a> -verbose run</b>
Compile 2 Java source files to directory "target\classes"
[PingPong-akka.actor.default-dispatcher-5] INFO akka.event.slf4j.Slf4jLogger - Slf4jLogger started
PING
PONG
PING
PONG
PING
PONG
PING
PONG
PING
PONG
PING
</pre>

> **:mag_right:** `PingPongScala` is the same [Akka] application written in [Scala] (e.g. [`MapReduceApplication.scala`](./Chapter03/PingPongScala/src/main/scala/PingPongApp.scala), [`PingPongActor.scala`](./Chapter03/PingPongScala/src/main/scala//MasterActor.scala)). For instance [**`build.bat`**](./Chapter03/PingPongScala/build.bat) generates the following output :
> &nbsp;
> <pre style="font-size:80%;">
> <b>&gt; <a href="./Chapter03/PingPongScala/build.bat">build</a> run</b>
> [PingPong-akka.actor.default-dispatcher-4] INFO akka.event.slf4j.Slf4jLogger - Slf4jLogger started
> PING
> PONG
> PING
> PONG
> PING
> PONG
> PING
> PONG
> PING
> PONG
> PING
> </pre>

## <span id="footnotes">Footnotes</span>

<span id="footnote_01">[1]</span> ***Source code updates*** [↩](#anchor_01)

<dl><dd>
We have updated several deprecated code in the original examples of <a href="https://www.packtpub.com/product/akka-essentials/9781849518284">Gupta's book</a>:
</dd>
<dd>
<table style="font-size:90%;">
<tr>
<th>Original source code</th>
<th>Updated source code <sup><b>a)</b></sup></th>
</tr>
<tr>
<td>(&le;2.5) <code>akka.actor.<a href="https://doc.akka.io/japi/akka/2.5/akka/actor/UntypedActor.html">UntypedActor</a></code></td>
<td><code>akka.actor.<a href="https://doc.akka.io/japi/akka/current/akka/actor/UntypedAbstractActor.html">UntypedAbstractActor</a></code></td>
</tr>
<tr style="padding:0;">
<td>(&le;2.3) <code>akka.routing.<a href="https://doc.akka.io/japi/akka/2.3/akka/routing/RoundRobinRouter.html">RoundRobinRouter</a></code></td>
<td><code>akka.routing.<a href="https://doc.akka.io/japi/akka/current/akka/routing/RoundRobinPool.html">RoundRobinPool</a></code></td>
</tr>
<tr style="padding:0;">
<td><code>akka.dispatch.Await</code></td>
<td><code>scala.concurrent.<a href="https://www.scala-lang.org/api/current/scala/concurrent/Await$.html">Await</a></code><sup><b>b)</b></sup></td>
</tr>
<tr style="padding:0;">
<td><code>akka.dispatch.Future</code></td>
<td><code>scala.concurrent.<a href="https://www.scala-lang.org/api/current/scala/concurrent/Future.html">Future</a></code><sup><b>c)</b></sup></td>
</tr>
<tr style="padding:0;">
<td>(&le;2.4) <code>_system.<a href="https://doc.akka.io/docs/akka/2.4/project/migration-guide-2.3.x-2.4.x.html#Actor_system_shutdown">shutdown()</a></code></td>
<td><code>_system.<a href="https://doc.akka.io/api/akka/current/akka/actor/ActorSystem.html#terminate():scala.concurrent.Future[akka.actor.Terminated]">terminate()</a></code></td>
</tr>
</table>
<div style="margin:0 0 8px 16px;font-size:80%;">
<sup><b>a)</b></sup> Akka version 2.6.x<br/>
<sup><b>b)</b></sup> Object <a href="https://www.scala-lang.org/api/current/scala/concurrent/Await$.html"><code><b>Await</b></code></a> is defined in file <a href="https://github.com/scala/scala/blob/89e57bc7ad4a1809864b637617456736fd7b8101/src/library/scala/concurrent/package.scala#L65"><code>scala/concurrent/package.scala</code></a> since Scala 2.10.
<br/>
<sup><b>c)</b></sup> Trait/object <a href="https://www.scala-lang.org/api/current/scala/concurrent/Future.html"><code><b>Future</b></code></a> is defined in file <a href="https://github.com/scala/scala/blob/89e57bc7ad4a1809864b637617456736fd7b8101/src/library/scala/concurrent/Future.scala#L93"><code>scala/concurrent/Future.scala</code></a> since Scala 2.10.
</div>
See the online documentation for further informations: 
<ul>
<li><a href="https://doc.akka.io/docs/akka/current/project/migration-guide-2.5.x-2.6.x.html">Migration Guide 2.5.x to 2.6.x</a></li>
<li><a href="https://doc.akka.io/docs/akka/2.5.32/project/migration-guide-2.4.x-2.5.x.html">Migration Guide 2.4.x to 2.5.x</a></li>
<li><a href="https://doc.akka.io/docs/akka/2.4/project/migration-guide-2.3.x-2.4.x.html">Migration Guide 2.3.x to 2.4.x</a></li>
</ul>
</dd></dl>

<span id="footnote_02">[2]</span> ***FirstAkkaApplication*: 2 versions** [↩](#anchor_02)

<dl><dd>
We actually provide two versions of <code>FirstAkkaApplication</code>:
</dd>
<dd>
<ul>
<li>in <a href="./Chapter02/FirstAkkaApplication/"><code>FirstAkkaApplication</code></a> the 4 actors are defined as subclasses of <code>UntypedAbstractActor</code> and do overwrite method <a href="https://doc.akka.io/japi/akka/current/akka/actor/UntypedAbstractActor.html#onReceive(java.lang.Object)"><code>onReceive(Object message)</code></a>.
<pre style="font-size:80%;">
<b>public class</b> MapActor <b>extends</b> <a href="https://doc.akka.io/japi/akka/current/akka/actor/UntypedAbstractActor.html">UntypedAbstractActor</a> {
    // ...
    <a href="https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/lang/Override.html">@Override</a>
    <b>public void</b> onReceive(Object message) <b>throws</b> Exception {
        <b>if</b> (message <b>instanceof</b> String) {
            // ...
        } <b>else</b>
            <a href="https://doc.akka.io/japi/akka/current/akka/actor/Actor.html#unhandled(java.lang.Object)">unhandled</a>(message);
    }
}
</pre>
</li>
<li>in <a href="./Chapter02/FirstAkkaApplication2/"><code>FirstAkkaApplication2</code></a> the 4 actors are defined as subclasses of <a href="https://doc.akka.io/japi/akka/current/akka/actor/AbstractActor.html"><code>akka.actor.AbstractActor</code></a> and do implement method <a href="https://doc.akka.io/japi/akka/current/akka/actor/AbstractActor.html#createReceive()"><code>createReceive()</code></a>.
<pre style="font-size:80%;">
<b>public class</b> MapActor <b>extends</b> <a href="https://doc.akka.io/japi/akka/current/akka/actor/AbstractActor.html">AbstractActor</a> {
    // ...
    <b>public</b> Receive createReceive() {
        <b>return</b> <a href="https://doc.akka.io/japi/akka/current/akka/actor/AbstractActor.html#receiveBuilder()">receiveBuilder()</a>
            .<a href="https://doc.akka.io/japi/akka/current/akka/japi/pf/ReceiveBuilder.html#match(java.lang.Class,akka.japi.pf.FI.UnitApply)">match</a>(String.class, word -> { /* ... */ })
            .<a href="https://doc.akka.io/japi/akka/current/akka/japi/pf/ReceiveBuilder.html#matchAny(java.util.function.BooleanSupplier,akka.japi.pf.FI.UnitApply)">matchAny</a>(message -> { <a href="https://doc.akka.io/japi/akka/current/akka/actor/Actor.html#unhandled(java.lang.Object)">unhandled</a>(message); })
            .<a href="https://doc.akka.io/japi/akka/current/akka/japi/pf/ReceiveBuilder.html#build()">build()</a>;
    }
}
</pre>
</li>
</ul>
</dd>
</dd></dl>

***

*[mics](https://lampwww.epfl.ch/~michelou/)/May 2022* [**&#9650;**](#top)
<span id="bottom">&nbsp;</span>

<!-- link refs -->

[akka]: https://akka.io/
[apache_ant_cli]: https://ant.apache.org/manual/running.html
[apache_maven_cli]: https://maven.apache.org/ref/current/maven-embedder/cli.html
[book_gupta]: https://www.packtpub.com/product/akka-essentials/9781849518284
[gradle_cli]: https://docs.gradle.org/current/userguide/command_line_interface.html
[sbt_cli]: https://www.scala-sbt.org/1.x/docs/Command-Line-Reference.html
[make_cli]: https://ftp.gnu.org/old-gnu/Manuals/make-3.79.1/html_node/make_86.html
[scala]: https://www.scala-lang.org/
