package org.jamieallen.effectiveakka.pattern.cameo

import akka.event.LoggingReceive
import org.jamieallen.effectiveakka.common._

class SavingsAccountsActor extends SavingsAccountsProxy {
  val accountData = Map[Long, List[(Long, BigDecimal)]](
    1L -> (List((1, 150000), (2, 29000))),
    2L -> (List((5, 80000)))
  )

  def receive = LoggingReceive {
    case GetCustomerAccountBalances(id: Long) =>
      println(s"SavingsAccounts    : Received GetCustomerAccountBalances for ID: $id")
      accountData.get(id) match {
        case Some(data) => sender() ! SavingsAccountBalances(Some(data))
        case None => sender() ! SavingsAccountBalances(Some(List()))
      }
  }

}
