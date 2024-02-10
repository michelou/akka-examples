package com.packt.chapter02

import akka.actor.{Actor, Props}

class ParentActor extends Actor {

  def receive: Receive = {
    case CreateChild =>
      val child = context.actorOf(Props[ChildActor](), "child")
      child ! Greet("Hello Child")
  }

}
