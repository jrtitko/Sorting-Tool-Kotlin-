fun main() {
    val inputs = readln().toInt()

    var perfect = 0
    var large = 0
    var reject = 0

    repeat(inputs) {
        val size = readln().toInt()

        if (size ==  1) large++
        if (size ==  0) perfect++
        if (size == -1) reject++
    }

    println("$perfect $large $reject")
}