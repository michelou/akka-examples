package akka.first.app.mapreduce.actors;

import akka.actor.AbstractActor;
import akka.first.app.mapreduce.messages.*;
import java.util.HashMap;
import java.util.Map;

/**
 * The Aggregate actor receives the reduced data list from the Master actor
 * and aggregates it into one big list.
 */
public class AggregateActor extends AbstractActor {
    private Map<String, Integer> finalReducedMap =
        new HashMap<String, Integer>();

    public Receive createReceive() {
        return receiveBuilder()
            .match(ReduceData.class, reduceData -> {
                aggregateInMemoryReduce(reduceData.getReduceDataList());
            })
            .match(Result.class, result -> {
                getSender().tell(finalReducedMap.toString(), getSelf());
            })
            .match(Object.class, message -> {
                unhandled(message);
            })
            .build();
    }

    private void aggregateInMemoryReduce(Map<String, Integer> reducedList) {
        Integer count = null;
        for (String key : reducedList.keySet()) {
            if (finalReducedMap.containsKey(key)) {
                count = reducedList.get(key) + finalReducedMap.get(key);
                finalReducedMap.put(key, count);
            } else {
                finalReducedMap.put(key, reducedList.get(key));
            }
        }
    }

}
