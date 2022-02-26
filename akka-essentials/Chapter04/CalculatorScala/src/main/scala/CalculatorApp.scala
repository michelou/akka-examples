package akka.examples

import akka.actor.{TypedActor, TypedProps}
import akka.actor.typed.ActorSystem
import akka.actor.typed.scaladsl.Behaviors
import akka.util.Timeout
import scala.concurrent.{Await, Future}
import scala.concurrent.duration._

object CalculatorApp {

  def main(args: Array[String]): Unit = {
    val root = Behaviors.empty[Nothing]
    val _system = ActorSystem[Nothing](root, "Calculator")
    val calculator: CalculatorInt =
      TypedActor(_system).typedActorOf(TypedProps[Calculator]())
    /*
    // An actor with a non-default constructor
    val calculator: CalculatorInt =
      TypedActor(_system).typedActorOf(
        TypedProps(classOf[CalculatorInt], new Calculator("foo"))
      )
    */
    calculator.incrementCount()

    // Invoke the method and wait for result
    val future = calculator.add(14, 6)
    val result = Await.result(future, 5.seconds)

    context.system.stop(calculator)
    Thread.sleep(2000)

    _system.terminate()
  }

}
