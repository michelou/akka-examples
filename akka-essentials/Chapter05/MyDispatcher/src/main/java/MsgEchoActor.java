package akka.examples;

import akka.actor.AbstractActor;

public class MsgEchoActor extends AbstractActor {
    
    public Receive createReceive() {
        return receiveBuilder()
            .matchAny(message -> {
                System.out.println("MsgEchoActor: message=" + message);
            })
            .build();
    }

}
