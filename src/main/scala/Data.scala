case class Transaction(size: Int, fee: Int, gist: String)
object Transaction {
  def apply(size: Int, fee: Int, gist: String = "Root"): Transaction = new Transaction(size, fee, gist)
  def apply(): Transaction = {
    val random = scala.util.Random
    val size = random.nextInt(99) + 1
    val fee = random.nextInt(999) + 1
    val gist = (for (i <- 0 to 10) yield random.nextPrintableChar).mkString
    new Transaction(size, fee, gist)
  }
}

case class Block(hash: String, size: Int, transactions: List[Transaction]) {
  def apply(hash: String, size: Int, transactions: List[Transaction]): Block = Block(hash, size, transactions)
}

case class BlockChain(blocks: List[Block])

sealed trait ChainMessage

final case object GimmeChain extends ChainMessage

final case object GiveLastBlock extends ChainMessage

final case object Produce extends ChainMessage

final case object GimmeNew extends ChainMessage


