class Stack<T> {
    private val data = mutableListOf<T>()
    fun count() = data.count()
    fun push(i:T) {
        data.add(i)
    }

    fun pop():T {
        if (data.isEmpty()) throw Exception("Stack is empty on Pop")
        val ret = data.last()
        data.removeLast()
        return ret
    }

    fun peek():T {
        if (data.isEmpty()) throw Exception("Stack is empty on Peek")
        return data.last()
    }

    fun asSequence(): Sequence<T> = data.asReversed().asSequence()
}

