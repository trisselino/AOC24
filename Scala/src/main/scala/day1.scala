import scala.io.Source

@main
def day1(): Unit =
    val lines: Array[String] = Source.fromResource("input1.txt").getLines().toArray
    val leftNumbersSorted: Array[Int] = lines.map(x => x.split("\\s+").head.toInt).sorted
    val rightNumbersSorted: Array[Int] = lines.map(x => x.split("\\s+").last.toInt).sorted

    val distances = leftNumbersSorted.zip(rightNumbersSorted).map { case (a, b) => math.abs(a - b) }
    println(distances.sum) //println(distances.foldLeft(0)(_ + _))

    val similarityScore = leftNumbersSorted.map(x => x * rightNumbersSorted.count(_ == x))
    printf("similarityScore: %d\n", similarityScore.sum)