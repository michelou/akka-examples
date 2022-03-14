package com.packt.chapter01

import akka.actor.ActorSystem

object HelloAkka {

  def main(args: Array[String]): Unit = {
    val actorSystem = ActorSystem("HelloAkka")
    println(actorSystem)
    
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
      case _: /*Interrupted,IO*/Exception => /* ignored */
    }
    finally {
      actor.terminate()
    }

}
