package com.alexandreesl

import java.nio.file.Paths
 
import akka.actor.ActorSystem
import akka.kafka.{ConsumerSettings, ProducerSettings, Subscriptions}
import akka.kafka.scaladsl.{Consumer, Producer}
import akka.stream.scaladsl.{FileIO, Flow, Framing, Sink}
import akka.stream.Materializer
import akka.util.ByteString
import com.alexandreesl.graph.AccountWriterGraphStage
import com.alexandreesl.graph.GraphMessages.{Account, InputMessage}
import com.alexandreesl.json.JsonParsing._
import com.typesafe.scalalogging.Logger
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.clients.producer.ProducerRecord
import org.apache.kafka.common.serialization.{StringDeserializer, StringSerializer}
import org.slf4j.LoggerFactory
import scala.concurrent.Future
import spray.json._

object Main {

  def main(args: Array[String]): Unit = {
    implicit val system: ActorSystem = ActorSystem("akka-streams-lab2")
    implicit val materializer = Materializer(system)
    implicit val ec = system.dispatcher
    val logger = Logger(LoggerFactory.getLogger("Main"))
    val configProducer = system.settings.config.getConfig("akka.kafka.producer")
    val producerSettings =
      ProducerSettings(configProducer, new StringSerializer, new StringSerializer)
        .withBootstrapServers("localhost:9092")
    val configConsumer = system.settings.config.getConfig("akka.kafka.consumer")
    val consumerSettings =
      ConsumerSettings(configConsumer, new StringDeserializer, new StringDeserializer)
        .withBootstrapServers("localhost:9092")
        .withGroupId("group1")
        .withProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest")
 
    logger.info("Starting streams...")
 
    def convertToClass(csv: Array[String]): Account = Account(
      csv(0).toLong,
      csv(1), csv(2),
      csv(3).toInt, csv(4),
      csv(5), csv(6),
      csv(7), csv(8),
      csv(9), csv(10),
      csv(11).toLong, csv(12)
    )
 
    val flow = Flow[String]
      .filter(s => !s.contains("COD"))
      .map(line => { convertToClass(line.split(",")) })
 
    FileIO.fromPath(Paths.get("input1.csv"))
      .via(Framing.delimiter(ByteString("\n"), 4096)
      .map(_.utf8String))
      .via(flow)
      .map(value => new ProducerRecord[String, String]("accounts", value.toJson.compactPrint))
      .runWith(Producer.plainSink(producerSettings))
 
    Consumer
      .committableSource(consumerSettings, Subscriptions.topics("accounts"))
      .mapAsync(10)(msg =>
        Future.successful(InputMessage(msg.record.value.parseJson.convertTo[Account], msg.committableOffset))
      ).via(AccountWriterGraphStage.graph)
      .mapAsync(10) { tuple =>
        val acc = tuple._1
        logger.info(s"persisted Account: $acc")
        tuple._2.commitScaladsl()
      }
      .runWith(Sink.ignore)
 
    logger.info("Stream system is initialized!")
  }

}
