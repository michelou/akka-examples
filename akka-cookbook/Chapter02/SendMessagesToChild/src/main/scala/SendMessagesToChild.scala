package com.packt.chapter02

import akka.actor.{ActorSystem, Props}

object SendMessagesToChild {

  def main(args: Array[String]): Unit = {
    val actorSystem = ActorSystem("SendMessagesToChild")
    val parent = actorSystem.actorOf(Props[ParentActor](), "parent")
    parent ! CreateChild
    parent ! CreateChild
    parent ! CreateChild
    parent ! Send
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
