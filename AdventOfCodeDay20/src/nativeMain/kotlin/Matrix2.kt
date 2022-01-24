import kotlin.math.max

enum class Side {
    Top,
    Right,
    Bottom,
    Left
}

enum class Rotate {
    Degrees0,
    Degrees90,
    Degrees180,
    @Suppress("unused")
    Degrees270
}

enum class Flipped {
    None,
    Vertical, // flip about middle of X (columns are switched)
    Horizontal, // flip about middle of y (rows are switched)
}

class Matrix2(sx: Int, sy: Int) {
    private val data = BooleanArray(sx * sy) { false }
    private val xStride = 1
    private val yStride = sx

    internal val rangeX = 0 until sx
    internal val rangeY = 0 until sy

    private val offsetX = rangeX.first
    private val offsetY = rangeY.first

    val lastX = rangeX.last
    val lastY = rangeY.last

    private fun getDataIndex(x: Int, y: Int) = (x - offsetX) * xStride + (y - offsetY) * yStride

    private fun contains(x: Int, y: Int) = x in rangeX && y in rangeY

    operator fun get(x: Int, y: Int): Boolean {
        var ret = false
        if (contains(x, y)) {
            ret = data[getDataIndex(x, y)]
        }
        return ret
    }

    operator fun get(t: Pair<Int, Int>) = get(t.first, t.second)
    operator fun set(x: Int, y: Int, v: Boolean) {
        data[getDataIndex(x, y)] = v
    }

    fun side(side: Side): String {
        return when (side) {
            Side.Top -> row(0)
            Side.Bottom -> row(lastY)
            Side.Left -> column(0)
            Side.Right -> column(lastX)
        }
    }

    fun rotate(r: Rotate): Matrix2 = when (r) {
        Rotate.Degrees0 -> this
        Rotate.Degrees90 -> {
            val result = Matrix2(this.lastY + 1, this.lastX + 1) // rotating by 90 or 180, x becomes y and vice-versa
            rangeX.forEach {
                result.setRow(it, column(it).reversed())
            }
            result
        }
        Rotate.Degrees180 -> {
            this.rotate(Rotate.Degrees90).rotate(Rotate.Degrees90)
        }
        else -> this.rotate(Rotate.Degrees90).rotate(Rotate.Degrees90).rotate(Rotate.Degrees90)
    }

    fun flip(flip: Flipped) =
        when (flip) {
            Flipped.None -> this
            Flipped.Vertical -> {
                val result = Matrix2(this.lastX + 1, this.lastY + 1)
                rangeX.reversed().forEachIndexed { x, nx ->
                    if (x != nx) {
                        result.setColumn(x, column(nx))
                    }
                }
                result
            }
            Flipped.Horizontal -> {
                val result = Matrix2(this.lastX + 1, this.lastY + 1)
                rangeY.reversed().forEachIndexed { y, ny ->
                    if (y != ny) {
                        result.setRow(y, row(ny))
                    }
                }
                result
            }
        }

    private fun setRow(rowNumber: Int, row: String) {
        if (rowNumber in rangeY) {
            val maxX = max(row.length, lastX + 1)
            for (x in 0 until maxX) {
                set(x, rowNumber, row[x] == '#')
            }
        }
    }

    private fun setColumn(colNumber: Int, col: String) {
        if (colNumber in rangeX) {
            val maxY = max(col.length, lastY + 1)
            for (y in 0 until maxY) {
                set(colNumber, y, col[y] == '#')
            }
        }
    }

    private fun row(rowNumber: Int): String = if (rowNumber !in rangeY) ""
    else rangeX.joinToString("") { if (get(it, rowNumber)) "#" else "." }

    private fun column(colNumber: Int): String = if (colNumber !in rangeX) ""
    else rangeY.joinToString("") { if (get(colNumber, it)) "#" else "." }

    fun print() = rangeY.joinToString("\n", transform = ::row)

    fun removeBorder(): Matrix2 {
        val ret = Matrix2(8, 8)
        (1 until lastX).forEach { x ->
            (1 until lastY).forEach { y ->
                ret[x - 1, y - 1] = this[x, y]
            }
        }
        return ret
    }

    fun setPartial(sx: Int, sy: Int, matrix: Matrix2): Matrix2 {
        matrix.rangeY.forEach { x ->
            matrix.rangeY.forEach { y ->
                this[sx + x, sy + y] = matrix[x, y]
            }
        }

        return this
    }

    fun getTruePoints() =
        (0 .. lastY).flatMap { y ->
            (0 .. lastX).map { x ->
                (Pair(x, y))
            }
        }.filter { get(it.first, it.second) }

    fun transform(how: Pair<Flipped, Rotate>) = this.flip(how.first).rotate(how.second)

    fun containsDragon(dragon:Matrix2):Int {
        val thisHeight = lastY+1
        val thisWidth = lastX+1
        val dragonHeight = dragon.lastY+1
        val dragonWidth = dragon.lastX+1
        val maxY = thisHeight - dragonHeight
        val maxX = thisWidth - dragonWidth
        // then starting with (0, 0) on this matrix, and going to (maxX-1,maxY-1)
        // place the dragon (0, 0) at that point and see if the dragon '#' lines
        // up with the '#' underneath it

        val dragonPoints = dragon.getTruePoints()

        return (0 until maxY).flatMap { y ->
            (0 until maxX).map { x ->
                Pair(x, y)
            }
        }.count { p ->
            dragonPoints.all{get(p.first+it.first, p.second+it.second)}
        }
    }

    fun removeDragons(dragon:Matrix2):Matrix2 {
        val thisHeight = lastY+1
        val thisWidth = lastX+1
        val dragonHeight = dragon.lastY+1
        val dragonWidth = dragon.lastX+1
        val maxY = thisHeight - dragonHeight
        val maxX = thisWidth - dragonWidth
        // then starting with (0, 0) on this matrix, and going to (maxX-1,maxY-1)
        // place the dragon (0, 0) at that point and see if the dragon '#' lines
        // up with the '#' underneath it

        val dragonPoints = dragon.getTruePoints()

        val startingPoints = (0 until maxY).flatMap { y ->
            (0 until maxX).map { x ->
                Pair(x, y)
            }
        }.filter { p ->
            dragonPoints.all{ get(p.first+it.first, p.second+it.second) }
        }

        startingPoints.forEach { p ->
            dragonPoints.forEach { dp ->
                set(p.first+dp.first, p.second+dp.second, false)
            }
        }
        return this
    }


    companion object {
        fun create(lines: List<String>) : Matrix2 {
            val sy = lines.count()
            val sx = lines[0].length
            val matrix = Matrix2(sx, sy)
            val yOffset = matrix.rangeY.first
            val xOffset = matrix.rangeX.first
            lines.forEachIndexed { y, s ->
                s.forEachIndexed { x, c ->
                    val v = c == '#'
                    matrix[x+xOffset, y+yOffset] = v
                }
            }
            return matrix
        }
    }
}




