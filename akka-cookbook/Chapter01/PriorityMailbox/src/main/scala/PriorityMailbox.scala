package com.packt.chapter01

import akka.actor.{ActorSystem, Props}

object PriorityMailbox {

  def main(args: Array[String]): Unit = {
    val actorSystem = ActorSystem("PriorityMailbox")
    val myPriorityActor = actorSystem.actorOf(
      Props[MyPriorityActor]().withDispatcher("prio-dispatcher")
    )
    myPriorityActor ! 6.0
    myPriorityActor ! 1
    myPriorityActor ! 5.0
    myPriorityActor ! 3
    myPriorityActor ! "Hello"
    myPriorityActor ! 5
    myPriorityActor ! "I'm priority actor"
    myPriorityActor ! "I process string messages first, then integer, long and others"
    Thread.sleep(100)

    terminate(actorSystem)
  }

  private def terminate(actor: ActorSystem): Unit =
    try {
      println(">>> Press ENTER to exit <<<")
      System.in.read()
    }
    catch {
      case _: java.io.IOException => /* ignored */
    }
    finally {
      actor.terminate()
    }

}
