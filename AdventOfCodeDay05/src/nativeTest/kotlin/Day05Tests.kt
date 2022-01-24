import kotlin.test.Test
import kotlin.test.assertEquals

class Day05Tests {
    @Test
    fun solveSample1Test() {
        val lines = """
            FBFBBFFRLR
            BFFFBBFRRR
            FFFBBBFRRR
            BBFFBBFRLL
        """.trimIndent().split("\n")

        val day05 = Day05(lines)
        val actual = day05.solvePart1()
        val expected = 820
        assertEquals(expected, actual)
    }
}