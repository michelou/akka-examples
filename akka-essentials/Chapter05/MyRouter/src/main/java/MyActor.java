package akka.examples;

import akka.actor.AbstractActor;

public class MyActor extends AbstractActor {
    
    public Receive createReceive() {
        return receiveBuilder()
            .matchAny(message -> {
                System.out.println("MyActor: message=" + message);
            })
            .build();
    }

}