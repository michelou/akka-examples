package com.packt.chapter02

import akka.actor.Actor

class PrinterActor extends Actor {

  override def preRestart(reason: Throwable, message: Option[Any]): Unit =
    println("Printer: I am restarting because of ArithmeticExcepton")

  def receive = {
    case msg: String => println(s"Printer: $msg")
    case msg: Int => 1 / 0
  }

}
