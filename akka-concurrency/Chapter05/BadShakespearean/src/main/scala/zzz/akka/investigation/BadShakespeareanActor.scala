package zzz.akka.investigation

import akka.actor.Actor

class BadShakespeareanActor extends Actor {

  def receive = {
    case "Good Morning" =>
      println("Him: Forsooth 'tis the 'morn, " +
              "but mourneth for thou doest I do!")
    case "You're terrible" =>
      println("Him: Yup")
  }

}
