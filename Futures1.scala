// 1 - the imports
import scala.concurrent.{Await, Future}
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global

object Futures1 extends App {

  // used by 'time' method
  // implicit val baseTime = System.currentTimeMillis

  // 2 - create a Future
  val f = Future {
      // Thread.sleep(500)
      1 + 1
  }

  val f2 = Future {
    2*2
  }
  // 3 - this is blocking (blocking is bad)
  val result = Await.result(f, 0 second)
  // val result = Await.result(f, 1)
  val result2 = Await.result(f2, 0 second)
  
  println(result)
  println(result2)
  // Thread.sleep(1000)

}
