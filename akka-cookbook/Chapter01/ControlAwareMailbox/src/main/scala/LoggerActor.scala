package com.packt.chapter01

import akka.actor.{Actor, Props}
import akka.dispatch.ControlMessage

case object MyControlMessage extends ControlMessage

class LoggerActor extends Actor {

  def receive = {
    case MyControlMessage =>
      println("Oh, I have to process control message first")
    case x =>
      println(x)    
  }

}
