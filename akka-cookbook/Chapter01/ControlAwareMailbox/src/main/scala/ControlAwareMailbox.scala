package com.packt.chapter01

import akka.actor.{ActorSystem, Props}

object ControlAwareMailbox {

  def main(args: Array[String]): Unit = {
    val actorSystem = ActorSystem("ControlAwareMailbox")
    val actor = actorSystem.actorOf(
      Props[LoggerActor]().withDispatcher("control-aware-dispatcher")
    )
    actor ! "hello"
    actor ! "how are"
    actor ! "you ?"
    actor ! MyControlMessage
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
