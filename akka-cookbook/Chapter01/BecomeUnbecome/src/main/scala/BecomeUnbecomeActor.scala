package com.packt.chapter01

import akka.actor.Actor
import akka.actor.AbstractActor.Receive

class BecomeUnbecomeActor extends Actor {

  def receive: Receive = {
    case true => context.become(isStateTrue)
    case false => context.become(isStateFalse)
    case _ => println("don't know what you want to say !!")
  }

  def isStateTrue: Receive = {
    case msg: String => println(msg)
    case false => context.become(isStateFalse)
  }

  def isStateFalse: Receive = {
    case msg: Int => println(msg)
    case true => context.become(isStateTrue)
  }

}
