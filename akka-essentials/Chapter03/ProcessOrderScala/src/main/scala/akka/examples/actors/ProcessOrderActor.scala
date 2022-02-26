package akka.examples.actors

import akka.actor.{Actor, Props}
import akka.examples.{Address, Order, OrderHistory}
import akka.pattern.pipe
import akka.pattern.Patterns.ask
import akka.util.Timeout
import scala.concurrent.ExecutionContext
import scala.concurrent.Future
import scala.concurrent.duration._

class ProcessOrderActor extends Actor {
  implicit val timeout = Timeout(5.seconds)
  implicit val ec = ExecutionContext.global

  val orderActor = context.actorOf(Props[OrderActor]())
  val addressActor = context.actorOf(Props[AddressActor]())
  val orderAggregateActor = context.actorOf(Props[OrderAggregateActor]())

  def receive = {
    case userId: Integer =>
      val aggResult: Future[OrderHistory] =
        for {
          // call pattern directly
          order <- ask(orderActor, userId, timeout).mapTo[Order]
          // call by implicit conversion
          address <- ask(addressActor, userId, timeout).mapTo(manifest[Address])
        } yield OrderHistory(order, address)
      aggResult pipeTo orderAggregateActor
      // pipe(aggResult).to(orderAggregateActor)
  }

}
