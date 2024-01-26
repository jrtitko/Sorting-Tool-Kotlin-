fun main() {
    val size = readln().toInt()

    var longest = 0
    var currentLength = 0

    var priorInt = 0
    for (i in 1..size) {
        val nextInt = readln().toInt()
        if (nextInt >= priorInt) {
            currentLength++
        } else if (currentLength >= longest) {
            longest = currentLength
            currentLength = 1
        }
        priorInt = nextInt
    }

    if (currentLength >= longest) {
        longest = currentLength
    }
    println(longest)
}