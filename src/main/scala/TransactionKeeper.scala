import Chain.{system, miner}
import akka.actor.Actor
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._

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
      system.log.info("Transactions after adding a new one: " + transactions)
    }
    case chainedTransactions: List[Transaction] => {
      transactions = transactions diff chainedTransactions
      println("Transactions after new block: " + transactions)
    }
    case _ =>
  }

}