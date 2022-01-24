import kotlin.test.Test
import kotlin.test.assertEquals

class Day23Tests {
    @Test
    fun solveDay23Part1With10Steps() {
        val day23 = Day23()
        val actual = day23.solvePart1("389125467", 10)
        val expected = "92658374"
        assertEquals(expected, actual)
    }

    @Test
    fun solveDay23Part1With100Steps() {
        val day23 = Day23()
        val actual = day23.solvePart1("389125467", 100)
        val expected = "67384529"
        assertEquals(expected, actual)
    }


    @Test
    fun solveDay23Part2With10MillionSteps() {
        val day23 = Day23()
        val actual = day23.solvePart2("389125467", 10_000_000)
        val expected = 149245887792
        assertEquals(expected, actual)

    }

}