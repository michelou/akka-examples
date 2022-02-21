package com.packt.chapter01

import akka.actor.{Actor, ActorRef, ActorSystem, Props}
import akka.dispatch.{Envelope, MailboxType, MessageQueue, ProducesMessageQueue, UnboundedMailbox}
import com.typesafe.config.Config
import java.util.concurrent.ConcurrentLinkedQueue

class MyMessageQueue extends MessageQueue {
  private final val queue = new ConcurrentLinkedQueue[Envelope]()

  def enqueue(receiver: ActorRef, handle: Envelope): Unit = {
    if (handle.sender.path.name == "MyActor") {
      handle.sender ! "Hey dude, How are you? I know your name, processing your request"
      queue.offer(handle)
    }
    else
      handle.sender ! "I don't talk to strangers, I can't process your request"
  }

  def dequeue(): Envelope = queue.poll
  def numberOfMessages: Int = queue.size
  def hasMessages: Boolean = !queue.isEmpty

  def cleanUp(owner: ActorRef, deadLetters: MessageQueue): Unit = {
    while (hasMessages) {
      deadLetters.enqueue(owner, dequeue())
    }
  }

}

class MyUnboundedMailbox extends MailboxType with ProducesMessageQueue[MyMessageQueue] {

  def this(settings: ActorSystem.Settings, confi: Config) = this()

  final override def create(owner: Option[ActorRef], system: Option[ActorSystem]): MessageQueue =
    new UnboundedMailbox.MessageQueue()

}
