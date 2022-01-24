import kotlin.test.Test
import kotlin.test.assertEquals

class ConvertToRPNTests {
    @Test
    fun basicTest() {
        val expression = "3 + 4"
        val actual = convertToRPN(expression)
        val expected = "3 4 +"
        assertEquals(expected, actual)
    }

    @Test
    fun sample1Test() {
        val expression = "1 + 2 * 3 + 4 * 5 + 6"
        val actual = convertToRPN(expression)
        val expected = "1 2 + 3 * 4 + 5 * 6 +"
        assertEquals(expected, actual)
    }

    @Test
    fun basicParenthesesTest() {
        val expression = "(1 + 2)"
        val actual = convertToRPN(expression)
        val expected = "1 2 +"
        assertEquals(expected, actual)
    }

    @Test
    fun doubleParenthesis2Test() {
        val expression = "(4 * (5 + 6))"
        val actual = convertToRPN(expression)
        val expected = "4 5 6 + *"
        assertEquals(expected, actual)
    }

    @Test
    fun doubleParenthesis22Test() {
        val expression = "((4 * 5) + 6)"
        val actual = convertToRPN(expression)
        val expected = "4 5 * 6 +"
        assertEquals(expected, actual)
    }

    @Test
    fun lastSampleTest() {
        val expression = "((2 + 4 * 9) * (6 + 9 * 8 + 6) + 6) + 2 + 4 * 2"
        val actual = convertToRPN(expression)
        val expected = "2 4 + 9 * 6 9 + 8 * 6 + * 6 + 2 + 4 + 2 *"
        assertEquals(expected, actual)
    }

}