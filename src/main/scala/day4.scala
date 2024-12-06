import scala.io.Source

@main
def day4(): Unit = {
    val input = Source.fromResource("input4.txt").getLines().toArray
    val rows, columns = 140

    val letterArray = Array.ofDim[Char](rows, columns)

    for (i <- 0 until rows) {
        for (j <- 0 until columns)
        letterArray(i)(j) = input(i).charAt(j)
    }

}