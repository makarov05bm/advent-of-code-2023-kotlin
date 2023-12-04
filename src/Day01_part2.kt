import java.io.File

fun main(args: Array<String>) {
    val linesList = mutableListOf<String>()

    File("src/data_day_1.txt").useLines { lines -> lines.forEach { item -> linesList.add(item) } }

    val numbers = mutableListOf<Int>()

    for (line in linesList) {
        var i = 0
        var k = line.length - 1
        var tmpStr = ""
        var endStr = ""

        while (
            ((!tmpStr.contains("one")) &&
            (!tmpStr.contains("two")) &&
            (!tmpStr.contains("three")) &&
            (!tmpStr.contains("four")) &&
            (!tmpStr.contains("five")) &&
            (!tmpStr.contains("six")) &&
            (!tmpStr.contains("seven")) &&
            (!tmpStr.contains("eight")) &&
            (!tmpStr.contains("nine"))) &&
            (i <= line.length-1)
        ) {
            tmpStr += line[i]
            i++
        }

//        println(tmpStr)

        var newStr = ""

        for (char in tmpStr) {
            if (char.code in 48..57) {
                if (
                    tmpStr.indexOf(char) < tmpStr.indexOf("one") ||
                    tmpStr.indexOf(char) < tmpStr.indexOf("two") ||
                    tmpStr.indexOf(char) < tmpStr.indexOf("three") ||
                    tmpStr.indexOf(char) < tmpStr.indexOf("four") ||
                    tmpStr.indexOf(char) < tmpStr.indexOf("five") ||
                    tmpStr.indexOf(char) < tmpStr.indexOf("six") ||
                    tmpStr.indexOf(char) < tmpStr.indexOf("seven") ||
                    tmpStr.indexOf(char) < tmpStr.indexOf("eight") ||
                    tmpStr.indexOf(char) < tmpStr.indexOf("nine")
                ) {
                    newStr += char.toString()

                    break
                } else if (
                    (tmpStr.indexOf("one") == -1) &&
                    (tmpStr.indexOf("two") == -1) &&
                    (tmpStr.indexOf("three") == -1) &&
                    (tmpStr.indexOf("four") == -1) &&
                    (tmpStr.indexOf("five") == -1) &&
                    (tmpStr.indexOf("six") == -1) &&
                    (tmpStr.indexOf("seven") == -1) &&
                    (tmpStr.indexOf("eight") == -1) &&
                    (tmpStr.indexOf("nine") == -1)
                ) {
                    newStr += char.toString()

                    break
                }
            }
        }

        if (newStr.isEmpty()) {
            if (tmpStr.contains("one")) {
                newStr += "1"
            } else if (tmpStr.contains("two")) {
                newStr += "2"
            } else if (tmpStr.contains("three")) {
                newStr += "3"
            } else if (tmpStr.contains("four")) {
                newStr += "4"
            } else if (tmpStr.contains("five")) {
                newStr += "5"
            } else if (tmpStr.contains("six")) {
                newStr += "6"
            } else if (tmpStr.contains("seven")) {
                newStr += "7"
            } else if (tmpStr.contains("eight")) {
                newStr += "8"
            } else if (tmpStr.contains("nine")) {
                newStr += "9"
            }
        }

        while (
            ((!endStr.contains("one".reversed())) &&
                    (!endStr.contains("two".reversed())) &&
                    (!endStr.contains("three".reversed())) &&
                    (!endStr.contains("four".reversed())) &&
                    (!endStr.contains("five".reversed())) &&
                    (!endStr.contains("six".reversed())) &&
                    (!endStr.contains("seven".reversed())) &&
                    (!endStr.contains("eight".reversed())) &&
                    (!endStr.contains("nine".reversed()))) &&
            (k >= 0)
        ) {
            endStr += line[k]
            k--
        }

        endStr = endStr.reversed()

        var found = false
        var indice = endStr.length - 1
        while (!found && indice >= 0) {
            if (endStr[indice].code in 48..57) {
                if (
                    endStr.indexOf(endStr[indice]) > endStr.indexOf("one") ||
                    endStr.indexOf(endStr[indice]) > endStr.indexOf("two") ||
                    endStr.indexOf(endStr[indice]) > endStr.indexOf("three") ||
                    endStr.indexOf(endStr[indice]) > endStr.indexOf("four") ||
                    endStr.indexOf(endStr[indice]) > endStr.indexOf("five") ||
                    endStr.indexOf(endStr[indice]) > endStr.indexOf("six") ||
                    endStr.indexOf(endStr[indice]) > endStr.indexOf("seven") ||
                    endStr.indexOf(endStr[indice]) > endStr.indexOf("eight") ||
                    endStr.indexOf(endStr[indice]) > endStr.indexOf("nine")
                ) {
                    newStr += endStr[indice].toString()
                    found = true
                }
            }

            indice--
        }

        if (newStr.length == 1) {
            if (endStr.contains("one")) {
                newStr += "1"
            } else if (endStr.contains("two")) {
                newStr += "2"
            } else if (endStr.contains("three")) {
                newStr += "3"
            } else if (endStr.contains("four")) {
                newStr += "4"
            } else if (endStr.contains("five")) {
                newStr += "5"
            } else if (endStr.contains("six")) {
                newStr += "6"
            } else if (endStr.contains("seven")) {
                newStr += "7"
            } else if (endStr.contains("eight")) {
                newStr += "8"
            } else if (endStr.contains("nine")) {
                newStr += "9"
            }
        }

        numbers.add(newStr.toInt())
    }

    println("sum: ${numbers.sum()}")
}
