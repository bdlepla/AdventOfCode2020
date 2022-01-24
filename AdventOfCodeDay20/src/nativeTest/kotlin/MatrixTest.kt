import kotlin.test.Test
import kotlin.test.assertEquals

class MatrixTest {
    @Test
    fun basicMatrixTest() {
        val lines = listOf(
           //0123456789
            "..##.#..#.", //0
            "##..#.....", //1
            "#...##..#.", //2
            "####.#...#", //3
            "##.##.###.", //4
            "##...#.###", //5
            ".#.#.#..##", //6
            "..#....#..", //7
            "###...#.#.", //8
            "..###..###"  //9
        )

        val matrix = Matrix2.create(lines)
        val actual = matrix.print()
        val expected = lines.joinToString("\n")
        assertEquals(expected, actual)
    }

    @Test
    fun sideTopMatrixTest() {
        val lines = listOf(
            //0123456789
            "..##.#..#.", //0
            "##..#.....", //1
            "#...##..#.", //2
            "####.#...#", //3
            "##.##.###.", //4
            "##...#.###", //5
            ".#.#.#..##", //6
            "..#....#..", //7
            "###...#.#.", //8
            "..###..###"  //9
        )

        val matrix = Matrix2.create(lines)
        val actual = matrix.side(Side.Top)
        val expected = "..##.#..#."
        assertEquals(expected, actual)
    }

    @Test
    fun sideBottomMatrixTest() {
        val lines = listOf(
            //0123456789
            "..##.#..#.", //0
            "##..#.....", //1
            "#...##..#.", //2
            "####.#...#", //3
            "##.##.###.", //4
            "##...#.###", //5
            ".#.#.#..##", //6
            "..#....#..", //7
            "###...#.#.", //8
            "..###..###"  //9
        )

        val matrix = Matrix2.create(lines)
        val actual = matrix.side(Side.Bottom)
        val expected = "..###..###"
        assertEquals(expected, actual)
    }

    @Test
    fun sideLeftMatrixTest() {
        val lines = listOf(
            //0123456789
            "..##.#..#.", //0
            "##..#.....", //1
            "#...##..#.", //2
            "####.#...#", //3
            "##.##.###.", //4
            "##...#.###", //5
            ".#.#.#..##", //6
            "..#....#..", //7
            "###...#.#.", //8
            "..###..###"  //9
        )

        val matrix = Matrix2.create(lines)
        val actual = matrix.side(Side.Left)
        val expected = ".#####..#."
        assertEquals(expected, actual)
    }

    @Test
    fun sideRightMatrixTest() {
        val lines = listOf(
            //0123456789
            "..##.#..#.", //0
            "##..#.....", //1
            "#...##..#.", //2
            "####.#...#", //3
            "##.##.###.", //4
            "##...#.###", //5
            ".#.#.#..##", //6
            "..#....#..", //7
            "###...#.#.", //8
            "..###..###"  //9
        )

        val matrix = Matrix2.create(lines)
        val actual = matrix.side(Side.Right)
        val expected = "...#.##..#"
        assertEquals(expected, actual)
    }

    @Test
    fun rotate0DegreesMatrixTest() {
        val lines = listOf(
            //0123456789
            "..##.#..#.", //0
            "##..#.....", //1
            "#...##..#.", //2
            "####.#...#", //3
            "##.##.###.", //4
            "##...#.###", //5
            ".#.#.#..##", //6
            "..#....#..", //7
            "###...#.#.", //8
            "..###..###"  //9
        )

        val matrix = Matrix2.create(lines)
        val actual = matrix.rotate(Rotate.Degrees0).print()
        val expected = lines.joinToString("\n")
        assertEquals(expected, actual)
    }

    @Test
    fun rotate90DegreesMatrixTest() {
        val lines = listOf(
           //0123456789
            "..##.#..#.", //0 0,0 -> 9, 0; 1, 0 -> 9, 1 -- nx = y.r ny = x
            "##..#.....", //1 0,1 ->
            "#...##..#.", //2
            "####.#...#", //3
            "##.##.###.", //4
            "##...#.###", //5
            ".#.#.#..##", //6
            "..#....#..", //7
            "###...#.#.", //8
            "..###..###"  //9 0,9 -> 0,0; 9,9 -> 0,9 => x,y ->
        )

        val expectedLines = listOf(
           //0123456789
            ".#..#####.", //0
            ".#.####.#.", //1
            "###...#..#", //2
            "#..#.##..#", //3
            "#....#.##.", //4
            "...##.##.#", //5
            ".#...#....", //6
            "#.#.##....", //7
            "##.###.#.#", //8
            "#..##.#..."  //9
        )

        val matrix = Matrix2.create(lines)
        val actual = matrix.rotate(Rotate.Degrees90).print()
        val expected = expectedLines.joinToString("\n")
        assertEquals(expected, actual)
    }

    @Test
    fun rotate180DegreesMatrixTest() {
        val lines = listOf(
            //0123456789
            "..##.#..#.", //0 0,0 -> 9, 0; 1, 0 -> 9, 1 -- nx = y.r ny = x
            "##..#.....", //1 0,1 ->
            "#...##..#.", //2
            "####.#...#", //3
            "##.##.###.", //4
            "##...#.###", //5
            ".#.#.#..##", //6
            "..#....#..", //7
            "###...#.#.", //8
            "..###..###"  //9 0,9 -> 0,0; 9,9 -> 0,9 => x,y ->
        )

        val expectedLines = listOf(
            //0123456789
            "###..###..", //0
            ".#.#...###", //1
            "..#....#..", //2
            "##..#.#.#.", //3
            "###.#...##", //4
            ".###.##.##", //5
            "#...#.####", //6
            ".#..##...#", //7
            ".....#..##", //8
            ".#..#.##.."  //9
        )

        val matrix = Matrix2.create(lines)
        val actual = matrix.rotate(Rotate.Degrees180).print()
        val expected = expectedLines.joinToString("\n")
        assertEquals(expected, actual)
    }

    @Test
    fun rotate270DegreesMatrixTest() {
        val lines = listOf(
            //0123456789
            "..##.#..#.", //0 0,0 -> 9, 0; 1, 0 -> 9, 1 -- nx = y.r ny = x
            "##..#.....", //1 0,1 ->
            "#...##..#.", //2
            "####.#...#", //3
            "##.##.###.", //4
            "##...#.###", //5
            ".#.#.#..##", //6
            "..#....#..", //7
            "###...#.#.", //8
            "..###..###"  //9 0,9 -> 0,0; 9,9 -> 0,9 => x,y ->
        )

        /*
            ".#..#####.", //0
            ".#.####.#.", //1
            "###...#..#", //2
            "#..#.##..#", //3
            "#....#.##.", //4
            "...##.##.#", //5
            ".#...#....", //6
            "#.#.##....", //7
            "##.###.#.#", //8
            "#..##.#..."  //9
        */
        val expectedLines = listOf(
            //0123456789
            "...#.##..#", //0
            "#.#.###.##", //1
            "....##.#.#", //2
            "....#...#.", //3
            "#.##.##...", //4
            ".##.#....#", //5
            "#..##.#..#", //6
            "#..#...###", //7
            ".#.####.#.", //8
            ".#####..#."  //9
        )

        val matrix = Matrix2.create(lines)
        val actual = matrix.rotate(Rotate.Degrees270).print()
        val expected = expectedLines.joinToString("\n")
        assertEquals(expected, actual)
    }


}