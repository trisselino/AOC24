import scala.collection.mutable.ArrayBuffer
import scala.io.Source

@main
def day2(): Unit = {
    val lines: Array[String] = Source.fromResource("input2.txt").getLines().toArray
    val values: Array[Array[Int]] = lines.map(x => x.split(" ").map(_.toInt))

    val faultyLines = ArrayBuffer[Array[Int]]()

    val reportsCount: Int = values.map { line =>
        // if for all 2 adjacent elements of the array the cond. match, then sum else return 0
        if (lineIsSafe(line))
            1
        else
            faultyLines += line
            0
    }.sum
    printf("Reportscount: %d\n", reportsCount)

    val fixedLinesCount = faultyLines.count { line =>
        line.indices.exists { i =>
            val buffer = line.toBuffer
            buffer.remove(i)
            lineIsSafe(buffer.toArray)
        }
    }

    printf("Fixed lines: %d\n", fixedLinesCount)
    printf("Together: %d\n", fixedLinesCount + reportsCount)
}

def lineIsSafe(line:Array[Int]): Boolean =
    if (line.sliding(2).forall { case Array(x, y) => x < y && (1 <= math.abs(x - y) && math.abs(x - y) <= 3) }
        || line.sliding(2).forall { case Array(x, y) => x > y && (1 <= math.abs(x - y) && math.abs(x - y) <= 3) })
        true
    else
        false