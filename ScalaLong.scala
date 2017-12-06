import java.util.Calendar
object AppS5 {
    def main(args: Array[String]) {
        val quantity = args(0).toInt
        val numberDec = Math.pow(2, quantity).toLong -1
        println(numberDec)
        var sum0, sum1 = 0
        val arr = Array.fill(quantity){scala.util.Random.nextInt(10)}
        arr.foreach({a => print(a + " ")})

        var difference = Long.MaxValue

        println("\n" + Calendar.getInstance.getTime)

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
        println(difference)
        println(Calendar.getInstance.getTime)
    }
    def getBit (nDec: Long, nBin: Long) =  {
        val x = (nDec >> nBin) & 1
        x
    }
}

// todo replace toBigInt to Long
