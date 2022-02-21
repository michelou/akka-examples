package akka.examples.actors

import akka.actor.Actor
import akka.examples.Order

class OrderActor extends Actor {

  private val orderData: Map[Int, Order] = Map(
     1 -> Order(1, "Screw driver"),
     2 -> Order(2, "Hammer"),
     3 -> Order(3, "Jigsaw")
  )

  def receive: Receive = {
    case userId: Int =>
      println(s"OrderActor: userId=$userId")
      sender() ! orderData.getOrElse(userId, Order.BAD_ORDER)
  }

}
