import java.io.File

fun main(args: Array<String>) {
    val linesList = mutableListOf<List<List<Any>>>()

    File("src/data_day04.txt").useLines { lines -> lines.forEach { line ->
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

    var totalCount = 0

    for (game in linesList) {
        val winningNumbers = game[0]
        val myNumbers = game[1]
        var count = 0

        for (i in myNumbers.indices) {
            for (j in winningNumbers.indices) {
                if (myNumbers[i] == winningNumbers[j]) {
                    if (count == 0) {
                        count++
                    } else {
                        count *= 2
                    }
                }
            }
        }
        totalCount += count
    }

    println("total points: $totalCount")
}
