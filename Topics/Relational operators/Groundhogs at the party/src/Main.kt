fun main() {
    val reeses = readln().toInt()
    val weekend = readln().toBoolean()

    println((weekend && reeses in 15..25) || (!weekend && reeses in 10..20))
}