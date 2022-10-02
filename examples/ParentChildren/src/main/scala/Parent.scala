package com.alvinalexander

import akka.actor.typed.{ActorRef, Behavior}
import akka.actor.typed.scaladsl.{ActorContext, Behaviors}

object Parent {

  // messages that Parent can handle
  sealed trait MessageToParent
  final case object HelloParent extends MessageToParent
  final case object TakeOutTheTrash extends MessageToParent
  final case object WashTheDishes extends MessageToParent

  // the factory method that lets other create new Parent instances
  def apply(): Behavior[MessageToParent] = Behaviors.setup {
    context: ActorContext[MessageToParent] =>

    // create two children
    val child1: ActorRef[Child.MessageToChild] = context.spawn(
      Child(), "Child_1"
    )
    val child2: ActorRef[Child.MessageToChild] = context.spawn(
      Child(), "Child_2"
    )

    // pass all the long-running work to the children
    Behaviors.receiveMessage { message: MessageToParent =>
      message match {
        case HelloParent =>
          println("Parent: Hi. I work as little as possible!")
          child1 ! Child.HelloChild
          Behaviors.same
        case TakeOutTheTrash =>
          child1 ! Child.TakeOutTheTrash
          println("Parent: LOL, the Child is taking out the trash.")
          Behaviors.same
        case WashTheDishes =>
          child2 ! Child.WashTheDishes
          println("Parent: LOL, the Child is washing the dishes.")
          Behaviors.same
      }
    }
  }

}
