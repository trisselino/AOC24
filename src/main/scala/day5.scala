import scala.io.Source

@main
def day5(): Unit =
    val rawInput = Source.fromResource("input5.txt").getLines().toArray
    val tuples = rawInput.take(rawInput.indexOf("")).map(_.split("""|"""))
    println(tuples.mkString("Array(", ", ", ")"))
