fun main() {
    val input = generateSequence(::readLine){readLine()}.joinToString("")
    val lines = input.split("\n")
    val count = lines.count()
    println("$count lines")

    val day22 = Day22(lines)

   // val result1 = day22.solvePart1()
   // println("result #1 is $result1")

    val result2 = day22.solvePart2(false)
    println("result #2 is $result2")
}