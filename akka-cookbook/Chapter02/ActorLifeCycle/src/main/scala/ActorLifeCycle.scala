package com.packt.chapter02

import akka.actor.{ActorRef, ActorSystem, Props}
import akka.actor.SupervisorStrategy._
import akka.pattern.ask
import akka.util.Timeout
import scala.concurrent.Await
import scala.concurrent.duration.Duration

object ActorLifeCycle {

  def main(args: Array[String]): Unit = {
    implicit val timeout = Timeout(Duration(2, "seconds"))
    val actorSystem = ActorSystem("ActorLifeCycle")
    val supervisor = actorSystem.actorOf(Props[SupervisorActor](), "supervisor")
    val childFuture = supervisor ? (Props(new LifeCycleActor), "LifeCycleActor")
    val child = Await.result(childFuture.mapTo[ActorRef], Duration(2, "seconds"))
    child ! Error
    Thread.sleep(1000)
    supervisor ! StopActor(child)
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
