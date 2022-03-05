package org.jamieallen.effectiveakka.pattern.cameo

import akka.actor.{Actor, ActorRef}
import org.jamieallen.effectiveakka.common._

class AccountBalanceRetriever(
    savingsAccounts: ActorRef,
    checkingAccounts: ActorRef,
    moneyMarketAccounts: ActorRef) extends Actor {

  def receive = {
    case message @ GetCustomerAccountBalances(id) =>
      //val originalSender = Some(sender())
      // I'm now using a factory method now from the companion object above!
      val handler = context.actorOf(
        AccountBalanceResponseHandler.props(savingsAccounts,
          checkingAccounts,
          moneyMarketAccounts,
          sender()
        ),
        s"cameo-message-handler-$id"
      )
      savingsAccounts.tell(message, handler)
      checkingAccounts.tell(message, handler)
      moneyMarketAccounts.tell(message, handler)
  }

}
