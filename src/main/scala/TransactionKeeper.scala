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

  def receive() = {
    case s: String => {
      val t = Transaction()
      println(t)
      self ! t
    }
    case t: Transaction => {
      transactions = transactions.+:(t)
    println(transactions)
    }
    case GiveWholeChain =>
    //case GiveLastBlock => sender() ! chain.blocks.last
    case _ =>
  }

}