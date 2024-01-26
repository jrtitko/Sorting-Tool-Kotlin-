fun main() {
    val input = readln().toInt()

    println(
        if ((-15 < input && input <= 12) ||
        (14 < input && input < 17) ||
        (19 <= input)) "True" else "False"
    )
}