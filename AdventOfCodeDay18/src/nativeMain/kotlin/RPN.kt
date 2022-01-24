fun evaluateRPN(expression: String) : Long {
    val tokens = expression.split(' ')
    //println("evaluateRPN(${expression} split into ${tokens.count()} parts)")
    val stack = Stack<String>()

    tokens.forEach{
        when (it) {
            "+", "*" -> {
                val a1 = stack.pop()
                val a2 = stack.pop()
                val  first = a1.toLong()
                val second = a2.toLong()
                val result = if (it == "+") first + second else first * second
                stack.push(result.toString())
            }
            else -> {
                //println("pushing $it")
                stack.push(it)
            }
        }
    }
    return stack.pop().toLong()
}