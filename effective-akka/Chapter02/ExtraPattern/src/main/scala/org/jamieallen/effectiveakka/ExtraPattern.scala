package org.jamieallen.effectiveakka

import akka.actor.{ActorSystem, Props}
import org.jamieallen.effectiveakka.common._
import org.jamieallen.effectiveakka.pattern.extra._

object ExtraPattern {

  def main(args: Array[String]): Unit = {
    val system = ActorSystem("ExtraPattern")

    val savingsAccountsActor = system.actorOf(
      Props[SavingsAccountsActor](),
      "extra-success-savings"
    )
    val checkingAccountsActor = system.actorOf(
      Props[CheckingAccountsActor](),
      "extra-success-checkings"
    )
    val moneyMarketAccountsActor = system.actorOf(
      Props[MoneyMarketAccountsActor](),
      "extra-success-money-markets"
    )
    val accountBalanceRetriever = system.actorOf(
      Props(new AccountBalanceRetriever(
        savingsAccountsActor,
        checkingAccountsActor,
        moneyMarketAccountsActor)
      ),
      "extra-retriever"
    )

    accountBalanceRetriever ! GetCustomerAccountBalances(1L)
    accountBalanceRetriever ! GetCustomerAccountBalances(2L)

    terminate(system)
  }

  /**
   * Terminates the actor system ignoring the checked exceptions.
   */
  private def terminate(actor: ActorSystem): Unit =
    try {
      Thread.sleep(1000)
      println(">>> Press ENTER to exit <<<")
      System.in.read()
    }
    catch {
      case _: /*Interrupted,IO*/Exception => /* ignored */
    }
    finally {
      actor.terminate()
    }

}
