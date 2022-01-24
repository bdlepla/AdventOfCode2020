class Tile(val id: Long, private val matrix:Matrix2) {
    constructor(id: Long, lines: List<String>) : this(id, Matrix2.create(lines))

    // the idea is for each of the 4 sides on this tile,
    // try to match to the rotated corresponding side of the other
    //
    // for example,
    // check top of this tile against the bottom of other tile
    // rotated 0, 90, 180 and 270 degrees; if any match return true
    //
    // otherwise, check right of this tile against left of other tile, through all
    // the rotations and return true if any match
    //
    // continue the same with this bottom and this left against all the top and right
    // rotations of the other tile
    fun anySideMatches(otherTile: Tile): Boolean = Side.values().any{ side ->
        when (side) {
            Side.Top -> otherTile.allTransformations().any { transformedMatrix ->
                matrix.side(side) == transformedMatrix.side(Side.Bottom)}
            Side.Bottom -> otherTile.allTransformations().any { transformedMatrix ->
                matrix.side(side) == transformedMatrix.side(Side.Top)}
            Side.Left -> otherTile.allTransformations().any { transformedMatrix ->
                matrix.side(side) == transformedMatrix.side(Side.Right)}
            Side.Right -> otherTile.allTransformations().any { transformedMatrix ->
                matrix.side(side) == transformedMatrix.side(Side.Left)}
        }
    }

    internal fun getMatch(otherTile: Tile): Match? {
        return Side.values().mapNotNull { side ->
            getMatchOfAnySide(side, otherTile )
        }.firstOrNull()
    }

    private fun getMatchOfAnySide(side: Side, otherTile: Tile): Match? {
        return Companion.buildTransformations.mapNotNull { transformation ->
            if (doesTransformedTileMatch(side, otherTile, transformation))
                transformation else null
        }.map { Match(id, side, otherTile.id, it) }.firstOrNull()
    }

    private fun doesTransformedTileMatch(side:Side, otherTile:Tile, transformation:Pair<Flipped, Rotate>):Boolean {
        val thisSide = this.matrix.side(side)
        val otherSide = otherTile.transform(transformation).side(opposite(side))
        val result = thisSide == otherSide
        //println("'$thisSide' =+ '$otherSide' ? $result")
        return result
    }

    private fun opposite(side: Side) =
        when (side){
            Side.Top -> Side.Bottom
            Side.Bottom -> Side.Top
            Side.Left -> Side.Right
            Side.Right -> Side.Left
        }

    private fun transform(how: Pair<Flipped, Rotate>): Matrix2 = this.matrix.transform(how)


    private fun allTransformations() = Companion.buildTransformations.map(::transform)

    internal fun rotate(how: Rotate) : Tile = Tile(id, this.matrix.rotate(how))

    internal fun transformToMatch(otherTile: Tile, otherSide: Side) : Tile? {
        //println("called transformToMatch(${otherTile.id}, $otherSide)")
        val transforms = Companion.buildTransformations.filter { doesTransformedTileMatch(otherSide, otherTile, it) }
        //println("   ${transforms.count()} transforms that match side")
        return if (transforms.isNotEmpty())
            Tile(otherTile.id, otherTile.transform(transforms.first()))
        else null
    }

    internal fun removeBorder(): Matrix2 = this.matrix.removeBorder()

    companion object {
        private fun lazyBuildTransformations(): List<Pair<Flipped, Rotate>> {
            val result = Rotate.values().flatMap { rotate ->
                Flipped.values().map { flipped ->
                    Pair(flipped, rotate)
                }
            }
            //println(result)
            return result
        }

        val buildTransformations: List<Pair<Flipped, Rotate>> by lazy {
            lazyBuildTransformations()
        }
    }

}