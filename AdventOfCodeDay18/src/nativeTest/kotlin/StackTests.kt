import kotlin.test.Test
import kotlin.test.assertEquals

class StackTests {
    @Test
    fun basicTest() {
        val stack = Stack<Int>()
        val actual = stack.count()
        val expected = 0
        assertEquals(expected, actual)
    }

    @Test
    fun pushPopTest() {
        val stack = Stack<Int>()
        val expected = 42
        stack.push(expected)
        val actual = stack.pop()
        assertEquals(expected, actual)
    }

    @Test
    fun pushPeekTest() {
        val stack = Stack<Int>()
        stack.push(100)
        val expected = 42
        stack.push(expected)
        val actual = stack.peek()
        assertEquals(expected, actual)

        val actualCount = stack.count()
        val expectedCount = 2
        assertEquals(expectedCount, actualCount)
    }

    @Test
    fun sequenceTest() {
        val list = listOf(1, 2, 3, 4, 5)
        val stack = Stack<Int>()
        list.forEach {stack.push(it)}
        val actual = stack.asSequence()
        val expected = list.asReversed().asSequence()
        assertSequenceEquals(expected, actual)
    }

    @Test
    fun makeSureItIsAStack() {
        val stack = Stack<Int>()
        stack.push(5)
        stack.push(10)
        val actual1 = stack.pop()
        assertEquals(10, actual1)

        val actual2 = stack.pop()
        assertEquals(5, actual2)
    }
}

fun <T> assertSequenceEquals(a:Sequence<T>, b:Sequence<T>) :Boolean {
    val ia = a.iterator()
    val ib = b.iterator()
    while (ia.hasNext()) {
        if (!ib.hasNext()) return false
        val na = ia.next()
        val nb = ib.next()
        if (na != nb) return false
    }

    return !ib.hasNext()
}