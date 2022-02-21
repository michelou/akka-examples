package com.packt.chapter02

import akka.actor.{ActorSystem, Props}

object SupervisorStrategy {

  def main(args: Array[String]): Unit = {
    val actorSystem = ActorSystem("SupervisorStrategy")
    val supervisor = actorSystem.actorOf(Props[SupervisorActor]())
    supervisor ! "Start"
    Thread.sleep(1000)
    
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
