fun main() {
    val input = generateSequence(::readLine){readLine()}.joinToString("")
    val lines = input.split("\n")
    val count = lines.count()
    println("$count lines")

    val idxOfBlankLine = lines.indexOf("")
    val ruleLines = lines.take(idxOfBlankLine)
    println("${ruleLines.count()} lines")

    val messages = lines.takeLast(count - idxOfBlankLine - 1)
    println("${messages.count()} messages")

    val rules = SolveDay19(lines)
    val matchZero  = rules.solvePart1()
    println("$matchZero messages match 0")
    val match2Zero  = rules.solvePart2()
    println("$match2Zero messages match 0 part 2")
}