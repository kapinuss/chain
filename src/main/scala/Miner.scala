import Chain.system
import akka.actor.Actor
import java.security.MessageDigest
import java.math.BigInteger

class Miner extends Actor {

  var chain = List.empty[Block]

  def receive: Receive = {
    case transactions: List[Transaction] => system.log.info(s"Miner has transactions: ${transactions.toString}.")
  }

  def buildSimpleBlock(list: List[Transaction]): Block = {
    def hash(string: String) = String.format("%032x", new BigInteger(1, MessageDigest.getInstance("SHA-256").digest(string.getBytes("UTF-8"))))
    Block(hash(chain.toString), list)
  }

}
