package com.packt.chapter01

import akka.actor.ActorSystem

object HelloAkka {

  def main(args: Array[String]): Unit = {
    val actorSystem = ActorSystem("HelloAkka")
    println(actorSystem)
    
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
