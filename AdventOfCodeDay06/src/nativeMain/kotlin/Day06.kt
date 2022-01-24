class Day06(private val lines:List<String>) {

    fun solvePart1():Int = linesToGroupsOfStrings(lines)
        .asSequence()
        .map{it.joinToString("")}
        .map{it.toSet()}
        .sumOf{it.count()}

    fun solvePart2(): Int = linesToGroupsOfStrings(lines)
        .map{ groupOfLines: List<String> ->
            groupOfLines
                .map{it.toSet()}
                .reduce{ a, b->a.intersect(b)}
        }
        .sumOf{it.count()}

    private fun linesToGroupsOfStrings(lines:List<String>):List<List<String>> =
        doWorkLinesToListOfStrings(lines, emptyList())

    private tailrec fun doWorkLinesToListOfStrings(lines:List<String>, currentResults:List<List<String>>):List<List<String>> {
        if (lines.isEmpty()) return currentResults
        val group = lines.takeWhile{line -> line.isNotBlank() }
        val restOfLines = lines.drop(group.count()).dropWhile{line->line.isBlank()}
        return doWorkLinesToListOfStrings(restOfLines, currentResults + listOf(group))
    }

}