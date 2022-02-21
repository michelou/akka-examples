package com.packt.chapter02

import akka.actor.Actor

case object CreateChild
case class Greet(msg: String)

class ChildActor extends Actor {

  def receive = {
    case Greet(msg) =>
      println(s"My parent ]${self.path.parent}] greeted to me [${self.path} ] $msg")
  }

}
