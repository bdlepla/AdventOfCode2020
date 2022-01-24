class Day05(private val lines:List<String>) {
    fun solvePart1():Int {
        return lines
            .map(::decodeLine)
            .maxOf{it}
    }

    fun solvePart2():Int {
        val seats = lines.map(::decodeLine).sorted()
        return seats
            .zipWithNext()
            .first{it.second - it.first > 1}.first + 1
    }

    private fun decodeLine(line:String):Int {
        val setOfOnes = setOf('B', 'R')
        val numS = line.map { c -> if (c in setOfOnes) '1' else '0' }
        return numS.joinToString("").toInt(2)
    }
}