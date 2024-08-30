import org.apache.pekko.actor.ActorContext
import org.apache.pekko.actor.ActorRef
import org.apache.pekko.actor.ActorSystem
import org.apache.pekko.actor.Props

object Main {

  def main(args: Array[String]): Unit = {
    val system = ActorSystem("Main")
    println("test")
    val ref = new AnyRef {
      import org.apache.pekko.actor.{ Actor, Props, Terminated }

      class WatchActor extends Actor {
        val child = context.actorOf(Props.empty, "child")
        context.watch(child)
        var lastSender = context.system.deadLetters

        def receive: PartialFunction[Any, Unit] = {
          case "kill" =>
            context.stop(child)
            lastSender = sender()
          case Terminated(`child`) =>
            //lastSender ! "finished"
            lastSender $bang "finished"
        }
      }
      val victim = system.actorOf(Props(classOf[WatchActor], this))
      print(victim.path.toSerializationFormat)
    }
    Thread.sleep(1000)
  }

}
