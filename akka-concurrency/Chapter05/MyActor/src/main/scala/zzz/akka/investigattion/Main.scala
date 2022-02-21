package zzz.akka.investigation

import akka.actor.{ActorSystem, Props}

object Main {

  def main(args: Array[String]): Unit = {
    val system = ActorSystem("MyActors")
    val actor = system.actorOf(Props[MyActor](), "MyActor")

    actor ! "Hello"
    actor ! 42
    Thread.sleep(100)

    system.terminate()
  }

}
