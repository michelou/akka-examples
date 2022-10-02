package com.example

/**
 * An interpretor is an actor which is able to understand the reply.
 */
trait Interpretor {
  this: MyActor =>

  def handleAnswer: Receive = {
    case Good =>
      val _sender = sender()
      println(s"$name receives Good from ${_sender.path.name}")
      _sender ! StopMessage
    case Bad =>
      val _sender = sender()
      println(s"$name receives Bad from ${_sender.path.name}")
      _sender ! StopMessage
  }

}
