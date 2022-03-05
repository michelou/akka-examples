package akka.examples;

import akka.actor.ActorSystem;
import akka.actor.TypedActor;
import akka.actor.TypedProps;
import akka.util.Timeout;
import static java.util.concurrent.TimeUnit.SECONDS;
import scala.concurrent.Await;
import scala.concurrent.Future;
import scala.concurrent.duration.Duration;

public class CalculatorApp {
    
    public static void main(String[] args) {
        ActorSystem _system = ActorSystem.create("Calculator");
        CalculatorInt calculator = TypedActor.get(_system).typedActorOf(
            new TypedProps<Calculator>(CalculatorInt.class, Calculator.class)
        );
        /*
        // An actor with a non-default constructor
        CalculatorInt calculator = TypedActor.get(_system).typedActorOf(
            new TypedProps<Calculator>(CalculatorInt.class, new Creator< Calculator >() {
                public Calculator create() {
                    return new Calculator("foo");
                }
            })
        );
        */
        calculator.incrementCount();

        Timeout timeout = new Timeout(Duration.create(5, SECONDS));

        // Invoke the method 'add' and wait for result
        try {
            Future<Integer> future = calculator.add(14, 6);
            Integer result = Await.result(future, timeout.duration());
            System.out.println("result=" + result);
        } catch (/*Interrupted,Timeout*/Exception e) {
            System.err.println(e.getMessage());
        }
        TypedActor.get(_system).stop(calculator);

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
        catch (/*Interrupted,IO*/Exception e) {
            /* ignored */
        }
        finally {
            system.terminate();
        }
    }

}
