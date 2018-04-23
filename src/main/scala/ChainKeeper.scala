import akka.actor.Actor

class ChainKeeper extends Actor{

  val initialBlock = Block("0", List.empty[Transaction])

  var chain = List(initialBlock)

  def receive: Receive = {
    case block: Block => { chain.:+(block) ; chain.foreach(println) }
  }
}
