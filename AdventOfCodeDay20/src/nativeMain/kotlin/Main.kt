fun main() {
    val input = generateSequence(::readLine){readLine()}.joinToString("")
    val lines = input.split("\n")
    val count = lines.count()
    println("$count lines")

    val day20 = Day20(lines)
    val numTiles = day20.tiles.count()
    println("$numTiles tiles to processes")

    //val result1 = day20.solvePart1()
    //println("Part1 result is $result1")

    val result2 = day20.solvePart2()
    println("Part2 result is $result2")


}