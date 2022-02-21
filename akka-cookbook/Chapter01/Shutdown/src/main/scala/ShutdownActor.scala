package com.packt.chapter01

import akka.actor.Actor
import akka.actor.AbstractActor.Receive

case object Stop

class ShutdownActor extends Actor {

  override def receive: Receive = {
    case msg: String => println(msg)
    case Stop => context.stop(self)
  }

}
