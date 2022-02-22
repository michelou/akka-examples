package com.packt.chapter02

import akka.actor.{ActorSystem, Props}

object ParentChild {

  def main(args: Array[String]): Unit = {
    val actorSystem = ActorSystem("ParentChild")
    val parent = actorSystem.actorOf(Props[ParentActor](), "parent")
    parent ! CreateChild
    
    terminate(actorSystem)
  }

  private def terminate(system: ActorSystem): Unit =
    try {
      Thread.sleep(1000)
      println(">>> Press ENTER to exit <<<")
      System.in.read()
    }
    catch {
      case _: Exception => /* ignored */
    }
    finally {
      system.terminate()
    }

}
