fun main(args: Array<String>) {

    val input = readln().toInt()



    val shape = when(input) {
        1 -> "square"
        2 -> "circle"
        3 -> "triangle"
        4 -> "rhombus"
        else -> null
    }

    if (shape != null) {
        println("You have chosen a $shape")
    } else {
        println("There is no such shape!")
    }
}