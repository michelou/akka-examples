package com.example

import akka.actor.typed.Behavior
import akka.actor.typed.javadsl.AbstractBehavior
import akka.actor.typed.javadsl.ActorContext
import akka.actor.typed.javadsl.Behaviors // factories for Behavior
import akka.actor.typed.javadsl.Receive

class GreeterBot(context: ActorContext<Greeter.Greeted>, val max: Int) : AbstractBehavior<Greeter.Greeted>(context) {

    companion object  {
        fun create(max: Int): Behavior<Greeter.Greeted> {
             return Behaviors.setup({ GreeterBot(it, max) })
        }
    }

    private var greetingCounter: Int = 0;

    override fun createReceive(): Receive<Greeter.Greeted>  {
        return newReceiveBuilder().onMessage(Greeter.Greeted::class.java, this::onGreeted).build()
    }

    private fun onGreeted(message: Greeter.Greeted): Behavior<Greeter.Greeted> {
        greetingCounter++;
        getContext().getLog().info("Greeting {} for {}", greetingCounter, message.whom)
        if (greetingCounter == max) {
            return Behaviors.stopped()
        }
        else {
            message.from.tell(Greeter.Greet(message.whom, getContext().getSelf()))
            return this
        }
    }

}
