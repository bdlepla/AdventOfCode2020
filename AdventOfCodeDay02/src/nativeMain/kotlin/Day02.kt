class Day02(private val lines:List<String>) {
    private val regex = "(\\d+)-(\\d+)\\s(\\w):\\s(\\w+).*".toRegex()
    fun solvePart1():Int =
        lines
            .mapNotNull { regex.find(it) }
            .count { mr ->
                val (pos1, pos2, character, password) = mr.destructured
                val num = password.count { it == character[0] }
                val range = pos1.toInt()..pos2.toInt()
                num in range
            }

    fun solvePart2():Int {
        val badPasswords = lines.map{regex.find(it)}.filter{passwordMatches(it!!)}
        return badPasswords.count()
    }

    companion object {
        private fun passwordMatches(matchResults: MatchResult) : Boolean {
            val (pos1, pos2, character, password) = matchResults.destructured
            val idx1 = pos1.toInt() - 1
            val idx2 = pos2.toInt() - 1
            val c = character[0]
            val passLen = password.length
            val found1 = idx1 < passLen && password[idx1] == c
            val found2 = idx2 < passLen && password[idx2] == c
            return found1 xor found2
        }
    }
}