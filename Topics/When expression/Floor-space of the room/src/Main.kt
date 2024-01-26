const val PI = 3.14

fun main() {

    val roomType = readln()

    var area = when(roomType) {
        "triangle" -> {
            val a = readln().toDouble()
            val b = readln().toDouble()
            val c = readln().toDouble()

            Math.sqrt((a + b + c) / 2 * ((a + b + c) / 2 - a) * ((a + b + c) / 2 - b) * ((a + b + c) / 2 - c))
        }
        "rectangle" -> {
            val a = readln().toDouble()
            val b = readln().toDouble()

            a * b
        }
        "circle" -> {
            val r = readln().toDouble()

            PI * r * r
        }

        else -> "Unknown"
    }

    println(area)
}