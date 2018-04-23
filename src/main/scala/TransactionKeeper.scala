import Chain.{system, miner}
import akka.actor.Actor
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.{HttpRequest, HttpResponse}

import scala.concurrent.Future
import scala.util.{Failure, Success}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._
import org.json4s._
import org.json4s.jackson.JsonMethods._

class TransactionKeeper extends Actor {

  var transactions: List[Transaction] = List.empty[Transaction]

  system.scheduler.schedule(3 seconds, 10 seconds) {
    self ! "Make new transaction!"
  }

  def receive(): PartialFunction[Any, Unit] = {
    case s: String => {
      system.log.info("Making new transaction.")
      self ! Transaction()
    }
    case t: Transaction => {
      transactions = transactions.+:(t)
      miner ! transactions
    }
    case GiveWholeChain =>
    case _ =>
  }

}