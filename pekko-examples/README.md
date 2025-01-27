# <span id="top">Pekko examples</span> <span style="font-size:90%;">[⬆](../README.md#top)</span>

<table style="font-family:Helvetica,Arial;line-height:1.6;">
  <tr>
  <td style="border:0;padding:0 10px 0 0;min-width:100px;"><a href="https://pekko.apache.org/" rel="external"><img style="border:0;" src="../docs/images/pekko_logo.png" width="100" alt="Pekko project"/></a></td>
  <td style="border:0;padding:0;vertical-align:text-top;">Directory <strong><code>pekko-examples\</code></strong> contains <a href="https:///pekko.apache.org/" alt="Akka">Pekko</a> code examples coming from various websites - mostly from the <a href="https://pekko.apache.org/" rel="external">Pekko project</a>.
  </td>
  </tr>
</table>

## <span id="ChatRoom">`ChatRoom` Example</span>

This example is taken from the [Apache Pekko documentation](https://pekko.apache.org/docs/pekko/current/typed/actors.html#functional-style); it has the following directory structure :

<pre style="font-size:80%;">
<b>&gt; <a href="https://learn.microsoft.com/en-us/windows-server/administration/windows-commands/tree" rel="external">tree</a> /f /a . | <a href="https://learn.microsoft.com/en-us/windows-server/administration/windows-commands/findstr" rel="external">findstr</a> /v /b [A-Z]</b>
|   <a href="./ChatRoom/00download.txt">00download.txt</a>
|   <a href="./ChatRoom/build.bat">build.bat</a>
|   <a href="./ChatRoom/build.sh">build.sh</a>
|   <a href="./ChatRoom/Makefile">Makefile</a>
\---<b>src</b>
    \---<b>main</b>
        +---<b>resources</b>
        |       <a href="./ChatRoom/src/main/resources/application.conf">application.conf</a>
        \---<b>scala</b>
                <a href="./ChatRoom/src/main/scala/Main.scala">Main.scala</a>
</pre>

Command [**`build.bat`**](./ChatRoom/build.bat)`-verbose clean run` generates and executes the Scala program `Main.class` :

<pre style="font-size:80%;">
<b>&gt; <a href="./ChatRoom/build.bat">build</a> -verbose clean run</b>
Delete directory "target"
Compile 1 Scala source file to directory "target\classes"
[ChatRoomDemo-pekko.actor.default-dispatcher-3] INFO org.apache.pekko.event.slf4j.Slf4jLogger - Slf4jLogger started
SLF4J(W): A number (1) of logging calls during the initialization phase have been intercepted and are
SLF4J(W): now being replayed. These are subject to the filtering rules of the underlying logging system.
SLF4J(W): See also https://www.slf4j.org/codes.html#replay
[ChatRoomDemo-pekko.actor.default-dispatcher-5] INFO Gabbler$ - message has been posted by 'ol? Gabbler': Hello World!
[ChatRoomDemo-pekko.actor.default-dispatcher-5] INFO org.apache.pekko.actor.CoordinatedShutdown - Running CoordinatedShutdown with reason [ActorSystemTerminateReason]
</pre>

<!--=======================================================================-->

## <span id="HelloWorld">`HelloWorld` Example</span> [**&#x25B4;**](#top)

This example has the following directory structure :

<pre style="font-size:80%;">
<b>&gt; <a href="https://learn.microsoft.com/en-us/windows-server/administration/windows-commands/tree">tree</a> /a /f . | <a href="https://learn.microsoft.com/en-us/windows-server/administration/windows-commands/findstr" rel="external">findstr</a> /v /b [A-Z]</b>
|   <a href="./HelloWorld/00download.txt">00download.txt</a>
|   <a href="./HelloWorld/build.bat">build.bat</a>
|   <a href="./HelloWorld/build.sh">build.sh</a>
|   <a href="./HelloWorld/Makefile">Makefile</a>
\---<b>src</b>
    \---<b>main</b>
        +---<b>resources</b>
        |       <a href="./HelloWorld/src/main/resources/application.conf">application.conf</a>
        \---<b>scala</b>
                <a href="./HelloWorld/src/main/scala/Main.scala">Main.scala</a>
</pre>

Command [**`build`**](./HelloWorld/build.bat)`-verbose clean run` generates and executes the Scala program `Main.class` :

<pre style="font-size:80%;">
<b>&gt; <a href="./HelloWorld/build.bat">build</a> -verbose clean run</b>
Delete directory "target"
Compile 1 Scala source file to directory "target\classes"
Execute Scala program "Main"
[hello-pekko.actor.default-dispatcher-3] INFO org.apache.pekko.event.slf4j.Slf4jLogger - Slf4jLogger started
SLF4J(W): A number (1) of logging calls during the initialization phase have been intercepted and are
SLF4J(W): now being replayed. These are subject to the filtering rules of the underlying logging system.
SLF4J(W): See also https://www.slf4j.org/codes.html#replay
[hello-pekko.actor.default-dispatcher-3] INFO HelloWorld$ - Hello World!
[hello-pekko.actor.default-dispatcher-3] INFO HelloWorld$ - Hello Pekko!
Greeting 1 for World
Greeting 1 for Pekko
[hello-pekko.actor.default-dispatcher-6] INFO HelloWorld$ - Hello World!
Greeting 2 for World
[hello-pekko.actor.default-dispatcher-6] INFO HelloWorld$ - Hello Pekko!
Greeting 2 for Pekko
[hello-pekko.actor.default-dispatcher-6] INFO HelloWorld$ - Hello Pekko!
Greeting 3 for Pekko
</pre>

***

*[mics](https://lampwww.epfl.ch/~michelou/)/January 2025* [**&#9650;**](#top)
<span id="bottom">&nbsp;</span>

<!-- link refs -->
