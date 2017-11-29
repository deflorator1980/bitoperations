object AppS {
  // val quantity = 11
  val quantity = 4
  // val number_dec = 2047                 // Integer.parseInt("11111111111",2)
  val number_dec = 15                 
  def main(args: Array[String]) {
    var sum0, sum1 = 0
    // val arr = Array.fill(quantity){scala.util.Random.nextInt(10)}
    val arr = Array(4,5,7,1)
    // val arr = Array(1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 10)
    // val arr = Array(4)
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
    println("\n" + res.reduce(_ min _))
    // res.foreach({r => print(r + " ")})
  }

  def getBit (n_dec: Int, n_bin: Int) =  {
    val x = (n_dec >> n_bin) & 1
    x
  }
}
// for (x <- 0 until arr.length) {q +="1"}
//  1 to arr.length foreach {_ => q += "1"}
// (1 to arr.length).foreach(_ => q += "1")
