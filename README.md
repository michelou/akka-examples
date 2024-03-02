# <span id="top">Playing with Akka on Windows</span>

<table style="font-family:Helvetica,Arial;line-height:1.6;">
  <tr>
  <td style="border:0;padding:0 10px 0 0;min-width:25%;"><a href="https://akka.io/" rel="external"><img src="docs/images/akka.svg" width="100" alt="Akka project"/></a></td>
  <td style="border:0;padding:0;vertical-align:text-top;">This repository gathers <a href="https://akka.io/" rel="external" title="Akka">Akka</a> code examples coming from various websites and books.<br/>
  It also includes several build scripts (<a href="https://ant.apache.org/manual/using.html" rel="external">Ant files</a>, <a href="https://tldp.org/LDP/Bash-Beginners-Guide/html/sect_02_01.html" rel="external">bash scripts</a>, <a href="https://en.wikibooks.org/wiki/Windows_Batch_Scripting" rel="external">batch files</a>, <a href="https://makefiletutorial.com/" rel="external">Make scripts</a>) for experimenting with <a href="https://akka.io/" rel="external">Akka</a> on a Windows machine.
  </td>
  </tr>
</table>

[Ada][ada_examples], [C++][cpp_examples], [Dart][dart_examples], [Deno][deno_examples], [Docker][docker_examples], [Flix][flix_examples], [Golang][golang_examples], [GraalVM][graalvm_examples], [Haskell][haskell_examples], [Kafka][kafka_examples], [Kotlin][kotlin_examples], [LLVM][llvm_examples], [Modula-2][m2_examples], [Node.js][nodejs_examples], [Rust][rust_examples], [Scala 3][scala3_examples], [Spark][spark_examples], [Spring][spring_examples], [TruffleSqueak][trufflesqueak_examples] and [WiX Toolset][wix_examples] are other topics we are continuously investigating.

> **&#9755;** [Apache Pekko][apache_pekko] is a fork of Akka 2.6.x, prior to the Akka project’s adoption of the [Business Source License][akka_license].

## <span id="proj_deps">Project dependencies</span>

This project depends on the following external software for the **Microsoft Windows** platform:

- [Git 2.44][git_downloads] ([*release notes*][git_relnotes])
- [Scala 2.13][scala_releases] (requires Java 8+) ([*release notes*][scala_relnotes])
- [Temurin OpenJDK 17 LTS][temurin_openjdk17] ([*release notes*][temurin_openjdk17_relnotes], [*bug fixes*][temurin_openjdk17_bugfixes])
<!--
- [Temurin OpenJDK 11 LTS][temurin_openjdk11] ([*release notes*][temurin_openjdk11_relnotes], [*bug fixes*][temurin_openjdk11_bugfixes])
-->

> **&#9755;** ***Maven packages***<br/>
> We present the [Maven][maven_repository] package dependencies in document [`PACKAGES.md`](./PACKAGES.md).

Optionally one may also install the following software:

- [Apache Ant 1.10][apache_ant] (requires Java 8+) ([*release notes*][apache_ant_relnotes])
- [Apache Maven 3.9][apache_maven] ([requires Java 8+][apache_maven_history])  ([*release notes*][apache_maven_relnotes])
- [GNU Make 3.81][gmake_install]
- [Gradle 8.6][gradle_install] <sup id="anchor_01">[1](#footnote_01)</sup> ([requires Java 8+][gradle_compatibility]) ([*release notes*][gradle_relnotes])
- [grpcurl 1.8][grpcurl_downloads]  ([*release notes*][grpcurl_relnotes])
- [sbt 1.9][sbt_downloads] (requires Java 8+) ([*release notes*][sbt_relnotes])
- [Temurin OpenJDK 21 LTS][temurin_openjdk21] <sup id="anchor_01">[1](#footnote_01)</sup> ([*release notes*][temurin_openjdk21_relnotes], [Java 21 API][oracle_openjdk21_api])
- [Visual Studio Code 1.86][vscode_downloads] ([*release notes*][vscode_relnotes])

<!--
1.10.0  -> https://archive.apache.org/dist/ant/RELEASE-NOTES-1.10.0.html
1.10.1  -> https://archive.apache.org/dist/ant/RELEASE-NOTES-1.10.1.html
1.10.2  -> https://archive.apache.org/dist/ant/RELEASE-NOTES-1.10.2.html
1.10.3  -> https://archive.apache.org/dist/ant/RELEASE-NOTES-1.10.3.html
1.10.11 -> https://archive.apache.org/dist/ant/RELEASE-NOTES-1.10.11.html
1.10.14 -> https://www.mail-archive.com/announce@apache.org/msg07860.html
1.10.14 -> https://archive.apache.org/dist/ant/RELEASE-NOTES-1.10.14.html
-->

> **:mag_right:** [Git for Windows][git_downloads] provides a Bash emulation used to run [**`git`**][git_cli] from the command line (as well as over 250 Unix commands like [**`awk`**][man1_awk], [**`diff`**][man1_diff], [**`file`**][man1_file], [**`grep`**][man1_grep], [**`more`**][man1_more], [**`mv`**][man1_mv], [**`rmdir`**][man1_rmdir], [**`sed`**][man1_sed] and [**`wc`**][man1_wc]).

For instance our development environment looks as follows (*March 2024*) <sup id="anchor_02">[2](#footnote_02)</sup>:

<pre style="font-size:80%;">
C:\opt\apache-ant\              <i>( 43 MB)</i>
C:\opt\apache-maven\            <i>( 10 MB)</i>
C:\opt\Git\                     <i>(367 MB)</i>
C:\opt\gradle\                  <i>(135 MB)</i>
C:\opt\grpcurl\                 <i>( 22 MB)</i>
C:\opt\jdk-temurin-17.0.10_7\   <i>(301 MB)</i>
C:\opt\jdk-temurin-21.0.2_13\   <i>(326 MB)</i>
C:\opt\make-3.81\               <i>(  2 MB)</i>
C:\opt\sbt\                     <i>(110 MB)</i>
C:\opt\scala-2.13.13\           <i>( 24 MB)</i>
C:\opt\VSCode\                  <i>(341 MB)</i>
</pre>

> **&#9755;** ***Installation policy***<br/>
> When possible we install software from a [Zip archive][zip_archive] rather than via a Windows installer. In our case we defined **`C:\opt\`** as the installation directory for optional software tools (*in reference to* the [**`/opt/`**][linux_opt] directory on Unix).

## <span id="structure">Directory structure</span> [**&#x25B4;**](#top)

This project is organized as follows:

<pre style="font-size:80%;">
<a href="./bin">bin\
alexandreesl-examples\{<a href="alexandreesl-examples/README.md">README.md</a>, <a href="alexandreesl-examples/akka-stream-lab/">akka-stream-lab</a>}
<a href="https://github.com/michelou/akka">akka</a>\     <i>(Git submodule)</i>
akka-concurrency\{<a href="akka-concurrency/README.md">README.md</a>, <a href="akka-concurrency/Chapter05/Avionics/">Avionics</a>, <a href="akka-concurrency/Chapter05/BadSharespearean/">BadSharespearean</a>, etc.}
akka-cookbook\{<a href="akka-cookbook/README.md">README.md</a>, <a href="akka-cookbook/Chapter01/HelloAkka/">HelloAkka</a>, <a href="akka-cookbook/Chapter01/PriorityMailbox/">PriorityMailbox</a>, etc.}
akka-essentials\{<a href="akka-essentials/README.md">README.md</a>, <a href="akka-essentials/Chapter02/FirstAkkaApplication/">FirstAkkaApplication</a>, etc.}
<a href="./docs">docs\</a>
effective-akka\{<a href="effective-akka/README.md">README.md</a>, <a href="effective-akka/ExtraPattern/">ExtraPattern</a>, etc.>}
examples\{<a href="examples/README.md">README.md</a>, <a href="examples/akka-quickstart-java/">akka-quickstart-java</a>, <a href="examples/akka-quickstart-kotlin/">akka-quickstart-kotlin</a>, ..}
pekko-examples\{<a href="pekko-examples/README.md">README.md</a>, <a href="pekko-examples/HelloWorld/">HelloWorld</a>, etc.>}
<a href="PACKAGES.md">PACKAGES.md</a>
README.md
<a href="RESOURCES.md">RESOURCES.md</a>
<a href="setenv.bat">setenv.bat</a>
</pre>

where

- directory [**`bin\`**](./bin) provides several utility [batch files][windows_batch_file]
- directory **`alexandreesl-examples\`** contains [Akka] code examples from [Alexandre's blog post][blog_alexandreesl].
- directory **`akka\`** contains our fork of the [akka/akka](https://github.com/akka/akka) repository as a [Github submodule](.gitmodules).
- directory **`akka-concurrency\`** contains [Akka] code examples from [Wyatt's book][book_wyatt].
- directory **`akka-cookbook\`** contains [Akka] code examples from [Ortiz's book][book_ortiz].
- directory **`akka-essentials\`** contains [Akka] code examples from [Gupta's book][book_gupta].
- directory [**`docs\`**](docs/) contains [Akka] related papers/articles.
- directory **`effective-akka\`** contains [Akka] code examples from [Allen's book][book_allen].
- directory [**`examples\`**](examples/) contains [Akka] examples grabbed from various websites (see file [**`examples\README.md`**](examples/README.md)).
- directory **`pekko-examples\`** contains [Akka] code examples from the [Pekko][apache_pekko] project.
- file [**`PACKAGES.md`**](PACKAGES.md) presents the [Maven][maven_repository] packages our projects depend on.
- file [**`README.md`**](README.md) is the [Markdown][github_markdown] document for this page.
- file [**`RESOURCES.md`**](RESOURCES.md) is the [Markdown][github_markdown] document presenting external resources.
- file [**`setenv.bat`**](setenv.bat) is the batch command for setting up our environment.

<!--=======================================================================-->

## <span id="commands">Batch commands</span>

### **`setenv.bat`** <sup id="anchor_03">[3](#footnote_03)</sup>

We execute command [**`setenv.bat`**](setenv.bat) once to setup our development environment; it makes external tools such as [**`ant.bat`**][apache_ant_cli], [**`git.exe`**][git_cli] or [**`sbt.bat`**][sbt_cli] directly available from the command prompt.

<pre style="font-size:80%;">
<b>&gt; <a href="setenv.bat">setenv</a></b>
Tool versions:
   javac 17.0.10 scalac 2.13.13, sbt 1.9.9,
   ant 1.10.14, gradle 8.6, mvn 3.9.6, grpcurl v1.8.9,
   make 3.81, git 2.44.0.windows.1, diff 3.10, bash 5.2.26(1)-release

<b>&gt; <a href="https://docs.microsoft.com/en-us/windows-server/administration/windows-commands/where_1" rel="external">where</a> ant git sbt</b>
C:\opt\apache-ant\bin\ant
C:\opt\apache-ant\bin\ant.bat
C:\opt\apache-ant\bin\ant.cmd
C:\opt\Git\bin\git.exe
C:\opt\Git\mingw64\bin\git.exe
C:\opt\sbt\bin\sbt
C:\opt\sbt\bin\sbt.bat
</pre>

### **`searchjars.bat`** 

Command [**`searchjars.bat`**](./bin/searchjars.bat) helps us to get the list of all JAR files containing a specific class or method name among the locally available libraries. In the following example we are searching for occurrences of class **`NotUsed`** :

<pre style="font-size:80%;">
<b>&gt; <a href="./bin/searchjars.bat">searchjars.bat</a> NotUsed</b>
Warning: Search all directories (no option specified)
Searching for class "NotUsed" in files "C:\opt\scala3-3.3.2\lib\*.jar"
Searching for class "NotUsed" in files "C:\opt\scala-2.13.13\lib\*.jar"
Searching for class "NotUsed" in files "C:\opt\jdk-temurin-17.0.10_7\lib\*.jar"
Searching for class "NotUsed" in files "%USERPROFILE%\.ivy2\cache\*.jar"
  akka-actor_2.13-2.8.5.jar:akka/NotUsed$.class
  akka-actor_2.13-2.8.5.jar:akka/NotUsed.class
Searching for class "NotUsed" in files "%USERPROFILE%\.m2\repository\*.jar"
  akka-actor_2.13-2.8.5.jar:akka/NotUsed$.class
  akka-actor_2.13-2.8.5.jar:akka/NotUsed.class
  akka-stream_2.13-2.8.5.jar:akka/stream/impl/PushNotUsed$.class
  akka-stream_2.13-2.8.5.jar:akka/stream/impl/PushNotUsed.class
  pekko-actor_2.13-1.0.1.jar:org/apache/pekko/NotUsed$.class
  pekko-actor_2.13-1.0.1.jar:org/apache/pekko/NotUsed.class
</pre>

<!--=================================================================================-->

## <span id="footnotes">Footnotes</span> [**&#x25B4;**](#top)

<span id="footnote_01">[1]</span> ***Gradle Support for Java*** [↩](#anchor_01)

<dl><dd>
<table>
<tr><th>Gradle version</th><th>Java version</th></tr>
<tr><td><a href="https://docs.gradle.org/8.4/release-notes.html" rel="external">8.4</a>, <a href="https://docs.gradle.org/8.5/release-notes.html" rel="external">8.5</a>, <a href="https://docs.gradle.org/8.6/release-notes.html" rel="external">8.6</a></td><td><a href="https://docs.gradle.org/current/javadoc/org/gradle/api/JavaVersion.html">21</a></td></tr>
<tr><td><a href="https://docs.gradle.org/8.3/release-notes.html" rel="external">8.3</a></td><td><a href="https://docs.gradle.org/current/javadoc/org/gradle/api/JavaVersion.html">20</a></td></tr>
<tr><td><a href="https://docs.gradle.org/7.6/release-notes.html" rel="external">7.6</a>, <a href="https://docs.gradle.org/8.0/release-notes.html" rel="external">8.0</a> <sup><b>a)</b></sup>, <a href="https://docs.gradle.org/8.1/release-notes.html" rel="external">8.1</a>, <a href="https://docs.gradle.org/8.2/release-notes.html" rel="external">8.2</a></td><td><a href="https://docs.gradle.org/current/javadoc/org/gradle/api/JavaVersion.html">19</a></td></tr>
<tr><td><a href="https://docs.gradle.org/7.5/release-notes.html" rel="external">7.5</a></td><td>18</td></tr>
<tr><td><a href="https://docs.gradle.org/7.3/release-notes.html" rel="external">7.3</a></td><td>17</td></tr>
<tr><td><a href="https://docs.gradle.org/7.0/release-notes.html" rel="external">7.0</a></td><td>16</td></tr>
<tr><td><a href="https://docs.gradle.org/6.7/release-notes.html" rel="external">6.7</a></td><td>15</td></tr>
<tr><td><a href="https://docs.gradle.org/6.3/release-notes.html" rel="external">6.3</a></td><td>14</td></tr>
<tr><td><a href="https://docs.gradle.org/6.0/release-notes.html" rel="external">6.0</a></td><td>13</td></tr>
</table>
<span><sup><b>a)</b></sup> Version 8.0.2 or newer is required for use with Scala 2.13 (see <a href="https://github.com/gradle/gradle/issues/23962">issue 23962</a>).
</dd></dl>

<span id="footnote_02">[2]</span> ***Downloads*** [↩](#anchor_02)

<dl><dd>
In our case we downloaded the following installation files (see <a href="#proj_deps">section 1</a>):
</dd>
<dd>
<pre style="font-size:80%;">
<a href="https://ant.apache.org/bindownload.cgi" rel="external">apache-ant-1.10.14-bin.zip</a>                         <i>(  9 MB)</i>
<a href="https://maven.apache.org/download.cgi">apache-maven-3.9.6-bin.zip</a>                         <i>( 10 MB)</i>
<a href="https://gradle.org/install/">gradle-8.6-bin.zip</a>                                 <i>(118 MB)</i>
<a href="https://github.com/fullstorydev/grpcurl/releases">grpcurl_1.8.9_windows_x86_64.zip</a>                   <i>(  6 MB)</i>
<a href="https://sourceforge.net/projects/gnuwin32/files/make/3.81/">make-3.81-bin.zip</a>                                  <i>( 10 MB)</i>
<a href="https://adoptium.net/releases.html?variant=openjdk17&jvmVariant=hotspot">OpenJDK17U-jdk_x64_windows_hotspot_17.0.10_7.zip</a>   <i>(188 MB)</i>
<a href="https://adoptium.net/releases.html?variant=openjdk21&jvmVariant=hotspot">OpenJDK21U-jdk_x64_windows_hotspot_21.0.2_13.zip</a>   <i>(191 MB)</i>
<a href="https://git-scm.com/download/win" rel="external">PortableGit-2.44.0-64-bit.7z.exe</a>                   <i>( 47 MB)</i>
<a href="https://github.com/sbt/sbt/releases" rel="external">sbt-1.9.9.zip</a>                                      <i>( 17 MB)</i>
<a href="https://www.scala-lang.org/files/archive/">scala-2.13.13.zip</a>                                  <i>( 21 MB)</i>
</pre>
</dd></dl>

<span id="footnote_03">[3]</span> **`setenv.bat` *usage*** [↩](#anchor_03)

<dl><dd>
Batch file <a href=./setenv.bat><code><b>setenv.bat</b></code></a> has specific environment variables set that enable us to use command-line developer tools more easily.
</dd>
<dd>It is similar to the setup scripts described on the page <a href="https://learn.microsoft.com/en-us/visualstudio/ide/reference/command-prompt-powershell" rel="external">"Visual Studio Developer Command Prompt and Developer PowerShell"</a> of the <a href="https://learn.microsoft.com/en-us/visualstudio/windows" rel="external">Visual Studio</a> online documentation.
</dd>
<dd>
For instance we can quickly check that the two scripts <code><b>Launch-VsDevShell.ps1</b></code> and <code><b>VsDevCmd.bat</b></code> are indeed available in our Visual Studio 2019 installation :
<pre style="font-size:80%;">
<b>&gt; <a href="https://learn.microsoft.com/en-us/windows-server/administration/windows-commands/where" rel="external">where</a> /r "C:\Program Files (x86)\Microsoft Visual Studio" *vsdev*</b>
C:\Program Files (x86)\Microsoft Visual Studio\2019\Community\Common7\Tools\Launch-VsDevShell.ps1
C:\Program Files (x86)\Microsoft Visual Studio\2019\Community\Common7\Tools\VsDevCmd.bat
C:\Program Files (x86)\Microsoft Visual Studio\2019\Community\Common7\Tools\vsdevcmd\core\vsdevcmd_end.bat
C:\Program Files (x86)\Microsoft Visual Studio\2019\Community\Common7\Tools\vsdevcmd\core\vsdevcmd_start.bat
</pre>
</dd>
<dd>
Concretely, in our GitHub projects which depend on Visual Studio (e.g. <a href="https://github.com/michelou/cpp-examples"><code>michelou/cpp-examples</code></a>), <a href="./setenv.bat"><code><b>setenv.bat</b></code></a> does invoke <code><b>VsDevCmd.bat</b></code> (resp. <code><b>vcvarall.bat</b></code> for older Visual Studio versions) to setup the Visual Studio tools on the command prompt. 
</dd></dl>

***

*[mics](https://lampwww.epfl.ch/~michelou/)/March 2024* [**&#9650;**](#top)
<span id="bottom">&nbsp;</span>

<!-- link refs -->

[ada_examples]: https://github.com/michelou/ada-examples
[akka]: https://akka.io/
[akka_license]: https://www.lightbend.com/akka/license-faq
[apache_ant]: https://ant.apache.org/
[apache_ant_cli]: https://ant.apache.org/manual/running.html
[apache_ant_relnotes]: https://github.com/apache/ant/blob/master/WHATSNEW
[apache_maven]: https://maven.apache.org/download.cgi
[apache_maven_cli]: https://maven.apache.org/ref/current/maven-embedder/cli.html
[apache_maven_history]: https://maven.apache.org/docs/history.html
[apache_maven_relnotes]: https://maven.apache.org/docs/3.9.6/release-notes.html
[apache_pekko]: https://pekko.apache.org/
[blog_alexandreesl]: https://alexandreesl.com/2019/01/02/akka-streams-developing-robust-applications-using-scala/
[book_allen]: https://www.oreilly.com/library/view/effective-akka/9781449360061/
[book_gupta]: https://www.packtpub.com/product/akka-essentials/9781849518284
[book_ortiz]: https://www.packtpub.com/product/akka-cookbook/9781785288180
[book_wyatt]: https://www.artima.com/shop/akka_concurrency
[cpp_examples]: https://github.com/michelou/cpp-examples
[dart_examples]: https://github.com/michelou/dart-examples
[deno_examples]: https://github.com/michelou/deno-examples
[docker_examples]: https://github.com/michelou/docker-examples
[flix_examples]: https://github.com/michelou/flix-examples
[git_bash]: https://www.atlassian.com/git/tutorials/git-bash
[git_cli]: https://git-scm.com/docs/git
[git_downloads]: https://git-scm.com/download/win
[git_relnotes]: https://raw.githubusercontent.com/git/git/master/Documentation/RelNotes/2.44.0.txt
[github_markdown]: https://github.github.com/gfm/
[gmake_install]: https://sourceforge.net/projects/gnuwin32/files/make/3.81/
[gradle_cli]: https://docs.gradle.org/current/userguide/command_line_interface.html
[gradle_compatibility]: https://docs.gradle.org/current/release-notes.html#upgrade-instructions
[gradle_install]: https://gradle.org/install/
[gradle_relnotes]: https://docs.gradle.org/8.6/release-notes.html
[golang_examples]: https://github.com/michelou/golang-examples
[graalvm_examples]: https://github.com/michelou/graalvm-examples
[grpcurl_downloads]: https://github.com/fullstorydev/grpcurl/releases
[grpcurl_relnotes]: https://github.com/fullstorydev/grpcurl/releases/tag/v1.8.9
[haskell_examples]: https://github.com/michelou/haskell-examples
[kafka_examples]: https://github.com/michelou/kafka-examples
[kotlin_examples]: https://github.com/michelou/kotlin-examples
[linux_opt]: https://tldp.org/LDP/Linux-Filesystem-Hierarchy/html/opt.html
[llvm_examples]: https://github.com/michelou/llvm-examples
[m2_examples]: https://github.com/michelou/m2-examples
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
[oracle_openjdk21]: https://jdk.java.net/21/
[oracle_openjdk21_api]: https://download.java.net/java/early_access/jdk21/docs/api/
[oracle_openjdk21_relnotes]: https://jdk.java.net/21/release-notes
[rust_examples]: https://github.com/michelou/rust-examples
[sbt_cli]: https://www.scala-sbt.org/1.x/docs/Command-Line-Reference.html
[sbt_downloads]: https://github.com/sbt/sbt/releases
[sbt_libs]: https://www.scala-sbt.org/1.x/docs/Library-Dependencies.html
[sbt_relnotes]: https://github.com/sbt/sbt/releases/tag/v1.9.9
[scala_releases]: https://www.scala-lang.org/files/archive/
[scala_relnotes]: https://github.com/scala/scala/releases/tag/v2.13.13
[scala3_examples]: https://github.com/michelou/dotty-examples
[scala3_home]: https://dotty.epfl.ch
[sh_cli]: https://man7.org/linux/man-pages/man1/sh.1p.html
[spark_examples]: https://github.com/michelou/spark-examples
[spring_examples]: https://github.com/michelou/spring-examples
<!--
### https://mail.openjdk.org/pipermail/jdk-updates-dev/
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
11.0.18 -> https://mail.openjdk.org/pipermail/jdk-updates-dev/2023-January/020111.html
11.0.19 -> https://mail.openjdk.org/pipermail/jdk-updates-dev/2023-April/021900.html
11.0.20 -> https://mail.openjdk.org/pipermail/jdk-updates-dev/2023-July/024064.html
11.0.21 -> https://mail.openjdk.org/pipermail/jdk-updates-dev/2023-October/026351.html
11.0.22 -> https://mail.openjdk.org/pipermail/jdk-updates-dev/2024-January/029215.html
-->
[temurin_openjdk11]: https://adoptium.net/releases.html?variant=openjdk11&jvmVariant=hotspot
[temurin_openjdk11_bugfixes]: https://www.oracle.com/java/technologies/javase/11-0-19-relnotes.html
[temurin_openjdk11_relnotes]: https://mail.openjdk.org/pipermail/jdk-updates-dev/2023-October/026351.html
<!--
17.0.7  -> https://mail.openjdk.org/pipermail/jdk-updates-dev/2023-April/021899.html
17.0.8  -> https://mail.openjdk.org/pipermail/jdk-updates-dev/2023-July/024063.html
17.0.9  -> https://mail.openjdk.org/pipermail/jdk-updates-dev/2023-October/026352.html
17.0.10 -> https://mail.openjdk.org/pipermail/jdk-updates-dev/2024-January/029089.html
-->
[temurin_openjdk17]: https://adoptium.net/releases.html?variant=openjdk17&jvmVariant=hotspot
[temurin_openjdk17_bugfixes]: https://www.oracle.com/java/technologies/javase/17-0-2-bugfixes.html
[temurin_openjdk17_relnotes]: https://mail.openjdk.org/pipermail/jdk-updates-dev/2023-October/026352.html
<!--
21_35   -> https://adoptium.net/fr/temurin/release-notes/?version=jdk-21+35
21.0.1  -> https://mail.openjdk.org/pipermail/jdk-updates-dev/2023-October/026351.html
-->
[temurin_openjdk21]: https://adoptium.net/releases.html?variant=openjdk21&jvmVariant=hotspot
[temurin_openjdk21_relnotes]: https://mail.openjdk.org/pipermail/jdk-updates-dev/2023-October/026351.html
[trufflesqueak_examples]: https://github.com/michelou/trufflesqueak-examples
[vs2019_downloads]: https://visualstudio.microsoft.com/en/downloads/
[vs2019_relnotes]: https://docs.microsoft.com/en-us/visualstudio/releases/2019/release-notes
[vscode_downloads]: https://code.visualstudio.com/#alt-downloads
[vscode_relnotes]: https://code.visualstudio.com/updates
[windows_batch_file]: https://en.wikibooks.org/wiki/Windows_Batch_Scripting
[wix_examples]: https://github.com/michelou/wix-examples
[zip_archive]: https://www.howtogeek.com/178146/htg-explains-everything-you-need-to-know-about-zipped-files/
