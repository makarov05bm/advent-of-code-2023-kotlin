import java.io.File

fun main(args: Array<String>) {
    val linesList = mutableListOf<List<List<Any>>>()
    var linesCount = 0

    File("src/data_day_4.txt").useLines { lines -> lines.forEach { line ->
        linesCount++
        val strs = line.split(" ")
        val numbers = mutableListOf<List<Int>>()
        var mine = false
        val firstPart = mutableListOf<Int>()
        val secondPart = mutableListOf<Int>()

        for (item in strs) {
            if ((item.matches(Regex("([0-9]|[1-9][0-9]|[1-9][0-9][0-9])"))) || (item.contains("|"))) {
                if (item.contains("|")) {
                    mine = true
                } else {
                    if (!mine) {
                        firstPart.add(item.toInt())
                    } else {
                        secondPart.add(item.toInt())
                    }
                }
            }
        }

        numbers.add(firstPart)
        numbers.add(secondPart)

        linesList.add(numbers)
    } }

    var cardOcc = Array<Int>(linesCount) { 0 }

    for ((index, game) in linesList.withIndex()) {
        val winningNumbers = game[0]
        val myNumbers = game[1]
        var count = 0

        for (i in myNumbers.indices) {
            for (j in winningNumbers.indices) {
                if (myNumbers[i] == winningNumbers[j]) {
                    count++
                }
            }
        }

        for (i in index..index+count) {
            if (i == index) {
                cardOcc[i] += 1
            } else {
                cardOcc[i] += cardOcc[index]
            }
        }
    }

    println("total points: ${cardOcc.sum()}")
}
