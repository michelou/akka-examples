# <span id="top">Book <i>Akka Essentials</i></span> <span style="size:30%;"><a href="../README.md">⬆</a></span>

<table style="font-family:Helvetica,Arial;font-size:14px;line-height:1.6;">
  <tr>
  <td style="border:0;padding:0 10px 0 0;min-width:100px;"><a href="https://akka.io/"><img style="border:0;" src="../docs/images/akka.svg" width="100" alt="Akka project"/></a></td>
  <td style="border:0;padding:0;vertical-align:text-top;">Directory <strong><code>akka-essentials\</code></strong> contains <a href="https://akka.io/" alt="Akka">Akka</a> code examples from Gupta's book <a href="https://www.packtpub.com/product/akka-essentials/9781849518284"><i>Akka Essentials</i></a> (Packt, 2012).<br/>It also includes different build scripts for experimenting with <a href="https://akka.io/" alt="Akka">Akka</a> on a Windows machine.
  </td>
  </tr>
</table>

Code examples can be built/run with the following tools:

| Build&nbsp;tool | Configuration file | Parent&nbsp;file | Environment(s) |
|:----------------|:-------------------|:----------------|:---------------|
| [**`make.exe`**][make_cli] | [`Makefile`](./Chapter02/FirstAkkaApplication/Makefile) | [`Makefile.inc`](./Makefile.inc) | Any<sup><b>a)</b></sup> |
| [**`mvn.cmd`**][apache_maven_cli] | [`pom.xml`](./Chapter02/FirstAkkaApplication/pom.xml) | [`pom.xml`](./pom.xml) | Any |
| [**`sbt.bat`**][sbt_cli] | [`build.sbt`](./Chapter02/FirstAkkaApplication/build.sbt) | &nbsp;        | Any |
| [**`build.bat`**](./Chapter02/FirstAkkaApplication/build.bat) | *none*             |  &nbsp;        | Windows only |
<div style="font-size:80%;">
<sup><b>a)</b></sup>: Here "Any" means "tested on Windows, Cygwin, MSYS2 and UNIX".
</div>

## <span id="first_scala">`FirstAkkaApplication`</span>

Code example `FirstAkkaApplication`<sup id="anchor_01">[1](#footnote_01)</sup> is an [Akka] application written in Java (e.g. [`MapReduceApplication.java`](./Chapter02/FirstAkkaApplication/src/main/java/akka/first/app/mapreduce/MapReduceApplication.java), [`MasterActor.java`](./Chapter02/FirstAkkaApplication/src/main/java/akka/first/app/mapreduce/actors/MasterActor.java)).

Batch file [**`build.bat`**](./Chapter02/FirstAkkaApplication/build.bat) matches what the user would run from the command prompt (use option **`-debug`** to see the execution details):

<pre style="font-size:80%;">
<b>&gt; <a href="./Chapter02/FirstAkkaApplication/build.bat">build</a> -verbose run</b>
Compile 9 Java source files to directory "target\classes"
[MapReduceApp-akka.actor.default-dispatcher-4] INFO akka.event.slf4j.Slf4jLogger - Slf4jLogger started
{over=1, quick=1, belong=1, lazy=1, best=1, man's=1, brown=1, fox=2, fell=1, tried=1, same=1, friend=1, family=1, dog=4, jump=1}
</pre>

> **:mag_right:** Commands [**`make`**][make_cli] ([`Makefile`](./Chapter02/FirstAkkaApplication/Makefile)), [**`mvn`**][apache_maven_cli] ([`pom.xml`](./Chapter02/FirstAkkaApplication/pom.xml)) and [**`sbt`**][sbt_cli] ([`build.sbt`](./Chapter02/FirstAkkaApplication/build.sbt)) produce the same output :
> <pre style="font-size:80%;">
> <b>&gt; <a href="https://www.gnu.org/software/make/manual/html_node/Running.html">make</a> -s run</b>
> {over=1, quick=1, belong=1, lazy=1, best=1, man's=1, brown=1, fox=2, fell=1, tried=1, same=1, friend=1, family=1, dog=4, jump=1}
> </pre>

## <span id="first_scala">`FirstAkkaApplicationScala`</span>

Code example `FirstAkkaApplicationScala` is the same [Akka] application written in [Scala] (e.g. [`MapReduceApplication.scala`](./Chapter02/FirstAkkaApplicationScala/src/main/scala/akka/first/app/mapreduce/MapReduceApplication.scala), [`MasterActor.scala`](./Chapter02/FirstAkkaApplicationScala/src/main/scala/akka/first/app/mapreduce/actors/MasterActor.scala)).

Batch file [**`build.bat`**](./Chapter02/FirstAkkaApplicationScala/build.bat) matches what the user would run from the command prompt (use option **`-debug`** to see the execution details):

<pre style="font-size:80%;">
<b>&gt; <a href="FirstAkkaAppicationScala/build.bat">build</a> run</b>
[MapReduceApp-akka.actor.default-dispatcher-4] INFO akka.event.slf4j.Slf4jLogger - Slf4jLogger started
HashMap(over -> 1, quick -> 1, belong -> 1, lazy -> 1, best -> 1, man's -> 1, brown -> 1, fox -> 2, fell -> 1, tried -> 1, same -> 1, friend -> 1, family -> 1, dog -> 4, jump -> 1)
</pre>

> **:mag_right:** Commands [**`make`**][make_cli] ([`Makefile`](./Chapter02/FirstAkkaApplicationScala/Makefile)), [**`mvn`**][apache_maven_cli] ([`pom.xml`](./Chapter02/FirstAkkaApplicationScala/pom.xml)) and [**`sbt`**][sbt_cli] ([`build.sbt`](./Chapter02/FirstAkkaApplicationScala/build.sbt)) produce the same output :
> <pre style="font-size:80%;">
> <b>&gt; <a href="https://www.gnu.org/software/make/manual/html_node/Running.html">make</a> -s run</b>
> HashMap(over -> 1, quick -> 1, belong -> 1, lazy -> 1, best -> 1, man's -> 1, brown -> 1, fox -> 2, fell -> 1, tried -> 1, same -> 1, friend -> 1, family -> 1, dog -> 4, jump -> 1)
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
<td><code>_system.terminate()</code></td>
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

***

*[mics](https://lampwww.epfl.ch/~michelou/)/February 2022* [**&#9650;**](#top)
<span id="bottom">&nbsp;</span>

<!-- link refs -->

[akka]: https://akka.io/
[apache_maven_cli]: https://maven.apache.org/ref/current/maven-embedder/cli.html
[book_gupta]: https://www.packtpub.com/product/akka-essentials/9781849518284
[sbt_cli]: https://www.scala-sbt.org/1.x/docs/Command-Line-Reference.html
[make_cli]: https://ftp.gnu.org/old-gnu/Manuals/make-3.79.1/html_node/make_86.html
[scala]: https://www.scala-lang.org/
