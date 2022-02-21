package com.example

import akka.actor.typed.ActorSystem

object AkkaQuickstart {

  def main(args: Array[String]): Unit = {
    val greeterMain: ActorSystem[GreeterMain.SayHello] =
      ActorSystem(GreeterMain(), "AkkaQuickStart")

    greeterMain ! GreeterMain.SayHello("Charles")

    terminate(greeterMain)
  }

  private def terminate[T](system: ActorSystem[T]): Unit =
    try {
      Thread.sleep(1000)
      println(">>> Press ENTER to exit <<<")
      System.in.read()
    }
    catch {
      case _: java.io.IOException => /* ignored */
    }
    finally {
      system.terminate()
    }

}
