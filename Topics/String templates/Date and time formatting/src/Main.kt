fun main() {
    val (hours, minutes, seconds) = readln().split(' ')
    val (month, day, year) = readln().split(' ')

    println("$hours:$minutes:$seconds $month/$day/$year")
}