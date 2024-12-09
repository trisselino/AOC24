import scala.collection.mutable.ArrayBuffer
import scala.io.Source

@main
def day5(): Unit =
    val rawInput = Source.fromResource("input5.txt").getLines().toArray
    val rules = rawInput.take(rawInput.indexOf("")).map{ str =>
        val parts = str.split("\\|")
        (parts(0).toInt, parts(1).toInt)
    }
    println(rules.mkString("Array(", ", ", ")"))

    val lists = rawInput.drop(rawInput.indexOf("")+1).map{ str =>
        str.split(",").map(_.toInt).toList
    }
    println(lists.mkString("Array(", ", ", ")"))

    def isObserved(ordering: (Int, Int), list: List[Int]): Boolean = {
        val indices = ordering.toList.map(list.indexOf(_))
        indices.contains(-1) || indices == indices.sorted
    }
    val (good, bad) = lists.partition(list => rules.forall(isObserved(_, list)))
    println(good.map(list => list(list.length/2)).sum)




