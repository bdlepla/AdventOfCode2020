class Day23 {
    fun solvePart1(seed: String, moves: Int): String {
        val cups = Cups(seed)
        val firstCup = cups.playRounds(moves)
        return buildString {
            var cup = firstCup.next
            while (cup.value != 1) {
                append(cup.value)
                cup = cup.next
            }
        }
    }

    fun solvePart2(seed:String, moves:Int):Long {
        val cups = Cups(seed, 1_000_000)
        val firstCup = cups.playRounds(moves)
        val second = firstCup.next.value.toLong()
        val third = firstCup.next.next.value.toLong()
        return second * third
    }

}