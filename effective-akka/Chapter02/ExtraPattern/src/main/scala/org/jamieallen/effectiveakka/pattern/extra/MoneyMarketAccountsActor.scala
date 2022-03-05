package org.jamieallen.effectiveakka.pattern.extra

import akka.event.LoggingReceive
import org.jamieallen.effectiveakka.common._

class MoneyMarketAccountsActor extends MoneyMarketAccountsProxy {
  val accountData = Map[Long, List[(Long, BigDecimal)]](
    2L -> List((9, 640000), (10, 1125000), (11, 40000))
  )

  def receive = LoggingReceive {
    case GetCustomerAccountBalances(id: Long) =>
      println(s"MoneyMarketAccounts: Received GetCustomerAccountBalances for ID: $id")
      accountData.get(id) match {
        case Some(data) => sender() ! MoneyMarketAccountBalances(Some(data))
        case None => sender() ! MoneyMarketAccountBalances(Some(List()))
      }
  }

}
