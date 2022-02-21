package com.packt.chapter01

import akka.actor.Actor

class SummingActor extends Actor {

  // state inside the actor
  var sum = 0
  
  // behaviour which is applied on the state
  override def receive: Receive = {
    case x: Int =>
      sum = sum + x
      println(s"my state as sum is $sum")
    case _ => 
      println("I don't konw what are you talking about")    
  }

}
