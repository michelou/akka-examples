package akka.first.app.mapreduce.actors;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.first.app.mapreduce.messages.MapData;
import akka.first.app.mapreduce.messages.ReduceData;
import akka.first.app.mapreduce.messages.Result;
import akka.routing.RoundRobinPool;

/**
 * The Master actor is responsible for the instantiation of the child actors.
 * The Master actor is the gateway for all messages that are passed on to the
 * other actors, namely the Map actor and Aggregate actor.
 */
public class MasterActor extends AbstractActor {

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

    public Receive createReceive() {
        return receiveBuilder()
            .match(String.class, word -> {
                mapActor.tell(word, getSelf());
            })
            .match(MapData.class, mapData -> {
                reduceActor.tell(mapData, getSelf());
            })
            .match(ReduceData.class, reduceData -> {
                aggregateActor.tell(reduceData, getSelf());
            })
            .match(Result.class, result -> {
                aggregateActor.forward(result, getContext());
            })
            .match(Object.class, message -> {
                unhandled(message);
            })
            .build();
    }

}
