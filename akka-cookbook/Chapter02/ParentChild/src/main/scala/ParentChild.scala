package com.packt.chapter02

import akka.actor.{ActorSystem, Props}

object ParentChild {

  def main(args: Array[String]): Unit = {
    val actorSystem = ActorSystem("ParentChild")
    val parent = actorSystem.actorOf(Props[ParentActor](), "parent")
    parent ! CreateChild
    
    terminate(actorSystem)
  }

  /**
   * Terminates the actor system ignoring the checked exceptions.
   */
  private def terminate(system: ActorSystem): Unit =
    try {
      Thread.sleep(1000)
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
