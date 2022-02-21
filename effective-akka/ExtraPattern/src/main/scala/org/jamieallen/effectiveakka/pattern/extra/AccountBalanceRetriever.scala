package org.jamieallen.effectiveakka.pattern.extra

import akka.actor.{Actor, ActorLogging, ActorRef, Props}
import akka.event.LoggingReceive
import org.jamieallen.effectiveakka.common._
import scala.concurrent.ExecutionContext
import scala.concurrent.duration.Duration

object AccountBalanceRetriever {
  case object AccountRetrievalTimeout
}

class AccountBalanceRetriever(
    savingsAccounts: ActorRef,
    checkingAccounts: ActorRef,
    moneyMarketAccounts: ActorRef
) extends Actor with ActorLogging {
  import AccountBalanceRetriever._

  def receive = LoggingReceive {
    case GetCustomerAccountBalances(id) => {
      val originalSender = sender()
      log.debug(s"Received GetCustomerAccountBalances for ID: $id from $originalSender")

      context.actorOf(Props(new Actor() {
        var checkingBalances, savingsBalances, mmBalances: Option[List[(Long, BigDecimal)]] = None
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
            sendResponseAndShutdown(AccountRetrievalTimeout)
        }

        def collectBalances = (checkingBalances, savingsBalances, mmBalances) match {
          case (Some(c), Some(s), Some(m)) =>
            log.debug(s"Values received for all three account types")
            timeoutMessager.cancel()
            sendResponseAndShutdown(AccountBalances(checkingBalances, savingsBalances, mmBalances))
          case _ =>
        }

        def sendResponseAndShutdown(response: Any) = {
          originalSender ! response
          log.debug("Stopping context capturing actor")
          context.stop(self)
        }

        savingsAccounts ! GetCustomerAccountBalances(id)
        checkingAccounts ! GetCustomerAccountBalances(id)
        moneyMarketAccounts ! GetCustomerAccountBalances(id)

        import context.dispatcher
        val duration = Duration(250, "milliseconds")
        val timeoutMessager = context.system.scheduler.scheduleOnce(duration, self, AccountRetrievalTimeout)
      }))
    }
  } // receive

}
