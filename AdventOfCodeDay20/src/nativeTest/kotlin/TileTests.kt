import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class TileTests {
    @Test
    fun tile1AnySideMatchesTile2AnySideTest() {
        val tile2311 = listOf(
            "..##.#..#.",
            "##..#.....",
            "#...##..#.",
            "####.#...#",
            "##.##.###.",
            "##...#.###",
            ".#.#.#..##",
            "..#....#..",
            "###...#.#.",
            "..###..###"
        )

        val matrix1 = Matrix2.create(tile2311)
        val tile1 = Tile(2311, matrix1)

        val tile1951 = listOf(
            "#.##...##.",
            "#.####...#",
            ".....#..##",
            "#...######",
            ".##.#....#",
            ".###.#####",
            "###.##.##.",
            ".###....#.",
            "..#.#..#.#",
            "#...##.#.."
        )

        val matrix2 = Matrix2.create(tile1951)
        val tile2 = Tile(1951, matrix2)

        val actual = tile1.anySideMatches(tile2)
        assertTrue(actual)
    }

    @Test
    fun tile1AnySideShouldNotMatchTile2AnySideTest() {

        val tile1171 = listOf(
            "####...##.",
            "#..##.#..#",
            "##.#..#.#.",
            ".###.####.",
            "..###.####",
            ".##....##.",
            ".#...####.",
            "#.##.####.",
            "####..#...",
            ".....##..."
        )

        val matrix1 = Matrix2.create(tile1171)
        val tile1 = Tile(1171, matrix1)

        val tile1951 = listOf(
            "#.##...##.",
            "#.####...#",
            ".....#..##",
            "#...######",
            ".##.#....#",
            ".###.#####",
            "###.##.##.",
            ".###....#.",
            "..#.#..#.#",
            "#...##.#.."
        )

        val matrix2 = Matrix2.create(tile1951)
        val tile2 = Tile(1951, matrix2)

        val actual = tile1.anySideMatches(tile2)
        assertFalse(actual)
    }
}