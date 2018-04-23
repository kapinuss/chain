import akka.NotUsed
import akka.actor.{ActorRef, ActorSystem, Props}
import akka.http.scaladsl.Http
import akka.http.scaladsl.model._
import akka.stream.ActorMaterializer
import akka.stream.scaladsl._
import akka.http.scaladsl.model.HttpMethods._
import akka.event.{LogSource, Logging}
import akka.http.scaladsl.model.ws.{BinaryMessage, Message, TextMessage, UpgradeToWebSocket}

import scala.concurrent.{ExecutionContextExecutor, Future}
import scala.concurrent.duration._

object Chain {

  implicit val system: ActorSystem = ActorSystem()
  implicit val materializer: ActorMaterializer = ActorMaterializer()
  implicit val context: ExecutionContextExecutor = system.dispatcher

  val transactionKeeper: ActorRef = system.actorOf(Props[TransactionKeeper], "transactionKeeper")

  system.scheduler.schedule(3 seconds, 60 seconds) {
    doWithScheduler()
  }

  def main(args: Array[String]): Unit = {
    system.log.info("App started.")
  }

  def doWithScheduler(): Unit = {
    println("Request for new transaction.")
    transactionKeeper ! "Make new transaction!"
  }


}
