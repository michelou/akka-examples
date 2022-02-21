package com.packt.chapter01

import akka.actor.Actor
import akka.actor.AbstractActor.Receive

class MySpecialActor extends Actor {
  override def receive: Receive = {
    case msg: String => println(s"msg is $msg")
  }
}
