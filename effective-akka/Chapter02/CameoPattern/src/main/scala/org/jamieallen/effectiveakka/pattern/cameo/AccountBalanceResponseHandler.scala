package org.jamieallen.effectiveakka.pattern.cameo

import akka.actor.{Actor, ActorLogging, ActorRef, Props}
import akka.event.LoggingReceive
import scala.concurrent.ExecutionContext
import scala.concurrent.duration._
import org.jamieallen.effectiveakka.common._

object AccountBalanceResponseHandler {

  case object AccountRetrievalTimeout

  // Factory method for our actor Props
  def props(savingsAccounts: ActorRef,
            checkingAccounts: ActorRef,
            moneyMarketAccounts: ActorRef,
            originalSender: ActorRef): Props = Props(
    new AccountBalanceResponseHandler(
      savingsAccounts, checkingAccounts,
      moneyMarketAccounts, originalSender)
  )

}

class AccountBalanceResponseHandler(
    savingsAccounts: ActorRef,
    checkingAccounts: ActorRef,
    moneyMarketAccounts: ActorRef,
    originalSender: ActorRef) extends Actor with ActorLogging {
  import AccountBalanceResponseHandler._

  var checkingBalances,
      savingsBalances,
      mmBalances: Option[List[(Long, BigDecimal)]] = None

  def receive = LoggingReceive {
    case CheckingAccountBalances(balances) =>
      log.debug(s"Received checking account balances: $balances")
      checkingBalances = balances
      collectBalances
    case SavingsAccountBalances(balances) =>
      log.debug(s"Received savings account balances: $balances")
      savingsBalances = balances
      collectBalances
    case MoneyMarketAccountBalances(balances) =>
      log.debug(s"Received money market account balances: $balances")
      mmBalances = balances
      collectBalances
    case AccountRetrievalTimeout =>
      log.debug("Timeout occurred")
      sendResponseAndShutdown(AccountRetrievalTimeout)
  }

  def collectBalances =
   (checkingBalances, savingsBalances, mmBalances) match {
      case (Some(c), Some(s), Some(m)) =>
        log.debug(s"Values received for all three account types")
        timeoutMessager.cancel()
        sendResponseAndShutdown(
          AccountBalances(checkingBalances, savingsBalances, mmBalances)
        )
      case _ =>
   }

  def sendResponseAndShutdown(response: Any) = {
    originalSender ! response
    log.debug("Stopping context capturing actor")
    context.stop(self)
  }

  import context.dispatcher
  val timeoutMessager = context.system.scheduler.
    scheduleOnce(250 milliseconds) {
      self ! AccountRetrievalTimeout
    }

}
