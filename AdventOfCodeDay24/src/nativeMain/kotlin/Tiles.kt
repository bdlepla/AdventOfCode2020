class Tiles {
    private val mapOfTiles = mutableMapOf<Pair<Int, Int>, Boolean>()
    fun arrangeTiles(lines:List<String>) {
        lines.forEach { line ->
            val tile = decodeTile(line)
            val bool = mapOfTiles.getOrPut(tile) { false }
            mapOfTiles[tile] = !bool
        }
    }

    fun copy() : Tiles {
        val ret = Tiles()
        mapOfTiles.forEach {
            ret.mapOfTiles[it.key] = it.value
        }
        return ret
    }

    fun performDailyFlip(): Tiles {
        val ret = Tiles()
        val maxX = mapOfTiles.keys.maxOf { it.first }
        val minX = mapOfTiles.keys.minOf { it.first }
        val maxY = mapOfTiles.keys.maxOf { it.second }
        val minY = mapOfTiles.keys.minOf { it.second }
        (minY-1..maxY+1).forEach { y ->
            (minX-1..maxX+1).forEach { x ->
                val tile = Pair(x, y)
                ret.mapOfTiles[tile] = mapOfTiles.determineColor(tile)
            }
        }

        return ret
    }

    fun countBlack() = mapOfTiles.values.count { it }

    companion object {
        private tailrec fun decodeTileRecurse(line: String, point:Pair<Int, Int>): Pair<Int, Int> {
            if (line.isEmpty()) return point

            val x = point.first
            val y = point.second

            if (line[0] == 'e') return decodeTileRecurse(line.drop(1), Pair(x+1, y))
            if (line[0] == 'w') return decodeTileRecurse(line.drop(1), Pair(x-1, y))

            val odd = y.mod(2) == 1
            val op = line.take(2)
            val restOfLine = line.drop(2)

            return when (op) {
                "ne" -> decodeTileRecurse(restOfLine, Pair(if (odd) x+1 else x, y-1))
                "nw" -> decodeTileRecurse(restOfLine, Pair(if (odd) x else x-1, y-1))
                "se" -> decodeTileRecurse(restOfLine, Pair(if (odd) x+1 else x, y+1))
                else -> decodeTileRecurse(restOfLine, Pair(if (odd) x else x-1, y+1))
            }
        }

        internal fun decodeTile(line:String):Pair<Int, Int> {
            return decodeTileRecurse(line, Pair(0, 0))
        }
    }
}

fun Map<Pair<Int, Int>, Boolean>.determineColor(tile:Pair<Int, Int>):Boolean {
    val thisIsBlack = this.getOrElse(tile) { false }
    val countBlack = countAdjacentBlack(tile)
    if (thisIsBlack && (countBlack == 0 || countBlack > 2)) return false
    if (!thisIsBlack && countBlack == 2) return true
    return thisIsBlack // unchanged
}

fun Map<Pair<Int, Int>, Boolean>.countAdjacentBlack(tile:Pair<Int, Int>):Int {
    return generateAdjacentTiles(tile).count { this.getOrElse(it) {false} }
}

fun generateAdjacentTiles(tile:Pair<Int, Int>):Sequence<Pair<Int, Int>> {
    val x = tile.first
    val y = tile.second
    val odd = y.mod(2) == 1

    return sequence {
        yield(Pair(x - 1, y)) // this is the "e" tile
        yield(Pair(x + 1, y)) // this is the "w" tile
        yield(Pair(if (odd)x+1 else x, y-1)) // this is the "ne" tile
        yield(Pair(if (odd)x else x-1, y-1)) // this is the "nw" tile
        yield(Pair(if (odd)x+1 else x, y+1)) // this is the "se" tile
        yield(Pair(if (odd)x else x-1, y+1)) // this is the "sw" tile
    }
}