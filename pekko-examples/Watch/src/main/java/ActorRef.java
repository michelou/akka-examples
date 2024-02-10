package watch;

public class ActorRef {

    //public void $bang(Object message, ActorRef sender) {
    public void $bang(Object message) {
    }

    public ActorPath path() {
        return new ActorPath() {
            public String toSerializationFormat() {
                return "<path>";
            }
        };
    }

}
