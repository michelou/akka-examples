package akka.examples

import akka.actor.{ActorRef, ActorSystem, Props}
import akka.examples.actors.ProcessOrderActor

object ProcessOrderApp {

  def main(args: Array[String]): Unit = {
    val _system = ActorSystem("ProcessOrder")
    val master = _system.actorOf(Props[ProcessOrderActor](), "master")
    val userId = 1000;
    master ! userId

    terminate(_system)
  }

  private def terminate(system: ActorSystem): Unit =
    try {
      Thread.sleep(2000)
      println(">>> Press ENTER to exit <<<")
      System.in.read()
    }
    catch {
      case _: Exception => /* ignored */
    }
    finally {
      system.terminate()
    }

}
