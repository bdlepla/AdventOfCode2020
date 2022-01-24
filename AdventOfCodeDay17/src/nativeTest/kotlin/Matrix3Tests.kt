import kotlin.test.Test
import kotlin.test.assertEquals

class Matrix3Tests {
    @Test
    fun createMatrixTest() {
        val lines = listOf(".#.","..#", "###" )
        val matrix = Matrix3.create(lines)

        val expectedX = -1..1
        val actualX = matrix.rangeX
        assertEquals(expectedX, actualX, "expectedX != actualX")

        val expectedY = -1..1
        val actualY = matrix.rangeY
        assertEquals(expectedY, actualY, "expectedY != actualY")

        val expectedZ = 0..0
        val actualZ = matrix.rangeZ
        assertEquals(expectedZ, actualZ, "expectedZ != actualZ")
    }

    @Test
    fun countOnMatrixTest() {
        val lines = listOf(".#.","..#", "###" )
        val matrix = Matrix3.create(lines)

        val expected = 5
        val actual = matrix.countTrue()
        assertEquals(expected, actual)
    }

    @Test
    fun countNeighborsNotOnPointOnMatrixTest() {
        val lines = listOf(".#.","..#", "###" )
        val matrix = Matrix3.create(lines)

        // this matrix is -1..1, -1,,1, 0..0
        val expected = 0
        val actual = matrix.countTrueNeighbors(1000, 1000, 1000)
        assertEquals(expected, actual)
    }

    @Test
    fun neighborsTest() {
        val neighbors = Matrix3.generateNeighbors(0, 0, 0)
        val actual = neighbors.count()
        val expected = 26
        assertEquals(expected,actual )
    }

    @Test
    fun neighborsInMatrixTest() {
        val lines = listOf(".#.","..#", "###" )
        val matrix = Matrix3.create(lines)

        val neighbors = matrix.getNeighbors(0, 0, 0)
        val actual = neighbors.count()
        val expected = 8
        assertEquals(expected, actual)

    }

    @Test
    fun countTrueNeighborsOfPointOnMatrixTest() {
        val lines = listOf(".#.","..#", "###" )
        val matrix = Matrix3.create(lines)

        val expected1 = 5
        val actual1 = matrix.countTrueNeighbors(0, 0, 0)
        assertEquals(expected1, actual1)
    }

    @Test
    fun isPointTrueOnMatrixTest() {
        val lines = listOf(".#.","..#", "###" )
        val matrix = Matrix3.create(lines)

        val expected1 = false
        val actual1 = matrix[-1, -1, 0]
        assertEquals(expected1, actual1)
    }

    @Test
    fun isPoint2TrueOnMatrixTest() {
        val lines = listOf(".#.","..#", "###" )
        val matrix = Matrix3.create(lines)

        val expected1 = true
        val actual1 = matrix[0, -1, 0]
        assertEquals(expected1, actual1)
    }

    @Test
    fun printWithBadZisEmptyTest() {
        val lines = listOf(".#.","..#", "###" )
        val matrix = Matrix3.create(lines)

        val actual = matrix.print(99) // 99 for z is out of range
        val expected = ""
        assertEquals(expected, actual)
    }

    @Test
    fun printBasicTest() {
        val lines = listOf(".#.","..#", "###" )
        val matrix = Matrix3.create(lines)

        val actual = matrix.print(0)
        val expected = lines.joinToString("\n")
        assertEquals(expected, actual)
    }

    @Test
    fun basicTransformTest() {
        val lines = listOf(".#.","..#", "###" )
        val matrix = Matrix3.create(lines)

        val newMatrix = matrix.transform()

        val expectedRangeX = -2..2
        val actualRangeX = newMatrix.rangeX
        assertEquals(expectedRangeX, actualRangeX)

        val expectedRangeY = -2..2
        val actualRangeY = newMatrix.rangeY
        assertEquals(expectedRangeY, actualRangeY)

        val expectedRangeZ = -1..1
        val actualRangeZ = newMatrix.rangeZ
        assertEquals(expectedRangeZ, actualRangeZ)

        val expectedZ0Print =
            listOf(
                ".....",
                ".....",
                ".#.#.",
                "..##.",
                "..#.."
            ).joinToString( "\n" )
        val actualZ0Print = newMatrix.print(0)
        assertEquals(expectedZ0Print, actualZ0Print)
    }

    @Test
    fun sampleTest() {
        val lines = listOf(".#.","..#", "###" )
        val matrix = Matrix3.create(lines)

        val finalMatrix = matrix.transform(6)
        val actual = finalMatrix.countTrue()
        val expected = 112
        assertEquals(expected, actual)
    }

    @Test
    fun actualRunTest() {
        val lines = listOf(
            ".##.####",
            ".#.....#",
            "#.###.##",
            "#####.##",
            "#...##.#",
            "#######.",
            "##.#####",
            ".##...#."
        )
        val matrix = Matrix3.create(lines)

        val finalMatrix = matrix.transform(6)
        val actual = finalMatrix.countTrue()
        val expected = 372
        assertEquals(expected, actual)
    }
}