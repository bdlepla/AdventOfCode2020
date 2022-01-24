fun main() {
    val input = generateSequence(::readLine){readLine()}.joinToString("")
    val lines = input.split("\n")
    val count = lines.count()
    println("$count lines")

    val day24 = Day24(lines)
    val result1 = day24.solvePart1()
    println("Result #1 is $result1")

    val result2 = day24.solvePart2(100)
    println("Result #2 is $result2")

}