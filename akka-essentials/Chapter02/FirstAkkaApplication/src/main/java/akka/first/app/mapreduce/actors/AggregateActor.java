package akka.first.app.mapreduce.actors;

import java.util.*;
import akka.actor.UntypedAbstractActor;
import akka.first.app.mapreduce.messages.*;

/**
 * The Aggregate actor receives the reduced data list from the Master actor
 * and aggregates it into one big list.
 */
public class AggregateActor extends UntypedAbstractActor {
    private Map<String, Integer> finalReducedMap =
        new HashMap<String, Integer>();

    @Override
    public void onReceive(Object message) throws Exception {
        if (message instanceof ReduceData) {
            ReduceData reduceData = (ReduceData) message;
            aggregateInMemoryReduce(reduceData.getReduceDataList());
        } else if (message instanceof Result) {
            getSender().tell(finalReducedMap.toString(), getSelf());
        } else
            unhandled(message);
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
