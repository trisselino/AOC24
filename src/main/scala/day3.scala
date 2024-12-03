import scala.io.Source
import scala.util.matching.Regex

@main
def day3() : Unit =
    val input = Source.fromResource("input3.txt").getLines().toArray

    val inputRegex = """mul\((\d{1,3}),(\d{1,3})\)""".r
    val mulMatches = input.flatMap (line => inputRegex.findAllMatchIn(line).map(x => x.matched) // (_.matched)
    )

    val valueRegex = """(\d{1,3})""".r
    val numbers: Array[Int] = mulMatches.flatMap(str => valueRegex.findAllMatchIn(str).map(_.matched.toInt))

    val sum = numbers.grouped(2).collect{
        case Array(a,b) => a * b
    }.sum
    printf("Sum: %d\n", sum)


//  val matches = input.flatMap { line =>
//    regex.findAllMatchIn(line).map(_.matched)
//  }
