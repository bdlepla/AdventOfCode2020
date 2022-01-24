class Day25(publicKeys:Pair<Int, Int>) {
    private val cardPublicKey = publicKeys.first.toLong()
    private val doorPublicKey = publicKeys.second.toLong()
    val card = CryptoGraphicEndpoint(cardPublicKey)
    val door = CryptoGraphicEndpoint(doorPublicKey)

    fun solvePart1():Long {
        val cardLoop = card.getLoopSize()
        val doorLoop = door.getLoopSize()
        val encryptionKey1 = cardLoop.transform(doorPublicKey)
        println("key 1 = $encryptionKey1")
        val encryptionKey2 = doorLoop.transform(cardPublicKey)
        println("key 2 = $encryptionKey2")
        return encryptionKey1
    }
}