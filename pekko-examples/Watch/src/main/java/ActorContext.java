package watch;

public interface ActorContext {

    public ActorRef actorOf(Props props);

    public ActorRef actorOf(Props props, String name);

    public ActorSystem system();

    public ActorRef watch(ActorRef subject);

    public ActorRef sender();

    public void stop(ActorRef actor);

}
