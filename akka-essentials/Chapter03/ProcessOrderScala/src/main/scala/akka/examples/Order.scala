package akka.examples

case class Order(val description: String) {
  val orderId: Long = Order.currId()
}

object Order {
  private var id = -1L
  private def currId(): Long = { val id0 = id; id += 1; id0 }
  val BAD_ORDER = Order("")
}
