import java.io.File

fun main(args: Array<String>) {
    val linesList = mutableListOf<String>()

    File("src/data_day_1.txt").useLines { lines -> lines.forEach { item -> linesList.add(item) } }

    val finalList = mutableListOf<Int>()

    for (line in linesList) {
        var strDigit = ""
        val digitsList = mutableListOf<Char>()

        for (char in line) {
            if (char.code in 48..57) {
                digitsList.add(char)
            }
        }

        strDigit = digitsList[0].toString() + digitsList[digitsList.size - 1].toString()

        finalList.add(strDigit.toInt())
    }

    println("Sum: ${finalList.sum()}")
}
