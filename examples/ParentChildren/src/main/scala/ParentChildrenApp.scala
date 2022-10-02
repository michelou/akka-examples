package com.alvinalexander

import akka.actor.typed.ActorSystem

object ParentChildrenApp {

  def main(args: Array[String]): Unit = {
    val actorSystem: ActorSystem[Parent.MessageToParent] = ActorSystem(
      Parent(),
      "ParentChildSystem"
    )

    actorSystem ! Parent.HelloParent
    actorSystem ! Parent.TakeOutTheTrash
    actorSystem ! Parent.WashTheDishes
    Thread.sleep(500)

    // shut down the system
    actorSystem.terminate()

  }

}
