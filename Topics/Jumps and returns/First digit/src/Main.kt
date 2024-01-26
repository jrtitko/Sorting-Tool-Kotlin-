fun main() {

    val input = readln()

    for (i in input) {
        if (i in '0'..'9') {
            println(i)
            break
        }
    }
}