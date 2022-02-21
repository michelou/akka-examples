package com.example;

import akka.actor.typed.ActorSystem;

public class AkkaQuickstart {

    public static void main(String[] args) {
        final ActorSystem<GreeterMain.SayHello> greeterMain =
            ActorSystem.create(GreeterMain.create(), "helloakka");

        greeterMain.tell(new GreeterMain.SayHello("Charles"));

        terminate(greeterMain);
    }

    private static void terminate(ActorSystem system) {
        try {
            Thread.sleep(1000);
            System.out.println(">>> Press ENTER to exit <<<");
            System.in.read();
        }
        catch (Exception ignored) {
            /* ignored */
        }
        finally {
            system.terminate();
        }
    }

}
