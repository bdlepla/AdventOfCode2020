// create a range of i elements centered around 0
// For odd numbers, the range will be -n to n where n = i/2
// for example, for 5, the range is -2..2 (-2, -1, 0, 1, 2)
// For even numbers, the range will be -m to n where m = n-1
// for example, for 8, the range will be -3 to 4
fun Int.createRange(): IntRange {
    val n = this / 2
    val adjustForEven = 1 - this % 2 // convert remainder of 0 or 1 to the opposite 1 or 0
    val start = -n + adjustForEven
    return start..n
}



