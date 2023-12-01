package a01.p2

import a01.input

val digitsMap = mapOf(
    "1" to 1,
    "2" to 2,
    "3" to 3,
    "4" to 4,
    "5" to 5,
    "6" to 6,
    "7" to 7,
    "8" to 8,
    "9" to 9,
    "one" to 1,
    "two" to 2,
    "three" to 3,
    "four" to 4,
    "five" to 5,
    "six" to 6,
    "seven" to 7,
    "eight" to 8,
    "nine" to 9
)

val digits = digitsMap.keys

// return if current index is at start of a digit and a digit itself
fun checkAnyDigit(line: String, index: Int): Pair<Boolean, String> {
    for (digit in digits) {
        val foundIt = line.regionMatches(index, digit, 0, digit.length)
        if (foundIt) {
            return Pair(true, digit)
        }
    }
    return Pair(false, "")
}

fun findFirstDigit(line: String): String {
    for (index in line.indices) {
        val (isDigit, digit) = checkAnyDigit(line, index)
        if (isDigit) {
            return digit;
        }
    }

    return ""
}

fun findLastDigit(line: String): String {
    for (index in line.indices.reversed()) {
        val (isDigit, digit) = checkAnyDigit(line, index)
        if (isDigit) {
            return digit;
        }
    }

    return ""
}

fun String.toDigit() = digitsMap[this]

fun getCalibrationValue(line: String) = "${findFirstDigit(line).toDigit()}${findLastDigit(line).toDigit()}".toInt()

fun getCalibrationValues(lines: List<String>) = lines.map(::getCalibrationValue)

fun main(args: Array<String>) {
    val testInput = "two1nine\n" +
            "eightwothree\n" +
            "abcone2threexyz\n" +
            "xtwone3four\n" +
            "4nineeightseven2\n" +
            "zoneight234\n" +
            "7pqrstsixteen"

    val calibrationValues = sequenceOf(29, 83, 13, 24, 42, 14, 76)
    val calculatedCalibrationValues = getCalibrationValues(testInput.split('\n'))

    println(calculatedCalibrationValues)
    println(calculatedCalibrationValues.sum())

    assert(calibrationValues.equals(calculatedCalibrationValues))

    println("Real deal")
    val realCalibrationValues = getCalibrationValues(input.split('\n'))
    println(realCalibrationValues.sum())
}
