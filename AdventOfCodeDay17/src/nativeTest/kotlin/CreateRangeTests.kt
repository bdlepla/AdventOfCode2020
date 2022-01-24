import kotlin.test.Test
import kotlin.test.assertEquals


class CreateRangeTests {
    @Test
    fun createRangeOf1ShouldCreate0To0Test() {
        val expected = 0..0
        val actual = 1.createRange()
        assertEquals(expected, actual, "range of 1 is not 0..0")
    }

    @Test
    fun createRangeOf3ShouldCreateMinus1to1Test() {
        val expected = -1..1
        val actual = 3.createRange()
        assertEquals(expected, actual, "range of 3 is not -1..1")
    }

    @Test
    fun createRangeOf4ShouldCreateMinus1to2Test() {
        val expected = -1..2
        val actual = 4.createRange()
        assertEquals(expected, actual, "range of 4 is not -1..2")
    }


}