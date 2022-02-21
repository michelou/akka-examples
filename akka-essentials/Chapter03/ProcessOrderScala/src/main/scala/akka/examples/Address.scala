package akka.examples

case class Address (val city: String, val street: String, val number: Int)

object Address {
  val BAD_ADDRESS = Address("", "", -1)
}
