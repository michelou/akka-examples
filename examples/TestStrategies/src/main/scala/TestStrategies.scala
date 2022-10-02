package com.example

import akka.actor.ActorSystem
import akka.actor.Props

object TestStrategies {

  def main(args: Array[String]): Unit = {
    val system = ActorSystem("StrategiesSystem")
    val names = List("alice", "bob", "carla")
    // alice is able to ask question and interpret answer
    val alice = system.actorOf(Props(new MyInterpretorActor(names)), "alice")
    // bob is able to reply and it is fine
    val bob = system.actorOf(Props(new MyOptimisticActor(names)), "bob")
    // carla is able to reply and it is not fine
    val carla = system.actorOf(Props(new MyPessimisticActor(names)), "carla")

    // alice must ask a question
    alice ! StartMessage

    terminate(system)
  }

  /**
   * Terminates the actor system ignoring the checked exceptions.
   */
  private def terminate(system: ActorSystem): Unit =
    try {
      Thread.sleep(2000)
      println(">>> Press ENTER to exit <<<")
      System.in.read()
    }
    catch {
      case _: /*Interrupted,IO*/Exception => /* ignored */
    }
    finally {
      system.terminate()
    }

}
