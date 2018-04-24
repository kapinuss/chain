import Chain._
import akka.actor.Actor
import scala.concurrent.duration._
import java.security.MessageDigest
import java.math.BigInteger
import scala.concurrent.ExecutionContext.Implicits.global

class Miner extends Actor {

  system.scheduler.schedule(12 seconds, 30 seconds) {
    transactionKeeper ! GimmeNew
  }

  var chain = List(Block("0", 0, List.empty[Transaction]))

  def receive: Receive = {
    case updatedChain: BlockChain => {
      chain = updatedChain.blocks
      system.log.info(s"Updated chain on a Miner: ${chain.size} blocks.")
    }
    case transactions: List[Transaction] => {
      val (block, chainedTransactions) = buildBlockWithOrdering(transactions)
      system.log.info("Miner built a block: " + block)
      transactionKeeper ! chainedTransactions
      chainKeeper ! block
    }
  }

  def hash(string: String): String = String
    .format("%032x", new BigInteger(1, MessageDigest.getInstance("SHA-256").digest(string.getBytes("UTF-8"))))

  def buildSimpleBlock(transactions: List[Transaction], size: Int): Block = Block(hash(chain.head.toString), size, transactions)

  //TODO заполнение оставшегося места в блоке мелкими менее прибыльными транзакциями
  def buildBlockWithOrdering(transactions: List[Transaction]): (Block, List[Transaction]) = {
    var sum = 0
    val sortedTransactions = transactions.sortWith( (x,y) => (x.fee / x.size) >= (y.fee / y.size))
    val sortedReducedTransactions = sortedTransactions.takeWhile(each => {sum += each.size; sum < 100} )
    sortedReducedTransactions.foreach(println)
    val size = sortedReducedTransactions.map(_.size).sum
    sortedReducedTransactions.foreach(println)
    (buildSimpleBlock(sortedReducedTransactions, size), sortedReducedTransactions)
  }

}
