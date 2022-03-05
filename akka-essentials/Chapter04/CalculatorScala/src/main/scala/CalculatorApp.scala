package akka.examples

import akka.actor.TypedProps
import akka.actor.typed.ActorSystem
import akka.actor.typed.scaladsl.Behaviors
import akka.util.Timeout
import scala.concurrent.Await
import scala.concurrent.duration._

object CalculatorApp {

  def main(args: Array[String]): Unit = {
    //val root = Behaviors.empty[Nothing]
    //val _system = ActorSystem[Nothing](root, "Calculator")
    val _typedSystem = ActorSystem[Nothing](Calculator(), "Calculator")
    val calculator: CalculatorInt =
      _typedSystem.actorOf(TypedProps[Calculator]())

    calculator.incrementCount()

    // Invoke method 'add' and wait for result
    val future = calculator.add(14, 6)
    val result = Await.result(future, 5.seconds)

    //_typedSystem.stop(calculator)

    terminate(_typedSystem)
  }

  private def terminate(system: ActorSystem[Nothing]): Unit =
    try {
      Thread.sleep(1000)
      println(">>> Press ENTER to exit <<<")
      System.in.read()
    }
    catch {
      case _: /*Interrupted,IO*/Exception => /* ignored */
    }
    finally {
      system.terminate()
    }

}
