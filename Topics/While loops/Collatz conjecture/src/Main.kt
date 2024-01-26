fun main() {

    var current = readln().toInt()

    print("$current ")

    while (current > 1) {

        current = if (current % 2 == 0) {
            current / 2
        } else {
            current * 3 + 1
        }

        print("$current ")
    }
}