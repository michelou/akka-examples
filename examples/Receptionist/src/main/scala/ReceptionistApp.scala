package akka.examples

import akka.actor.typed.ActorSystem
//import akka.actor.typed.scaladsl.{AbstractBehavior ,ActorContext, Behaviors}
//import akka.actor.typed.receptionist.{Receptionist, ServiceKey}
//import java.util.Scanner

object ReceptionistApp {

  def main(args: Array[String]): Unit = {
    val supervisor: ActorSystem[Supervisor.SupervisorMessage] = ActorSystem(
        Supervisor(),
        "Supervisor"
    )

    // send the Start message to the Supervisor
    supervisor ! Supervisor.Start

    // wait a few moments, and then stop the Supervisor
    Thread.sleep(200)
    supervisor.terminate()
  }

}
