# <span id="top">Playing with Akka on Windows</span>

<table style="font-family:Helvetica,Arial;font-size:14px;line-height:1.6;">
  <tr>
  <td style="border:0;padding:0 10px 0 0;min-width:25%;"><a href="https://akka.io/" rel="external"><img src="docs/images/akka.svg" width="100" alt="Akka project"/></a></td>
  <td style="border:0;padding:0;vertical-align:text-top;">This repository gathers <a href="https://akka.io/" rel="external" title="Akka">Akka</a> code examples coming from various websites and books.<br/>
  It also includes several <a href="https://en.wikibooks.org/wiki/Windows_Batch_Scripting" rel="external">batch files</a> for experimenting with <a href="https://akka.io/" rel="external">Akka</a> on a Windows machine.
  </td>
  </tr>
</table>

[Ada][ada_examples], [C++][cpp_examples], [Deno][deno_examples], [Flix][flix_examples], [Golang][golang_examples], [GraalVM][graalvm_examples], [Haskell][haskell_examples], [Kotlin][kotlin_examples], [LLVM][llvm_examples], [Node.js][nodejs_examples], [Rust][rust_examples], [Scala 3][scala3_examples], [Spark][spark_examples], [Spring][spring_examples], [TruffleSqueak][trufflesqueak_examples] and [WiX][wix_examples] are other topics we are continuously investigating.

## <span id="proj_deps">Project dependencies</span>

This project depends on the following external software for the **Microsoft Windows** platform:

- [Git 2.37][git_downloads] ([*release notes*][git_relnotes])
- [Scala 2.13][scala_releases] (requires Java 8) ([*release notes*][scala_relnotes])
- [Temurin OpenJDK 11 LTS][temurin_openjdk11] ([*release notes*][temurin_openjdk11_relnotes], [*bug fixes*][temurin_openjdk11_bugfixes])

Optionally one may also install the following software:

- [Apache Ant 1.10][apache_ant] (requires Java 8) ([*release notes*][apache_ant_relnotes])
- [Apache Maven 3.8][apache_maven] ([requires Java 7][apache_maven_history])  ([*release notes*][apache_maven_relnotes])
- [Gradle 7.5][gradle_install] ([requires Java 8 or newer][gradle_compatibility]) ([*release notes*][gradle_relnotes])
- [grpcurl 1.8][grpcurl_downloads]  ([*release notes*][grpcurl_relnotes])

> **:mag_right:** [Git for Windows][git_downloads] provides a Bash emulation used to run [**`git`**][git_cli] from the command line (as well as over 250 Unix commands like [**`awk`**][man1_awk], [**`diff`**][man1_diff], [**`file`**][man1_file], [**`grep`**][man1_grep], [**`more`**][man1_more], [**`mv`**][man1_mv], [**`rmdir`**][man1_rmdir], [**`sed`**][man1_sed] and [**`wc`**][man1_wc]).

For instance our development environment looks as follows (*October 2022*) <sup id="anchor_01">[1](#footnote_01)</sup>:

<pre style="font-size:80%;">
C:\opt\apache-ant-1.10.12\      <i>( 40 MB)</i>
C:\opt\apache-maven-3.8.6\      <i>( 10 MB)</i>
C:\opt\Git-2.37.3\              <i>(289 MB)</i>
C:\opt\gradle-7.5.1\            <i>(121 MB)</i>
C:\opt\grpcurl-1.8.7\           <i>( 22 MB)</i>
C:\opt\jdk-temurin-11.0.16_8\   <i>(300 MB)</i>
C:\opt\scala-2.13.8\            <i>( 24 MB)</i>
</pre>

> **&#9755;** ***Installation policy***<br/>
> When possible we install software from a [Zip archive][zip_archive] rather than via a Windows installer. In our case we defined **`C:\opt\`** as the installation directory for optional software tools (*in reference to* the [`/opt/`][linux_opt] directory on Unix).

## <span id="structure">Directory structure</span>[**&#x25B4;**](#top)

This project is organized as follows:

<pre style="font-size:80%;">
alexandreesl-examples\{<a href="alexandreesl-examples/README.md">README.md</a>, <a href="alexandreesl-examples/akka-stream-lab/">akka-stream-lab</a>}
akka\     <i>(Git submodule)</i>
akka-concurrency\{<a href="akka-concurrency/README.md">README.md</a>, <a href="akka-concurrency/Chapter05/Avionics/">Avionics</a>, <a href="akka-concurrency/Chapter05/BadSharespearean/">BadSharespearean</a>, etc.}
akka-cookbook\{<a href="akka-cookbook/README.md">README.md</a>, <a href="akka-cookbook/Chapter01/HelloAkka/">HelloAkka</a>, <a href="akka-cookbook/Chapter01/PriorityMailbox/">PriorityMailbox</a>, etc.}
akka-essentials\{<a href="akka-essentials/README.md">README.md</a>, <a href="akka-essentials/Chapter02/FirstAkkaApplication/">FirstAkkaApplication</a>, etc.}
docs\
effective-akka\{<a href="effective-akka/README.md">README.md</a>, <a href="effective-akka/ExtraPattern/">ExtraPattern</a>, etc.>}
examples\{<a href="examples/README.md">README.md</a>, <a href="examples/akka-quickstart-java/">akka-quickstart-java</a>, <a href="examples/akka-quickstart-kotlin/">akka-quickstart-kotlin</a>, ..}
README.md
<a href="RESOURCES.md">RESOURCES.md</a>
<a href="setenv.bat">setenv.bat</a>
</pre>

where

- directory **`alexandreesl-examples\`** contains [Akka] code examples from [Alexandre's blog post][blog_alexandreesl].
- directory **`akka\`** contains our fork of the [akka/akka](https://github.com/akka/akka) repository as a [Github submodule](.gitmodules).
- directory **`akka-concurrency\`** contains [Akka] code examples from [Wyatt's book][book_wyatt].
- directory **`akka-cookbook\`** contains [Akka] code examples from [Ortiz's book][book_ortiz].
- directory **`akka-essentials\`** contains [Akka] code examples from [Gupta's book][book_gupta].
- directory [**`docs\`**](docs/) contains [Scala 3][scala3_home] related papers/articles (see file [**`docs\README.md`**](docs/README.md)).
- directory **`effective-akka\`** contains [Akka] code examples from [Allen's book][book_allen].
- directory [**`examples\`**](examples/) contains [Scala 3][scala3_home] examples grabbed from various websites (see file [**`examples\README.md`**](examples/README.md)).
- file [**`README.md`**](README.md) is the [Markdown][github_markdown] document for this page.
- file [**`RESOURCES.md`**](RESOURCES.md) is the [Markdown][github_markdown] document presenting external resources.
- file [**`setenv.bat`**](setenv.bat) is the batch command for setting up our environment.

## <span id="footnotes">Footnotes</span>

<span id="footnote_01">[1]</span> ***Downloads*** [↩](#anchor_01)

<dl><dd>
In our case we downloaded the following installation files (see <a href="#proj_deps">section 1</a>):
</dd>
<dd>
<pre style="font-size:80%;">
<a href="https://ant.apache.org/bindownload.cgi">apache-ant-1.10.12-bin.zip</a>                         <i>(  9 MB)</i>
<a href="https://maven.apache.org/download.cgi">apache-maven-3.8.6-bin.zip</a>                         <i>( 10 MB)</i>
<a href="https://gradle.org/install/">gradle-7.5.1-bin.zip</a>                               <i>(103 MB)</i>
<a href="https://github.com/fullstorydev/grpcurl/releases">grpcurl_1.8.7_windows_x86_64.zip</a>                   <i>(  6 MB)</i>
<a href="https://adoptium.net/releases.html?variant=openjdk11&jvmVariant=hotspot">OpenJDK11U-jdk_x64_windows_hotspot_11.0.16_8.zip</a>   <i>(188 MB)</i>
<a href="https://git-scm.com/download/win">PortableGit-2.37.3-64-bit.7z.exe</a>                   <i>( 41 MB)</i>
<a href="https://www.scala-lang.org/files/archive/">scala-2.13.8.zip</a>                                   <i>( 21 MB)</i>
</pre>
</dd></dl>

***

*[mics](https://lampwww.epfl.ch/~michelou/)/October 2022* [**&#9650;**](#top)
<span id="bottom">&nbsp;</span>

<!-- link refs -->

[ada_examples]: https://github.com/michelou/ada-examples
[akka]: https://akka.io/
[blog_alexandreesl]: https://alexandreesl.com/2019/01/02/akka-streams-developing-robust-applications-using-scala/
[apache_ant]: https://ant.apache.org/
[apache_ant_cli]: https://ant.apache.org/manual/running.html
[apache_ant_relnotes]: https://archive.apache.org/dist/ant/RELEASE-NOTES-1.10.12.html
[apache_maven]: https://maven.apache.org/download.cgi
[apache_maven_cli]: https://maven.apache.org/ref/current/maven-embedder/cli.html
[apache_maven_history]: https://maven.apache.org/docs/history.html
[apache_maven_relnotes]: https://maven.apache.org/docs/3.8.6/release-notes.html
[book_allen]: https://www.oreilly.com/library/view/effective-akka/9781449360061/
[book_gupta]: https://www.packtpub.com/product/akka-essentials/9781849518284
[book_ortiz]: https://www.packtpub.com/product/akka-cookbook/9781785288180
[book_wyatt]: https://www.artima.com/shop/akka_concurrency
[cpp_examples]: https://github.com/michelou/cpp-examples
[deno_examples]: https://github.com/michelou/deno-examples
[flix_examples]: https://github.com/michelou/flix-examples
[git_bash]: https://www.atlassian.com/git/tutorials/git-bash
[git_cli]: https://git-scm.com/docs/git
[git_downloads]: https://git-scm.com/download/win
[git_relnotes]: https://raw.githubusercontent.com/git/git/master/Documentation/RelNotes/2.37.3.txt
[github_markdown]: https://github.github.com/gfm/
[gradle_cli]: https://docs.gradle.org/current/userguide/command_line_interface.html
[gradle_compatibility]: https://docs.gradle.org/current/release-notes.html#upgrade-instructions
[gradle_install]: https://gradle.org/install/
[gradle_relnotes]: https://docs.gradle.org/6.7/release-notes.html
[golang_examples]: https://github.com/michelou/golang-examples
[graalvm_examples]: https://github.com/michelou/graalvm-examples
[grpcurl_downloads]: https://github.com/fullstorydev/grpcurl/releases
[grpcurl_relnotes]: https://github.com/fullstorydev/grpcurl/releases/tag/v1.8.7
[haskell_examples]: https://github.com/michelou/haskell-examples
[kotlin_examples]: https://github.com/michelou/kotlin-examples
[linux_opt]: https://tldp.org/LDP/Linux-Filesystem-Hierarchy/html/opt.html
[llvm_examples]: https://github.com/michelou/llvm-examples
[man1_awk]: https://www.linux.org/docs/man1/awk.html
[man1_diff]: https://www.linux.org/docs/man1/diff.html
[man1_file]: https://www.linux.org/docs/man1/file.html
[man1_grep]: https://www.linux.org/docs/man1/grep.html
[man1_more]: https://www.linux.org/docs/man1/more.html
[man1_mv]: https://www.linux.org/docs/man1/mv.html
[man1_rmdir]: https://www.linux.org/docs/man1/rmdir.html
[man1_sed]: https://www.linux.org/docs/man1/sed.html
[man1_wc]: https://www.linux.org/docs/man1/wc.html
[nodejs_examples]: https://github.com/michelou/nodejs-examples
[rust_examples]: https://github.com/michelou/rust-examples
[scala_releases]: https://www.scala-lang.org/files/archive/
[scala_relnotes]: https://github.com/scala/scala/releases/tag/v2.13.8
[scala3_examples]: https://github.com/michelou/dotty-examples
[scala3_home]: https://dotty.epfl.ch
[spark_examples]: https://github.com/michelou/spark-examples
[spring_examples]: https://github.com/michelou/spring-examples
<!--
11.0.14 -> https://www.oracle.com/java/technologies/javase/11-0-14-bugfixes.html
11.0.15 -> https://www.oracle.com/java/technologies/javase/11-0-15-bugfixes.html
11.0.16 -> https://www.oracle.com/java/technologies/javase/11-0-16-bugfixes.html
-->
[temurin_openjdk11_bugfixes]: https://www.oracle.com/java/technologies/javase/11-0-16-bugfixes.html
<!--
11.0.3  -> https://mail.openjdk.java.net/pipermail/jdk-updates-dev/2019-April/000951.html
11.0.4  -> https://mail.openjdk.java.net/pipermail/jdk-updates-dev/2019-July/001423.html
11.0.5  -> https://mail.openjdk.java.net/pipermail/jdk-updates-dev/2019-October/002025.html
11.0.6  -> https://mail.openjdk.java.net/pipermail/jdk-updates-dev/2020-January/002374.html
11.0.7  -> https://mail.openjdk.java.net/pipermail/jdk-updates-dev/2020-April/003019.html
11.0.8  -> https://mail.openjdk.java.net/pipermail/jdk-updates-dev/2020-July/003498.html
11.0.9  -> https://mail.openjdk.java.net/pipermail/jdk-updates-dev/2020-October/004007.html
11.0.10 -> https://mail.openjdk.java.net/pipermail/jdk-updates-dev/2021-January/004689.html
11.0.11 -> https://mail.openjdk.java.net/pipermail/jdk-updates-dev/2021-April/005860.html
11.0.13 -> https://mail.openjdk.java.net/pipermail/jdk-updates-dev/2021-October/009368.html
11.0.14 -> https://mail.openjdk.java.net/pipermail/jdk-updates-dev/2022-January/011643.html
11.0.15 -> https://mail.openjdk.java.net/pipermail/jdk-updates-dev/2022-April/014104.html
-->
[temurin_openjdk11_relnotes]: https://mail.openjdk.java.net/pipermail/jdk-updates-dev/2022-April/014104.html
[temurin_openjdk11]: https://adoptium.net/releases.html?variant=openjdk11&jvmVariant=hotspot
[trufflesqueak_examples]: https://github.com/michelou/trufflesqueak-examples
[vs2019_downloads]: https://visualstudio.microsoft.com/en/downloads/
[vs2019_relnotes]: https://docs.microsoft.com/en-us/visualstudio/releases/2019/release-notes
[wix_examples]: https://github.com/michelou/wix-examples
[zip_archive]: https://www.howtogeek.com/178146/htg-explains-everything-you-need-to-know-about-zipped-files/
