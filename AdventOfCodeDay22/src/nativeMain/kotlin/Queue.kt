class Queue<T> {
    val data = mutableListOf<T>() // add to the back; remove from the front
    fun enqueue(e:T) = data.add(e)
    fun dequeue():T = data.removeAt(0)
    fun isEmpty():Boolean = data.isEmpty()
    fun isNotEmpty() = !isEmpty()
    override fun toString() = data.toString()
    fun count() = data.count()
    fun asSequence() = data.asSequence()
}