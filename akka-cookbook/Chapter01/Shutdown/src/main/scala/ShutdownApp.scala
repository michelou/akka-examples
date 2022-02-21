package com.packt.chapter01

import akka.actor.{ActorSystem, PoisonPill, Props}

object ShutdownApp {

  def main(args: Array[String]): Unit = {
    val actorSystem = ActorSystem("Shutdown")
    val actor1 = actorSystem.actorOf(Props[ShutdownActor](), "shutdownActor1")
    actor1 ! "hello"
    actor1 ! PoisonPill
    actor1 ! "Are you there ?"
    
    val actor2 = actorSystem.actorOf(Props[ShutdownActor](), "shutdownActor2")
    actor2 ! "hello"
    actor2 ! Stop
    actor2 ! "Are you there ?"
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
