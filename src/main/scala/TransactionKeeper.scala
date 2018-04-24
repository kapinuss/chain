import Chain.{system, miner}
import akka.actor.Actor
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._

class TransactionKeeper extends Actor {

  var transactions: List[Transaction] = List.empty[Transaction]

  system.scheduler.schedule(3 seconds, 5 seconds) {
    self ! Produce
  }

  def receive(): PartialFunction[Any, Unit] = {
    case Produce => {
      self ! Transaction()
    }
    case GimmeNew => sender ! transactions
    case t: Transaction => {
      transactions = t +: transactions
      system.log.info("TransactionKeeper made a new transaction: " + t)
    }
    case chainedTransactions: List[Transaction] => {
      transactions = transactions diff chainedTransactions
      system.log.info("Transactions after new block: " + transactions.size)
    }
    case _ =>
  }

}