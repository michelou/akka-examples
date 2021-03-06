package akka.examples;

import akka.actor.ActorRef;
import akka.actor.TypedActor;
import akka.dispatch.Futures;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import akka.japi.Option;
import scala.concurrent.Future;

public class Calculator implements TypedActor.Receiver, CalculatorInt {
    Integer counter = 0;

    //Non-blocking request response
    public Future<Integer> add(Integer first, Integer second) {
        return Futures.successful(first + second); // TypedActor.dispatcher());
    }

    //Non-blocking request response
    public Future<Integer> subtract(Integer first, Integer second) {
        return Futures.successful(first - second); // TypedActor.dispatcher());
    }

    //fire and forget
    public void incrementCount() {
        counter++;
    }

    //Blocking request response
    public Option<Integer> incrementAndReturn() {
        return Option.some(++counter);
    }

    LoggingAdapter log = Logging.getLogger(TypedActor.context().system(), this);

    public void onReceive(Object msg, ActorRef actor) {
        log.info("Calculator: Received Message -> {}", msg);
    }

    //Allows to tap into the Actor PreStart hook
    public void preStart() {
        log.info("Actor Started !");
    }

    //Allows to tap into the Actor PostStop hook
    public void postStop() {
        log.info("Actor Stopped !");
    }

}
