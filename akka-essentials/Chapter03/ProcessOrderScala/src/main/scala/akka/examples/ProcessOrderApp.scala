package akka.examples

import akka.actor.{ActorRef, ActorSystem, Props}
import akka.examples.actors.ProcessOrderActor

object ProcessOrderApp {

  def main(args: Array[String]): Unit = {
    val _system = ActorSystem("ProcessOrder")
    val master = _system.actorOf(Props[ProcessOrderActor](), "master")
    val userId = 1;
    master ! userId
    Thread.sleep(5000)

    _system.terminate()
  }

}
