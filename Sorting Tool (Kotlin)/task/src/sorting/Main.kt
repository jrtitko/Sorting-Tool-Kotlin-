package sorting

import java.io.File
import java.security.InvalidParameterException
import java.util.*

var numOfEntries = 0
var dataType: String? = "word"
var sortType: String? = "natural"
var inputFileName: String? = null
var outputFileName: String? = null

//private fun parseElements(lines: List<String>) = lines.flatMap { it.split("\\s+".toRegex()) }
//val parseElements = fun(lines: List<String>) = lines.flatMap { it.split("\\s+".toRegex()) }
val splitOnSpace = { line: String -> line.split("\\s+".toRegex()) }
val parseElements = fun(lines: List<String>) = lines.flatMap ( splitOnSpace )

private fun parseElementsAsInt(lines: List<String>) = parseElements(lines).filter {
    try {
        it.toInt()
        true
    } catch(e: NumberFormatException) {
        println("\"${it}\" is not a long. It will be skipped.")
        false
    }
}.map { it.toInt() }

private fun outputLine(line: String) = if (outputFileName == null) println(line) else File(outputFileName!!).appendText(line + "\n")

fun main(args: Array<String>) {

    try {
        extractParameters(args)
    } catch (e: InvalidParameterException) {
        println(e.message)
    }

    val inputLines = getInputLines(inputFileName)

    val unsortedList: List<Any>
    // Organize Data
    when (dataType) {
        "long" -> {
            unsortedList = parseElementsAsInt(inputLines)
            numOfEntries = unsortedList.size
            outputLine("Total numbers: $numOfEntries.")
        }
        "line" -> {
            unsortedList = inputLines
            numOfEntries = unsortedList.size
            outputLine("Total lines: $numOfEntries.")
        }
        "word" -> {
            unsortedList = parseElements(inputLines)
            numOfEntries = unsortedList.size
            outputLine("Total words: $numOfEntries.")
        }
        else -> {
            println("Invalid parameter for -dataType")
            return
        }
    }

    when (sortType) {
        "natural" -> {
            val sortedList: List<Any>
            if (dataType == "long") {
                sortedList = processMergeSort(unsortedList.map { it.toString().toInt() })
            } else {
                sortedList = processMergeSort(unsortedList.map { it.toString() })
            }
            reportNaturalResults(sortedList, dataType == "line")
        }
        "byCount" -> {
            val sortedPairList: List<Pair<Int, List<Any>>>
            if (dataType == "long") {
                var countElementList = pairByCount(unsortedList.map { it.toString().toInt() })
                sortedPairList = processMergeSortPair(countElementList).map { Pair(it.first, processMergeSort(it.second)) }

            } else {
                var countElementList = pairByCount(unsortedList.map { it.toString() })
                sortedPairList = processMergeSortPair(countElementList).map { Pair(it.first, processMergeSort(it.second)) }
            }

            reportByCountResults(sortedPairList)

        }
    }
}

private fun getInputLines(inputFileName: String?): MutableList<String> {
    val inputLines = mutableListOf<String>()

    if (inputFileName.isNullOrBlank()) {
        val scanner = Scanner(System.`in`)
        while (scanner.hasNext()) inputLines.add(scanner.nextLine())
    } else {
        File(inputFileName).forEachLine { inputLines.add(it) }
    }

    return inputLines
}

fun extractParameters(args: Array<String>): Unit {

    var index = -1
    while (++index < args.size) {
        when {
            args[index] == "-dataType" -> {
                dataType = checkOpArg(args, ++index, "No data type defined!", listOf("long", "line", "word"))
            }
            args[index] == "-sortingType" -> {
                sortType = checkOpArg(args, ++index, "No sort type defined!", listOf("natural", "byCount"))
            }
            args[index] == "-inputFile" -> {
                inputFileName = checkOpArg(args, ++index, "No input file defined!")
            }
            args[index] == "-outputFile" -> {
                outputFileName = checkOpArg(args, ++index, "No output file defined!")
                File(outputFileName).delete()
            }
            else -> {
                println("\"${args[index]}\" is not a valid parameter.  It will be skipped")
            }
        }
    }
}

fun checkOpArg(args: Array<String>, index: Int, errorMsg: String, validList: List<String> = listOf()): String {
    if ((index) < args.size && (validList.isEmpty() || args[index] in validList)) { // An empty list accepts everything
        return args[index]
    }
    throw InvalidParameterException(errorMsg)
}

fun reportNaturalResults(sortedList: List<Any>, linebreaks: Boolean = false) {

    val reportLines = listOf("Sorted data:") + sortedList
    outputLine(reportLines.joinToString(if (linebreaks) "\n" else " "))
}

fun reportByCountResults(sortedList: List<Pair<Int, List<Any>>>) {

    for (lineCount in sortedList) {
        for (line in lineCount.second) {
            outputLine("$line: ${lineCount.first} time(s), ${lineCount.first * 100 / numOfEntries}%")
        }
    }
}

fun <T : Comparable<T>> pairByCount(unsortedList: List<T>): MutableList<Pair<Int, MutableList<T>>> {
    var elementCountMap = mutableMapOf<T, Int>()
    for (element in unsortedList) {
        elementCountMap.put(element, elementCountMap.getOrDefault(element, 0) + 1)
    }

    var countElementMap = mutableMapOf<Int, MutableList<T>>()
    for ((element, count) in elementCountMap) {
        countElementMap.putIfAbsent(count, mutableListOf())
        countElementMap.get(count)?.add(element)
    }

    var countElementList = mutableListOf<Pair<Int, MutableList<T>>>()
    for ((count, elements) in countElementMap) {
        countElementList.add(Pair(count, elements))
    }
    return countElementList
}

fun <T : Comparable<T>> processMergeSort(unsortedList: List<T>): List<T> {

    if (unsortedList.size == 1) {
        return unsortedList
    }

    val left = processMergeSort(unsortedList.subList(0, unsortedList.size / 2))
    val right = processMergeSort(unsortedList.subList(unsortedList.size / 2, unsortedList.size))

    val sorted = mutableListOf<T>()
    var l: Int = 0
    var r: Int = 0
    while (l < left.size || r < right.size) {
        when {
            l == left.size -> {
                sorted.addAll(right.subList(r, right.size))
                r = right.size
            }
            r == right.size -> {
                sorted.addAll(left.subList(l, left.size))
                l = left.size
            }
            left[l] <= right[r] -> sorted.add(left[l++])
            left[l] > right[r] -> sorted.add(right[r++])
        }
    }

    return sorted
}

fun <T : Comparable<T>> processMergeSortPair(unsortedList: List<Pair<Int, List<T>>>): List<Pair<Int, List<T>>> {

    if (unsortedList.size == 1) {
        return unsortedList
    }

    val left = processMergeSortPair(unsortedList.subList(0, unsortedList.size / 2))
    val right = processMergeSortPair(unsortedList.subList(unsortedList.size / 2, unsortedList.size))

    val sorted = mutableListOf<Pair<Int, List<T>>>()
    var l: Int = 0
    var r: Int = 0
    while (l < left.size || r < right.size) {
        when {
            l == left.size -> {
                sorted.addAll(right.subList(r, right.size))
                r = right.size
            }
            r == right.size -> {
                sorted.addAll(left.subList(l, left.size))
                l = left.size
            }
            left[l].first <= right[r].first -> sorted.add(left[l++])
            left[l].first > right[r].first -> sorted.add(right[r++])
        }
    }

    return sorted
}
