package akka.examples;

import akka.actor.AbstractActor;

public class MsgEchoActor extends AbstractActor {
    
    public Receive createReceive() {
        return receiveBuilder()
            .match(Object.class, d -> {
                System.out.println("MsgEchoActor: d=" + d);
            })
            .build();
    }

}
