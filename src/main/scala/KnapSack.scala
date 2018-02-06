object KnapSack {
  def main(args: Array[String]) {
    val quantity = Integer.parseInt(args(0))
    val arr = Array.fill(quantity) {
      scala.util.Random.nextInt(10)
    }
    arr.foreach(a => print(a + " "))
    val sum = arr.reduce(_ + _)
    println("\nsum: " + sum)
    val W = sum / 2
    val n = arr.length
    val one = knapSack(W, arr, n)
    println("result: " + (sum - (one*2)))
  }
  def knapSack(W: Int, arr: Array[Int], n: Int) = {
    val K = Array.ofDim[Int](n + 1, W + 1)
    for (i <- 0 to n) {
      for (w <- 0 to W) {
        if (i == 0 || w == 0)
          K(i)(w) = 0;
        else if (arr(i - 1) <= w)
          K(i)(w) = Math.max(arr(i - 1) + K(i - 1)(w - arr(i - 1)), K(i - 1)(w))
        else K(i)(w) = K(i - 1)(w)
      }
    }
    K(n)(W)
  }

}

