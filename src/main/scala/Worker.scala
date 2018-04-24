import akka.actor.Actor
import Chain._

class Worker extends Actor {

  def receive: Receive = {
    case transactions: List[Transaction] => sender ! doSomeWork(transactions)
    case _ =>
  }

  def doSomeWork(transactionSets: List[Transaction]): List[Transaction] = {
    val subSets: List[Set[Transaction]] = transactionSets.toSet.subsets.toList
    val result: (Set[Transaction], Int, Int) = subSets.map(each => (each, each.map(t => t.size).sum, each.map(t => t.fee).sum))
      .filter(_._2 <= 100).maxBy(_._3)
    system.log.info("Best set of transactions for a block: " + result)
    result._1.toList
  }
}
