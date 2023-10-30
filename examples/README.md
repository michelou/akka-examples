# <span id="top">Akka examples</span> <span style="size:30%;"><a href="../README.md">⬆</a></span>

<table style="font-family:Helvetica,Arial;line-height:1.6;">
  <tr>
  <td style="border:0;padding:0 10px 0 0;min-width:100px;"><a href="https://akka.io/" rel="external"><img style="border:0;" src="../docs/images/akka.svg" width="100" alt="Akka project"/></a></td>
  <td style="border:0;padding:0;vertical-align:text-top;">Directory <strong><code>examples\</code></strong> contains <a href="https://akka.io/" alt="Akka">Akka</a> code examples coming from various websites - mostly from the <a href="https://akka.io/" rel="external">Akka project</a>.
  </td>
  </tr>
</table>

## <span id="akka-quickstart">`akka-quickstart` Example</span>

We have implemented this code example in 3 different programming languages :
|      | Source files     | Batch      | [Gradle]   | [Maven]    |
|:-----|:-----------------|:-----------|:-----------|:-----------|
| Java | [`AkkaQuickstart.java`](akka-quickstart-java/src/main/java/com/example/AkkaQuickstart.java), [`Greeter.java`](akka-quickstart-java/src/main/java/com/example/Greeter.java) | [`build.bat`](akka-quickstart-java/build.bat) | [`build.gradle`](akka-quickstart-java/build.gradle) | [`pom.xml`](akka-quickstart-java/pom.xml) |
| Kotlin | [`AkkaQuickstart.kt`](akka-quickstart-kotlin/src/main/kotlin/com/example/AkkaQuickstart.kt), [`Greeter.kt`](akka-quickstart-kotlin/src/main/kotlin/com/example/Greeter.kt) | [`build.bat`](akka-quickstart-kotlin/build.bat) | [`build.gradle`](akka-quickstart-kotlin/build.gradle) | [`pom.xml`](akka-quickstart-kotlin/pom.xml) |
| Scala | [`AkkaQuickstart.scala`](akka-quickstart-scala/src/main/scala/com/example/AkkaQuickstart.scala), [`Greeter.scala`](akka-quickstart-scala/src/main/scala/com/example/Greeter.scala) | [`build.bat`](akka-quickstart-scala/build.bat) | [`build.gradle`](akka-quickstart-scala/build.gradle) | [`pom.xml`](akka-quickstart-scala/pom.xml) |

Here are 3 command executions for the Java code example :

<pre style="font-size:80%;">
<b>&gt; <a href="https://learn.microsoft.com/en-us/windows-server/administration/windows-commands/cd" rel="external">cd</a> akka-quickstart-java</b>
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
<b>&gt; <a href="https://docs.gradle.org/current/userguide/command_line_interface.html" rel="external">gradle</a> -q run</b>
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
<b>&gt; <a href="https://maven.apache.org/ref/3-LATEST/maven-embedder/cli.html" rel="external">mvn</a> -q compile exec:java</b>
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

## <span id="shopping-cart-service">`shopping-cart-service` Example</span> [**&#x25B4;**](#top)

We add 3 socks to a cart (see section [**6.1 Exercise the service**](https://developer.lightbend.com/docs/akka-platform-guide/microservices-tutorial/grpc-service.html#_exercise_the_service)) with the [grpcurl]<sup id="anchor_01">[1](#footnote_01)</sup> tool :

<pre style="font-size:80%;">
<b>&gt; <a href="https://docs.microsoft.com/en-us/windows-server/administration/windows-commands/where">where</a> grpcurl</b>
C:\opt\grpcurl-1.8.7\grpcurl.exe
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
<b>&gt; <a href="https://github.com/fullstorydev/grpcurl#invoking-rpcs">grpcurl</a> -d "{\"cartId\":\"cart1\", \"itemId\":\"t-shirt\", \"quantity\":2}" -plaintext 127.0.0.1:8101 shoppingcart.ShoppingCartService.AddItem</b>
{
  "items": [
    {
      "itemId": "t-shirt",
      "quantity": 2
    }
  ]
}
</pre>

## <span id="receptionist">`Receptionist` Example</span> [**&#x25B4;**](#top)

The `Receptionist` example is adapted from [Alvin's tutorial]( https://alvinalexander.com/scala/akka-typed-how-lookup-find-actor-receptionist/) on Typed Akka.

Command [**`build.bat`**](./Receptionist/build.bat) `clean run` generates the [Akka] application from the source files [`Brain.scala`](./Receptionist/src/main/scala/Brain.scala), [`Mouth.scala`](./Receptionist/src/main/scala/Mouth.scala), [`ReceptionistApp.scala`](./Receptionist/src/main/scala/ReceptionistApp.scala) and [`Supervisor.scala`](./Receptionist/src/main/scala/Supervisor.scala) and executes it :

<pre style="font-size:80%;">
<b>&gt; <a href="./Receptionist/build.bat">build</a> -verbose clean run</b>
Delete directory "target"
Compile 4 Scala source files to directory "target\classes"
Copy resource files to directory "target\classes"
Run Scala program "akka.examples.ReceptionistApp"
[Supervisor-akka.actor.default-dispatcher-3] INFO akka.event.slf4j.Slf4jLogger - Slf4jLogger started
SLF4J: A number (1) of logging calls during the initialization phase have been intercepted and are
SLF4J: now being replayed. These are subject to the filtering rules of the underlying logging system.
SLF4J: See also https://www.slf4j.org/codes.html#replay
[Supervisor-akka.actor.internal-dispatcher-4] DEBUG akka.actor.typed.internal.receptionist.LocalReceptionist - Actor was registered: ServiceKey[akka.examples.Mouth$MessageToMouth](Mouth) Actor[akka://Supervisor/user/Mouth#-1231001945]Supervisor got a Start message

Brain: got a FindTheMouth message
listingAdapter:listing: Listing(ServiceKey[akka.examples.Mouth$MessageToMouth](Mouth),Set(Actor[akka://Supervisor/user/Mouth#-1231001945]),Set(Actor[akka://Supervisor/user/Mouth#-1231001945]),true)
Brain: got a ListingResponse message
Mouth: got a msg: Brain says hello to Mouth
listingAdapter:listing: Listing(ServiceKey[akka.examples.Mouth$MessageToMouth](Mouth),Set(Actor[akka://Supervisor/user/Mouth#-1231001945]),Set(Actor[akka://Supervisor/user/Mouth#-1231001945]),true)
Brain: got a ListingResponse message
Mouth: got a msg: Brain says hello to Mouth
[Supervisor-akka.actor.default-dispatcher-5] INFO akka.actor.CoordinatedShutdown - Running CoordinatedShutdown with reason [ActorSystemTerminateReason]
[Supervisor-akka.actor.internal-dispatcher-4] DEBUG akka.actor.typed.internal.receptionist.LocalReceptionist - Registered actor terminated: [ServiceKey[akka.examples.Mouth$MessageToMouth](Mouth)] Actor[akka://Supervisor/user/Mouth#-1231001945]
[Supervisor-akka.actor.internal-dispatcher-4] DEBUG akka.actor.typed.internal.receptionist.LocalReceptionist - Subscribed actor terminated: [ServiceKey[akka.examples.Mouth$MessageToMouth](Mouth)] Actor[akka://Supervisor/user/Brain/$$a-adapter#741795935]
</pre>

## <span id="footnotes">Footnotes</span> [**&#x25B4;**](#top)

<span id="footnote_01">[1]</span> ***grpcurl*** [↩](#anchor_01)

<dl><dd>
<a href="https://sadique.io/blog/2018/04/04/command-line-clients-for-grpc-grpcurl/">Command line clients for gRPC - grpcurl</a>, April 2018.
</dd></dl>

***

*[mics](https://lampwww.epfl.ch/~michelou/)/October 2023* [**&#9650;**](#top)
<span id="bottom">&nbsp;</span>

<!-- link refs -->

[akka]: https://akka.io/
[gradle]: https://docs.gradle.org/current/userguide/what_is_gradle.html
[grpcurl]: https://github.com/fullstorydev/grpcurl#grpcurl
[maven]: https://maven.apache.org/what-is-maven.html
