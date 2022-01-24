class CryptoGraphicEndpoint(private val publicKey:Long) {

    fun getLoopSize(target:Long=publicKey):Int =
        generateSequence(1L) { it.innerTransform()}.indexOf(target)

}

fun Long.innerTransform(other:Long = 7):Long = (this * other) % 20201227L
fun Int.transform(subject:Long):Long = generateSequence(1L){ it.innerTransform(subject)}.drop(this).first()