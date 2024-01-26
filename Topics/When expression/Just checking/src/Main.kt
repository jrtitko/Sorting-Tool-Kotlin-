fun main() {

    val input = readln().toInt()

    val response = when (input) {
        1 -> "No!"
        2 -> "Yes!"
        3 -> "No!"
        4 -> "No!"
        else -> "Unknown number"
    }

    println(response)
}