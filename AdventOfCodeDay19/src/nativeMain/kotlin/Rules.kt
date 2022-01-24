interface Rule
data class Leaf(val c:Char):Rule
data class MultipleRule(val refId:Int):Rule

// given an expression string, and the current rule to look at and the current position of
// the expression to investigate, and the rules, return a list of positions that this rule
// matches up to.
fun String.matches(ruleId:Int, rules:Map<Int, List<List<Rule>>>, position:Int=0): List<Int> {
    return rules.getValue(ruleId).flatMap { listOfRules ->
        var positions = listOf(position)
        listOfRules.forEach { rule ->
            positions = positions.mapNotNull {
                when {
                    rule is Leaf && this.getOrNull(position) == rule.c ->
                        listOf(position + 1)
                    rule is MultipleRule ->
                        this.matches(rule.refId, rules, position)
                    else -> null
                }
            }.flatten()
        }
        positions
    }
}

class Rules(rules: List<String>) {
    private val data = rules.mapNotNull { buildRule(it) }.associate { k -> k }
    fun count() = data.count()

    fun match(expression: String) = data.keys.map { ruleId ->
        if (match(expression, ruleId).any{expression.length == it}) ruleId else -1
    }.filter{-1 != it}

    fun match(expression: String, ruleId:Int) = expression.matches(ruleId, data)

    companion object {
        internal fun buildRule(line: String): Pair<Int, List<List<Rule>>>? {
            //println("buildRule($line)")
            val colonIdx = line.indexOf(':')
            if (colonIdx == -1) return null

            val idx = line.substring(0, colonIdx).toInt()
            //println("idx = $idx")

            val startQuoteIdx = line.indexOf('\"', 0, false)
            if (startQuoteIdx != -1) {
                val endQuoteIdx = line.indexOf('\"', startQuoteIdx+1, false)
                if (endQuoteIdx == -1) return null
                val letter = line.substring(startQuoteIdx+1 until endQuoteIdx)
                //println("Building Letter")
                //println("letter = $letter")
                return Pair(idx, listOf(listOf(Leaf(letter[0]))))
            }

            val pipeIdx = line.indexOf('|')
            if (pipeIdx == -1) {
                //println("building One")
                val aa = line.substring(colonIdx+1).trim()
                //println("mustMatch = $aa")
                val numS = aa.split(' ').map{it.toInt()}
                return Pair(idx, listOf(numS.map{MultipleRule(it)}))
            }

            //println("Building Either")
            val aa = line.substring(colonIdx+1 until pipeIdx-1).trim()
            //println("matchThis = $aa")
            val numAs = aa.split(' ').map{it.toInt()}
            val bb = line.substring(pipeIdx+1).trim()
            //println("orMatchThis = $bb")
            val numBs = bb.split(' ').map{it.toInt()}
            return Pair(idx, listOf(numAs.map{MultipleRule(it)}, numBs.map{MultipleRule(it)}))
        }
    }
}


