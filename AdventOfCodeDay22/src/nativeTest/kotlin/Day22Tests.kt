import kotlin.test.Test
import kotlin.test.assertEquals

class Day22Tests {
    @Test
    fun solvePart1Test() {
        val lines = """
            Player 1:
            9
            2
            6
            3
            1

            Player 2:
            5
            8
            4
            7
            10
        """.trimIndent().split("\n")
        val day22 = Day22(lines)

        val actual = day22.solvePart1()
        val expected = 306
        assertEquals(expected, actual)
    }

    @Test
    fun solvePart2Test() {
        val lines = """
            Player 1:
            9
            2
            6
            3
            1

            Player 2:
            5
            8
            4
            7
            10
        """.trimIndent().split("\n")
        val day22 = Day22(lines)

        val actual = day22.solvePart2(true)
        val expected = 291
        assertEquals(expected, actual)
    }
}