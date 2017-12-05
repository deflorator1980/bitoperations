import java.util.Calendar
object AppS5 {
    def main(args: Array[String]) {
        val quantity = Integer.parseInt(args(0))
        // val numberDec = Math.pow(2, quantity).toInt -1
        val numberDec = BigDecimal(2).pow(quantity) - 1
        println(numberDec)
        var sum0, sum1 = 0
        val arr = Array.fill(quantity){scala.util.Random.nextInt(10)}
        arr.foreach({a => print(a + " ")})

        // var difference = Int.MaxValue
        var difference = Double.PositiveInfinity

        val start = Calendar.getInstance.getTime
        println("\n" + start)

        // for (nDec <- 1 to numberDec) {
        var nDec = BigInt(1)
        while(nDec <= numberDec.toBigInt ){
          for (n_bin <- 0 until arr.length) {
                if (getBit(nDec, n_bin) == 0) {
                    sum0 += arr(n_bin)
                } else if (getBit(nDec, n_bin) == 1) {
                    sum1 += arr(n_bin)
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
        val stop = Calendar.getInstance.getTime
        println(stop)
    }
    // def getBit (nDec: Int, nBin: Int) =  {
    def getBit (nDec: BigInt, nBin: Int) =  {
        val x = (nDec >> nBin) & 1
        x
    }
}
