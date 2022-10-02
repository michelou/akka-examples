package com.example

import akka.actor.Actor
import scala.util.Random

sealed trait Move
case object StartMessage extends Move
case object StopMessage extends Move
case object Fine extends Move
case object Good extends Move
case object Bad extends Move

abstract class MyActor(val others: List[String]) extends Actor { 
  //its name
  val name = self.path.name

  // It knows to choose on interlocutor.
  val interlocutor = Random.shuffle(others.filterNot(_ == name)).head

  // All the actors are able to interpret the start/stop messages.
  def metaReceive: Receive = {
    case StartMessage => //start to ask question
      println(s"$name send Fine to $interlocutor")
      context.actorSelection("../"+interlocutor) ! Fine
    case StopMessage =>
      println(s"$name stops")
      context.stop(self)
  }

}

class MyOptimisticActor(others: List[String]) extends MyActor(others) with Optimistic {
  override def receive = metaReceive orElse handleFine //orElse ...
}

class MyPessimisticActor(others: List[String]) extends MyActor(others) with Pessimistic {
  override def receive = metaReceive orElse handleFine //orElse ...
}
class MyInterpretorActor(others: List[String]) extends MyActor(others) with Interpretor {
  override def receive = metaReceive orElse handleAnswer
}
