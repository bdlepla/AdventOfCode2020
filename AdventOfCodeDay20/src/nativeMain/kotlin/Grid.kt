import kotlin.math.sqrt

class Grid(sx:Int, sy:Int) {
    private val data = Array<Tile?>(sx*sy) { null }
    private val xStride = 1
    private val yStride = sx

    internal val rangeX = 0 until sx
    internal val rangeY = 0 until sy

    private val offsetX = rangeX.first
    private val offsetY = rangeY.first

    private val lastX = rangeX.last
    private val lastY = rangeY.last

    private fun getDataIndex(x: Int, y: Int) = (x-offsetX)*xStride + (y-offsetY)*yStride

    private fun contains(x:Int, y:Int) = x in rangeX && y in rangeY

    operator fun get(x: Int, y: Int) : Tile? =
        if (contains(x, y)) {
            val idx = getDataIndex(x, y)
            data[idx]
        } else null

    operator fun get(t:Pair<Int, Int>) = get(t.first, t.second)
    operator fun set(x: Int, y: Int, v: Tile) {
        data[getDataIndex(x, y)] = v
    }

    private fun determineBestTopLeftCorner(corners:Map<Long, List<Match>>, tiles:Map<Long, Tile>):Tile {
        val topLeft = corners.filter {
            it.value.any { m -> m.side == Side.Bottom } &&
            it.value.any { m -> m.side == Side.Right }
        }
        if (topLeft.isNotEmpty()) {
            return tiles[topLeft.keys.first()]!!
        }

        // just pick one at random
        val firstKey = corners.keys.first()
        val ret = tiles[firstKey]!!
        val firstMatches = corners.values.first()
        return if (firstMatches.any { it.side == Side.Bottom } &&
            firstMatches.any { it.side == Side.Left}) {
            ret.rotate(Rotate.Degrees90)
        } else if (firstMatches.any { it.side == Side.Top } &&
            firstMatches.any { it.side == Side.Left}) {
            ret.rotate(Rotate.Degrees180)
        } else ret.rotate(Rotate.Degrees270)
    }

    private fun determineBestLeftEdge(lastEdgeTile: Tile, lastEdgeMatch: List<Match>, edges:Map<Long, List<Match>>, tiles:Map<Long, Tile>):Tile {
        val potentialTiles = lastEdgeMatch.filter { edges.keys.contains(it.otherTileId)}.map {tiles[it.otherTileId]}
        return potentialTiles.mapNotNull { lastEdgeTile.transformToMatch(it!!, Side.Bottom)}.first()
    }

    private fun determineNextTile(lastTile: Tile, lastMatch: List<Match>, collection: Map<Long, List<Match>>, tiles:Map<Long, Tile>):Tile {
        //val lastMatchOtherIds = lastMatch.map {it.otherTileId}.joinToString(",")
        //println("   searching other Ids $lastMatchOtherIds")
        //val amongCollectionIds = collection.keys.joinToString(",")
        //println("   among these Ids $amongCollectionIds")
        val potentialMatches = lastMatch.filter { collection.keys.contains(it.otherTileId)}
        //println("   found ${potentialMatches.count()} matches")
        val potentialTiles = potentialMatches.map {tiles[it.otherTileId]}
        //println("   found ${potentialTiles.count()} tiles that match")
        val transformedTiles = potentialTiles.mapNotNull { lastTile.transformToMatch(it!!, Side.Right)}
        //println("   found ${transformedTiles.count()} tiles were transformed")
        return transformedTiles.first()
    }

    fun arrange(tiles:List<Tile>, matches:List<Match>) {
        val mapOfTiles = tiles.associateBy { it.id }
        val groups = matches.groupBy {it.tileId}
        //println("matches = $groups")
        val corners = groups.filter {it.value.count() == 2}.toMutableMap()
        val cornersCount = corners.count()
        //println("found $cornersCount corners")
        val edges = groups.filter { it.value.count() == 3}.toMutableMap()
        val edgesCount = edges.count()
        //println("found $edgesCount edges")
        val internals = groups.filter { it.value.count() == 4}.toMutableMap()
        val internalCount = internals.count()
        //println("found $internalCount internals")
        val numSide = sqrt(tiles.count().toDouble()).toInt()

        var leftEdgeAboveRowMatch: List<Match>? = null
        var leftEdgeAboveRowTile: Tile? = null

        (0 until numSide).forEach { y ->
            var lastMatch: List<Match>? = null
            var lastTile: Tile? = null
            (0 until numSide).forEach { x ->
                //println("determining grid[$x,$y]")
                when {
                    x == 0 -> {
                        when (y) {
                            0 -> {
                                //println("Calling determineBestTopLeftCorner with corners")
                                leftEdgeAboveRowTile = determineBestTopLeftCorner(corners, mapOfTiles)
                                //println("grid[$x,$y]=${leftEdgeAboveRowTile!!.id}")
                                this[x, y] = leftEdgeAboveRowTile!!
                                leftEdgeAboveRowMatch = corners[leftEdgeAboveRowTile!!.id]!!
                                lastMatch = leftEdgeAboveRowMatch
                                lastTile = leftEdgeAboveRowTile!!
                            }
                            numSide - 1 -> {
                                //println("Calling determineBestLeftEdge with corners")
                                lastTile = determineBestLeftEdge(leftEdgeAboveRowTile!!, leftEdgeAboveRowMatch!!, corners, mapOfTiles)
                                //println("grid[$x,$y]=${lastTile!!.id}")
                                this[x, y] = lastTile!!
                                lastMatch =  corners[lastTile!!.id]!!
                            }
                            else -> {
                                //println("Calling determineBestLeftEdge with edges")
                                leftEdgeAboveRowTile = determineBestLeftEdge(leftEdgeAboveRowTile!!, leftEdgeAboveRowMatch!!, edges, mapOfTiles)
                                //println("grid[$x,$y]=${leftEdgeAboveRowTile!!.id}")
                                this[x, y] = leftEdgeAboveRowTile!!
                                leftEdgeAboveRowMatch = edges[leftEdgeAboveRowTile!!.id]!!
                                lastMatch = leftEdgeAboveRowMatch
                                lastTile = leftEdgeAboveRowTile!!
                            }
                        }
                    }
                    x == numSide - 1 -> {
                        when (y) {
                            0, numSide - 1 -> {
                                //println("Calling nextTile with corners")
                                lastTile =  determineNextTile(lastTile!!, lastMatch!!, corners, mapOfTiles)!!
                                //println("grid[$x,$y]=${lastTile!!.id}")
                                this[x, y] = lastTile!!
                            }
                            else -> {
                                //println("Calling nextTile with edges")
                                lastTile = determineNextTile(lastTile!!, lastMatch!!, edges, mapOfTiles)!!
                                //println("grid[$x,$y]=${lastTile!!.id}")
                                this[x, y] = lastTile!!
                            }
                        }
                    }
                    y == 0 || y == numSide - 1 -> {
                        //println("Calling nextTile with edges")
                        lastTile = determineNextTile(lastTile!!, lastMatch!!, edges, mapOfTiles)
                        //println("   lastTile = ${lastTile!!.id}")
                        lastMatch = edges[lastTile!!.id]!!
                        //println("grid[$x,$y]=${lastTile!!.id}")
                        this[x, y] = lastTile!!
                    }
                    else -> {
                        //println("Calling nextTile with internals")
                        lastTile = determineNextTile(lastTile!!, lastMatch!!, internals, mapOfTiles)
                        lastMatch = internals[lastTile!!.id]!!
                        //println("grid[$x,$y]=${lastTile!!.id}")
                        this[x, y] = lastTile!!
                    }
                }
            }
        }
    }

    fun mergeTiles(): Matrix2 {
        val newSide = (this.lastX +1) * 8
        val ret = Matrix2(newSide, newSide)
        rangeX.forEach { x ->
            rangeY.forEach { y ->
                val tile = this[x, y]!!.removeBorder()
                ret.setPartial(x*8, y*8, tile)
            }
        }

        return ret
    }
}