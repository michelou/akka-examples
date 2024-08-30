package watch;

public class ActorSystem {

    public ActorSystem() {
    }

    public ActorSystem(String name) {
    }

    public static ActorSystem apply(String name) {
        return new ActorSystem();
    }

    public ActorRef deadLetters() {
        return null;
    }

}
