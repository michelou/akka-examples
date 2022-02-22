package com.packt.chapter01

import akka.actor.{Actor, ActorRef, ActorSystem, Props}
import akka.dispatch.{Envelope, MailboxType, MessageQueue, ProducesMessageQueue}
import com.typesafe.config.Config

object CustomMailbox {
  
  def main(args: Array[String]): Unit = {
    val actorSystem = ActorSystem("CustomMailbox")
    val actor = actorSystem.actorOf(
      Props[MySpecialActor]().withDispatcher("custom-dispatcher"))
    val actor1 = actorSystem.actorOf(Props[MyActor](), "xyz")
    val actor2 = actorSystem.actorOf(Props[MyActor](), "MyActor")

    actor1 ! ("hello", actor)
    actor2 ! ("hello", actor)
  
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
