package com.packt.chapter02

import akka.actor.{Actor, OneForOneStrategy, Props}
import akka.actor.SupervisorStrategy.{Escalate, Restart, Resume, Stop}
import scala.concurrent.duration.Duration

class SupervisorActor extends Actor {
  override val supervisorStrategy =
    OneForOneStrategy(maxNrOfRetries = 10, withinTimeRange = Duration(1, "minutes")) {
      case _: ArithmeticException => Restart
      case _: NullPointerException => Resume
      case _: IllegalArgumentException => Stop
      case _: Exception => Escalate
    }
  val printer = context.actorOf(Props[PrinterActor]())
  val intAdder = context.actorOf(Props[IntAdderActor]())

  def receive = {
    case "Start" =>
      printer ! "Hello printer"
      printer ! 1
      intAdder ! 10
      intAdder ! 10
      intAdder ! "Hello int adder"
  }

}
