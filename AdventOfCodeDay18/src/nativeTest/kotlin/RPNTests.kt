import kotlin.test.Test
import kotlin.test.assertEquals

class RPNTests {
    @Test
    fun rpnBasicAddTest() {
        val expression = "3 4 +"
        val actual = evaluateRPN(expression)
        val expected = 7L
        assertEquals(expected, actual)
    }

    @Test
    fun rpnBasicMultTest() {
        val expression = "3 4 *"
        val actual = evaluateRPN(expression)
        val expected = 12L
        assertEquals(expected, actual)
    }

    @Test
    fun complicatedTest() {
        val expression = "3 4 2 * +"
        val actual = evaluateRPN(expression)
        val expected = 11L
        assertEquals(expected, actual)
    }

    @Test
    fun complicated2Test() {
        val expression = "3 2 6 * +"
        val actual = evaluateRPN(expression)
        val expected = 15L
        assertEquals(expected, actual)
    }

    @Test
    fun finalTest() {
        val expression = "3 4 2 * 1 5 + 2 3 * * + +"
        val actual = evaluateRPN(expression)
        val expected = 47L
        assertEquals(expected, actual)
    }
}