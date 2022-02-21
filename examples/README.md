# <span id="top">Akka examples</span> <span style="size:30%;"><a href="../README.md">⬆</a></span>

<table style="font-family:Helvetica,Arial;font-size:14px;line-height:1.6;">
  <tr>
  <td style="border:0;padding:0 10px 0 0;min-width:100px;"><a href="https://akka.io/"><img style="border:0;" src="../docs/images/akka.svg" width="100" alt="Akka project"/></a></td>
  <td style="border:0;padding:0;vertical-align:text-top;">Directory <strong><code>examples\</code></strong> contains <a href="https://akka.io/" alt="Akka">Akka</a> code examples coming from various websites - mostly from the <a href="https://akka.io/" rel="external">Akka project</a>.
  </td>
  </tr>
</table>

## <span id="akka-quickstart">`akka-quickstart`</span>

We have implemented this code example in 3 different progamming languages :
|      | Source files     | Batch      | Grade      | Maven      |
|:-----|:-----------------|:-----------|:-----------|:-----------|
| Java | [`AkkaQuickstart.java`](akka-quickstart-java/src/main/java/com/example/AkkaQuickstart.java), [`Greeter.java`](akka-quickstart-java/src/main/java/com/example/Greeter.java) | [`build.bat`](akka-quickstart-java/build.bat) | [`build.gradle`](akka-quickstart-java/build.gradle) | [`pom.xml`](akka-quickstart-java/pom.xml) |
| Kotlin | [`AkkaQuickstart.kt`](akka-quickstart-kotlin/src/main/kotlin/com/example/AkkaQuickstart.kt), [`Greeter.kt`](akka-quickstart-kotlin/src/main/kotlin/com/example/Greeter.kt) | [`build.bat`](akka-quickstart-kotlin/build.bat) | [`build.gradle`](akka-quickstart-kotlin/build.gradle) | [`pom.xml`](akka-quickstart-kotlin/pom.xml) |
| Scala | [`AkkaQuickstart.scala`](akka-quickstart-scala/src/main/scala/com/example/AkkaQuickstart.scala), [`Greeter.scala`](akka-quickstart-scala/src/main/scala/com/example/Greeter.scala) | [`build.bat`](akka-quickstart-scala/build.bat) | [`build.gradle`](akka-quickstart-scala/build.gradle) | [`pom.xml`](akka-quickstart-scala/pom.xml) |

Here are 3 command executions for the Java code example :

<pre style="font-size:80%;">
<b>&gt; cd</b>
%USERPROFILE%\workspace-perso\akka-examples\examples\akka-quickstart-jav
&nbsp;
<b>&gt; <a href="akka-quickstart-java/build.bat">build</a> -verbose run</b>
Compile 4 Java source files to directory "target\classes"
[helloakka-akka.actor.default-dispatcher-6] INFO akka.event.slf4j.Slf4jLogger - Slf4jLogger started
>>> Press ENTER to exit <<<
[helloakka-akka.actor.default-dispatcher-3] INFO com.example.Greeter - Hello Charles!
[helloakka-akka.actor.default-dispatcher-6] INFO com.example.GreeterBot - Greeting 1 for Charles
[helloakka-akka.actor.default-dispatcher-3] INFO com.example.Greeter - Hello Charles!
[helloakka-akka.actor.default-dispatcher-6] INFO com.example.GreeterBot - Greeting 2 for Charles
[helloakka-akka.actor.default-dispatcher-3] INFO com.example.Greeter - Hello Charles!
[helloakka-akka.actor.default-dispatcher-6] INFO com.example.GreeterBot - Greeting 3 for Charles

[helloakka-akka.actor.default-dispatcher-3] INFO akka.actor.CoordinatedShutdown - Running CoordinatedShutdown with reason [ActorSystemTerminateReason]
&nbsp;
<b>&gt; gradle -q run</b>
SLF4J: A number (1) of logging calls during the initialization phase have been intercepted and are
SLF4J: now being replayed. These are subject to the filtering rules of the underlying logging system.
SLF4J: See also http://www.slf4j.org/codes.html#replay
[2022-02-10 17:54:31,240] [INFO] [akka.event.slf4j.Slf4jLogger] [helloakka-akka.actor.default-dispatcher-3] [] - Slf4jLogger started
>>> Press ENTER to exit <<<
[2022-02-10 17:54:31,289] [INFO] [com.example.Greeter] [helloakka-akka.actor.default-dispatcher-7] [akka://helloakka/user/greeter] - Hello Charles!
[2022-02-10 17:54:31,289] [INFO] [com.example.GreeterBot] [helloakka-akka.actor.default-dispatcher-3] [akka://helloakka/user/Charles] - Greeting 1 for Charles
[2022-02-10 17:54:31,299] [INFO] [com.example.Greeter] [helloakka-akka.actor.default-dispatcher-7] [akka://helloakka/user/greeter] - Hello Charles!
[2022-02-10 17:54:31,299] [INFO] [com.example.GreeterBot] [helloakka-akka.actor.default-dispatcher-3] [akka://helloakka/user/Charles] - Greeting 2 for Charles
[2022-02-10 17:54:31,299] [INFO] [com.example.Greeter] [helloakka-akka.actor.default-dispatcher-7] [akka://helloakka/user/greeter] - Hello Charles!
[2022-02-10 17:54:31,299] [INFO] [com.example.GreeterBot] [helloakka-akka.actor.default-dispatcher-3] [akka://helloakka/user/Charles] - Greeting 3 for Charles
<=========----> 75% EXECUTING [3s]
&nbsp;
<b>&gt; mvn -q compile exec:java</b>
SLF4J: A number (1) of logging calls during the initialization phase have been intercepted and are[2022-02-10 17:50:47,070] [INFO] [akka.event.slf4j.Slf4jLogger] [helloakka-akka.actor.default-dispatcher-3] [] - Slf4jLogger started
&nbsp;
SLF4J: now being replayed. These are subject to the filtering rules of the underlying logging system.
SLF4J: See also http://www.slf4j.org/codes.html#replay
>>> Press ENTER to exit <<<
[2022-02-10 17:50:47,120] [INFO] [com.example.Greeter] [helloakka-akka.actor.default-dispatcher-5] [akka://helloakka/user/greeter] - Hello Charles!
[2022-02-10 17:50:47,130] [INFO] [com.example.GreeterBot] [helloakka-akka.actor.default-dispatcher-3] [akka://helloakka/user/Charles] - Greeting 1 for Charles
[2022-02-10 17:50:47,130] [INFO] [com.example.Greeter] [helloakka-akka.actor.default-dispatcher-5] [akka://helloakka/user/greeter] - Hello Charles!
[2022-02-10 17:50:47,130] [INFO] [com.example.GreeterBot] [helloakka-akka.actor.default-dispatcher-3] [akka://helloakka/user/Charles] - Greeting 2 for Charles
[2022-02-10 17:50:47,130] [INFO] [com.example.Greeter] [helloakka-akka.actor.default-dispatcher-5] [akka://helloakka/user/greeter] - Hello Charles!
[2022-02-10 17:50:47,130] [INFO] [com.example.GreeterBot] [helloakka-akka.actor.default-dispatcher-3] [akka://helloakka/user/Charles] - Greeting 3 for Charles
&nbsp;
[2022-02-10 17:50:49,880] [INFO] [akka.actor.CoordinatedShutdown] [helloakka-akka.actor.default-dispatcher-5] [CoordinatedShutdown(akka://helloakka)] - Running CoordinatedShutdown with reason [ActorSystemTerminateReason]
</pre>

## <span id="shopping-cart-service">`shopping-cart-service`</span>

We add 3 socks to a cart (see section [**6.1 Exercise the service**](https://developer.lightbend.com/docs/akka-platform-guide/microservices-tutorial/grpc-service.html#_exercise_the_service)):

<pre style="font-size:80%;">
<b>&gt; <a href="https://docs.microsoft.com/en-us/windows-server/administration/windows-commands/where">where</a> grpcurl</b>
C:\opt\grpcurl-1.8.5\grpcurl.exe
&nbsp;
<b>&gt; <a href="https://github.com/fullstorydev/grpcurl#invoking-rpcs">grpcurl</a> -d "{\"cartId\":\"cart1\", \"itemId\":\"socks\", \"quantity\":3}" -plaintext 127.0.0.1:8101 shoppingcart.ShoppingCartService.AddItem</b>
{
  "items": [
    {
      "itemId": "socks",
      "quantity": 3
    }
  ]
}
</pre>

We add 2 t-shirts to the same cart (see section [**6.1 Exercise the service**](https://developer.lightbend.com/docs/akka-platform-guide/microservices-tutorial/grpc-service.html#_exercise_the_service)):

<pre style="font-size:80%;">
<b>&gt; a href="https://github.com/fullstorydev/grpcurl#invoking-rpcs">grpcurl</a> -d "{\"cartId\":\"cart1\", \"itemId\":\"t-shirt\", \"quantity\":2}" -plaintext 127.0.0.1:8101 shoppingcart.ShoppingCartService.AddItem</b>
{
  "items": [
    {
      "itemId": "t-shirt",
      "quantity": 2
    }
  ]
}
</pre>

## <span id="footnotes">Footnotes</span>

<span id="footnote_01">[1]</span> ***grpcurl*** [↩](#anchor_01)

<dl><dd>
<a href="https://sadique.io/blog/2018/04/04/command-line-clients-for-grpc-grpcurl/">Command line clients for gRPC - grpcurl</a>, April 2018.
</dd></dl>

***

*[mics](https://lampwww.epfl.ch/~michelou/)/February 2022* [**&#9650;**](#top)
<span id="bottom">&nbsp;</span>

<!-- link refs -->
