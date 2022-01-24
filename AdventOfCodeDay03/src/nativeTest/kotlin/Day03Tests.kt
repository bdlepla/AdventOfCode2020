import kotlin.test.Test
import kotlin.test.assertEquals

class Day03Tests {
    @Test
    fun solveSample1Test(){
        val lines = """
            ..##.......
            #...#...#..
            .#....#..#.
            ..#.#...#.#
            .#...##..#.
            ..#.##.....
            .#.#.#....#
            .#........#
            #.##...#...
            #...##....#
            .#..#...#.#
        """.trimIndent().split("\n")
        val day03 = Day03(lines)
        val actual = day03.solvePart1()
        val expected = 7L
        assertEquals(expected, actual)
    }

    @Test
    fun solveSample2Test(){
        val lines = """
            ..##.......
            #...#...#..
            .#....#..#.
            ..#.#...#.#
            .#...##..#.
            ..#.##.....
            .#.#.#....#
            .#........#
            #.##...#...
            #...##....#
            .#..#...#.#
        """.trimIndent().split("\n")
        val day03 = Day03(lines)
        val actual = day03.solvePart2()
        val expected = 336L
        assertEquals(expected, actual)
    }
}