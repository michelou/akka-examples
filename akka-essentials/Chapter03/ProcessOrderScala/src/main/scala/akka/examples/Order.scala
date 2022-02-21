package akka.examples

case class Order(val orderId: Int, val description: String)

object Order {
  val BAD_ORDER = Order(-1, "")
}
