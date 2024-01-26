const val GRAVITY_CONSTANT = 9.8

fun main() {

    val density = readln().toDouble()
    val column = readln().toDouble()

    println(density * column * GRAVITY_CONSTANT)
}