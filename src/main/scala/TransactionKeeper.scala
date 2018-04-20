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

  def receive() = {
    case String => {
      val t = Transaction
      println(t)
    }
    //chain = chain.copy(blocks = chain.blocks :: new Block())
    case GiveWholeChain =>
    //case GiveLastBlock => sender() ! chain.blocks.last
    case _ =>
  }

}