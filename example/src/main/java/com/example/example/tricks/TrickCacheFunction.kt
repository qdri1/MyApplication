package com.example.example.tricks

import kotlin.math.cos
import kotlin.system.measureNanoTime

class TrickCacheFunction {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {

            val cachedCos = cached { x: Double -> cos(x) }

            val time1 = measureNanoTime { cachedCos(Math.PI * 2) }
            println(time1) // 329378 ns

            /* value of cos for 2π is now cached */

            val time2 = measureNanoTime { cachedCos(Math.PI * 2) }
            println(time2) // 6286 ns
        }
    }

}

fun <In, Out> cached(f: (In) -> Out): (In) -> Out {
    val cache = mutableMapOf<In, Out>()

    return { input: In ->
        cache.computeIfAbsent(input, f)
    }
}