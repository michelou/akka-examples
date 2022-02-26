package akka.first.app.mapreduce;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.first.app.mapreduce.actors.MasterActor;
import akka.first.app.mapreduce.messages.Result;
import akka.pattern.Patterns;
import akka.util.Timeout;
import java.util.concurrent.TimeUnit;
import scala.concurrent.Await;
import scala.concurrent.Future;
import scala.concurrent.duration.Duration;

public class MapReduceApplication {

    public static void main(String[] args) throws Exception {
        ActorSystem _system = ActorSystem.create("MapReduceApp");
        ActorRef master = _system.actorOf(
            Props.create(MasterActor.class),
            "master"
        );
        master.tell("The quick brown fox tried to jump over the lazy dog and fell on the dog", ActorRef.noSender());
        master.tell("Dog is man's best friend", ActorRef.noSender());
        master.tell("Dog and Fox belong to the same family", ActorRef.noSender());
        Thread.sleep(5000);

        Timeout timeout = new Timeout(Duration.create(5, TimeUnit.SECONDS));
        Future<Object> future = Patterns.ask(master, new Result(), timeout);
        String result = (String) Await.result(future, timeout.duration());
        System.out.println(result);

        _system.terminate();
    }

}
