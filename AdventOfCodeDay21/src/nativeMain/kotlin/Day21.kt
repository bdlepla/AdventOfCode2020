class Day21(lines: List<String>) {
    val food = lines.associate {
        val (ingredients, allergens) = parseLine(it)
        ingredients to allergens
    }

    val allAllergies = food.values.flatten().toSet()
    val allIngredients = food.keys.flatten().toSet()

    fun solvePart2(): String {
        // for each allergy, I want a list of bad ingredients associated with that allergy

        val allergyToBadIngredients = mutableMapOf<String, MutableSet<String>>()
        allAllergies.forEach { allergy ->
            allergyToBadIngredients[allergy] =  food
                .filter { allergy in it.value }
                .map { it.key }
                .reduce {carry, s -> s intersect carry }.toMutableSet()
        }

        println(allergyToBadIngredients)
        val resultMap = mutableMapOf<String, String>()

        while (allergyToBadIngredients.values.any{it.isNotEmpty()}) {
            val wow = allergyToBadIngredients.filter {it.value.count() == 1}
            val key = wow.keys.first()
            val value = allergyToBadIngredients[key]!!.first()
            resultMap[key]=value
            allergyToBadIngredients.values.forEach { setOfIngredients ->
                setOfIngredients.remove(value)
            }
        }

        val sortedKeys = resultMap.keys.sorted()
        return sortedKeys.map{resultMap[it]}.joinToString(",")
    }

    fun solvePart1(): Long {
        val badIngredients = allAllergies.flatMap { allergy ->
            food
                .filter { allergy in it.value }
                .map { it.key }
                .reduce {carry, s -> s intersect carry }
        }.toSet()
        println("badIngredients = $badIngredients")

        val goodIngredients = allIngredients subtract badIngredients
        println("goodIngredients = $goodIngredients")

        val result = food.keys.sumOf { recipe ->
            recipe.count{ingredient -> ingredient in goodIngredients}
        }

        return result.toLong()

    }

    companion object {
        fun parseLine(line: String):Pair<Set<String>, Set<String>> {
            // x y z a b c (contains n, m, o)
            val ingredientList = line.substringBefore(" (").split(' ').toSet()
            val allergenList = line.substringAfter("(contains ")
                .substringBefore(")").split(", ").toSet()
            return Pair(ingredientList, allergenList)
        }
    }

}