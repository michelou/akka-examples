package akka.examples

import akka.actor.Actor

case class PING()
case class PONG()

class PingPongActor extends Actor {
  import context._ // become, unbecome, stop
  var count = 0

  def receive: Receive = {
    case PING =>
      println("PING")
      count += 1
      Thread.sleep(100)
      self ! PONG
      become {
        case PONG =>
          println("PONG")
          count += 1
          Thread.sleep(100)
          self ! PING
          // restores the original message handling loop for PING messages.
          unbecome()
      }
      if (count > 10) stop(self)
  }

}
