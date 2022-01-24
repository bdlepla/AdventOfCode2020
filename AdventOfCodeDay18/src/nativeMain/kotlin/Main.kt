fun main() {
    val input = generateSequence(::readLine){readLine()}.joinToString("")
    val lines = input.split("\n")
    val count = lines.count()
    println("$count lines")

    val result = lines.sumOf {evaluateExpression(it)}
    println("no precedence result = $result")

    val result2 = lines.sumOf{evaluateExpression2(it)}
    println("addition precedence result = $result2")

}

fun evaluateExpression(equation: String) = evaluateRPN(convertToRPN(equation))
fun evaluateExpression2(equation: String) = evaluateRPN(convertToRPN2(equation))


