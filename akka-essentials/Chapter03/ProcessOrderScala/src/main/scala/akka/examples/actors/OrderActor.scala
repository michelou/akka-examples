package akka.examples.actors

import akka.actor.Actor
import akka.examples.Order

class OrderActor extends Actor {

  private val orderData: Map[Int, Order] = Map(
     1000 -> Order("Screw driver"),
     2000 -> Order("Hammer"),
     3000 -> Order("Jigsaw")
  )

  def receive: Receive = {
    case userId: Int =>
      println(s"OrderActor: userId=$userId")
      sender() ! orderData.getOrElse(userId, Order.BAD_ORDER)
  }

}
