class Day24(private val lines: List<String>) {
    private val tiles = Tiles().also { it.arrangeTiles(lines) }
    fun solvePart1() = tiles.countBlack()
    fun solvePart2(days:Int):Int {
        var workingTiles = tiles.copy()
        repeat(days)
        {
            workingTiles = workingTiles.performDailyFlip()
            val day = it+1
            val count = workingTiles.countBlack()
            println("Day $day: $count")
        }

        return workingTiles.countBlack()
    }
}