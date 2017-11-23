object AppS {
  val quantity = 4
  val number_bin = 15
  def main(args: Array[String]) {
    var sum0, sum1 = 0
    println(getBit(1, 0))
    val arr = Array.fill(quantity){scala.util.Random.nextInt(10)}
    arr.foreach({a => print(a + " ")})

    /**
      * todo for(i <- 1 until arr.length) {println(arr(i))
      */
  }

  def getBit (n_b: Int, n_d: Int) =  {
    val x = (n_b >> n_d) & 1
    x
  }
}
//todo look up