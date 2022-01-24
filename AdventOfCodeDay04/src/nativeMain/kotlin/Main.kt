import kotlin.system.measureTimeMicros

fun main() {
    val input = generateSequence(::readLine){readLine()}
        .joinToString("")
    val lines = input.split("\n")
    val count = lines.count()
    println("$count lines")

    val day04 = Day04(lines)

    val time1 = measureTimeMicros {
        val result1 = day04.solvePart1()
        println("Result #1 = $result1")
    }
    println("Results #1 in $time1 ms")

    /* val time1a = measureTimeMicros {
         val result1a = day03.solvePart1a()
         println("Result #1a = $result1a")
     }
     println("Results #1a in $time1a ms")
     println("")
*/
     val time2 = measureTimeMicros {
         val result2 = day04.solvePart2()
         println("Result #2 = $result2")
     }
     println("Result #2 in $time2 ms")
/*
     val time2a = measureTimeMicros {
         val result2a = day03.solvePart2a()
         println("Result #2a = $result2a")
     }
     println("Result #2a in $time2a ms")*/
}