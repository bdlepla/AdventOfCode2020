import kotlin.test.Test
import kotlin.test.assertEquals

class RunRuleTests {
    @Test
    fun b_ShouldMatchRule3FromSample1Test()
    {
        val lines = listOf(
            "0: 1 2",
            """1: "a"""",
            "2: 1 3 | 3 1",
            """3: "b""""
        )

        val rules = Rules(lines)
        val actual1 = rules.match("b", 0) // this returns the positions of matches
        val expected1 = listOf<Int>()
        assertEquals(expected1, actual1)

        val actual = rules.match("b") // this returns the rule ids that match
        val expected = listOf(3)
        assertEquals(expected, actual)
    }

    @Test
    fun a_ShouldMatchRule1FromSample1Test()
    {
        val lines = listOf(
            "0: 1 2",
            """1: "a"""",
            "2: 1 3 | 3 1",
            """3: "b""""
        )

        val rules = Rules(lines)
        val actual = rules.match("a")
        val expected = listOf(1)
        assertEquals(expected, actual)
    }

    @Test
    fun ab_ShouldMatchSimpleOneRuleFromSample1Test()
    {
        val lines = listOf(
            "0: 1 2",
            """1: "a"""",
            """2: "b""""
        )

        val rules = Rules(lines)
        //val actual = rules.match("ab")
        //val expected = listOf(0)
        val actual = rules.match("ab", 1)
        val expected = listOf(-1)
        assertEquals(expected, actual)
    }

    @Test
    fun ab_ShouldMatch2FromSample1Test()
    {
        val lines = listOf(
            "0: 1 2",
            """1: "a"""",
            "2: 1 3 | 3 1",
            """3: "b""""
        )

        val rules = Rules(lines)
        val actual = rules.match("ab")
        val expected = listOf(2)
        assertEquals(expected, actual)
    }

    @Test
    fun ba_ShouldMatch2FromSample1Test()
    {
        val lines = listOf(
            "0: 1 2",
            """1: "a"""",
            "2: 1 3 | 3 1",
            """3: "b""""
        )

        val rules = Rules(lines)

        val actual = rules.match("ba")
        val expected = listOf(2)
        assertEquals(expected, actual)
    }

    @Test
    fun aab_ShouldMatch0FromSample1Test()
    {
        val lines = listOf(
            "0: 1 2",
            """1: "a"""",
            "2: 1 3 | 3 1",
            """3: "b""""
        )

        val rules = Rules(lines)
        val actual = rules.match("aab")
        val expected = listOf(0)
        assertEquals(expected, actual)
    }

    @Test
    fun aba_ShouldMatch0FromSample1Test()
    {
        val lines = listOf(
            "0: 1 2",
            """1: "a"""",
            "2: 1 3 | 3 1",
            """3: "b""""
        )

        val rules = Rules(lines)
        val actual = rules.match("aba")
        val expected = listOf(0)
        assertEquals(expected, actual)
    }

    @Test
    fun bab_ShouldNotMatchAnythingFromSample1Test()
    {
        val lines = listOf(
            "0: 1 2",
            """1: "a"""",
            "2: 1 3 | 3 1",
            """3: "b""""
        )

        val rules = Rules(lines)
        val actual = rules.match("bab")
        val expected = listOf<Int>()
        assertEquals(expected, actual)
    }

    @Test
    fun bab_ShouldNotMatchAnythingFromSample2Test()
    {
        val lines = listOf(
            "0: 1 2",
            """1: "a"""",
            "2: 1 3 | 3 1",
            """3: "b""""
        )

        val rules = Rules(lines)
        val actual = rules.match("bab")
        val expected = listOf<Int>()
        assertEquals(expected, actual)
    }

    @Test
    fun shouldBe2InputsThatMatch0FromSample2Test()
    {
        val lines = listOf(
            "0: 4 1 5",
            "1: 2 3 | 3 2",
            "2: 4 4 | 5 5",
            "3: 4 5 | 5 4",
            """4: "a"""",
            """5: "b""""
        )

        val inputs = listOf(
            "ababbb",
            "bababa",
            "abbbab",
            "aaabbb",
            "aaaabbb"
        )

        val rules = Rules(lines)
        val matches = inputs.map{rules.match(it)}
        val actual = matches.count{it.contains(0)}
        val expected = 2
        assertEquals(expected, actual)
    }

    @Test
    fun shouldBe3InputsThatMatchFromSample3WithNoLoopsTest()
    {
        val lines = listOf(
            "42: 9 14 | 10 1",
            "9: 14 27 | 1 26",
            "10: 23 14 | 28 1",
            """1: "a"""",
            "11: 42 31",
            "5: 1 14 | 15 1",
            "19: 14 1 | 14 14",
            "12: 24 14 | 19 1",
            "16: 15 1 | 14 14",
            "31: 14 17 | 1 13",
            "6: 14 14 | 1 14",
            "2: 1 24 | 14 4",
            "0: 8 11",
            "13: 14 3 | 1 12",
            "15: 1 | 14",
            "17: 14 2 | 1 7",
            "23: 25 1 | 22 14",
            "28: 16 1",
            "4: 1 1",
            "20: 14 14 | 1 15",
            "3: 5 14 | 16 1",
            "27: 1 6 | 14 18",
            """14: "b"""",
            "21: 14 1 | 1 14",
            "25: 1 1 | 1 14",
            "22: 14 14",
            "8: 42",
            "26: 14 22 | 1 20",
            "18: 15 15",
            "7: 14 5 | 1 21",
            "24: 14 1"
        )

        val inputs = listOf(
            "abbbbbabbbaaaababbaabbbbabababbbabbbbbbabaaaa",
            "bbabbbbaabaabba",
            "babbbbaabbbbbabbbbbbaabaaabaaa",
            "aaabbbbbbaaaabaababaabababbabaaabbababababaaa",
            "bbbbbbbaaaabbbbaaabbabaaa",
            "bbbababbbbaaaaaaaabbababaaababaabab",
            "ababaaaaaabaaab",
            "ababaaaaabbbaba",
            "baabbaaaabbaaaababbaababb",
            "abbbbabbbbaaaababbbbbbaaaababb",
            "aaaaabbaabaaaaababaa",
            "aaaabbaaaabbaaa",
            "aaaabbaabbaaaaaaabbbabbbaaabbaabaaa",
            "babaaabbbaaabaababbaabababaaab",
            "aabbbbbaabbbaaaaaabbbbbababaaaaabbaaabba"
        )

        val rules = Rules(lines)
        val matches = inputs.map{rules.match(it)}
        val actual = matches.count()
        val expected = 3
        assertEquals(expected, actual)
    }

    @Test
    fun shouldBe12InputsThatMatchFromSample3WithLoopsTest()
    {
        val lines = listOf(
            "42: 9 14 | 10 1",
            "9: 14 27 | 1 26",
            "10: 23 14 | 28 1",
            """1: "a"""",
            "11: 42 31 | 42 11 31",
            "5: 1 14 | 15 1",
            "19: 14 1 | 14 14",
            "12: 24 14 | 19 1",
            "16: 15 1 | 14 14",
            "31: 14 17 | 1 13",
            "6: 14 14 | 1 14",
            "2: 1 24 | 14 4",
            "0: 8 11",
            "13: 14 3 | 1 12",
            "15: 1 | 14",
            "17: 14 2 | 1 7",
            "23: 25 1 | 22 14",
            "28: 16 1",
            "4: 1 1",
            "20: 14 14 | 1 15",
            "3: 5 14 | 16 1",
            "27: 1 6 | 14 18",
            """14: "b"""",
            "21: 14 1 | 1 14",
            "25: 1 1 | 1 14",
            "22: 14 14",
            "8: 42 | 42 8",
            "26: 14 22 | 1 20",
            "18: 15 15",
            "7: 14 5 | 1 21",
            "24: 14 1"
        )

        val inputs = listOf(
            "abbbbbabbbaaaababbaabbbbabababbbabbbbbbabaaaa",
            "bbabbbbaabaabba",
            "babbbbaabbbbbabbbbbbaabaaabaaa",
            "aaabbbbbbaaaabaababaabababbabaaabbababababaaa",
            "bbbbbbbaaaabbbbaaabbabaaa",
            "bbbababbbbaaaaaaaabbababaaababaabab",
            "ababaaaaaabaaab",
            "ababaaaaabbbaba",
            "baabbaaaabbaaaababbaababb",
            "abbbbabbbbaaaababbbbbbaaaababb",
            "aaaaabbaabaaaaababaa",
            "aaaabbaaaabbaaa",
            "aaaabbaabbaaaaaaabbbabbbaaabbaabaaa",
            "babaaabbbaaabaababbaabababaaab",
            "aabbbbbaabbbaaaaaabbbbbababaaaaabbaaabba"
        )

        val rules = Rules(lines)
        val matches = inputs.map{rules.match(it)}
        val actual = matches.count()
        val expected = 12
        assertEquals(expected, actual)
    }

    @Test
    fun shouldBe0ThatMatchFromSample3WithLoopsTest()
    {
        val lines = listOf(
            "42: 9 14 | 10 1",
            "9: 14 27 | 1 26",
            "10: 23 14 | 28 1",
            """1: "a"""",
            "11: 42 31 | 42 11 31",
            "5: 1 14 | 15 1",
            "19: 14 1 | 14 14",
            "12: 24 14 | 19 1",
            "16: 15 1 | 14 14",
            "31: 14 17 | 1 13",
            "6: 14 14 | 1 14",
            "2: 1 24 | 14 4",
            "0: 8 11",
            "13: 14 3 | 1 12",
            "15: 1 | 14",
            "17: 14 2 | 1 7",
            "23: 25 1 | 22 14",
            "28: 16 1",
            "4: 1 1",
            "20: 14 14 | 1 15",
            "3: 5 14 | 16 1",
            "27: 1 6 | 14 18",
            """14: "b"""",
            "21: 14 1 | 1 14",
            "25: 1 1 | 1 14",
            "22: 14 14",
            "8: 42 | 42 8",
            "26: 14 22 | 1 20",
            "18: 15 15",
            "7: 14 5 | 1 21",
            "24: 14 1"
        )

        val expression =  "aaaaabbaabaaaaababaa"

        val rules = Rules(lines)
        val actual = rules.match(expression)
        val expected = listOf(0)
        assertEquals(expected, actual)
    }

    @Test
    fun simpleCircleLoop() {
        val lines = listOf(
            "0: 1 2 | 0 1",
            """1: \"a\"""",
            """2: \"b\""""
        )

        val rules = Rules(lines)
        assertEquals(3, rules.count())

        val actual1 = rules.match("ab")
        val expected1 = listOf<Int>()
        assertEquals(expected1, actual1)


    }

}