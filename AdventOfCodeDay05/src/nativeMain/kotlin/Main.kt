import kotlin.system.measureTimeMicros

fun main() {
    val lines = readAllLinesFromStdInput()
    val count = lines.count()
    println("$count lines")

    val day05 = Day05(lines)

    val time1 = measureTimeMicros {
        val result1 = day05.solvePart1()
        println("Result #1 = $result1")
    }
    println("Results #1 in $time1 ms")

    //val time1a = measureTimeMicros {
    //    val result1a = day07.solvePart1a()
    //    println("Result #1a = $result1a")
    //}
    //println("Results #1a in $time1a ms")

    val time2 = measureTimeMicros {
        val result2 = day05.solvePart2()
        println("Result #2 = $result2")
    }
    println("Results #2 in $time2 ms")

    //val time2a = measureTimeMicros {
    //    val result2a = day07.solvePart2a()
    //    println("Result #2a = $result2a")
    //}
    //println("Results #2a in $time2a ms")
}

fun readAllLinesFromStdInput():List<String> {
    val ret = emptyList<String>().toMutableList()
    while (true) {
        val line = readlnOrNull() ?: break
        ret.add(line)
    }
    return ret
}
