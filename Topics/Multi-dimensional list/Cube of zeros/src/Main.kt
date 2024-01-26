fun main() {

    val matrix3D = mutableListOf(
        mutableListOf(
            mutableListOf<Int>(0, 0, 0),
            mutableListOf<Int>(0, 0, 0),
            mutableListOf<Int>(0, 0, 0),
        ),
        mutableListOf(
            mutableListOf<Int>(0, 0, 0),
            mutableListOf<Int>(0, 0, 0),
            mutableListOf<Int>(0, 0, 0),
        ),
        mutableListOf(
            mutableListOf<Int>(0, 0, 0),
            mutableListOf<Int>(0, 0, 0),
            mutableListOf<Int>(0, 0, 0),
        ),
    )

//    println(matrix3D.joinToString { it.joinToString { it.joinToString() } })
//    println(matrix3D.joinToString())
//    println(matrix3D.joinToString("\n") { it.joinToString("\n") })
    println(matrix3D)

}