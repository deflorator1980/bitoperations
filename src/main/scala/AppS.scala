object AppS {
  val quantity = 4
  val number_bin = 15
  def main(args: Array[String]) {
    var sum0, sum1 = 0
    println(getBit(1, 0))
  }

  def getBit (n_b: Int, n_d: Int) =  {
    val x = (n_b >> n_d) & 1
    x + quantity
  }
}
//todo scala.util.Random.nextInt(10)