package com.packt.chapter01

import akka.actor.ActorSystem
import akka.actor.Props

object BehaviourAndState {

  def main(args: Array[String]): Unit = {
    val actorSystem = ActorSystem("BehaviourAndState")  
    val actor = actorSystem.actorOf(Props[SummingActor](), "summingActor")
    println(s"actor.path=${actor.path}")

    actor ! 2
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

