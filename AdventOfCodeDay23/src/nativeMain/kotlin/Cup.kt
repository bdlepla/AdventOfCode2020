class Cup(var value:Int) {
    lateinit var next: Cup
    fun getNext3Cups(): List<Cup>{
        return (1..3).runningFold(this){previous, _ -> previous.next }.drop(1)
    }
}