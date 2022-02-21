package com.example

import akka.actor.typed.ActorRef
import akka.actor.typed.Behavior
import akka.actor.typed.javadsl.AbstractBehavior
import akka.actor.typed.javadsl.ActorContext
import akka.actor.typed.javadsl.Behaviors
import akka.actor.typed.javadsl.Receive

class GreeterMain(context: ActorContext<SayHello>) : AbstractBehavior<GreeterMain.SayHello>(context) {

    // https://kotlinlang.org/docs/data-classes.html
    data class SayHello(val name: String)

    companion object {
        fun create(): Behavior<SayHello> {
            // https://doc.akka.io/japi/akka/current/akka/actor/typed/javadsl/Behaviors.html
            return Behaviors.setup({ GreeterMain(it) })
        }
    }

    private val greeter: ActorRef<Greeter.Greet> =
        context.spawn(Greeter.create(), "greeter")

    override fun createReceive(): Receive<SayHello> {
        return newReceiveBuilder().onMessage(SayHello::class.java, this::onSayHello).build()
    }

    private fun onSayHello(command: SayHello): Behavior<SayHello> {
        var replyTo: ActorRef<Greeter.Greeted> =
            getContext().spawn(GreeterBot.create(3), command.name)
        greeter.tell(Greeter.Greet(command.name, replyTo))
        return this
    }

}
