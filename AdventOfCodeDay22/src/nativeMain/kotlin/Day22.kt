class Day22(lines: List<String>) {
    private val deck1 = lines.takeWhile{it.isNotBlank()}.drop(1).map{it.toInt()}
    private val deck2 = lines.dropWhile{it.isNotBlank()}.drop(2).map{it.toInt()}

    fun solvePart1():Int {
        val player1 = CardDeck(1, deck1)
        val player2 = CardDeck(2, deck2)

        while (player1.isNotLost() && player2.isNotLost()) {
            println(player1)
            println(player2)
            val card1 = player1.play()
            println("Player 1 plays: $card1")
            val card2 = player2.play()
            println("Player 2 plays: $card2")
            val winner = if (card1 > card2) 1 else 2
            println("Player $winner wins")
            if (winner == 1) {
                player1.add(card1)
                player1.add(card2)
            } else {
                player2.add(card2)
                player2.add(card1)
            }
        }

        val player1Score = player1.score()
        val player2Score = player2.score()
        return maxOf(player1Score, player2Score)

    }

    private var gameCounter = 1

    fun solvePart2(debug:Boolean):Int {
        if (debug) println("=== Game 1 ===")
        if (!debug) println("")

        val player1 = CardDeck(1, deck1)
        val player2 = CardDeck(2, deck2)

        var player1WinsBecauseOfPreviousDecks = false
        var round = 1
        while (player1.isNotLost() && player2.isNotLost()) {
            if (!debug) print(",")
            if (debug) println("")
            if (debug) println("-- Round $round (Game 1) --")
            if (player1.deckIsSameAsPrevious() && player2.deckIsSameAsPrevious()) {
                if (debug) println("Player 1 wins match because Both decks are the same as ones previously saved")

                player1WinsBecauseOfPreviousDecks = true
                break
            }

            player1.saveDeck()
            player2.saveDeck()

            if (debug) println(player1)
            if (debug) println(player2)

            val card1 = player1.play()
            if (debug) println("Player 1 plays: $card1")

            val card2 = player2.play()
            if (debug) println("Player 2 plays: $card2")

            var winner = if (card1 > card2) 1 else 2
            if (player1.countCards() >= card1 && player2.countCards() >= card2) {
                if (debug) println("Playing a sub-game to determine the winner...")
                gameCounter += 1
                winner = playSubGame(debug, gameCounter, player1.copy(card1), player2.copy(card2))
                if (debug) println("...anyway, back to game 1")
            }

            if (debug) println("Player $winner wins round $round of game 1!")

            if (winner == 1) {
                player1.add(card1)
                player1.add(card2)
            } else {
                player2.add(card2)
                player2.add(card1)
            }

            round += 1
        }

        val player1Score = player1.score()
        val player2Score = player2.score()

        return if (player1WinsBecauseOfPreviousDecks) player1Score
        else maxOf(player1Score, player2Score)
    }

    fun playSubGame(debug:Boolean, game:Int, player1:CardDeck, player2:CardDeck):Int {
        if (debug) println("")
        if (debug) println("=== Game $game ===")
        var round = 1
        while (player1.isNotLost() && player2.isNotLost()) {
            if (!debug) print(".")
            if (debug) println("")
            if (debug) println("-- Round $round (Game $game) -- ")
            if (player1.deckIsSameAsPrevious() && player2.deckIsSameAsPrevious()) {
                return 1
            }

            player1.saveDeck()
            player2.saveDeck()

            if (debug) println(player1)
            if (debug) println(player2)

            val card1 = player1.play()
            if (debug) println("Player 1 plays: $card1")

            val card2 = player2.play()
            if (debug) println("Player 2 plays: $card2")

            var winner = if (card1 > card2) 1 else 2
            if (player1.countCards() >= card1 && player2.countCards() >= card2) {
                if (debug) println("Playing a sub-game to determine the winner...")
                gameCounter += 1
                winner = playSubGame(debug, gameCounter, player1.copy(card1), player2.copy(card2))
                if (debug) println("...anyway, back to game $game")
            }

            if (debug) println("Player $winner wins round $round of game $game!")
            if (winner == 1) {
                player1.add(card1)
                player1.add(card2)
            } else {
                player2.add(card2)
                player2.add(card1)
            }

            round += 1
        }
        return if (player1.score() > player2.score()) 1 else 2
    }

}