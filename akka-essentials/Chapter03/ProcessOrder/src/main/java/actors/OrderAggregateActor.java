package akka.examples.actors;

import akka.actor.UntypedAbstractActor;
import akka.examples.OrderHistory;

public class OrderAggregateActor extends UntypedAbstractActor {

    @Override
    public void onReceive(Object message) throws Exception {
        if (message instanceof OrderHistory) {
            OrderHistory histo = (OrderHistory) message;
            System.out.println("OrderAggregate: Received histo");
        }
        else {
            System.out.println("OrderAggregate: "+message.getClass().getName());   
        }
    }

}
