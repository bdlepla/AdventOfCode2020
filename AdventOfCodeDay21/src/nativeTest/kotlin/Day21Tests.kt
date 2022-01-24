import kotlin.test.Test
import kotlin.test.assertEquals

class Day21Tests {

    @Test
    fun parseLineTest() {
        val line = "mxmxvkd kfcds sqjhc nhms (contains dairy, fish)"
        val pair = Day21.parseLine(line)
        val part1 = pair.first
        val expected1 = setOf("mxmxvkd", "kfcds", "sqjhc", "nhms")
        assertEquals(expected1, part1)

        val part2 = pair.second
        val expected2 = setOf("dairy", "fish")
        assertEquals(expected2, part2)
    }

    @Test
    fun solvePart1Test() {
        val lines =
            """
                mxmxvkd kfcds sqjhc nhms (contains dairy, fish)
                trh fvjkl sbzzf mxmxvkd (contains dairy)
                sqjhc fvjkl (contains soy)
                sqjhc mxmxvkd sbzzf (contains fish)
            """.trimIndent().split("\n")

        val day21 = Day21(lines)
        val actual = day21.solvePart1()
        val expected = 5L
        assertEquals(expected, actual)
    }

    @Test
    fun solvePart2Test() {
        val lines =
            """
                mxmxvkd kfcds sqjhc nhms (contains dairy, fish)
                trh fvjkl sbzzf mxmxvkd (contains dairy)
                sqjhc fvjkl (contains soy)
                sqjhc mxmxvkd sbzzf (contains fish)
            """.trimIndent().split("\n")

        val day21 = Day21(lines)
        val actual = day21.solvePart2()
        val expected = "mxmxvkd,sqjhc,fvjkl"
        assertEquals(expected, actual)
    }


}