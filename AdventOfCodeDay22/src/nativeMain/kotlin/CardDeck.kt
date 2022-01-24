class CardDeck(private val player: Int) {
    constructor(player:Int, cards: List<Int>):this(player) { deal(cards) }
    private val data = Queue<Int>()
    private val previousDecks = mutableListOf<String>()
    private fun deal(cards:List<Int>) = cards.forEach(::add)
    fun add(c:Int) = data.enqueue(c)
    fun play():Int = data.dequeue()
    fun isNotLost() = data.isNotEmpty()
    override fun toString() = "Player $player's deck: $data"
    fun countCards() = data.count()
    fun score(): Int {
        val numCards = data.count()
        return data.asSequence().mapIndexed{ idx, num -> (numCards - idx)*num}.sum()
    }
    fun saveDeck() = previousDecks.add(data.asSequence().joinToString(","))
    fun deckIsSameAsPrevious():Boolean {
        val currentDeck = data.asSequence().joinToString(",")
        return previousDecks.any {it == currentDeck}
    }
    fun copy(num:Int):CardDeck = CardDeck(player, data.asSequence().take(num).toList())
}