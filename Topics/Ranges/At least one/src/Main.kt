fun main() {

    val start1 = readln().toInt()
    val end1 = readln().toInt()

    val start2 = readln().toInt()
    val end2 = readln().toInt()

    val input = readln().toInt()

    println( input in start1..end1 || input in start2..end2 )
}