
fun noPrecedence(a: String, b:String) = 0
fun addPrecedence(a: String, b:String) = if (a == b) 0 else if (a == "+") -1 else 1

fun convertToRPN(expression: String) = convertToRPNWork(expression, ::noPrecedence)
fun convertToRPN2(expression: String) = convertToRPNWork(expression, ::addPrecedence)

fun convertToRPNWork(expression: String, determinePrecedence: (String, String)->Int): String {
    val output = mutableListOf<String>()
    val operators = Stack<String>()
    val tokens = expression.split(' ')
    //println("convertToRPN '$expression' split into ${tokens.count()} parts")
    tokens.forEach {
        //println("Parsing token '$it'")

        when (it) {
            "*", "+"  -> {
                //println("pushing operator $it")

                while (operators.count() > 0 && operators.peek() != "(") {
                    val stackPeek = operators.peek()

                    val prec = determinePrecedence(it, stackPeek)
                    if (prec >= 0) { // meaning stackPeek has precedence over it, or they have same precedence
                        output.add(operators.pop())
                    } else break
                }

                operators.push(it)
            }
            else -> {
                val lp = it.count{ c -> c == '('}
                //println("Found $lp left parentheses")
                repeat(lp) {
                    //println("pushing '(' to operators")
                    operators.push("(")
                }

                val theRest = it.replace('(', ' ')
                    .replace(')', ' ').trim()
                //println("adding $theRest to output")
                output.add(theRest)

                val rp = it.count{c -> c == ')'}
                //println("Found $rp right parentheses")
                repeat(rp) {
                    while (operators.peek() != "(") {
                        val operator = operators.pop()
                        //println("popped operator $operator and added to output")
                        output.add(operator)

                    }
                    //println("found '('")
                    operators.pop()
                }
            }
        }

        //println("after token $it, output = ${output.joinToString(",")} operators=${operators.asSequence().joinToString(",") }")
    }
    output.addAll(operators.asSequence())
    val ret = output.joinToString(" ")
    //println("result = $ret")
    return ret
}