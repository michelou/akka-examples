package akka.first.app.mapreduce.actors;

import java.util.HashMap;
import java.util.List;
import akka.actor.UntypedAbstractActor;
import akka.first.app.mapreduce.messages.MapData;
import akka.first.app.mapreduce.messages.ReduceData;
import akka.first.app.mapreduce.messages.WordCount;

/**
 * The Reduce actor goes through the list of words and reduce for duplicate
 * words, and accordingly increase the number of instances counted for such
 * words. The reduced list is then sent back to the Master actor.
 */
public class ReduceActor extends UntypedAbstractActor {

    @Override
    public void onReceive(Object message) throws Exception {
        if (message instanceof MapData) {
            MapData mapData = (MapData) message;
            // reduce the incoming data and forward the result to Master actor
            getSender().tell(reduce(mapData.getDataList()), getSelf());
        } else
            unhandled(message);
    }

    private ReduceData reduce(List<WordCount> dataList) {
        String word = null;
        HashMap<String, Integer> reducedMap = new HashMap<String, Integer>();
        for (WordCount wordCount : dataList) {
            word = wordCount.getWord();
            if (reducedMap.containsKey(word)) {
                Integer value = (Integer) reducedMap.get(word);
                value++;
                reducedMap.put(word, value);
            } else {
                reducedMap.put(word, Integer.valueOf(1));
            }
        }
        return new ReduceData(reducedMap);
    }

}
