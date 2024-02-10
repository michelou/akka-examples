package com.packt.chapter02

import akka.actor.{Actor, OneForOneStrategy, Props}
import akka.actor.SupervisorStrategy.{Escalate, Restart}

class SupervisorActor extends Actor {

  override val supervisorStrategy = OneForOneStrategy(maxNrOfRetries = 10) {
    case _: ArithmeticException =>
      Restart
    case t =>
      super.supervisorStrategy.decider.applyOrElse(t, (_: Any) => Escalate)
  }

  def receive: Receive = {
    case (props: Props, name: String) =>
      sender() ! context.actorOf(props, name)
    case StopActor(actorRef) =>
      context.stop(actorRef)
  }

}
