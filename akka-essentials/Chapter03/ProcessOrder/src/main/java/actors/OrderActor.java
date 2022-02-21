package akka.examples.actors;

import akka.actor.UntypedAbstractActor;
import akka.examples.Order;
import java.util.HashMap;
import java.util.Map;

public class OrderActor extends UntypedAbstractActor {
    private Map<Integer, Order> orderData = new HashMap<Integer, Order>() {{
        put(1, new Order(1, "Screw driver"));
        put(2, new Order(2, "Hammer"));
        put(2, new Order(3, "Jigsaw"));
    }};

    @Override
    public void onReceive(Object message) throws Exception {
        if (message instanceof Integer) {
            Integer userId = (Integer) message;
            System.out.println("OrderActor: userId="+userId);
            sender().tell(orderData.getOrDefault(userId, Order.BAD_ORDER), null);
        }
    }

}
