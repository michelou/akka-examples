package akka.examples;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.examples.actors.ProcessOrderActor;
//import akka.pattern.Patterns;
//import akka.util.Timeout;
//import java.util.concurrent.TimeUnit;
//import scala.concurrent.Await;
//import scala.concurrent.Future;
//import scala.concurrent.duration.Duration;

public class ProcessOrderApp {

    public static void main(String[] args) throws Exception {
        ActorSystem _system = ActorSystem.create("ProcessOrder");
        ActorRef master = _system.actorOf(
            Props.create(ProcessOrderActor.class),
            "master"
        );
        int userId = 1;
        master.tell(userId, null);
        Thread.sleep(5000);
/*
        Timeout timeout = new Timeout(Duration.create(5, TimeUnit.SECONDS));
        Future<Object> future = Patterns.ask(master, new Result(), timeout);
        String result = (String) Await.result(future, timeout.duration());
        System.out.println(result);
*/
        _system.terminate();
    }

}
