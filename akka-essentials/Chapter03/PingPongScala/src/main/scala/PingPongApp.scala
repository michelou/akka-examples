package akka.examples

import akka.actor.{ActorSystem, Props}

object PingPongApp {

  def main(args: Array[String]): Unit = {
    val _system = ActorSystem("PingPong")
    val actor = _system.actorOf(Props[PingPongActor](), "pingPongActor")
    actor ! PING

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
