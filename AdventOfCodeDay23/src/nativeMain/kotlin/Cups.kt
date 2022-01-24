class Cups(seed:String, numCups:Int = seed.length) {
    // idx are 0 based
    // values are 1 based
    private val cups = List(numCups) {Cup(it+1)}
    private val orderedCupIdx = seed.map{it.asInt()-1} + (seed.length+1..numCups).map{it-1}
    private val firstCup = cups[orderedCupIdx.first()]
    private var currentCup = firstCup

    init {
        orderedCupIdx
            .map {cups[it]} // gets the cup and will be sorted by cupsToInitialize; cup->next defines order
            .fold(cups[orderedCupIdx.last()]) { previous, cup ->
                cup.also{ previous.next = cup}
            }

        cups[orderedCupIdx.last()].next = cups[orderedCupIdx.first()]
    }

    fun playRounds(rounds:Int):Cup {
        repeat(rounds) {
            //val move = it+1
            //println("-- move $move -- ")
            playRound()
            //println("")
        }
       // val final = printCups()
        //println("")
        //println("final: $final")
        return cups[0]
    }

    private fun printCups():String {
        return buildString {
            var cup = firstCup
            repeat(9) {

                val cupValue = cup.value
                if (cup == currentCup) {
                    append("($cupValue) ")
                }
                else {
                    append("$cupValue ")
                }
                cup = cup.next
            }
        }
    }

    private fun playRound() {
        //val listOfCups = cups.map{it.value}.joinToString(",")
        //println("cups: $listOfCups")
        //val orderedCups = printCups()
        //println("ordered cups: $orderedCups")
        // get the next 3 cups clockwise from the current cup
        val next3Cups = currentCup.getNext3Cups()
        //val pickupCupsString = next3Cups.map{it.value}.joinToString(", ")
        //println("pick up: $pickupCupsString")
        // find the next destination
        val destination = findNextDestination(next3Cups.map{it.value}.toSet())
        //val destValue = destination.value
        //println("destination: $destValue")
        // remove the 3 cups and insert them after the destination
        moveCups(destination, next3Cups)
        currentCup = currentCup.next
    }

   private fun findNextDestination(excludeCups:Set<Int>):Cup {
       var dest = currentCup.value - 1
       if (dest == 0) dest = cups.size
       while (dest in excludeCups) {
           dest -= 1
           if (dest < 1) dest = cups.size
       }
       var destIdx = dest - 1
       if (destIdx < 0) destIdx = cups.size-1
       return cups[destIdx]
   }

    private fun moveCups(destinationCup:Cup, pickupCups: List<Cup>) {
        // remove the pickup cups from just after the current cup
        currentCup.next = pickupCups.last().next

        // we are going to insert the pickup cups between the destinationCup and destinationCup.next
        val saveDestNext = destinationCup.next
        destinationCup.next = pickupCups.first()
        pickupCups.last().next = saveDestNext
    }
}

fun Char.asInt() = this.toString().toInt()