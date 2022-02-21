package akka.first.app.mapreduce.actors;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedAbstractActor;
import akka.first.app.mapreduce.messages.MapData;
import akka.first.app.mapreduce.messages.ReduceData;
import akka.first.app.mapreduce.messages.Result;
import akka.routing.RoundRobinPool;

/**
 * The Master actor is responsible for the instantiation of the child actors.
 * The Master actor is the gateway for all messages that are passed on to the
 * other actors, namely the Map actor and Aggregate actor.
 */
public class MasterActor extends UntypedAbstractActor {

    ActorRef mapActor = getContext().actorOf(
        Props.create(MapActor.class).withRouter(new RoundRobinPool(5)),
        "map"
    );
    ActorRef reduceActor = getContext().actorOf(
        Props.create(ReduceActor.class).withRouter(new RoundRobinPool(5)),
        "reduce"
    );
    ActorRef aggregateActor = getContext().actorOf(
        Props.create(AggregateActor.class),
        "aggregate"
    );

    @Override
    public void onReceive(Object message) throws Exception {
        if (message instanceof String) {
            mapActor.tell(message, getSelf());
        } else if (message instanceof MapData) {
            reduceActor.tell(message, getSelf());
        } else if (message instanceof ReduceData) {
            aggregateActor.tell(message, getSelf());
        } else if (message instanceof Result) {
            aggregateActor.forward(message, getContext());
        } else
            unhandled(message);
    }

}
