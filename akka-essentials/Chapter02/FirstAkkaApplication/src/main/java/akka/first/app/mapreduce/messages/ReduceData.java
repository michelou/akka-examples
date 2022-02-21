package akka.first.app.mapreduce.messages;

import java.util.HashMap;

/**
 * Mmssage passed between the Reduce actor and the Aggregate actor.
 */
public final class ReduceData {
    private final HashMap<String, Integer> reduceDataList;

    public HashMap<String, Integer> getReduceDataList() {
        return reduceDataList;
    }

    public ReduceData(HashMap<String, Integer> reduceDataList) {
        this.reduceDataList = reduceDataList;
    }

}