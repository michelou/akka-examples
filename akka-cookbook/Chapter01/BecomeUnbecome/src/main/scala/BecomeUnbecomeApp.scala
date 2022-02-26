package com.packt.chapter01

import akka.actor.{ActorSystem, Props}

object BecomeUnbecomeApp {

  def main(args: Array[String]): Unit = {
    val actorSystem = ActorSystem("BecomeUnbecome")
    val actor = actorSystem.actorOf(Props[BecomeUnbecomeActor]())
    actor ! true
    actor ! "Hello how are you?"
    actor ! false
    actor ! 1100
    actor ! true
    actor ! "What do u do ?"

    terminate(actorSystem)    
  }

  /**
   * Terminates the actor system ignoring the checked exceptions.
   */
  private def terminate(actor: ActorSystem): Unit =
    try {
      Thread.sleep(1000)
      println(">>> Press ENTER to exit <<<")
      System.in.read()
    }
    catch {
      case _: Exception => /* ignored */
    }
    finally {
      actor.terminate()
    }

}
