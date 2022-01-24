class Document(line: String) {
    private val keyValues = line.split(' ')
    private val regex = """(\S+):(\S+)""".toRegex()
    private val keyValuePairs = keyValues.map {
        val (key, value) = regex.find(it)!!.destructured
        key to value
    }
    fun isSimplyValid() = isValid(true)

    fun isValid(simple:Boolean=false): Boolean {
        println("")
        return birthYearValid(simple) &&
                issueYearValid(simple) &&
                expirationYearValid(simple) &&
                heightValid(simple) &&
                hairColorValid(simple) &&
                eyeColorValid(simple) &&
                passportIdValid(simple)
    }

    private fun birthYearValid(simple: Boolean): Boolean {
        val value = getValue("byr") ?: return false
        if (simple) return true

        val year = value.toInt()
        println("byr is $year")
        return year in 1920..2002
    }

    private fun issueYearValid(simple: Boolean): Boolean {
        val value = getValue("iyr") ?: return false
        if (simple) return true

        val year = value.toInt()
        println("iyr is $year")
        return year in 2010..2020
    }

    private fun expirationYearValid(simple: Boolean): Boolean {
        val value = getValue("eyr") ?: return false
        if (simple) return true

        val year = value.toInt()
        println("eyr in $year")
        return year in 2020..2030
    }

    private fun heightValid(simple:Boolean) : Boolean {
        val value = getValue("hgt") ?: return false
        if (simple) return true

        val inchesRegex = "([0-9]+)in".toRegex()
        if (inchesRegex.matches(value)) {
            val (inches) = inchesRegex.find(value)!!.destructured
            println("inches is $inches")
            return inches.toInt() in 59..76
        }

        val centRegex = "([0-9]+)cm".toRegex()
        if (centRegex.matches(value)) {
            val (cents) = centRegex.find(value)!!.destructured
            println("cents is $cents")
            return cents.toInt() in 150..193
        }
        return false
    }

    private fun hairColorValid(simple:Boolean) : Boolean {
        val value = getValue("hcl") ?: return false
        if (simple) return true
        val regex = "#[0-9a-f]{6}".toRegex()
        val ret = regex.matches(value)
        println("hcl is $value and matches? $ret")
        return ret
    }

    private fun eyeColorValid(simple:Boolean) : Boolean {
        val value = getValue("ecl") ?: return false
        if (simple) return true
        val validColors = listOf("amb", "blu", "brn", "gry", "grn", "hzl", "oth")
        println("ecl is $value")
        return value in validColors
    }

    private fun passportIdValid(simple:Boolean) : Boolean {
        val value = getValue("pid") ?: return false
        if (simple) return true
        val regex = "[0-9]{9}".toRegex()
        println("pid is $value")
        return regex.matches(value)
    }

    fun getValue(key: String) : String? =
        keyValuePairs.firstOrNull { it.first == key}?.second
}