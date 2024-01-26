fun summator(map: Map<Int, Int>): Int {
    var total = 0
    for ((k,v) in map) {
        if (k % 2 == 0) total += v
    }

    return total
}