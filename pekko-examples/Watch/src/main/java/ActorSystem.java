package watch;

public class ActorSystem {

    public ActorSystem() {
    }

    public ActorRef actorOf(Props props) {
        return new ActorRef();
    }

    public static ActorSystem apply(String name) {
        return new ActorSystem();
    }

    public ActorRef deadLetters() {
        return null;
    }

}
