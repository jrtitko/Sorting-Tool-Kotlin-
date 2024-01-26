fun main() {

    val input = readln()

loop@
    for (i in 'a'.. 'z') {
        for (j in input) {
            if (i == j) continue@loop
        }
        print(i)
    }
}