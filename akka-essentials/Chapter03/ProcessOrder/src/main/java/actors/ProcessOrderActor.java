package akka.examples.actors;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedAbstractActor;
import akka.dispatch.Futures;
import akka.dispatch.Mapper;
import akka.examples.Address;
import akka.examples.Order;
import akka.examples.OrderHistory;
import akka.pattern.Patterns;
import akka.util.Timeout;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;
import scala.concurrent.ExecutionContext;
import scala.concurrent.Future;
import scala.concurrent.duration.Duration;

public class ProcessOrderActor extends UntypedAbstractActor {

    final Timeout timeout = new Timeout(Duration.create(5, TimeUnit.SECONDS));
    ActorRef orderActor = getContext()
        .actorOf(Props.create(OrderActor.class));
    ActorRef addressActor = getContext()
        .actorOf(Props.create(AddressActor.class));
    ActorRef orderAggregateActor = getContext()
        .actorOf(Props.create(OrderAggregateActor.class));

    @Override
    public void onReceive(Object message) throws Exception {
        if (message instanceof Integer) {
            Integer userId = (Integer) message;
            final ArrayList<Future<Object>> futures =
                new ArrayList<Future<Object>>();
            //make concurrent calls to actors
            futures.add(Patterns.ask(orderActor, userId, timeout));
            futures.add(Patterns.ask(addressActor, userId, timeout));
            //set the sequence in which the reply are expected
            final ExecutionContext ec = getContext().dispatcher();
            final Future<Iterable<Object>> aggregate =
                Futures.sequence(futures, ec);
            //once the replies comes back, we loop through the
            // Iterable to get the replies in same order
            final Future<OrderHistory> aggResult = aggregate.map(
                new Mapper<Iterable<Object>, OrderHistory>() {
                    public OrderHistory apply(Iterable<Object> coll) {
                        final Iterator<Object> it = coll.iterator();
                        final Order order = (Order) it.next();
                        final Address address = (Address) it.next();
                        return new OrderHistory(order, address);
                    }
                },
                ec
            );
            //aggregated result is piped to another actor
            Patterns.pipe(aggResult, ec).to(orderAggregateActor);
        }
    }

}
