object AppS2 {
    def main(args: Array[String]) {
        val quantity = Integer.parseInt(args(0))
        var arrSize = ""
        1 to quantity foreach {_ => arrSize += "1"}
        val numberDec = Integer.parseInt(arrSize, 2)
        
    }
}
