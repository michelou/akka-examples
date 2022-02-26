package akka.examples;

import akka.actor.UntypedAbstractActor;

public class PingPongActor extends UntypedAbstractActor {

    static String PING = "PING";
    static String PONG = "PONG";
    int count = 0;

    public void onReceive(Object message) throws Exception {
        if (message.equals(PING)) {
            System.out.println("PING");
            count += 1;
            Thread.sleep(100);
            getSelf().tell(PONG, getSender());
            //getContext().become(new akka.japi.Procedure<Object>() {
            getContext().become(receiveBuilder()
                .matchEquals(PONG, p -> {
                    System.out.println("PONG");
                    count += 1;
                    try { Thread.sleep(100); }
                    catch (Exception e) { /* ignore */ }
                    getSelf().tell(PING, getSender());
                    // restores the original message handling loop for PING messages.
                    getContext().unbecome();
                })
                .build()
            );
            if (count > 10)
                getContext().stop(getSelf());
        }
    }

}
