package a01.p1

import a01.input

fun getCalibrationValue(line: String) = "${line.first(Char::isDigit)}${line.last(Char::isDigit)}".toInt()

fun getCalibrationValues(lines: List<String>) = lines.map(::getCalibrationValue)

fun main(args: Array<String>) {
    val testInput = "1abc2\n" +
            "pqr3stu8vwx\n" +
            "a1b2c3d4e5f\n" +
            "treb7uchet"

    val calibrationValues = sequenceOf(12, 38, 15, 77)
    val calculatedCalibrationValues = getCalibrationValues(testInput.split('\n'))

    println(calculatedCalibrationValues)
    println(calculatedCalibrationValues.sum())

    assert(calibrationValues.equals(calculatedCalibrationValues))

    println("Real deal")
    val realCalibrationValues = getCalibrationValues(input.split('\n'))
    println(realCalibrationValues.sum())
}
