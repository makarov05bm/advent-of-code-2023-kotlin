import java.io.File

class Set(var red: Int, var green: Int, var blue: Int)
class Game() {
    val sets = mutableListOf<Set>()
}

fun main(args: Array<String>) {
    val linesList = mutableListOf<String>()

    File("src/data_day_2.txt").useLines { lines -> lines.forEach { line -> linesList.add(line) } }

    val games = mutableListOf<List<String>>()

    for (line in linesList) {
        val set = line.split(";")
        games.add(set)
    }

    val gameList = mutableListOf<Game>()

    for (game in games) {
        var gameStruct = Game()

        for (set in game) {
            val gameSet = Set(0, 0, 0)

            val cleanString = set.split(":")
            var cleanSet = cleanString[0]

            if (cleanString.size > 1) {
                cleanSet = cleanString[1]
            }

            val setItems = mutableListOf<Set>()

            val colors = cleanSet.split(",")

            for (item in colors) {
                val number = if (item[2].code in 48..57) {
                    (item[1].toString() + item[2].toString()).toInt()
                } else {
                    item[1].toString().toInt()
                }

                if (item.contains("red")) {
                    gameSet.red = number
                } else if (item.contains("green")) {
                    gameSet.green = number
                } else {
                    gameSet.blue = number
                }
            }

            gameStruct.sets.add(gameSet)
        }
        gameList.add((gameStruct))
    }

    val elfGames = mutableListOf<Int>()

    for ((gameId, game) in gameList.withIndex()) {
        var isSuccess = true

        for (set in game.sets) {
            if (set.red > 12 || set.green > 13 || set.blue > 14) {
                isSuccess = false
            }
        }

        if (isSuccess) {
            elfGames.add(gameId + 1)
        }
    }

    println(elfGames.sum())
}
