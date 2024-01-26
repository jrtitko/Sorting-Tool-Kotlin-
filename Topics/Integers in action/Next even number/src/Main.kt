fun main() {
    val input = readln().toInt()

    val output = if (input % 2 == 0) (input + 2) else (input + 1)

    println(output)
}