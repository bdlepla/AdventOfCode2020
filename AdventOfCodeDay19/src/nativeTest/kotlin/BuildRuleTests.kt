import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertNull

class BuildRuleTests {

    @Test
    fun notArRuleTest() {
        val expression = "bryan is not a rule(r)!"
        val rulePair = Rules.buildRule(expression)
        assertNull(rulePair)
    }

    @Test
    fun basicLetterRuleTest() {
        val expression = "1: \"a\""
        val rulePair = Rules.buildRule(expression)
        assertNotNull(rulePair)

        val idx = rulePair.first
        assertEquals(1, idx)

        val actualRule = rulePair.second
        val expectedRule = listOf(listOf(Leaf('a')))
        assertEquals(expectedRule, actualRule)
    }

    @Test
    fun basicOneRuleTest() {
        val expression = "2: 5 17"
        val rulePair = Rules.buildRule(expression)
        assertNotNull(rulePair)
        val idx = rulePair.first
        assertEquals(2, idx)

        val actualRule = rulePair.second
        val expectedRule = listOf(listOf(MultipleRule(5), MultipleRule(17)))
        assertEquals(expectedRule, actualRule)
    }

    @Test
    fun basicEitherRuleTest() {
        val expression = "3: 4 11 | 10 2"
        val rulePair = Rules.buildRule(expression)
        assertNotNull(rulePair)
        val idx = rulePair.first
        assertEquals(3, idx)

        val actualRule = rulePair.second
        val expectedRule = listOf(
            listOf(MultipleRule(4), MultipleRule(11)),
            listOf(MultipleRule(10), MultipleRule(2))
        )
        assertEquals(expectedRule, actualRule)
    }

    @Test
    fun buildRulesFromSample1Test() {
        val lines = listOf(
            "0: 1 2",
            """1: "a"""",
            "2: 1 3 | 3 1",
            """3: "b""""
        )

        val rules = Rules(lines)
        val actual = rules.count()
        val expected = 4
        assertEquals(expected, actual)
    }
}