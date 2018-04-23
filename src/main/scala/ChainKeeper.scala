import akka.actor.Actor
import Chain.{system, miner}

class ChainKeeper extends Actor {

  val initialBlock = Block("0", List.empty[Transaction])

  var chain: List[Block] = List(initialBlock)

  def receive: Receive = {
    case s: String => sender ! chain
    case block: Block => {
      system.log.info("New block is received. Current state of chain presented to successful miner.")
      chain = block :: chain
      println(chain)
      sender ! chain
    }
  }
}
