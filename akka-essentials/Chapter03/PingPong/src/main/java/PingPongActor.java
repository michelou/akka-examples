package akka.examples;

import akka.actor.UntypedAbstractActor;
import scala.PartialFunction;
import scala.runtime.BoxedUnit;

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
            getContext().become(new PartialFunction<Object, BoxedUnit>() {            
                public BoxedUnit apply(Object message) {
                    System.out.println("PONG");
                    count += 1;
                    try { Thread.sleep(100); }
                    catch (Exception e) { /* ignore */ }
                    getSelf().tell(PING, getSender());
                    // restores the original message handling loop for PING messages.
                    getContext().unbecome();
                    return BoxedUnit.UNIT;
                }
                public boolean isDefinedAt(Object message) { return true; }
            });
            if (count > 10)
                getContext().stop(getSelf());
        }
    }

}
