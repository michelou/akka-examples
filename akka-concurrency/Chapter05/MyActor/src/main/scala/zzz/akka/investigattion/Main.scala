package zzz.akka.investigation

import akka.actor.{ActorSystem, Props}

object Main {

  def main(args: Array[String]): Unit = {
    val system = ActorSystem("MyActors")
    val actor = system.actorOf(Props[MyActor](), "MyActor")

    actor ! "Hello"
    actor ! 42

    terminate(system)
  }

  /**
   * Terminates the actor system ignoring the checked exceptions.
   */
  private def terminate(system: ActorSystem): Unit =
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
