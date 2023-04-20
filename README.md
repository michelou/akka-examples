# <span id="top">Playing with Akka on Windows</span>

<table style="font-family:Helvetica,Arial;line-height:1.6;">
  <tr>
  <td style="border:0;padding:0 10px 0 0;min-width:25%;"><a href="https://akka.io/" rel="external"><img src="docs/images/akka.svg" width="100" alt="Akka project"/></a></td>
  <td style="border:0;padding:0;vertical-align:text-top;">This repository gathers <a href="https://akka.io/" rel="external" title="Akka">Akka</a> code examples coming from various websites and books.<br/>
  It also includes several build scripts (<a href="https://ant.apache.org/manual/using.html" rel="external">Ant files</a>, <a href="https://en.wikibooks.org/wiki/Windows_Batch_Scripting" rel="external">batch files</a>, <a href="https://makefiletutorial.com/" rel="external">Make scripts</a>) for experimenting with <a href="https://akka.io/" rel="external">Akka</a> on a Windows machine.
  </td>
  </tr>
</table>

[Ada][ada_examples], [C++][cpp_examples], [Dart][dart_examples], [Deno][deno_examples], [Flix][flix_examples], [Golang][golang_examples], [GraalVM][graalvm_examples], [Haskell][haskell_examples], [Kafka][kafka_examples], [Kotlin][kotlin_examples], [LLVM][llvm_examples], [Node.js][nodejs_examples], [Rust][rust_examples], [Scala 3][scala3_examples], [Spark][spark_examples], [Spring][spring_examples], [TruffleSqueak][trufflesqueak_examples] and [WiX Toolset][wix_examples] are other topics we are continuously investigating.

## <span id="proj_deps">Project dependencies</span>

This project depends on the following external software for the **Microsoft Windows** platform:

- [Git 2.40][git_downloads] ([*release notes*][git_relnotes])
- [Scala 2.13][scala_releases] (requires Java 8+) ([*release notes*][scala_relnotes])
- [Temurin OpenJDK 11 LTS][temurin_openjdk11] ([*release notes*][temurin_openjdk11_relnotes], [*bug fixes*][temurin_openjdk11_bugfixes])

> **&#9755;** ***Maven packages***<br/>
> We present the Maven package dependencies in document [`PACKAGES.md`](./PACKAGES.md).

Optionally one may also install the following software:

- [Apache Ant 1.10][apache_ant] (requires Java 8+) ([*release notes*][apache_ant_relnotes])
- [Apache Maven 3.9][apache_maven] ([requires Java 8+][apache_maven_history])  ([*release notes*][apache_maven_relnotes])
- [GNU Make 3.81][gmake_install]
- [Gradle 8.1][gradle_install] <sup id="anchor_01">[1](#footnote_01)</sup> ([requires Java 8+][gradle_compatibility]) ([*release notes*][gradle_relnotes])
- [grpcurl 1.8][grpcurl_downloads]  ([*release notes*][grpcurl_relnotes])

<!--
1.10.0  -> https://archive.apache.org/dist/ant/RELEASE-NOTES-1.10.0.html
1.10.1  -> https://archive.apache.org/dist/ant/RELEASE-NOTES-1.10.1.html
1.10.2  -> https://archive.apache.org/dist/ant/RELEASE-NOTES-1.10.2.html
1.10.3  -> https://archive.apache.org/dist/ant/RELEASE-NOTES-1.10.3.html
1.10.13 -> https://github.com/apache/ant/blob/master/WHATSNEW
-->

> **:mag_right:** [Git for Windows][git_downloads] provides a Bash emulation used to run [**`git`**][git_cli] from the command line (as well as over 250 Unix commands like [**`awk`**][man1_awk], [**`diff`**][man1_diff], [**`file`**][man1_file], [**`grep`**][man1_grep], [**`more`**][man1_more], [**`mv`**][man1_mv], [**`rmdir`**][man1_rmdir], [**`sed`**][man1_sed] and [**`wc`**][man1_wc]).

For instance our development environment looks as follows (*April 2023*) <sup id="anchor_02">[2](#footnote_02)</sup>:

<pre style="font-size:80%;">
C:\opt\apache-ant-1.10.13\      <i>( 43 MB)</i>
C:\opt\apache-maven-3.9.1\      <i>( 10 MB)</i>
C:\opt\Git-2.40.0\              <i>(314 MB)</i>
C:\opt\gradle-8.1\              <i>(131 MB)</i>
C:\opt\grpcurl-1.8.7\           <i>( 22 MB)</i>
C:\opt\jdk-temurin-11.0.18_10\  <i>(300 MB)</i>
C:\opt\make-3.81\               <i>(  2 MB)</i>
C:\opt\scala-2.13.10\           <i>( 24 MB)</i>
</pre>

> **&#9755;** ***Installation policy***<br/>
> When possible we install software from a [Zip archive][zip_archive] rather than via a Windows installer. In our case we defined **`C:\opt\`** as the installation directory for optional software tools (*in reference to* the [`/opt/`][linux_opt] directory on Unix).

## <span id="structure">Directory structure</span> [**&#x25B4;**](#top)

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
<a href="PACKAGES.md">PACKAGES.md</a>
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
- directory [**`docs\`**](docs/) contains [Akka] related papers/articles.
- directory **`effective-akka\`** contains [Akka] code examples from [Allen's book][book_allen].
- directory [**`examples\`**](examples/) contains [Akka] examples grabbed from various websites (see file [**`examples\README.md`**](examples/README.md)).
- file [**`PACKAGES.md`**](PACKAGES.md) presents the [Maven][maven_repository] packages our projects depend on.
- file [**`README.md`**](README.md) is the [Markdown][github_markdown] document for this page.
- file [**`RESOURCES.md`**](RESOURCES.md) is the [Markdown][github_markdown] document presenting external resources.
- file [**`setenv.bat`**](setenv.bat) is the batch command for setting up our environment.

<!--=======================================================================-->

## <span id="commands">Batch commands</span>

### **`setenv.bat`** <sup id="anchor_03">[3](#footnote_03)</sup>

Command [**`setenv.bat`**](setenv.bat) is executed once to setup our development environment; it makes external tools such as [**`ant.bat`**][apache_ant_cli] and [**`git.exe`**][git_cli] directly available from the command prompt.

<pre style="font-size:80%;">
<b>&gt; <a href="setenv.bat">setenv</a></b>
Tool versions:
   javac 11.0.18, scalac 2.13.11-20230306-195001-unknown,
   ant 1.10.13, gradle 8.1, mvn 3.9.1, sbt 1.8.2, grpcurl v1.8.7,
   make 3.81, git 2.40.0.windows.1, diff 3.9, bash 5.2.15(1)-release

<b>&gt; <a href="https://docs.microsoft.com/en-us/windows-server/administration/windows-commands/where_1" rel="external">where</a> ant git</b>
C:\opt\apache-ant-1.10.13\bin\ant
C:\opt\apache-ant-1.10.13\bin\ant.bat
C:\opt\apache-ant-1.10.13\bin\ant.cmd
C:\opt\Git-2.40.0\bin\git.exe
C:\opt\Git-2.40.0\mingw64\bin\git.exe
</pre>

<!--=================================================================================-->

## <span id="footnotes">Footnotes</span> [**&#x25B4;**](#top)

<span id="footnote_01">[1]</span> ***Gradle Support for Java*** [↩](#anchor_01)

<dl><dd>
<table>
<tr><th>Gradle version</th><th>Java version</th></tr>
<tr><td><a href="https://docs.gradle.org/8.0/release-notes.html" rel="external">8.0</a> <sup>(<b>1</b>)</sup></td><td>19</td></tr>
<tr><td><a href="https://docs.gradle.org/7.6/release-notes.html" rel="external">7.6</a></td><td>19</td></tr>
<tr><td><a href="https://docs.gradle.org/7.5/release-notes.html" rel="external">7.5</a></td><td>18</td></tr>
<tr><td><a href="https://docs.gradle.org/7.3/release-notes.html" rel="external">7.3</a></td><td>17</td></tr>
<tr><td><a href="https://docs.gradle.org/7.0/release-notes.html" rel="external">7.0</a></td><td>16</td></tr>
<tr><td><a href="https://docs.gradle.org/6.7/release-notes.html" rel="external">6.7</a></td><td>15</td></tr>
<tr><td><a href="https://docs.gradle.org/6.3/release-notes.html" rel="external">6.3</a></td><td>14</td></tr>
<tr><td><a href="https://docs.gradle.org/6.0/release-notes.html" rel="external">6.0</a></td><td>13</td></tr>
</table>
</dd></dl>
<span><sup>(<b>1</b>)</sup> Version 8.0.2 or newer is required for use with Scala 2.13 (see <a href="https://github.com/gradle/gradle/issues/23962">issue 23962</a>).<br/>&nbsp;</span>

<span id="footnote_02">[2]</span> ***Downloads*** [↩](#anchor_02)

<dl><dd>
In our case we downloaded the following installation files (see <a href="#proj_deps">section 1</a>):
</dd>
<dd>
<pre style="font-size:80%;">
<a href="https://ant.apache.org/bindownload.cgi" rel="external">apache-ant-1.10.13-bin.zip</a>                         <i>(  9 MB)</i>
<a href="https://maven.apache.org/download.cgi">apache-maven-3.9.1-bin.zip</a>                         <i>( 10 MB)</i>
<a href="https://gradle.org/install/">gradle-8.1-bin.zip</a>                                 <i>(118 MB)</i>
<a href="https://github.com/fullstorydev/grpcurl/releases">grpcurl_1.8.7_windows_x86_64.zip</a>                   <i>(  6 MB)</i>
<a href="https://sourceforge.net/projects/gnuwin32/files/make/3.81/">make-3.81-bin.zip</a>                                  <i>( 10 MB)</i>
<a href="https://adoptium.net/releases.html?variant=openjdk11&jvmVariant=hotspot" rel="external">OpenJDK11U-jdk_x64_windows_hotspot_11.0.18_10.zip</a>  <i>(188 MB)</i>
<a href="https://git-scm.com/download/win" rel="external">PortableGit-2.40.0-64-bit.7z.exe</a>                   <i>( 46 MB)</i>
<a href="https://www.scala-lang.org/files/archive/">scala-2.13.10.zip</a>                                  <i>( 21 MB)</i>
</pre>
</dd></dl>

<span id="footnote_03">[3]</span> **`setenv.bat` *usage*** [↩](#anchor_03)

<dl><dd>
<a href=./setenv.bat><code><b>setenv.bat</b></code></a> has specific environment variables set that enable us to use command-line developer tools more easily.
</dd>
<dd>It is similar to the setup scripts described on the page <a href="https://learn.microsoft.com/en-us/visualstudio/ide/reference/command-prompt-powershell" rel="external">"Visual Studio Developer Command Prompt and Developer PowerShell"</a> of the <a href="https://learn.microsoft.com/en-us/visualstudio/windows" rel="external">Visual Studio</a> online documentation.
</dd>
<dd>
For instance we can quickly check that the two scripts <code>Launch-VsDevShell.ps1</code> and <code>VsDevCmd.bat</code> are indeed available in our Visual Studio 2019 installation :
<pre style="font-size:80%;">
<b>&gt; <a href="https://learn.microsoft.com/en-us/windows-server/administration/windows-commands/where" rel="external">where</a> /r "C:\Program Files (x86)\Microsoft Visual Studio" *vsdev*</b>
C:\Program Files (x86)\Microsoft Visual Studio\2019\Community\Common7\Tools\Launch-VsDevShell.ps1
C:\Program Files (x86)\Microsoft Visual Studio\2019\Community\Common7\Tools\VsDevCmd.bat
C:\Program Files (x86)\Microsoft Visual Studio\2019\Community\Common7\Tools\vsdevcmd\core\vsdevcmd_end.bat
C:\Program Files (x86)\Microsoft Visual Studio\2019\Community\Common7\Tools\vsdevcmd\core\vsdevcmd_start.bat
</pre>
</dd>
<dd>
Concretely, <code>setenv.bat</code> in our GitHub projects which depend on Visual Studio (e.g. <a href="https://github.com/michelou/cpp-examples"><code>michelou/cpp-examples</code></a>) do invoke <code>VsDevCmd.bat</code> (resp. <code>vcvarall.bat</code> for older Visual Studio versions) to setup the Visual Studio tools on the command prompt. 
</dd></dl>

***

*[mics](https://lampwww.epfl.ch/~michelou/)/April 2023* [**&#9650;**](#top)
<span id="bottom">&nbsp;</span>

<!-- link refs -->

[ada_examples]: https://github.com/michelou/ada-examples
[akka]: https://akka.io/
[blog_alexandreesl]: https://alexandreesl.com/2019/01/02/akka-streams-developing-robust-applications-using-scala/
[apache_ant]: https://ant.apache.org/
[apache_ant_cli]: https://ant.apache.org/manual/running.html
[apache_ant_relnotes]: https://github.com/apache/ant/blob/master/WHATSNEW
[apache_maven]: https://maven.apache.org/download.cgi
[apache_maven_cli]: https://maven.apache.org/ref/current/maven-embedder/cli.html
[apache_maven_history]: https://maven.apache.org/docs/history.html
[apache_maven_relnotes]: https://maven.apache.org/docs/3.9.1/release-notes.html
[book_allen]: https://www.oreilly.com/library/view/effective-akka/9781449360061/
[book_gupta]: https://www.packtpub.com/product/akka-essentials/9781849518284
[book_ortiz]: https://www.packtpub.com/product/akka-cookbook/9781785288180
[book_wyatt]: https://www.artima.com/shop/akka_concurrency
[cpp_examples]: https://github.com/michelou/cpp-examples
[dart_examples]: https://github.com/michelou/dart-examples
[deno_examples]: https://github.com/michelou/deno-examples
[flix_examples]: https://github.com/michelou/flix-examples
[git_bash]: https://www.atlassian.com/git/tutorials/git-bash
[git_cli]: https://git-scm.com/docs/git
[git_downloads]: https://git-scm.com/download/win
[git_relnotes]: https://raw.githubusercontent.com/git/git/master/Documentation/RelNotes/2.34.0.txt
[github_markdown]: https://github.github.com/gfm/
[gmake_install]: https://sourceforge.net/projects/gnuwin32/files/make/3.81/
[gradle_cli]: https://docs.gradle.org/current/userguide/command_line_interface.html
[gradle_compatibility]: https://docs.gradle.org/current/release-notes.html#upgrade-instructions
[gradle_install]: https://gradle.org/install/
[gradle_relnotes]: https://docs.gradle.org/8.1/release-notes.html
[golang_examples]: https://github.com/michelou/golang-examples
[graalvm_examples]: https://github.com/michelou/graalvm-examples
[grpcurl_downloads]: https://github.com/fullstorydev/grpcurl/releases
[grpcurl_relnotes]: https://github.com/fullstorydev/grpcurl/releases/tag/v1.8.7
[haskell_examples]: https://github.com/michelou/haskell-examples
[kafka_examples]: https://github.com/michelou/kafka-examples
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
[maven_repository]: https://mvnrepository.com/
[nodejs_examples]: https://github.com/michelou/nodejs-examples
[rust_examples]: https://github.com/michelou/rust-examples
[scala_releases]: https://www.scala-lang.org/files/archive/
[scala_relnotes]: https://github.com/scala/scala/releases/tag/v2.13.10
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
11.0.12 -> https://mail.openjdk.org/pipermail/jdk-updates-dev/2021-July/006954.html
11.0.13 -> https://mail.openjdk.org/pipermail/jdk-updates-dev/2021-October/009368.html
11.0.14 -> https://mail.openjdk.org/pipermail/jdk-updates-dev/2022-January/011643.html
11.0.15 -> https://mail.openjdk.org/pipermail/jdk-updates-dev/2022-April/014104.html
11.0.16 -> https://mail.openjdk.org/pipermail/jdk-updates-dev/2022-July/016017.html
11.0.17 -> https://mail.openjdk.org/pipermail/jdk-updates-dev/2022-October/018119.html
-->
[temurin_openjdk11_relnotes]: https://mail.openjdk.java.net/pipermail/jdk-updates-dev/2022-April/014104.html
[temurin_openjdk11]: https://adoptium.net/releases.html?variant=openjdk11&jvmVariant=hotspot
[trufflesqueak_examples]: https://github.com/michelou/trufflesqueak-examples
[vs2019_downloads]: https://visualstudio.microsoft.com/en/downloads/
[vs2019_relnotes]: https://docs.microsoft.com/en-us/visualstudio/releases/2019/release-notes
[wix_examples]: https://github.com/michelou/wix-examples
[zip_archive]: https://www.howtogeek.com/178146/htg-explains-everything-you-need-to-know-about-zipped-files/
