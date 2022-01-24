package sample

class Day01(lines: List<String>) {

    private val data = lines.map{it.toLong()}.sorted().asSequence()

    fun solvePart1() = getAllPairs(data)
        .filter{it.first + it.second == 2020L}
        .map{it.first * it.second}
        .first()

    fun solvePart1a() =
        // data is sorted() and that is the trick to only drop while sum < 2020
        data.mapIndexedNotNull { idx, a ->
            data
                .drop(idx+1)
                .dropWhile {a + it < 2020L }
                .take(1)
                .firstOrNull{a + it == 2020L}
                ?.let{a * it}

        }.first()

    fun solvePart2() = getAllTriples(data)
        .filter{it.first + it.second + it.third == 2020L}
        .map{it.first * it.second * it.third}
        .first()

    fun solvePart2a() =
        data.mapIndexedNotNull { idx, a ->
            data
                .drop(idx+1)
                .mapIndexedNotNull{idx2, b ->
                    data
                        .drop(idx2+1)
                        .dropWhile {a + b + it < 2020L } // this works because data is sorted()
                    .take(1)
                    .firstOrNull{a + b + it == 2020L}
                    ?.let{a * b * it}
                }.firstOrNull()
        }.first()


    companion object {
        fun getAllPairs(list:Sequence<Long>):Sequence<Pair<Long, Long>> =
            sequence {
                val secondList = list.drop(1)
                if (secondList.count() > 0) {
                    val first = list.first()
                    yieldAll(secondList.map { Pair(first, it) })
                    yieldAll(getAllPairs(secondList))
                }
            }

        fun getAllTriples(list:Sequence<Long>):Sequence<Triple<Long, Long, Long>> =
            sequence {
                val secondList = list.drop(1)
                if (secondList.count() > 0) {
                    val first = list.first()
                    yieldAll(getAllPairs(secondList).map{Triple(first, it.first, it.second)})
                    yieldAll(getAllTriples(secondList))
                }
            }
    }
}