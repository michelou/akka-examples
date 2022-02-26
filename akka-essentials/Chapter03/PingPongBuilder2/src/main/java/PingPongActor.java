package akka.examples;

import akka.actor.AbstractActor;

public class PingPongActor extends AbstractActor {

    static String PING = "PING";
    static String PONG = "PONG";
    int count = 0;

    public Receive createReceive() { return pingReceive; }

    private Receive pongReceive = receiveBuilder()
        .matchEquals(PONG, p -> {
            System.out.println("PONG");
            count += 1;
            try { Thread.sleep(100); }
            catch (Exception e) { /* ignore */ }
            getSelf().tell(PING, getSender());
            // restores the original message handling loop for PING messages.
            getContext().unbecome();
        })
        .build();

    private Receive pingReceive = receiveBuilder()
        .matchEquals(PING, p -> {
            System.out.println("PING");
            count += 1;
            Thread.sleep(100);
            getSelf().tell(PONG, getSender());
            getContext().become(pongReceive);
            if (count > 10)
                getContext().stop(getSelf());
        })
        .build();

}
