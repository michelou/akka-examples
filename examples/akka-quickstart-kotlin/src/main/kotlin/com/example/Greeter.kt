package com.example

import akka.actor.typed.ActorRef
import akka.actor.typed.Behavior
import akka.actor.typed.javadsl.AbstractBehavior
import akka.actor.typed.javadsl.ActorContext
import akka.actor.typed.javadsl.Behaviors // factories for Behavior
import akka.actor.typed.javadsl.Receive

class Greeter(context: ActorContext<Greet>) : AbstractBehavior<Greeter.Greet>(context) {

    data class Greet(val whom: String, val replyTo: ActorRef<Greeted>)

    data class Greeted(val whom: String, val from: ActorRef<Greet>)

    companion object {
        fun create(): Behavior<Greet> {
            return Behaviors.setup({ Greeter(it) })
        }
    }

    override fun createReceive(): Receive<Greet> {
        return newReceiveBuilder().onMessage(Greet::class.java, this::onGreet).build()
    }

    private fun onGreet(command: Greet): Behavior<Greet> {
        getContext().getLog().info("Hello {}!", command.whom)
        command.replyTo.tell(Greeted(command.whom, getContext().getSelf()))
        return this
    }

}
