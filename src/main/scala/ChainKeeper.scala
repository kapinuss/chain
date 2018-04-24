import akka.actor.Actor
import Chain.{system, miner}

class ChainKeeper extends Actor {

  val initialBlock = Block("0", List.empty[Transaction])

  var chain: BlockChain = BlockChain(List(initialBlock))

  def receive: Receive = {
    case GimmeChain => sender ! chain
    case block: Block => {
      chain = BlockChain(block :: chain.blocks)
      system.log.info("New block is received. Current state of chain on ChainKeeper: " + chain)
      sender ! chain
    }
  }
}
