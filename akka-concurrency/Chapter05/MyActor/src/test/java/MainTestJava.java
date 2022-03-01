package zzz.akka.investigation;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import org.junit.AfterClass;
import org.junit.BeforeClass;

/**
 * Java workaround for implementing the two JUnit static methods
 * 'onceBeforeAllTests' and 'onceAfterAllTests'.
 * Drawback: ressources shared between tests must be defined here.
 */
public abstract class MainTestJava {
    protected static ActorSystem system;
    protected static ActorRef actor;

    @BeforeClass
    public static void onceBeforeAllTests() {
        System.out.println("MainTestJava: before all tests");
        system = ActorSystem.create("MyActors");
        actor = system.actorOf(Props.create(MyActor.class), "MyActor");
    }

    @AfterClass
    public static void onceAfterAllTests() {
        System.out.println("MainTestJava: after all tests");
        if (system != null) system.terminate();
    }

}
