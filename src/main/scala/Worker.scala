import akka.actor.Actor
import Chain.{system, miner}

class Worker extends Actor {

  def receive: Receive = {
    case list: List[List[Transaction]] => sender ! doSomeWork(list)
    case _ =>
  }

  def doSomeWork(transactionSets: List[List[Transaction]]): String = {
    "mock"
  }
}
