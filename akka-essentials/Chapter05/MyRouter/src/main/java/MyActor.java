package akka.examples;

import akka.actor.AbstractActor;

public class MyActor extends AbstractActor {
    
    public Receive createReceive() {
        return receiveBuilder()
            .match(Object.class, d -> {
                System.out.println("MyActor: d=" + d);
            })
            .build();
    }

}