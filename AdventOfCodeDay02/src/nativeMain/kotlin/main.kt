import kotlin.system.measureTimeMicros

fun main() {
    val input = generateSequence(::readLine){readLine()}.joinToString("")
    val lines = input.split("\n")
    val count = lines.count()
    println("$count lines")

    val day02 = Day02(lines)

    val time1 = measureTimeMicros {
        val result1 = day02.solvePart1()
        println("Result #1 = $result1")
    }
    println("Results #1 in $time1 ms")
    println("")


    val time2 = measureTimeMicros {
        val result2 = day02.solvePart2()
        println("Result #2 = $result2")
    }
    println("Result #2 in $time2 ms")





}

