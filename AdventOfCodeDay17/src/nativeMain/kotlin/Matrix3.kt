data class Tuple3(val first: Int, val second: Int, val third: Int)

class Matrix3(sx: Int, sy: Int, sz: Int) {
    private val data = BooleanArray(sx*sy*sz) { false }
    private val xStride = 1
    private val yStride = sx
    private val zStride = sx*sy

    internal val rangeX = sx.createRange()
    internal val rangeY = sy.createRange()
    internal val rangeZ = sz.createRange()

    private val offsetX = rangeX.first
    private val offsetY = rangeY.first
    private val offsetZ = rangeZ.first

    private fun getDataIndex(x: Int, y: Int, z: Int) =
        (x-offsetX)*xStride + (y-offsetY)*yStride + (z-offsetZ)*zStride

    private fun contains(x:Int, y:Int, z:Int) = x in rangeX && y in rangeY && z in rangeZ
    private fun contains(t:Tuple3) = contains(t.first, t.second, t.third )

    operator fun get(x: Int, y: Int, z: Int) : Boolean {
        var ret = false
        if (contains(x, y, z)) {
            ret = data[getDataIndex(x, y, z)]
        }
        return ret
    }
    operator fun get(t:Tuple3) = get(t.first, t.second, t.third)
    operator fun set(x: Int, y: Int, z: Int, v: Boolean) {
        data[getDataIndex(x, y, z)] = v
    }

    internal fun countTrue() = data.count{b -> b}

    internal fun getNeighbors(x: Int, y:Int, z:Int): Sequence<Tuple3> =
        generateNeighbors(x, y, z).filter(::contains)

    private fun getTrueNeighbors(x:Int, y:Int, z:Int) =
        getNeighbors(x, y, z).filter(::get)

    fun countTrueNeighbors(x:Int, y:Int, z:Int): Int = getTrueNeighbors(x, y, z).count()

    // print x, y grid for given z
    fun print(z:Int): String {
        if (z !in rangeZ) return ""
        val ret = mutableListOf<String>()
        for (y in rangeY) {
            var line = String()
            for (x in rangeX) {
                val v = get(x, y, z)
                val c = if (v) '#' else '.'
                line += c
            }
            ret.add(line)
        }
        return ret.joinToString("\n")
    }

    fun transform(n: Int) = doTransformWork(n, this)

    fun transform(): Matrix3 {
        val nx = rangeX.count() + 2
        val ny = rangeY.count() + 2
        val nz = rangeZ.count() + 2
        val ret = Matrix3(nx, ny, nz)
        for (ix in ret.rangeX) {
            for (iy in ret.rangeY) {
                for (iz in ret.rangeZ) {
                    val currentTrue = get(ix, iy, iz)
                    val numberOfNeighbors = countTrueNeighbors(ix, iy, iz)
                    var newTrue = false
                    if (currentTrue && numberOfNeighbors in 2..3) {
                        newTrue = true
                    } else if (!currentTrue && numberOfNeighbors == 3) {
                        newTrue = true
                    }
                    if (newTrue) {
                        ret[ix, iy, iz] = true
                    }
                }
            }
        }
        return ret
    }

    companion object {
        // generate the 26 neighbors immediately around the given point
        fun generateNeighbors(x: Int, y:Int, z:Int): Sequence<Tuple3> =
            sequence {
                for (ix in x-1..x+1) {
                    for (iy in y-1..y+1) {
                        for (iz in z-1..z+1) {
                            if (ix != x || iy != y || iz != z) {
                                yield(Tuple3(ix, iy, iz))
                            }
                        }
                    }
                }
            }

        private tailrec fun doTransformWork(n:Int, m: Matrix3):Matrix3 =
            if (n == 0) m
            else doTransformWork(n-1, m.transform())

        fun create(lines: List<String>) : Matrix3 {
            val sy = lines.count()
            val sx = lines[0].length
            val sz = 1
            val matrix = Matrix3(sx, sy, sz)
            val yOffset = matrix.rangeY.first
            val xOffset = matrix.rangeX.first
            lines.forEachIndexed { y, s ->
                s.forEachIndexed { x, c ->
                    val v = c == '#'
                    matrix[x+xOffset, y+yOffset, 0] = v
                }
            }
            return matrix
        }

    }
}