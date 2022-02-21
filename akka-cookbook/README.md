# <span id="top">Book <i>Akka Cookbook</i></span> <span style="size:30%;"><a href="../README.md">⬆</a></span>

<table style="font-family:Helvetica,Arial;font-size:14px;line-height:1.6;">
  <tr>
  <td style="border:0;padding:0 10px 0 0;min-width:120px;"><a href="https://akka.io/"><img src="../docs/images/akka.svg" width="120" alt="Akka project"/></a></td>
  <td style="border:0;padding:0;vertical-align:text-top;">Directory <a href="."><strong><code>akka-cookbook\</code></strong></a> contains <a href="https://akka.io/" alt="Akka">Akka</a> examples presented in Ortiz's book <a href="https://www.packtpub.com/product/akka-cookbook/9781785288180"><i>Akka Cookbook</i></a> (<a href="https://www.packtpub.com/" rel="external" title="Packt Publishing">Packt</a>, 2017).<br/>It also includes different build scripts for experimenting with <a href="https://akka.io/" alt="Akka">Akka</a> on a Windows machine.</td>
  </tr>
</table>

Code examples can be built/run with the following tools:

| Build&nbsp;tool | Configuration file | Parent&nbsp;file | Environment(s) |
|:----------------|:-------------------|:----------------|:---------------|
| [**`ant.bat`**][apache_ant_cli] | [`build.xml`](./Chapter01/HelloAkka/build.xml) | [`build.xml`](./build.xml) | Any <sup><b>a)</b></sup> |
| [**`make.exe`**][make_cli] | [`Makefile`](./Chapter01/HelloAkka/Makefile) | [`Makefile.inc`](./Makefile.inc) | Any |
| [**`mvn.cmd`**][apache_maven_cli] | [`pom.xml`](./Chapter01/HelloAkka/pom.xml) | [`pom.xml`](./pom.xml) | Any |
| [**`sbt.bat`**][sbt_cli] | [`build.sbt`](./Chapter01/HelloAkka/build.sbt) | &nbsp;        | Any |
| [**`build.bat`**](./Chapter01/HelloAkka/build.bat) | *none*             |  &nbsp;        | Windows only |
<div style="font-size:80%;">
<sup><b>a)</b></sup>: Here "Any" means "tested on Windows, Cygwin, MSYS2 and UNIX".
</div>

## <span id="helloakka">`HelloAkka`</span>

Batch file [`build.bat`](./Chapter01/HelloAkka/build.bat) matches what a user would run from the command prompt (use option **`-debug`** to see the execution details):

<pre style="font-size:80%;">
<b>&gt; <a href="Chapter01/HelloAkka/build.bat">build</a> -verbose run</b>
Compile 1 Scala source file to directory "target\classes"
[HelloAkka-akka.actor.default-dispatcher-4] INFO akka.event.slf4j.Slf4jLogger - Slf4jLogger started
akka://HelloAkka
>>> Press ENTER to exit <<<
</pre>

> **:mag_right:** Commands [**`ant.bat`**][apache_ant_cli] ([`build.xml`](./Chapter01/HelloAkka/build.xml)) [**`make`**][make_cli] ([`Makefile`](./Chapter01/HelloAkka/Makefile)), [**`mvn`**][apache_maven_cli] ([`pom.xml`](./Chapter01/HelloAkka/pom.xml)) and [**`sbt`**][sbt_cli] ([`build.sbt`](./Chapter01/HelloAkka/build.sbt)) produce the same output; for instance :
> <pre style="font-size:80%;">
> <b>&gt; <a href="https://www.gnu.org/software/make/manual/html_node/Running.html">make</a> -s run</b>
> akka://HelloAkka
> >>> Press ENTER to exit <<<
> </pre>

## <span id="behaviourandstate">`BehaviourAndState`</span>

[`build.sbt`](./Chapter01/BehaviourAndState/build.sbt) and [`build.bat`](./Chapter01/BehaviourAndState/build.bat).


***

*[mics](https://lampwww.epfl.ch/~michelou/)/February 2022* [**&#9650;**](#top)
<span id="bottom">&nbsp;</span>

<!-- link refs -->

[apache_ant_cli]: https://ant.apache.org/manual/running.html
[apache_maven_cli]: https://maven.apache.org/ref/current/maven-embedder/cli.html
[sbt_cli]: https://www.scala-sbt.org/1.x/docs/Command-Line-Reference.html
[make_cli]: https://ftp.gnu.org/old-gnu/Manuals/make-3.79.1/html_node/make_86.html
