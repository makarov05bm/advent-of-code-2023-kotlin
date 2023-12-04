import java.io.File

fun main(args: Array<String>) {
    var linesCount = 0
    val len = (File("src/data_day_3.txt").useLines { it.firstOrNull() })!!.length

    File("src/data_day_03.txt").useLines { lines -> lines.forEach { line ->
        linesCount++
    } }

    var mtx = Array(linesCount) { Array<Char>(len) {'x'} }

    var lineIndex = 0
    var partNumbers = mutableListOf<Int>()

    File("src/data_day_03.txt").useLines { lines -> lines.forEach { line ->
        for ((charIndex, char) in line.withIndex()) {
            mtx[lineIndex][charIndex] = char
        }

        lineIndex++
    } }

    for (i in 0..<linesCount) {
        for (j in 0..<len) {
            var strNumber = ""
            if (mtx[i][j].code in 48..57) {
                if (i in 1..<linesCount-1 && j in 1..<len-1) {
                    if (
                        (mtx[i][j - 1].code !in 48..57 && mtx[i][j - 1] != '.') ||
                        (mtx[i][j + 1].code !in 48..57 && mtx[i][j + 1] != '.') ||
                        (mtx[i - 1][j].code !in 48..57 && mtx[i - 1][j] != '.') ||
                        (mtx[i + 1][j].code !in 48..57 && mtx[i + 1][j] != '.') ||
                        (mtx[i - 1][j - 1].code !in 48..57 && mtx[i - 1][j - 1] != '.') ||
                        (mtx[i - 1][j + 1].code !in 48..57 && mtx[i - 1][j + 1] != '.') ||
                        (mtx[i + 1][j - 1].code !in 48..57 && mtx[i + 1][j - 1] != '.') ||
                        (mtx[i + 1][j + 1].code !in 48..57 && mtx[i + 1][j + 1] != '.')
                    ) {
                        if ((mtx[i][j-2].code in 48..57) && (mtx[i][j-1].code in 48..57)) {
                            strNumber += mtx[i][j-2]
                            strNumber += mtx[i][j-1]
                            strNumber += mtx[i][j]
                            strNumber += "|"
                        } else if ((mtx[i][j-1].code in 48..57) && (mtx[i][j+1].code in 48..57)) {
                            strNumber += mtx[i][j-1]
                            strNumber += mtx[i][j]
                            strNumber += mtx[i][j+1]
                            mtx[i][j+1] = '.'
                            strNumber += "|"
                        } else if (mtx[i][j-1].code in 48..57) {
                            strNumber += mtx[i][j-1]
                            strNumber += mtx[i][j]
                            strNumber += "|"
                        } else if ((mtx[i][j+1].code in 48..57) && (mtx[i][j+2].code in 48..57)) {
                            strNumber += mtx[i][j]
                            strNumber += mtx[i][j+1]
                            strNumber += mtx[i][j+2]
                            mtx[i][j+1] = '.'
                            mtx[i][j+2] = '.'
                            strNumber += "|"
                        } else if (mtx[i][j+1].code in 48..57) {
                            strNumber += mtx[i][j]
                            strNumber += mtx[i][j+1]
                            mtx[i][j+1] = '.'
                            strNumber += "|"
                        } else {
                            strNumber += mtx[i][j]
                            strNumber += "|"
                        }
                    }
                } else {
                    if (((i == 0) || (i == linesCount - 1)) && (j != 0) && (j != len - 1)) {
                        if (
                            ((i == 0) && mtx[0][j+1].code !in 48..57 && mtx[0][j+1] != '.') ||
                            ((i == 0) && mtx[1][j].code !in 48..57 && mtx[1][j] != '.') ||
                            ((i == 0) && (mtx[1][j+1].code !in 48..57 && mtx[1][j+1] != '.')) ||
                            ((i == 0) && (mtx[1][j-1].code !in 48..57 && mtx[1][j-1] != '.')) ||
                            ((i == linesCount - 1) && mtx[i-1][j].code !in 48..57 && mtx[i-1][j] != '.') ||
                            ((i == linesCount - 1) && (mtx[i-1][j+1].code !in 48..57 && mtx[i-1][j+1] != '.')) ||
                            ((i == linesCount - 1) && (mtx[i-1][j-1].code !in 48..57 && mtx[i-1][j-1] != '.'))

                        ) {
                            if ((j >= 2) && (mtx[i][j-2].code in 48..57) && (mtx[i][j-1].code in 48..57)) {
                                strNumber += mtx[i][j-2]
                                strNumber += mtx[i][j-1]
                                strNumber += mtx[i][j]
                                strNumber += "|"
                            } else if ((mtx[i][j-1].code in 48..57) && (mtx[i][j+1].code in 48..57)) {
                                strNumber += mtx[i][j-1]
                                strNumber += mtx[i][j]
                                strNumber += mtx[i][j+1]
                                mtx[i][j+1] = '.'
                                strNumber += "|"
                            } else if ((mtx[i][j-1].code in 48..57)) {
                                strNumber += mtx[i][j-1]
                                strNumber += mtx[i][j]
                                strNumber += "|"
                            } else if ((mtx[i][j+1].code in 48..57) && (mtx[i][j+2].code in 48..57)) {
                                strNumber += mtx[i][j]
                                strNumber += mtx[i][j+1]
                                strNumber += mtx[i][j+2]
                                mtx[i][j+1] = '.'
                                mtx[i][j+2] = '.'
                                strNumber += "|"
                            } else if (mtx[i][j+1].code in 48..57) {
                                strNumber += mtx[i][j]
                                strNumber += mtx[i][j+1]
                                mtx[i][j+1] = '.'
                                strNumber += "|"
                            } else {
                                strNumber += mtx[i][j]
                                strNumber += "|"
                            }
                        }
                    }
                }
            }


            val items = strNumber.split("|")

            for (i in items) {
                if (i.isNotEmpty()) {
                    partNumbers.add(i.toInt())
                }
            }
        }
    }

    for (i in partNumbers) {
        println(i)
    }

    println("Sum of part numbers: ${partNumbers.sum()}")
}
