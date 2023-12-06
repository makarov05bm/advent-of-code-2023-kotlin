import java.io.File
import kotlin.math.abs

class Seed() {
    var seed: Long = 0
    var soil: Long = 0
    var fertilizer: Long = 0
    var water: Long = 0
    var light: Long = 0
    var temperature: Long = 0
    var humidity: Long = 0
    var location: Long = 0
}

fun main(args: Array<String>) {
    val linesList = mutableListOf<String>()

    File("src/data_day_5.txt").useLines { lines -> lines.forEach { line ->
        if (!line.contains("map")) {
            linesList.add(line)
        }
    } }

    val finalList = mutableListOf<List<String>>()
    val tmpList = mutableListOf<String>()

    for (item in linesList) {
        if (item != "") {
            tmpList.add(item)
        } else {
            val copyList = tmpList.toMutableList()
            finalList.add(copyList)

            tmpList.clear()
        }
    }

    val seeds = finalList[0][0].replace("seeds: ", "").split(" ").map {
            seed -> seed.toLong()
    }

    finalList.removeAt(0)

    val mapList = mutableListOf<List<List<Long>>>()

    for (item in finalList) {
        val semiTotalList = mutableListOf<List<Long>>()
        for (str in item) {
            val tmpList = mutableListOf<Long>()
            val numbers = str.split(" ")

            for (number in numbers) {
                tmpList.add(number.toLong())
            }

            semiTotalList.add(tmpList)
        }

        mapList.add(semiTotalList)
    }

    val listOfSeed = mutableListOf<Seed>()

    fun findLowest(firstSrc: Long) {
        var src: Long = firstSrc
        var seed = Seed()

        seed.seed = src

        var seedFound = false
        var soilSrc: Long = 0
        for (seedMap in mapList[0]) {
            for (item in seedMap) {
                if (src in seedMap[1]..<(seedMap[1] + seedMap[2])) {
                    seedFound = true
                    soilSrc = seedMap[0] + abs(src - seedMap[1])
                    break
                }
            }
        }

        if (seedFound) {
            seed.soil = soilSrc
        } else {
            seed.soil = src
        }

        src = seed.soil

        var soilFound = false
        var fertilizerSrc: Long = 0
        for (soilMap in mapList[1]) {
            for (item in soilMap) {
                if (src in soilMap[1]..<(soilMap[1] + soilMap[2])) {
                    soilFound = true
                    fertilizerSrc = soilMap[0] + abs(src - soilMap[1])
                    break
                }
            }
        }

        if (soilFound) {
            seed.fertilizer = fertilizerSrc
        } else {
            seed.fertilizer = src
        }

        src = seed.fertilizer

        var fertilizerFound = false
        var waterSrc: Long = 0
        for (fertilizerMap in mapList[2]) {
            for (item in fertilizerMap) {
                if (src in fertilizerMap[1]..<(fertilizerMap[1] + fertilizerMap[2])) {
                    fertilizerFound = true
                    waterSrc = fertilizerMap[0] + abs(src - fertilizerMap[1])
                    break
                }
            }
        }

        if (fertilizerFound) {
            seed.water = waterSrc
        } else {
            seed.water = src
        }

        src = seed.water

        var waterFound = false
        var lightSrc: Long = 0
        for (waterMap in mapList[3]) {
            for (item in waterMap) {
                if (src in waterMap[1]..<(waterMap[1] + waterMap[2])) {
                    waterFound = true
                    lightSrc = waterMap[0] + abs(src - waterMap[1])
                    break
                }
            }
        }

        if (waterFound) {
            seed.light = lightSrc
        } else {
            seed.light = src
        }

        src = seed.light

        var lightFound = false
        var temperatureSrc: Long = 0
        for (temperatureMap in mapList[4]) {
            for (item in temperatureMap) {
                if (src in temperatureMap[1]..<(temperatureMap[1] + temperatureMap[2])) {
                    lightFound = true
                    temperatureSrc = temperatureMap[0] + abs(src - temperatureMap[1])
                    break
                }
            }
        }

        if (lightFound) {
            seed.temperature = temperatureSrc
        } else {
            seed.temperature = src
        }

        src = seed.temperature

        var temperatureFound = false
        var humiditySrc: Long = 0
        for (humidityMap in mapList[5]) {
            for (item in humidityMap) {
                if (src in humidityMap[1]..<(humidityMap[1] + humidityMap[2])) {
                    temperatureFound = true
                    humiditySrc = humidityMap[0] + abs(src - humidityMap[1])
                    break
                }
            }
        }

        if (temperatureFound) {
            seed.humidity = humiditySrc
        } else {
            seed.humidity = src
        }

        src = seed.humidity

        var humidityFound = false
        var locationSrc: Long = 0
        for (locationMap in mapList[6]) {
            for (item in locationMap) {
                if (src in locationMap[1]..<(locationMap[1] + locationMap[2])) {
                    humidityFound = true
                    locationSrc = locationMap[0] + abs(src - locationMap[1])
                    break
                }
            }
        }

        if (humidityFound) {
            seed.location = locationSrc
        } else {
            seed.location = src
        }

        listOfSeed.add(seed)
    }

    for (seed in seeds) {
        findLowest(seed)
    }

    var lowest = listOfSeed[0].location
    var seedId: Long = 0
    for (seed in listOfSeed) {
        if (seed.location < lowest) {
            lowest = seed.location
            seedId = seed.seed
        }
    }

    println("$seedId -> $lowest")
}
