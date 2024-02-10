package com.packt.chapter02

import akka.actor.Actor

case object CreateChild
case class Greet(msg: String)

class ChildActor extends Actor {

  def receive: Receive = {
    case Greet(msg) =>
      println(s"My parent [${self.path.parent}] " +
              s"greeted to me [${self.path}] $msg")
  }

}
