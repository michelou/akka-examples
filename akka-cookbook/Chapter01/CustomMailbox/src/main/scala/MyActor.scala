package com.packt.chapter01

import akka.actor.{Actor, ActorRef}
import akka.actor.AbstractActor.Receive

class MyActor extends Actor {
  override def receive: Receive = {
    case (msg: String, actorRef: ActorRef) => actorRef ! msg
    case msg => println(msg)
  }
}
