import java.util.Calendar
object AppS2 {
    def main(args: Array[String]) {
        val quantity = Integer.parseInt(args(0))
        val numberDec = Math.pow(2, quantity).toInt -1
        println(numberDec)
        var sum0, sum1 = 0
        val arr = Array.fill(quantity){scala.util.Random.nextInt(10)}
        arr.foreach({a => print(a + " ")})

        var difference = Int.MaxValue

        val start = Calendar.getInstance.getTime
        println("\n" + start)

        for (n_dec <- 1 to numberDec) {
          for (n_bin <- 0 until arr.length) {
                if (getBit(n_dec, n_bin) == 0) {
                    sum0 += arr(n_bin)
                } else if (getBit(n_dec, n_bin) == 1) {
                    sum1 += arr(n_bin)
                }
            }

            var dif = Math.abs(sum1 - sum0)

            if (dif < difference) {
                difference = dif
            }

            sum0 = 0; sum1 = 0
        }
        println(difference)
        val stop = Calendar.getInstance.getTime
        println(stop)
    }
    def getBit (nDec: Int, nBin: Int) =  {
        val x = (nDec >> nBin) & 1
        x
    }
}
