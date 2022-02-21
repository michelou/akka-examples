package akka.examples.actors;

import akka.actor.UntypedAbstractActor;
import akka.examples.Address;
import java.util.HashMap;
import java.util.Map;

public class AddressActor extends UntypedAbstractActor {
    private Map<Integer, Address> orderData = new HashMap<Integer, Address>() {{
        // https://fr.hotels.com/go/usa/famous-streets-los-angeles
        put(1000, new Address("Los Angeles", "Rodeo Drive", 10));
        put(1001, new Address("Los Angeles", "Hollywood Boulevard", 51));
        put(1002, new Address("Los Angeles", "Sunset Boulevard", 2));
        // https://fr.hotels.com/go/usa/most-popular-streets-new-york
        put(2000, new Address("New York", "Broadway", 101));
        put(2001, new Address("New York", "Park Avenue", 1));
        // https://fr.hotels.com/go/usa/most-popular-streets-denver
        put(3000, new Address("Denver", "East Colfax Avenue", 3));
        put(3001, new Address("Denver", "South Broadway", 22));
        put(3002, new Address("Denver", "Larimer Street", 9));
    }};

    @Override
    public void onReceive(Object message) throws Exception {
        if (message instanceof Integer) {
            Integer userId = (Integer) message;
        }
    }

}
