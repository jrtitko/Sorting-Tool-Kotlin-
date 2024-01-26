fun main() {
    val studentsMarks = mutableMapOf<String, Int>()

    do {
        val name = readln()
        if (name == "stop") break
        var grade = readln().toInt()

        if (!studentsMarks.contains(name)) {
            studentsMarks[name] = grade
        }
    } while(true)

    println(studentsMarks.toString())
}