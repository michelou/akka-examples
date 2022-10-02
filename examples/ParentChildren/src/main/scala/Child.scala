package com.alvinalexander

import akka.actor.typed.Behavior
import akka.actor.typed.scaladsl.Behaviors

object Child {

  // message that Child can handle
  sealed trait MessageToChild
  final case object HelloChild extends MessageToChild
  final case object TakeOutTheTrash extends MessageToChild
  final case object WashTheDishes extends MessageToChild

  // the factory method that lets other create new Child instances.
  // this line of code is long, so it is wrapped onto two lines here.
  def apply(): Behavior[MessageToChild] = {
    Behaviors.receive[MessageToChild] { (context, message) =>

    message match {
      case HelloChild =>
        println("Child: I’m the child. I do all the work.")
        Behaviors.same
      case TakeOutTheTrash =>
        println("Child: *sigh* I’m taking out the trash.")
        takeOutTheTrash()
        Behaviors.same
      case WashTheDishes =>
        println("Child: Yeah, yeah, I’m washing the dishes.")
        washTheDishes()
        Behaviors.same
    }
  }}

  // simulate some long-running-tasks
  private def takeOutTheTrash(): Unit = Thread.sleep(100)
  private def washTheDishes(): Unit = Thread.sleep(200)

}
