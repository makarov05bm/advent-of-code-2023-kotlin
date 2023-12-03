import java.io.File

fun main(args: Array<String>) {
    var linesCount = 0
    val len = (File("src/data_day_03.txt").useLines { it.firstOrNull() })!!.length

    File("src/data_day_03.txt").useLines { lines -> lines.forEach { line ->
        linesCount++
    } }

    var mtx = Array(linesCount) { Array<Char>(len) {'x'} }

    var lineIndex = 0
    var gearPartNumbers = mutableListOf<List<Int>>()

    File("src/data_day_03.txt").useLines { lines -> lines.forEach { line ->
        for ((charIndex, char) in line.withIndex()) {
            mtx[lineIndex][charIndex] = char
        }

        lineIndex++
    } }

    for (i in 0..<linesCount) {
        for (j in 0..<len) {
            if (mtx[i][j] == '*') {
                var count = 0
                var partNumbers = mutableListOf<Int>()

                if ((mtx[i][j+1].code in 48..57)) {
                    count++

                    var k = j+1
                    var tmpStr = ""
                    while (mtx[i][k].code in 48..57 && (k < len)) {
                        tmpStr += mtx[i][k]
                        k++
                    }

                    partNumbers.add(tmpStr.toInt())
                }

                if ((mtx[i][j-1].code in 48..57)) {
                    count++

                    var k = j-1
                    var tmpStr = ""
                    while ((k >= 0) && mtx[i][k].code in 48..57) {
                        tmpStr += mtx[i][k]
                        k--
                    }

                    partNumbers.add(tmpStr.reversed().toInt())
                }

                if ((mtx[i-1][j].code in 48..57)) {
                    count++

                    var tmpStr = ""

                    if (mtx[i-1][j-2].code in 48..57 && (mtx[i-1][j-1].code in 48..57)) {
                        tmpStr += mtx[i-1][j-2]
                        tmpStr += mtx[i-1][j-1]
                        tmpStr += mtx[i-1][j]
                    } else if (mtx[i-1][j-1].code in 48..57) {
                        tmpStr += mtx[i-1][j-1]

                        if (mtx[i-1][j+1].code in 48..57) {
                            tmpStr += mtx[i-1][j]
                            tmpStr += mtx[i-1][j+1]
                        } else {
                            tmpStr += mtx[i-1][j]
                        }
                    } else {
                        var k = j

                        while (mtx[i-1][k].code in 48..57 && (k <= len)) {
                            tmpStr += mtx[i-1][k]
                            k++
                        }
                    }

                    partNumbers.add(tmpStr.toInt())
                } else {
                    if ((mtx[i-1][j+1].code in 48..57)) {
                        count++

                        var tmpStr = ""
                        var k = j+1

                        while (mtx[i-1][k].code in 48..57 && (k < len)) {
                            tmpStr += mtx[i-1][k]
                            k++
                        }

                        partNumbers.add(tmpStr.toInt())
                    }

                    if ((mtx[i-1][j-1].code in 48..57)) {
                        count++

                        var tmpStr = ""
                        var k = j-1

                        while ((k >= 0) && mtx[i-1][k].code in 48..57) {
                            tmpStr += mtx[i-1][k]
                            k--
                        }

                        partNumbers.add(tmpStr.reversed().toInt())
                    }
                }

                if ((mtx[i+1][j].code in 48..57)) {
                    count++

                    var tmpStr = ""

                    if (mtx[i+1][j-2].code in 48..57 && (mtx[i+1][j-1].code in 48..57)) {
                        tmpStr += mtx[i+1][j-2]
                        tmpStr += mtx[i+1][j-1]
                        tmpStr += mtx[i+1][j]
                    } else if (mtx[i+1][j-1].code in 48..57) {
                        tmpStr += mtx[i+1][j-1]

                        if (mtx[i+1][j+1].code in 48..57) {
                            tmpStr += mtx[i+1][j]
                            tmpStr += mtx[i+1][j+1]
                        } else {
                            tmpStr += mtx[i+1][j]
                        }
                    } else {
                        var k = j

                        while (mtx[i+1][k].code in 48..57 && (k <= len)) {
                            tmpStr += mtx[i+1][k]
                            k++
                        }
                    }

                    partNumbers.add(tmpStr.toInt())

                } else {
                    if ((mtx[i+1][j+1].code in 48..57)) {
                        count++

                        var tmpStr = ""
                        var k = j+1

                        while (mtx[i+1][k].code in 48..57 && (k <= len)) {
                            tmpStr += mtx[i+1][k]
                            k++
                        }

                        partNumbers.add(tmpStr.toInt())
                    }

                    if ((mtx[i+1][j-1].code in 48..57)) {
                        count++

                        var tmpStr = ""
                        var k = j-1

                        while ((k >= 0) && mtx[i+1][k].code in 48..57) {
                            tmpStr += mtx[i+1][k]
                            k--
                        }

                        partNumbers.add(tmpStr.reversed().toInt())
                    }
                }

                if (count == 2) {
                    gearPartNumbers.add(partNumbers)
                }
            }
        }
    }

    var sum = 0

    for (set in gearPartNumbers) {
        sum += set[0] * set[1]
    }

    println("sum of gear part numbers: $sum")
}
