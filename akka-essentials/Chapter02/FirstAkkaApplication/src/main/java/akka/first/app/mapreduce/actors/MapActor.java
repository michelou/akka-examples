package akka.first.app.mapreduce.actors;

import java.util.*; // ArrayList, Arrays, List
import java.util.StringTokenizer;
import akka.actor.UntypedAbstractActor;
import akka.first.app.mapreduce.messages.MapData;
import akka.first.app.mapreduce.messages.WordCount;

/**
 * The Map actor will send the MapData message to the Master actor,
 * who passes it to the Reduce actor.
 */
public class MapActor extends UntypedAbstractActor {
    private static String[] STOP_WORDS = {
        "a", "am", "an", "and", "are", "as", "at",
        "be",
        "do", "go", "if", "in", "is", "it", "of", "on", "the",
        "to" };
    private static List<String> STOP_WORDS_LIST = Arrays.asList(STOP_WORDS);

    @Override
    public void onReceive(Object message) throws Exception {
        if (message instanceof String) {
            String work = (String) message;
            // map the words in the sentence and send the result to MasterActor
            getSender().tell(evaluateExpression(work), getSelf());
        } else
            unhandled(message);
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
