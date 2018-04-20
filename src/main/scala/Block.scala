case class Transaction(size: Int, fee: Int, gist: String)

case class Block(hash: String, transactions: List[Transaction])

case class Chain(blocks: List[Block])

object Chain {
  def apply(): Chain = {
    new Chain(blocks = List.empty)
  }
  def apply(newBlock: Block): Chain = {
    new Chain(blocks = List.empty)//change it
  }

  def copy(): Unit = {}
}

sealed trait ChainMessage

final case object GiveWholeChain extends ChainMessage

final case object GiveLastBlock extends ChainMessage


