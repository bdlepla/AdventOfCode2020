data class Tuple4(val first: Int, val second: Int, val third: Int, val fourth: Int)

class Matrix4(sx: Int, sy: Int, sz: Int, sw: Int)  {
    private val data = BooleanArray(sx*sy*sz*sw) { false }
    private val xStride = 1
    private val yStride = sx
    private val zStride = sx*sy
    private val wStride = sx*sy*sz

    internal val rangeX = sx.createRange()
    internal val rangeY = sy.createRange()
    internal val rangeZ = sz.createRange()
    internal val rangeW = sw.createRange()

    private val offsetX = rangeX.first
    private val offsetY = rangeY.first
    private val offsetZ = rangeZ.first
    private val offsetW = rangeW.first

    private fun getDataIndex(x: Int, y: Int, z: Int, w:Int) =
        (x-offsetX)*xStride + (y-offsetY)*yStride + (z-offsetZ)*zStride + (w-offsetW)*wStride

    private fun contains(x:Int, y:Int, z:Int, w:Int) =
        x in rangeX && y in rangeY && z in rangeZ && w in rangeW
    private fun contains(t:Tuple4) = contains(t.first, t.second, t.third, t.fourth)

    operator fun get(x: Int, y: Int, z: Int, w: Int) : Boolean {
        var ret = false
        if (contains(x, y, z, w)) {
            ret = data[getDataIndex(x, y, z, w)]
        }
        return ret
    }
    operator fun get(t:Tuple4) = get(t.first, t.second, t.third, t.fourth)
    operator fun set(x: Int, y: Int, z: Int, w: Int, v: Boolean) {
        data[getDataIndex(x, y, z, w)] = v
    }

    internal fun countTrue() = data.count{b -> b}

    internal fun getNeighbors(x: Int, y:Int, z:Int, w:Int): Sequence<Tuple4> =
        generateNeighbors(x, y, z, w).filter(::contains)

    private fun getTrueNeighbors(x:Int, y:Int, z:Int, w:Int) =
        getNeighbors(x, y, z, w).filter(::get)

    fun countTrueNeighbors(x:Int, y:Int, z:Int, w:Int): Int = getTrueNeighbors(x, y, z, w).count()

    fun transform(n: Int) = doTransformWork(n, this)

    fun transform(): Matrix4 {
        val nx = rangeX.count() + 2
        val ny = rangeY.count() + 2
        val nz = rangeZ.count() + 2
        val nw = rangeW.count() + 2
        val ret = Matrix4(nx, ny, nz, nw)
        for (ix in ret.rangeX) {
            for (iy in ret.rangeY) {
                for (iz in 0..ret.rangeZ.last) { // z > 0 mirrors z < 0
                    for (iw in 0..ret.rangeW.last) { // w > 0 mirrors w < 0
                        val currentTrue = get(ix, iy, iz, iw)
                        val numberOfNeighbors = countTrueNeighbors(ix, iy, iz, iw)
                        var newTrue = false
                        if (currentTrue && numberOfNeighbors in 2..3) {
                            newTrue = true
                        } else if (!currentTrue && numberOfNeighbors == 3) {
                            newTrue = true
                        }
                        if (newTrue) {
                            ret[ix, iy, iz, iw] = true
                            // mirror the z and w
                            ret[ix, iy, iz, -iw] = true
                            ret[ix, iy, -iz, iw] = true
                            ret[ix, iy, -iz, -iw] = true
                        }
                    }
                }
            }
        }
        return ret
    }

    fun print(z:Int, w:Int): String {
        if (z !in rangeZ) return ""
        val ret = mutableListOf<String>()
        for (y in rangeY) {
            var line = String()
            for (x in rangeX) {
                val v = get(x, y, z, w)
                val c = if (v) '#' else '.'
                line += c
            }
            ret.add(line)
        }
        return ret.joinToString("\n")
    }


    companion object {
        // generate the 26 neighbors immediately around the given point
        fun generateNeighbors(x: Int, y:Int, z:Int, w:Int): Sequence<Tuple4> =
            sequence {
                for (ix in x-1..x+1) {
                    for (iy in y-1..y+1) {
                        for (iz in z-1..z+1) {
                            for (iw in w-1..w+1)
                            if (ix != x || iy != y || iz != z || iw != w) {
                                yield(Tuple4(ix, iy, iz, iw))
                            }
                        }
                    }
                }
            }

        private tailrec fun doTransformWork(n:Int, m: Matrix4):Matrix4 =
            if (n == 0) m
            else doTransformWork(n-1, m.transform())

        fun create(lines: List<String>) : Matrix4 {
            val sy = lines.count()
            val sx = lines[0].length
            val sz = 1
            val sw = 1
            val matrix = Matrix4(sx, sy, sz, sw)
            val yOffset = matrix.rangeY.first
            val xOffset = matrix.rangeX.first
            lines.forEachIndexed { y, s ->
                s.forEachIndexed { x, c ->
                    val v = c == '#'
                    matrix[x+xOffset, y+yOffset, 0, 0] = v
                }
            }
            return matrix
        }
    }
}