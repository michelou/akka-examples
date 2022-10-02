package com.example

import akka.actor.AbstractActor.Receive

/**
 * An optimistic actor says it is fine.
 */
trait Optimistic {
  self: MyActor =>

  def handleFine: Receive = {
    case Fine =>
      val _sender = sender()
      println(s"$name replies Good to ${_sender.path.name}")
      _sender ! Good   
  }  

}
