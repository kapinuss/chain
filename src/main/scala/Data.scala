case class Transaction(size: Int, fee: Int, gist: String)
object Transaction {
  def apply(fee: Int, gist: String): Transaction = {
    val random = scala.util.Random
    val size = random.nextInt(100)
    val fee = random.nextInt(1000)
    val gist = (for (i <- 0 to 256) yield random.nextPrintableChar).mkString
    new Transaction(size, fee, gist)
  }
}

case class Block(hash: String, transactions: List[Transaction])

case class Chain(title: String, blocks: List[Block])

sealed trait ChainMessage

final case object GiveWholeChain extends ChainMessage

final case object GiveLastBlock extends ChainMessage


