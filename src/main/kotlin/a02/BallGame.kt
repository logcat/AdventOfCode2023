package a02

val redLimit = 12
val greenLimit = 13
val blueLimit = 14

data class Grab(val red: Int, val green: Int, val blue: Int) {
    fun isValid(): Boolean {
        return red <= redLimit && green <= greenLimit && blue <= blueLimit
    }
}

data class Game(val id: Int, val grabs: List<Grab>) {
    fun isValid(): Boolean {
        return grabs.all(Grab::isValid)
    }

    fun minSetOfCubes(): MinSetOfCubes {
        return grabs
            .fold(MinSetOfCubes(0, 0, 0)) { minSetOfCubes, grab ->
                minSetOfCubes.adjustToGrab(grab)
            }
    }
}

data class MinSetOfCubes(var red: Int, var green: Int, var blue: Int) {
    fun adjustToGrab(grab: Grab): MinSetOfCubes {
        if (grab.red > red) {
            red = grab.red
        }

        if (grab.green > green) {
            green = grab.green
        }

        if (grab.blue > blue) {
            blue = grab.blue
        }

        return this
    }

    fun power(): Int {
        val red = if (this.red == 0) 1 else this.red
        val green = if (this.green == 0) 1 else this.green
        val blue = if (this.blue == 0) 1 else this.blue
        return red * green * blue
    }
}

// 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green
// 1 red, 2 green, 6 blue
// no order!
fun parseGrab(grabLine: String): Grab{
    var red = 0
    var green = 0
    var blue = 0
    grabLine.split(", ").forEach {
        val (nString, color) = it.split(" ")
        val n = nString.toInt()
        when (color) {
            "red" -> red = n
            "green" -> green = n
            "blue" -> blue = n
        }
    }
    return Grab(red, green, blue)
}

// 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green
fun parseGrabs(grabsLine: String): List<Grab> {
    return grabsLine.split("; ").map(::parseGrab)
}

// Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green
fun parseGame(gameLine: String): Game {
    val idStartIndex = "Game ".length
    val idEndIndex = gameLine.indexOf(": ")
    val grabsStartIndex = idEndIndex + 2
    val id = gameLine.substring(idStartIndex, idEndIndex).toInt()
    val grabsLine = gameLine.substring(grabsStartIndex, gameLine.length)

    return Game(id, parseGrabs(grabsLine))
}
fun parseInput(input: String): List<Game> {
    return input.split("\n").map(::parseGame)
}
fun main(args: Array<String>) {
    println(testInput)
    val testGames = parseInput(testInput)
    println(testGames)

    val sumOfTestIds = testGames.filter(Game::isValid).sumOf(Game::id)
    println(sumOfTestIds)

    println("Real deal")
    val games = parseInput(input)
    val sumOfIds = games.filter(Game::isValid).sumOf(Game::id)
    println(sumOfIds)

    println("Fixing water")
    val sumOfTestPowers = testGames.map(Game::minSetOfCubes).sumOf(MinSetOfCubes::power)
    println(sumOfTestPowers)

    println("Real deal")
    val sumOfPowers = games.map(Game::minSetOfCubes).sumOf(MinSetOfCubes::power)
    println(sumOfPowers)

}

val testInput = "Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green\n" +
        "Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue\n" +
        "Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red\n" +
        "Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red\n" +
        "Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green"