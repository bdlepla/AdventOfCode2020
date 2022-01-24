import kotlin.math.exp
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.expect

class Day02Tests {
    @Test
    fun sample1Test() {
        val lines = """
            1-3 a: abcde
            1-3 b: cdefg
            2-9 c: ccccccccc
        """.trimIndent().split("\n")
        val day02 = Day02(lines)
        val actual = day02.solvePart1()
        val expected = 2
        assertEquals(expected, actual)
    }

    @Test
    fun sample2Test() {
        val lines = """
            1-3 a: abcde
            1-3 b: cdefg
            2-9 c: ccccccccc
        """.trimIndent().split("\n")
        val day02 = Day02(lines)
        val actual = day02.solvePart2()
        val expected = 1
        assertEquals(expected, actual)
    }
}