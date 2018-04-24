import akka.actor.{ActorRef, ActorSystem, Props}
import akka.stream.ActorMaterializer
import scala.concurrent.{ExecutionContextExecutor, Future}

object Chain {

  implicit val system: ActorSystem = ActorSystem()
  implicit val materializer: ActorMaterializer = ActorMaterializer()
  implicit val context: ExecutionContextExecutor = system.dispatcher

  val transactionKeeper: ActorRef = system.actorOf(Props[TransactionKeeper], "transactionKeeper")
  val miner: ActorRef = system.actorOf(Props[Miner], "miner")
  val chainKeeper: ActorRef = system.actorOf(Props[ChainKeeper], "chainKeeper")

  def main(args: Array[String]): Unit = {
    system.log.info("App started.")
  }




}
