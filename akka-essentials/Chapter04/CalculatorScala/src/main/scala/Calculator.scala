package akka.examples

import akka.actor.TypedActor
import akka.dispatch.Futures
import akka.event.Logging
import scala.concurrent.{Future, Promise}

class Calculator extends CalculatorInt {
  var counter: Int = 0

  def add(first: Int, second: Int): Future[Int] =
    Futures.successful(first + second)

  def subtract(first: Int, second: Int): Future[Int] =
    Futures.successful(first - second)

  def incrementCount(): Unit = counter += 1

  def incrementAndReturn(): Option[Int] = {
    counter += 1
    Some(counter)
  }

  val log = Logging(TypedActor.context.system, TypedActor.self.getClass())

  def preStart(): Unit =
    log.info ("Actor Started")

  def postStop(): Unit =
    log.info ("Actor Stopped")

}
