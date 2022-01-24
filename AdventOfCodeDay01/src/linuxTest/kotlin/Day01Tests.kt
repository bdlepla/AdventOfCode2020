import sample.Day01
import kotlin.test.Test
import kotlin.test.assertEquals

class Day01Tests {
    @Test
    fun sample1Test() {
        val lines = """
            1721
            979
            366
            299
            675
            1456
        """.trimIndent().split("\n")
        val day01 = Day01(lines)
        val actual = day01.solvePart1()
        val expected = 514579L
        assertEquals(expected, actual)
    }

    @Test
    fun sample2Test() {
        val lines = """
            1721
            979
            366
            299
            675
            1456
        """.trimIndent().split("\n")
        val day01 = Day01(lines)
        val actual = day01.solvePart2()
        val expected = 241861950L
        assertEquals(expected, actual)
    }

    @Test
    fun testAllPairsWith3Elements() {
        val list = listOf(1L,2L,3L).asSequence()
        val allPairs = Day01.getAllPairs(list)
        val expected = listOf(Pair(1L,2L), Pair(1L,3L), Pair(2L,3L)).asSequence()
        assertEquals(expected.toList(), allPairs.toList())
    }

    @Test
    fun testAllPairsWith4Elements() {
        val list = listOf(1L,2L,3L,4L).asSequence()
        val allPairs = Day01.getAllPairs(list)
        val expected = listOf(Pair(1L,2L), Pair(1L,3L), Pair(1L,4L),
            Pair(2L,3L), Pair(2L,4L), Pair(3L, 4L)).asSequence()
        assertEquals(expected.toList(), allPairs.toList())
    }
}