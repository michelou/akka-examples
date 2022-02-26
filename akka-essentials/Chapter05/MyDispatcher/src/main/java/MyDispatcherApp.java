package akka.examples;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import com.typesafe.config.ConfigFactory;

public class MyDispatcherApp {
    
    public static void main(String[] args) {
        ActorSystem _system = ActorSystem.create(
            "dispatcher",
            ConfigFactory.load().getConfig("MyDispatcher"));
        ActorRef actor = _system.actorOf(
            Props.create(MsgEchoActor.class).withDispatcher("my-dispatcher"));
        actor.tell(0, ActorRef.noSender());

        terminate(_system);
    }

    /**
     * Terminates the actor system ignoring the checked exceptions.
     */
    private static void terminate(ActorSystem system) {
        try {
            Thread.sleep(1000);
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
