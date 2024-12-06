import scala.io.Source


val input: Array[String] = Source.fromResource("input4.txt").getLines().toArray
val rows, columns = 140
val letterArray: Array[Array[Char]] = Array.ofDim[Char](rows, columns)

@main
def day4(): Unit = {
    for (i <- 0 until rows) {
        for (j <- 0 until columns)
            letterArray(i)(j) = input(i).charAt(j)
    }

    var counter = 0

    for (i <- 0 until rows)
        for (j <- 0 until columns)
            if (letterArray(i)(j) == 'X')
                counter += checkForXmas(i, j)

    printf("Counter: %d\n", counter)
}

def checkForXmas(row: Int, column: Int): Int =
    var temp = 0

    // left to right
    if (column + 3 < letterArray(row).length && letterArray(row)(column+1) == 'M')
        if (letterArray(row)(column+2) == 'A')
            if (letterArray(row)(column+3) == 'S')
                temp += 1

    // right to left
    if (column - 3 >= 0 && letterArray(row)(column-1) == 'M')
        if (letterArray(row)(column-2) == 'A')
            if (letterArray(row)(column-3) == 'S')
                temp += 1

    // bottom up
    if (row - 3 >= 0 && letterArray(row-1)(column) == 'M')
        if (letterArray(row-2)(column) == 'A')
            if (letterArray(row-3)(column) == 'S')
                temp += 1

    // top down
    if (row + 3 < letterArray.length && letterArray(row+1)(column) == 'M')
        if (letterArray(row+2)(column) == 'A')
            if (letterArray(row+3)(column) == 'S')
                temp += 1

    // diagonal right down
    if (row + 3 < letterArray.length && column + 3 < letterArray(row).length && letterArray(row+1)(column+1) == 'M')
        if (letterArray(row+2)(column+2) == 'A')
            if (letterArray(row+3)(column+3) == 'S')
                temp += 1

    // diagonal right up
    if (row - 3 >= 0 && column + 3 < letterArray(row).length && letterArray(row-1)(column+1) == 'M')
        if (letterArray(row-2)(column+2) == 'A')
            if (letterArray(row-3)(column+3) == 'S')
                temp += 1

    // diagonal left up
    if (row - 3 >= 0 && column - 3 >= 0 && letterArray(row-1)(column-1) == 'M')
        if (letterArray(row-2)(column-2) == 'A')
            if (letterArray(row-3)(column-3) == 'S')
                temp += 1

    // diagonal left down
    if (row + 3 < letterArray.length && column - 3 >= 0 && letterArray(row+1)(column-1) == 'M')
        if (letterArray(row+2)(column-2) == 'A')
            if (letterArray(row+3)(column-3) == 'S')
                temp += 1

    temp


