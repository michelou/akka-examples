package com.packt.chapter02

import akka.actor.Actor

class IntAdderActor extends Actor {
  var sum = 0

  def receive: Receive = {
    case x: Int =>
      sum += x
      println(s"IntAdder: sum is $sum")
    case msg: String =>
      throw new IllegalArgumentException
  }

  override def postStop(): Unit =
    println("IntAdder: I am getting stopped because I got a string message")

}
