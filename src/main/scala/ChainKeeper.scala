import akka.actor.Actor
import Chain._

class ChainKeeper extends Actor {

  val initialBlock = Block("0", 0, List(Transaction(1, 0)))

  var chain: BlockChain = BlockChain(List(initialBlock))

  def receive: Receive = {
    case GimmeChain => sender ! chain
    case block: Block => {
      chain = BlockChain(block :: chain.blocks)
      system.log.info("New block is received. Current state of chain on ChainKeeper: ")
      chain.blocks.foreach(block => system.log.info(block.toString))
      sender ! chain
    }
  }
}
