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

  var chain = List.empty[Block]

  def receive: Receive = {
    case updatedChain: BlockChain => {
      chain = updatedChain.blocks
      system.log.info(s"Updated the chain on a Miner: ${chain.toString}.")
    }
    case transactions: List[Transaction] => {
      system.log.info(s"Miner has transactions: ${transactions.toString}.")
      val (block, chainedTransactions) = buildBlockWithOrdering(transactions)
      println("Miner built a block: " + block)
      transactionKeeper ! chainedTransactions
      chainKeeper ! block
    }
  }

  def hash(string: String): String = String
    .format("%032x", new BigInteger(1, MessageDigest.getInstance("SHA-256").digest(string.getBytes("UTF-8"))))

  def buildSimpleBlock(transactions: List[Transaction]): Block = {
    Block(hash(chain.toString), transactions)
  }

  def buildBlockWithOrdering(transactions: List[Transaction]): (Block, List[Transaction]) = {
    var sum = 0
    val sortedTransactions = transactions.sortWith( (x,y) => (x.fee / x.size) >= (y.fee / y.size))
    val sortedReducedTransactions = sortedTransactions.takeWhile(each => {sum += each.size; sum < 100} )
    println("----------------------")
    transactions.foreach(println)
    println("----------------------")
    sortedTransactions.foreach(println)
    println(s"size---------------------- $sum " + sortedReducedTransactions.map(_.size).sum)
    sortedReducedTransactions.foreach(println)
    (buildSimpleBlock(sortedReducedTransactions), sortedReducedTransactions)
  }

}
