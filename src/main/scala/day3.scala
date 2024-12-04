import scala.io.Source

@main
def day3() : Unit =
    // part one
    val input = Source.fromResource("input3.txt").getLines().toArray

    val inputRegex = """mul\((\d{1,3}),(\d{1,3})\)""".r
    val mulMatches = input.flatMap(line => inputRegex.findAllMatchIn(line).map(x => x.matched)) // (_.matched)

    val valueRegex = """(\d{1,3})""".r
    val values: Array[Int] = mulMatches.flatMap(str => valueRegex.findAllMatchIn(str).map(_.matched.toInt))

    val sum = values.grouped(2).collect{
        case Array(a,b) => a * b
    }.sum
    printf("Sum: %d\n", sum)

    //part two
    val regex2 = """don't\(\).*?do\(\)""".r
    val firstfilteredInput = regex2.replaceAllIn(input.mkString, "")
    val dontEOF = """don't\(\).*""".r
    val filteredInput = {
        // necessary because don't() appears two times before EOF in input3 -> not a universal solution!
        val index = firstfilteredInput.lastIndexOf("don't()")
        val upToLastOcc = firstfilteredInput.substring(0, index)
        val secondToLastIndex = upToLastOcc.lastIndexOf("don't()")
        val upToSecondLastOcc = upToLastOcc.substring(0, secondToLastIndex)
        upToSecondLastOcc
    }

    // the rest is practically the same as in part one
    val filteredMatches = inputRegex.findAllMatchIn(filteredInput).map(_.matched).toArray
    val filteredValues =  filteredMatches.flatMap(str => valueRegex.findAllMatchIn(str).map(_.matched.toInt))
    val filteredSum = filteredValues.grouped(2).collect{
        case Array(x,y) => x * y
    }.sum
    printf("Filter Sum: %d\n", filteredSum)