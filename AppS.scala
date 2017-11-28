object AppS {
  val quantity = 5
  val number_dec = 31                 // Integer.parseInt("11111",2)
  def main(args: Array[String]) {
    var sum0, sum1 = 0
    // val arr = Array.fill(quantity){scala.util.Random.nextInt(10)}
    val arr = Array(4,5,7,1)
    val res = new Array[Int](number_dec)
    arr.foreach({a => print(a + " ")})

    for (n_dec <- 1 to number_dec) {
      for (n_bin <- 0 until arr.length) {
        if (getBit(n_dec, n_bin) == 0) {
          sum0 += arr(n_bin)
        } else if (getBit(n_dec, n_bin) == 1) {
          sum1 += arr(n_bin)
        }
      }
      res(n_dec-1) = Math.abs(sum1 - sum0)
      sum0 = 0; sum1 = 0
    }
    res.foreach({r => print(r + " ")})
    println("\n" + res.reduce(_ min _))
  }

  def getBit (n_dec: Int, n_bin: Int) =  {
    val x = (n_dec >> n_bin) & 1
    x
  }
}