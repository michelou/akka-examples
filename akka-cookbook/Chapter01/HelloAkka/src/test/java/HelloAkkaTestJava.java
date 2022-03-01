package com.packt.chapter01;

import akka.actor.ActorSystem;
import org.junit.AfterClass;
import org.junit.BeforeClass;

/**
 * Java workaround for implementing the two JUnit static methods
 * 'onceBeforeAllTests' and 'onceAfterAllTests'.
 * Drawback: ressources shared between tests must be defined here.
 */
public abstract class HelloAkkaTestJava {
    protected static ActorSystem actorSystem;

    @BeforeClass
    public static void onceBeforeAllTests() {
        System.out.println("HelloAkkaTestJava: before all tests");
        actorSystem = ActorSystem.create("HelloAkka");
    }

    @AfterClass
    public static void onceAfterAllTests() {
        System.out.println("HelloAkkaTestJava: after all tests");
        if (actorSystem != null) actorSystem.terminate();
    }

}
