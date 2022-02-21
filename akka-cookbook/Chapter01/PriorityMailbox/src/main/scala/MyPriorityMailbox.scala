package com.packt.chapter01

import akka.actor.{Actor, ActorSystem}
import akka.dispatch.{PriorityGenerator, UnboundedPriorityMailbox}
import com.typesafe.config.Config

/**
 * A PriorityGenerator is a convenience API to create a Comparator that
 * orders the messages of a PriorityDispatcher.
 */
class MyPriorityMailbox(settings: ActorSystem.Settings, config: Config)
    extends UnboundedPriorityMailbox(PriorityGenerator {
      case x: Int => 1
      case x: String => 0
      case x: Long => 2
      case _ => 3
    })
