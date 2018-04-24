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
    self ! Produce
  }

  def receive(): PartialFunction[Any, Unit] = {
    case Produce => {
      system.log.info("Making new transaction.")
      self ! Transaction()
    }
    case GimmeNew => sender ! transactions
    case t: Transaction => {
      transactions = t +: transactions
      println(transactions)
      //miner ! transactions
    }
    case chainedtransactions: List[Transaction] => {
      transactions = transactions diff chainedtransactions
      println("Transactions after new block: " + transactions)
    }
    case GiveWholeChain =>
    case _ =>
  }

}