case class Transaction(size: Int, fee: Int, gist: String)
object Transaction {
  def apply(): Transaction = {
    val random = scala.util.Random
    val size = random.nextInt(100)
    val fee = random.nextInt(1000)
    val gist = (for (i <- 0 to 10) yield random.nextPrintableChar).mkString
    new Transaction(size, fee, gist)
  }
}

case class Block(hash: String, transactions: List[Transaction]) {
  def apply(hash: String, transactions: List[Transaction]): Block = new Block(hash, transactions)
}

case class BlockChain(blocks: List[Block])

sealed trait ChainMessage

final case object GimmeChain extends ChainMessage

final case object GiveLastBlock extends ChainMessage

final case object Produce extends ChainMessage

final case object GimmeNew extends ChainMessage


