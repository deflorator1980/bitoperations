import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._
import java.util.Date

object AppSparkLong {
    def main(args: Array[String]) {
        val quantity = args(0).toInt
        val arr = Array.fill(quantity){scala.util.Random.nextInt(10)}
        scala.tools.nsc.io.File("arr_long.txt").writeAll(arr.mkString(" "))
        val numberDec = Math.pow(2, quantity).toLong - 1

        val sc = new SparkContext(new SparkConf().setAppName("AppSpark").setMaster("local[4]"))
        val arrs = sc.textFile("arr_long.txt").flatMap(l => l.split(" "))
        val arrn = arrs.zipWithIndex.map(_.swap)

        println(numberDec)
        var sum0, sum1 = 0

        var difference = Long.MaxValue

        println("\n" + new Date)
        scala.tools.nsc.io.File("result_long.txt").writeAll(numberDec + "\n")
        scala.tools.nsc.io.File("result_long.txt").appendAll((new Date).toString + "\n")
        scala.tools.nsc.io.File("result_long.txt").appendAll(arr.mkString(" ") + "\n")

        var nDec = 1L
        while(nDec <= numberDec){
          for (nBin <- 0 until arrn.count.toInt) {
                if (getBit(nDec, nBin) == 0) {
                    sum0 += arrn.lookup(nBin).map(_.toInt).lift(0).get
                } else if (getBit(nDec, nBin) == 1) {
                    sum1 += arrn.lookup(nBin).map(_.toInt).lift(0).get
                }
            }

            var dif = Math.abs(sum1 - sum0)

            if (dif < difference) {
                difference = dif
            }

            sum0 = 0; sum1 = 0
            nDec += 1
        }
        println(difference)
        scala.tools.nsc.io.File("result_long.txt").appendAll(difference + "\n")
        println(new Date)
        scala.tools.nsc.io.File("result_long.txt").appendAll((new Date).toString)

        sc.stop
    }

    def getBit (nDec: Long, nBin: Long) =  {
        val x = (nDec >> nBin) & 1
        x
    }
}    

// $SPARK_HOME/bin/spark-submit --class "AppSpark" target/scala-2.11/appspark_2.11-1.0.jar 8
// $SPARK_HOME/bin/spark-submit --class "AppSparkLong" target/scala-2.11/appspark_2.11-1.0.jar 8