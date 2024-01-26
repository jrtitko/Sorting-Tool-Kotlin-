fun main() {

    val a = readln().toInt()
    val b = readln().toInt()

    var total = 0
    for (i in a..b) {
        total += i
    }

    println(total)
}