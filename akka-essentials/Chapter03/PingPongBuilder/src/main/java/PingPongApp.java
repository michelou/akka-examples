package akka.examples;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

public class PingPongApp {
    
    public static void main(String[] args) throws Exception {
        ActorSystem _system = ActorSystem.create("PingPong");
        ActorRef actor = _system.actorOf(
            Props.create(PingPongActor.class),
            "pingPongActor"
        );
        actor.tell(PingPongActor.PING, ActorRef.noSender());

        terminate(_system);
    }

    private static void terminate(ActorSystem system) {
        try {
            Thread.sleep(2000);
            System.out.println(">>> Press ENTER to exit <<<");
            System.in.read();
        }
        catch (Exception e) {
            /* ignored */
        }
        finally {
            system.terminate();
        }
    }

}
