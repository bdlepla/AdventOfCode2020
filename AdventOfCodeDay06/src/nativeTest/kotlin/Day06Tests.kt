import kotlin.test.Test
import kotlin.test.assertEquals

class Day06Tests {
    @Test
    fun solveSample1Test() {
        val lines = """
            abc

            a
            b
            c

            ab
            ac

            a
            a
            a
            a

            b
        """.trimIndent().split("\n")

        val day06 = Day06(lines)
        val actual = day06.solvePart1()
        val expected = 11
        assertEquals(expected, actual)
    }

    @Test
    fun solveSample2Test() {
        val lines = """
            abc

            a
            b
            c

            ab
            ac

            a
            a
            a
            a

            b
        """.trimIndent().split("\n")

        val day06 = Day06(lines)
        val actual = day06.solvePart2()
        val expected = 6
        assertEquals(expected, actual)
    }
}