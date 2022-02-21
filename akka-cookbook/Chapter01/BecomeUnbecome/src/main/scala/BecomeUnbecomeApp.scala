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
