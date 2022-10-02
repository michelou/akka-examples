package com.example

import akka.actor.AbstractActor.Receive

/**
 * A pessimistic actor says it is not fine.
 */
trait Pessimistic {
  this: MyActor =>

  def handleFine: Receive = {
    case Fine =>
      val _sender = sender()
      println(s"$name replies Bad to ${_sender.path.name}")
      _sender ! Bad
  }  

}
