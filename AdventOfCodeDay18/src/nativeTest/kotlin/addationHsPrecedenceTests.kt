import kotlin.test.Test
import kotlin.test.assertEquals

class AdditionHasPrecedenceTests {
    @Test
    fun basicTest() {
        val expression = "5 * 2 + 1"
        val actual = evaluateExpression2(expression)
        val expected = 15L
        assertEquals(expected, actual)
    }
}