package com.packt.chapter02

import akka.actor.{Actor, ActorRef}

case object Error
case class StopActor(actorRef: ActorRef)

class LifeCycleActor extends Actor {
  var sum = 1

  override def preRestart(reason: Throwable, message: Option[Any]): Unit =
    println(s"sum in preRestart is $sum")

  override def preStart(): Unit =
    println(s"sum in preStart is $sum")

  def receive = {
    case Error => throw new ArithmeticException()
    case _ => println("default msg")
  }

  override def postStop(): Unit =
    println(s"sum in postStop is ${sum * 3}")

  override def postRestart(reason: Throwable): Unit = {
    sum *= 2
    println(s"sum in postRestart is $sum")
  }

}
