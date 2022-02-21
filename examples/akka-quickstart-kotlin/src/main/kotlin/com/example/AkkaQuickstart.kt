package com.example

import akka.actor.typed.ActorSystem

@Suppress("UNUSED_PARAMETER")
fun main(args: Array<String>) {
    val greeterMain: ActorSystem<GreeterMain.SayHello> =
        ActorSystem.create(GreeterMain.create(), "AkkaQuickstart")

    greeterMain.tell(GreeterMain.SayHello("Charles"))

    try {
        println(">>> Press ENTER to exit <<<")
        readLine()!!
    }
    catch (ignored: java.io.IOException) {
        /* ignored */
    }
    finally {
        greeterMain.terminate()
    }
}
