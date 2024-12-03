import scala.collection.mutable.ArrayBuffer
import scala.io.Source

@main
def day2(): Unit = {
    val lines: Array[String] = Source.fromResource("input2.txt").getLines().toArray
    val values: Array[Array[Int]] = lines.map(x => x.split(" ").map(_.toInt))

    val faultyLines = ArrayBuffer[Array[Int]]()
    val reportsCount: Int = values.map { arr =>
        // if for all 2 adjacent elements of the array the cond. match, then sum else return 0
        if (lineIsSafe(arr))
            1
        else
            faultyLines += arr
            0
    }.sum
    println(reportsCount)

    val fixedLinesCount = faultyLines.map { arr =>
        val buffer = arr.toBuffer
        getFirstUnsafeElement(arr).foreach(buffer.remove)
        if (lineIsSafe(buffer.toArray))
            1
        else
            0
    }.sum

    println(fixedLinesCount)
    println(fixedLinesCount + reportsCount)
}

def getFirstUnsafeElement(line: Array[Int]): Option[Int] = {
    line.sliding(2).zipWithIndex.collectFirst {
        case (Array(x, y), index) if !(1 <= math.abs(x - y) && math.abs(x - y) <= 3) => index
        case (Array(x, y), index) if index > 0 && ((line(index - 1) < x && x > y) || (line(index - 1) > x && x < y)) => index
    }
}

def lineIsSafe(line:Array[Int]): Boolean =
    if (line.sliding(2).forall { case Array(x, y) => x < y && (1 <= math.abs(x - y) && math.abs(x - y) <= 3) }
        || line.sliding(2).forall { case Array(x, y) => x > y && (1 <= math.abs(x - y) && math.abs(x - y) <= 3) })
        true
    else
        false