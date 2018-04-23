import akka.actor.Actor
import Chain.miner

class ChainKeeper extends Actor {

  val initialBlock = Block("0", List.empty[Transaction])

  var chain = List(initialBlock)

  def receive: Receive = {
    case String => sender ! chain
    case block: Block => {
      chain = block :: chain
      chain.foreach(println)
      miner ! chain
    }
  }
}
