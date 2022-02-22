package com.packt.chapter02

import akka.actor.{ActorRef, ActorSystem, Props}
import akka.actor.SupervisorStrategy._
import akka.pattern.ask
import akka.util.Timeout
import scala.concurrent.Await
import scala.concurrent.duration._

object ActorLifeCycle {

  def main(args: Array[String]): Unit = {
    implicit val timeout = Timeout(2.seconds)
    val actorSystem = ActorSystem("ActorLifeCycle")
    val supervisor = actorSystem.actorOf(Props[SupervisorActor](), "supervisor")
    val childFuture = supervisor ? (Props(new LifeCycleActor), "LifeCycleActor")
    val child = Await.result(childFuture.mapTo[ActorRef], 2.seconds)
    child ! Error
    Thread.sleep(1000)
    supervisor ! StopActor(child)

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
