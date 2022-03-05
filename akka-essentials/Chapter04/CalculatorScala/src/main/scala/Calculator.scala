package akka.examples

import akka.actor.ActorSystem//TypedActor
import akka.actor.typed.Behavior
import akka.actor.typed.scaladsl.{AbstractBehavior, ActorContext, Behaviors}
import akka.dispatch.Futures
//import akka.event.Logging
//import akka.event.LogSource
import scala.concurrent.Future

object Calculator {
  def apply(): Behavior[Nothing] =
    Behaviors.setup[Nothing](context => new Calculator(context))
}

class Calculator(context: ActorContext[Nothing])
    extends AbstractBehavior[Nothing](context) with CalculatorInt {

  private var counter: Int = 0

  def add(first: Int, second: Int): Future[Int] =
    Futures.successful(first + second)

  def subtract(first: Int, second: Int): Future[Int] =
    Futures.successful(first - second)

  def incrementCount(): Unit = counter += 1

  def incrementAndReturn(): Option[Int] = {
    counter += 1
    Some(counter)
  }

  //private val log = Logging(ActorSystem("Calculator"), this)

  def preStart(): Unit =
    context.log.info ("Actor Started")

  def postStop(): Unit =
    context.log.info ("Actor Stopped")

  override def onMessage(msg: Nothing): Behavior[Nothing] = {
    // no need to handle any messages
    Behaviors.unhandled
  }

}
