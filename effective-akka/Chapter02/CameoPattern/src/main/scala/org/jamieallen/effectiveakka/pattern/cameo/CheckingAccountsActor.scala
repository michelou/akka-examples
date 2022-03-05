package org.jamieallen.effectiveakka.pattern.cameo

import akka.event.LoggingReceive
import org.jamieallen.effectiveakka.common._

class CheckingAccountsActor extends CheckingAccountsProxy {
  val accountData = Map[Long, List[(Long, BigDecimal)]](
    1L -> List((3, 15000)),
    2L -> List((6, 640000), (7, 1125000), (8, 40000))
  )

  def receive = LoggingReceive {
    case GetCustomerAccountBalances(id: Long) =>
      println(s"CheckingAccounts   : Received GetCustomerAccountBalances for ID: $id")
      accountData.get(id) match {
        case Some(data) => sender() ! CheckingAccountBalances(Some(data))
        case None => sender() ! CheckingAccountBalances(Some(List()))
      }
  }

}
