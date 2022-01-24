class Day03(private val lines:List<String>) {
    val width = lines[0].length
    val height = lines.count()

    fun solvePart1() = countTrees(3, 1, lines)
    fun solvePart1a() = treesOnSlope(3 to 1)
    private fun treesOnSlope(slope:Pair<Int,Int>) = path(slope).count{ it in lines }
    private operator fun List<String>.contains(location: Pair<Int, Int>): Boolean =
        this[location.second][location.first % width] == '#'
    private fun path(slope: Pair<Int,Int>): Sequence<Pair<Int, Int>> = generateSequence(Pair(0,0)) { prev ->
        (prev + slope).takeIf { next -> next.second < height }
    }
    private operator fun Pair<Int,Int>.plus(that: Pair<Int,Int>): Pair<Int,Int> =
        Pair(this.first+that.first, this.second+that.second)

    fun solvePart2() = listOf(1 to 1, 3 to 1, 5 to 1, 7 to 1, 1 to 2)
        .map { countTrees(it.first, it.second, lines) }
        .multiply()

    fun solvePart2a(): Long =
        listOf(1 to 1, 3 to 1, 5 to 1, 7 to 1, 1 to 2)
            .map { treesOnSlope(it).toLong() }
            .multiply()

    private fun countTrees(moveRight: Int, moveDown: Int, lines: List<String>) : Long {
        var xPos = 0
        var treesFound = 0L
        var yPos = 0
        while(yPos < height) {
            val line = lines[yPos]
            val c = line[xPos]
            if (c == '#') treesFound++
            xPos += moveRight
            xPos %= width
            yPos += moveDown
        }
        return treesFound
    }
}

fun List<Long>.multiply():Long = this.reduce { x, y -> x * y }
