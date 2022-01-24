class Day07(lines:List<String>) {
    private val bags = parseLines(lines)
    fun solvePart1() = findParents().count() - 1

    fun solvePart2() = baggageCost() - 1

    private fun parseLines(lines: List<String>): Set<Rule> =
        lines.filterNot { it.contains("no other") }
            .flatMap { row ->
                val parts = row.replace(unusedText, "").split(whitespace)
                val parent = parts.take(2).joinToString(" ")
                parts.drop(2)
                    .windowed(3, 3, false)
                    .map { child ->
                        Rule(
                            parent,
                            child.first().toInt(),
                            child.drop(1).joinToString(" ")
                        )
                    }
            }
            .toSet()

    private fun findParents(bag: String = "shiny gold"): Set<String> =
        bags
            .filter { it.child == bag }
            .flatMap { findParents(it.parentName) }.toSet() + bag

    private fun baggageCost(bag: String = "shiny gold"): Int =
        bags
            .filter { it.parentName == bag }
            .sumOf { it.quantity * baggageCost(it.child) } + 1

    companion object {
        private val unusedText = """bags|bag|contain|,|\.""".toRegex()
        private val whitespace = """\s+""".toRegex()
    }
}