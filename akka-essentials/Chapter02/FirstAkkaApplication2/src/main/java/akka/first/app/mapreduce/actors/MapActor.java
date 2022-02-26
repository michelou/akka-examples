package akka.first.app.mapreduce.actors;

import akka.actor.AbstractActor;
import akka.first.app.mapreduce.messages.MapData;
import akka.first.app.mapreduce.messages.WordCount;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/**
 * The Map actor will send the MapData message to the Master actor,
 * who passes it to the Reduce actor.
 */
public class MapActor extends AbstractActor {
    private static String[] STOP_WORDS = {
        "a", "am", "an", "and", "are", "as", "at",
        "be",
        "do", "go", "if", "in", "is", "it", "of", "on", "the",
        "to" };
    private static List<String> STOP_WORDS_LIST = Arrays.asList(STOP_WORDS);

    public Receive createReceive() {
        return receiveBuilder()
            .match(String.class, word -> {
                // map the words in the sentence and send the result to MasterActor
                getSender().tell(evaluateExpression(word), getSelf());
            })
            .matchAny(message -> {
                unhandled(message);
            })
            .build();
    }

    private MapData evaluateExpression(String line) {
        List<WordCount> dataList = new ArrayList<WordCount>();
        StringTokenizer parser = new StringTokenizer(line);
        while (parser.hasMoreTokens()) {
            String word = parser.nextToken().toLowerCase();
            if (!STOP_WORDS_LIST.contains(word)) {
                dataList.add(new WordCount(word,Integer.valueOf(1)));
            }
        }
        return new MapData(dataList);
    }

}
