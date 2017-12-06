import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._
import java.util.Calendar

object AppSpark {
    def main(args: Array[String]) {
        val quantity = Integer.parseInt(args(0))
        val arr = Array.fill(quantity){scala.util.Random.nextInt(10)}
        scala.tools.nsc.io.File("arr.txt").writeAll(arr.mkString(" "))
        val numberDec = BigDecimal(2).pow(quantity) - 1

        val sc = new SparkContext(new SparkConf().setAppName("AppSpark").setMaster("local[4]"))
        val arrs = sc.textFile("arr.txt").flatMap(l => l.split(" "))
        // val arr = arrs.collect.map(n => n.toInt)
        val arrn = arrs.zipWithIndex.map(_.swap)

        println(numberDec)
        var sum0, sum1 = 0

        var difference = Double.PositiveInfinity

        val start = Calendar.getInstance.getTime
        println("\n" + start)
        scala.tools.nsc.io.File("result.txt").writeAll(start.toString + "\n")
        scala.tools.nsc.io.File("result.txt").appendAll(arr.mkString(" ") + "\n")

        // for (nDec <- 1 to numberDec) {
        var nDec = BigInt(1)
        while(nDec <= numberDec.toBigInt ){
        //   for (nBin <- 0 until arr.length) {
        //   for (nBin <- 0 until arrs.count.toInt) {
          for (nBin <- 0 until arrn.count.toInt) {
                if (getBit(nDec, nBin) == 0) {
                    // sum0 += arr(nBin)
                    sum0 += arrn.lookup(nBin).map(_.toInt).lift(0).get
                } else if (getBit(nDec, nBin) == 1) {
                    // sum1 += arr(nBin)
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
        println(difference.toInt)
        scala.tools.nsc.io.File("result.txt").appendAll(difference.toInt + "\n")
        val stop = Calendar.getInstance.getTime
        println(stop)
        scala.tools.nsc.io.File("result.txt").appendAll(stop.toString)

        sc.stop
    }

    def getBit (nDec: BigInt, nBin: Int) =  {
        val x = (nDec >> nBin) & 1
        x
    }
}    

// arrs.count
// $SPARK_HOME/bin/spark-submit --class "AppSpark" target/scala-2.11/appspark_2.11-1.0.jar 8
// zipWithIndex.map(_.swap).lookup(14)
// $SPARK_HOME/bin/spark-shell