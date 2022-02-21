package akka.examples.actors

import akka.actor.Actor
import akka.examples.Address

class AddressActor extends Actor {

  private val addressData: Map[Int, Address] = Map(
    // https://fr.hotels.com/go/usa/famous-streets-los-angeles
    1000 -> Address("Los Angeles", "Rodeo Drive", 10),
    1001 -> Address("Los Angeles", "Hollywood Boulevard", 51),
    1002 -> Address("Los Angeles", "Sunset Boulevard", 2),
    // https://fr.hotels.com/go/usa/most-popular-streets-new-york
    2000 -> Address("New York", "Broadway", 101),
    2001 -> Address("New York", "Park Avenue", 1),
    // https://fr.hotels.com/go/usa/most-popular-streets-denver
    3000 -> Address("Denver", "East Colfax Avenue", 3),
    3001 -> Address("Denver", "South Broadway", 22),
    3002 -> Address("Denver", "Larimer Street", 9)
  )

  def receive: Receive = {
    case userId: Int =>
      println(s"AddressActor: userId=$userId")
      sender() ! addressData.getOrElse(userId, Address.BAD_ADDRESS)
  }

}
