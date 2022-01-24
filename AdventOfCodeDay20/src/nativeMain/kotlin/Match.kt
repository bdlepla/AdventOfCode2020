data class Match(
    val tileId:Long,
    val side: Side,
    val otherTileId:Long,
    val transform: Pair<Flipped, Rotate> )