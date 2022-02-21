package zzz.akka.investigation

import akka.actor.{ActorSystem, Props}

object BadShakespeareanMain {
  val system = ActorSystem("BadShakespearean")
  val actor = system.actorOf(Props[BadShakespeareanActor](), "Shakespearean")

  // We'll use this utility method to talk with our Actor
  def send(msg: String): Unit = {
    println(s"Me: $msg")
    actor ! msg
    Thread.sleep(100)
  }

  // And our driver
  def main(args: Array[String]): Unit = {
    send("Good Morning")
    send("You're terrible")
    system.terminate()
  }

}
