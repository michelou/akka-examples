package com.alexandreesl
 
import akka.actor.ActorSystem
import akka.kafka.ProducerSettings
import akka.kafka.scaladsl.Producer
import akka.stream.scaladsl.{FileIO, Flow, Framing}
import akka.stream.Materializer
import akka.util.ByteString
import com.alexandreesl.json.JsonParsing._
import com.alexandreesl.model.Account
import com.typesafe.scalalogging.Logger
import java.nio.file.Paths
import org.apache.kafka.clients.producer.ProducerRecord
import org.apache.kafka.common.serialization.StringSerializer
import org.slf4j.LoggerFactory
import spray.json._

object Main {

  def main(args: Array[String]): Unit = {
    implicit val system = ActorSystem("akka-streams-lab")
    implicit val materializer = Materializer(system)
    implicit val ec = system.dispatcher
    val logger = Logger(LoggerFactory.getLogger("Main"))
    val config = system.settings.config.getConfig("akka.kafka.producer")
    val producerSettings =
      ProducerSettings(config, new StringSerializer, new StringSerializer)
        .withBootstrapServers("localhost:9092")

    logger.info("Starting streams...")

    def convertToClass(csv: Array[String]): Account = {
      Account(csv(0).toLong,
      csv(1), csv(2),
      csv(3).toInt, csv(4),
      csv(5), csv(6),
      csv(7), csv(8),
      csv(9), csv(10),
      csv(11).toLong, csv(12))
    }

    val flow = Flow[String]
      .filter(s => !s.contains("COD"))
      .map(line => { convertToClass(line.split(",")) })

    FileIO.fromPath(Paths.get("input1.csv"))
      .via(Framing.delimiter(ByteString("\n"), 4096)
      .map(_.utf8String))
      .via(flow)
      .map(value => new ProducerRecord[String, String]("accounts", value.toJson.compactPrint))
      .runWith(Producer.plainSink(producerSettings))

    logger.info("Stream system is initialized!")
  }

}
