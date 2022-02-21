package akka.examples

import java.time.Instant

case class OrderHistory(order: Order, address: Address, timestamp: Instant = Instant.now())
