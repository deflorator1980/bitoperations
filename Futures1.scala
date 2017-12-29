// 1 - the imports
import scala.concurrent.{Await, Future}
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global
import java.util.Date
object Futures1 extends App {

  // used by 'time' method
  // implicit val baseTime = System.currentTimeMillis

  // 2 - create a Future
  val f = Future {
      // Thread.sleep(500)
      val quantity = 4
      val numberDec = Math.pow(2, quantity).toLong - 1 
        println(numberDec)
        var sum0, sum1 = 0
        val arr = Array.fill(quantity){scala.util.Random.nextInt(10)}
        arr.foreach({a => print(a + " ")})

        // var difference = Int.MaxValue
        var difference = Long.MaxValue

        println("\n" + new Date)

        // for (nDec <- 1 to numberDec) {
        var nDec = 1L
        while(nDec <= numberDec){
          for (nBin <- 0 until arr.length) {
                if (getBit(nDec, nBin) == 0) {
                    sum0 += arr(nBin)
                } else if (getBit(nDec, nBin) == 1) {
                    sum1 += arr(nBin)
                }
            }

            var dif = Math.abs(sum1 - sum0)

            if (dif < difference) {
                difference = dif
            }

            sum0 = 0; sum1 = 0
            nDec += 1
        }
        // println(difference)
        println("min dif: " + difference)
        println(new Date)
      1 + 1
  }
  def getBit (nDec: Long, nBin: Int) =  {
        val x = (nDec >> nBin) & 1
        x
    }

  val f2 = Future {
    2 * 2
  }
  // 3 - this is blocking (blocking is bad)
  val result = Await.result(f, 10 second)
  // val result = Await.result(f, 1)
  val result2 = Await.result(f2, 10 second)
  
  println(result)
  println(result2)
  // Thread.sleep(1000)

}
// http://bit.ly/2BLHJS7