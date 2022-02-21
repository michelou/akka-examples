package akka.first.app.mapreduce.actors

import akka.actor.Actor
import akka.first.app.mapreduce.MapData
import akka.first.app.mapreduce.ReduceData
import akka.first.app.mapreduce.WordCount
import scala.collection.immutable.Map

class ReduceActor extends Actor {

  def receive: Receive = {
    case MapData(dataList) =>
      sender() ! reduce(dataList.toIndexedSeq)
  }

  def reduce(words: IndexedSeq[WordCount]): ReduceData = ReduceData {
    words.foldLeft(Map.empty[String, Int]) { (index, words) =>
      if (index contains words.word)
        index + (words.word -> (index.get(words.word).get + 1))
      else
        index + (words.word -> 1)
    }
  }

}
