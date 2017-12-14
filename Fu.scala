import scala.concurrent.{Future}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Failure, Success}
import scala.util.Random
import java.util.concurrent._

object Fu {
    def main(args: Array[String]) {
        println("hui!")
        
        val future = new FutureTask[String](new Callable[String]() {
        def call(): String = {
            // searcher.search(target);
            "Ha!"
        }})

        executor.execute(future)

        val blockingResult = Await.result(future)

        println(blockingResult)
    }
}
