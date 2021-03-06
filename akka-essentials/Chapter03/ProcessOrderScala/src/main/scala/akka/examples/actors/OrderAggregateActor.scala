package akka.examples.actors

import akka.actor.Actor
import akka.examples.OrderHistory

class OrderAggregateActor extends Actor {

  def receive: Receive = {
    case histo: OrderHistory =>
      println(s"OrderAggregateActor: Received histo $histo");
    case x =>
      println(s"OrderAggregateActor: x=$x")
  }

}
