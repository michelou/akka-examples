package com.packt.chapter01

import akka.actor.{ActorSystem, Props}

object BehaviourAndState {

  def main(args: Array[String]): Unit = {
    val actorSystem = ActorSystem("BehaviourAndState")  
    val actor = actorSystem.actorOf(Props[SummingActor](), "summingActor")
    println(s"actor.path=${actor.path}")

    actor ! 2

    terminate(actorSystem)
  }

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
