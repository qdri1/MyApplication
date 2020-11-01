package com.example.example.tricks

import kotlin.system.measureNanoTime
import kotlin.system.measureTimeMillis

fun main() {
//    blockMeasurement()
    join("", emptyList())
}

// region code block measurement
fun blockMeasurement() {

    val hello = measureTimeMillis {
        println("Hello world")
    }

    println("Saying hello took $hello ms")
}
// endregion


// region deprecation levels & replacement
@Deprecated(
    message = "Use strings.joinToString(sep).",
    level = DeprecationLevel.WARNING,
    replaceWith = ReplaceWith("strings.joinToString(sep)")
)
fun join(sep: String, strings: List<String>): String {
    TODO()
}
// endregion


// region asSequence
fun arraySequence() {

    val numbers = listOf(1, 2, 3, 4, 5)

    numbers.asSequence()
        .map { it * 2 }
        .filter { it % 2 == 0 }
        .toList()
}
// endregion


// region erasing erasure
@JvmName("sortStrings")
fun sort(strings: List<String>) {

}

@JvmName("sortInts")
fun sort(ints: List<Int>) {

}
// endregion