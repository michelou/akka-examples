package akka.examples;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.routing.Pool;
import akka.routing.RandomPool;
import akka.routing.RoundRobinPool;
import akka.routing.SmallestMailboxPool;
import com.typesafe.config.ConfigFactory;

public class MyRouterApp {

    public static void main(String[] args) {
        String arg = args.length > 0 ? args[0] : "";

        Integer nrOfInstances = 5;
        ActorSystem _system = ActorSystem.create(
            "MyRouter",
            ConfigFactory.load().getConfig("MyRouter")
        );
        ActorRef router = _system.actorOf(
            Props.create(MyActor.class)
                .withRouter(createPool(arg, nrOfInstances)),
            getActorName(arg)
        );
        router.tell(0, ActorRef.noSender());

        terminate(_system);
    }

    /**
     * RoundRobinPool (default):
     * - Uses round-robin to select a routee.
     *
     * RandomPool:
     * - Randomly selects one of the target routees to send a message to.
     *
     * SmallestMailboxPool:
     * - Tries to send to the non-suspended routee with fewest messages in mailbox.
     *
     * The configuration parameter trumps the constructor arguments.
     * This means that if you provide `nrOfInstances` during instantiation
     * they will be ignored if the router is defined in the configuration
     * file for the actor being used.
     */
    private static Pool createPool(String arg, Integer nrOfInstances) {
        return arg.equals("random")
            ? new RandomPool(nrOfInstances)
            : arg.equals("smallest")
                ? new SmallestMailboxPool(nrOfInstances)
                : new RoundRobinPool(nrOfInstances);
    }

    private static String getActorName(String arg) {
        return arg.equals("random")
            ? "myRandomRouterActor"
            : arg.equals("smallest")
                ? "mySmallestMailboxRouterActor"
                : "myRoundRobinRouterActor";
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
