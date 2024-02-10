package com.packt.chapter02

import akka.actor.Actor

case object CreateChild
case class DoubleValue(x: Int)
case class Response(x: Int)
case object Send

class DoubleActor extends Actor {

  def receive: Receive = {
    case DoubleValue(number) =>
      println(s"${self.path.name} Got the number $number")
      sender() ! Response(number * 2)
  }

}
