import kotlin.test.Test
import kotlin.test.assertEquals

class Day25Tests {
    @Test
    fun solvePart1SampleTest() {
        val day25 = Day25(Pair(5764801, 17807724))
        val actual = day25.solvePart1()
        val expected = 14897079L
        assertEquals(expected, actual)
    }

    @Test
    fun getLoopSizeOfCardTest() {
        val card = CryptoGraphicEndpoint(5764801)
        val actual = card.getLoopSize()
        val expected = 8
        assertEquals(expected, actual)
    }

    @Test
    fun getLoopSizeOfDoorTest() {
        val door = CryptoGraphicEndpoint(17807724)
        val actual = door.getLoopSize()
        val expected = 11
        assertEquals(expected, actual)
    }

    @Test
    fun transformCardLoopWithDoorKeyTest() {
        val actual = 8.transform(17807724)
        val expected = 14897079L
        assertEquals(expected, actual)
    }

    @Test
    fun transformDoorLoopWithCardKeyTest() {
        val actual = 11.transform(5764801)
        val expected = 14897079L
        assertEquals(expected, actual)
    }
}