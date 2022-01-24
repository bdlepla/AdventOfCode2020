import kotlin.test.Test
import kotlin.test.assertEquals

class BasicTests {

    @Test
    fun basicAdditionTest() {
        val expression = "1 + 2"
        val actual = evaluateExpression(expression)
        val expected = 3L
        assertEquals(expected, actual)
    }

    @Test
    fun basicMultiplicationTest() {
        val expression = "3 * 6"
        val actual = evaluateExpression(expression)
        val expected = 18L
        assertEquals(expected, actual)
    }

    @Test
    fun moreComplicatedTest() {
        val expression = "3 + 2 * 6"
        val actual = evaluateExpression(expression)
        val expected = 30L
        assertEquals(expected, actual)
    }

    @Test
    fun sample1Test() {
        val expression = "1 + 2 * 3 + 4 * 5 + 6"
        val actual = evaluateExpression(expression)
        val expected = 71L
        assertEquals(expected, actual)
    }

    @Test
    fun basicParenthesesTest() {
        val expression = "(2 + 6)"
        val actual = evaluateExpression(expression)
        val expected = 8L
        assertEquals(expected, actual)
    }

    @Test
    fun moreComplicatedWithParenthesesTest() {
        val expression = "3 + (2 * 6)"
        val actual = evaluateExpression(expression)
        val expected = 15L
        assertEquals(expected, actual)
    }

    @Test
    fun sample2Test() {
        val expression = "2 * 3 + (4 * 5)"
        val actual = evaluateExpression(expression)
        val expected = 26L
        assertEquals(expected, actual)
    }

    @Test
    fun sample3Test() {
        val expression = "5 + (8 * 3 + 9 + 3 * 4 * 3)"
        val actual = evaluateExpression(expression)
        val expected = 437L
        assertEquals(expected, actual)
    }

    @Test
    fun sample4Test() {
        val expression = "5 * 9 * (7 * 3 * 3 + 9 * 3 + (8 + 6 * 4))"
        val actual = evaluateExpression(expression)
        val expected = 12240L
        assertEquals(expected, actual)
    }

    @Test
    fun parentheses4Test() {
        val expression = "(2)"
        val actual = evaluateExpression(expression)
        val expected = 2L
        assertEquals(expected, actual)
    }

    @Test
    fun parentheses5Test() {
        val expression = "(1 + 1) * (2 + 2)"
        val actual = evaluateExpression(expression)
        val expected = 8L
        assertEquals(expected, actual)
    }

    @Test
    fun parentheses6Test() {
        val expression = "((1 + 1) * (2 + 2))"
        val actual = evaluateExpression(expression)
        val expected = 8L
        assertEquals(expected, actual)
    }

    @Test
    fun parentheses7Test() {
        val expression = "((1 + 1) * (2 + 2) + 1)"
        val actual = evaluateExpression(expression)
        val expected = 9L
        assertEquals(expected, actual)
    }

    @Test
    fun sample5Test() {
        val expression = "((2 + 4 * 9) * (6 + 9 * 8 + 6) + 6) + 2 + 4 * 2"
        val actual = evaluateExpression(expression)
        val expected = 13632L
        assertEquals(expected, actual)
    }
}