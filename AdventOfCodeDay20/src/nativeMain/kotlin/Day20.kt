import kotlin.math.sqrt

class Day20(lines: List<String>) {
    val tiles = mutableListOf<Tile>()
    init {
        var currentTile = 0
        val tileLines = mutableListOf<String>()
        lines.forEach {
            if (it.trim().isNotEmpty()) {
                if (it.startsWith("Tile")) {
                    if (currentTile != 0 && tileLines.isNotEmpty()) {
                        tiles.add(Tile(currentTile.toLong(), tileLines))
                        currentTile = 0
                        tileLines.clear()
                    }
                    currentTile = it.substring(5..8).toInt()
                }
                else
                {
                    tileLines.add(it)
                }
            }
        }

        if (currentTile != 0 && tileLines.isNotEmpty())
        {
            tiles.add(Tile(currentTile.toLong(), tileLines))
        }
    }

    fun findMatches(): Map<Long, Int> {
        val result = tiles.associate { it.id to 0 }.toMutableMap()
        val count = tiles.count()
        for (i in 0 until count) {
            for (j in i+1 until count) {
                val tile1 = tiles[i]
                val tile2 = tiles[j]
                if (tile1.anySideMatches(tile2)) {
                    result[tile1.id] = 1 + result[tile1.id]!!
                    result[tile2.id] = 1 + result[tile2.id]!!
                }
            }
        }
        return result
    }

    private fun buildMatches(): List<Match> {
        val count = tiles.count()
        val pairsOfTiles = (0 until count-1).flatMap {
            i -> (i+1 until count).map {
                j -> Pair(i, j)
            }
        }

        val result2 = pairsOfTiles.flatMap {
            val tile1 = tiles[it.first]
            val tile2 = tiles[it.second]
            val match = tile1.getMatch(tile2)
            if (match != null) {
                val match2 = tile2.getMatch(tile1)
                listOf(match, match2)
            }
            else listOf<Match>()

        }.filterNotNull()

        return result2
    }

    fun solvePart1(): Long {
        println("Starting solve part 1...")
        val matches = findMatches()
        //println("matches = $matches")
        val corners = matches.filter {it.value == 2}
        println("found $corners corners")
        val result = corners.keys.multiply()
        println("Finished solve part 1")
        return result
    }

    fun solvePart2(): Int {
        println("Starting solve part 2...")
        val matches = buildMatches()
        //val groups = matches.groupBy { it.tileId }
        //val corners = groups.filter {it.value.count() == 2}
        //println("corners = $corners")
        val numSide = sqrt(tiles.count().toDouble()).toInt()
        println("building $numSide x $numSide grid")
        val grid = Grid(numSide, numSide)
        grid.arrange(tiles, matches)
        val combinedMatrix = grid.mergeTiles()
        val dragonStrings =
            """
                              # 
            #    ##    ##    ###
             #  #  #  #  #  #
            """.trimIndent().split("\n")
        val dragonMatrix = Matrix2.create(dragonStrings)
        //println(dragonMatrix.print())
        //val dragonPoints = dragonMatrix.getTruePoints()
        //println(dragonPoints)
        val dragonMatches = Tile.buildTransformations.map {
            val transMatrix = combinedMatrix.transform(it)
            val count = transMatrix.containsDragon(dragonMatrix)
            Pair(count, transMatrix)
        }.filter {it.first != 0}
        val matched = dragonMatches.first().second
        val matchedWithoutDragonMatches = matched.removeDragons(dragonMatrix)

        val ret = matchedWithoutDragonMatches.getTruePoints().count()

        println("Finished solve part 2")
        return ret
    }
}