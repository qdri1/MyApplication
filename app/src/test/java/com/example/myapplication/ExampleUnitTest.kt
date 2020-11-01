package com.example.myapplication

import org.junit.Test

import org.junit.Assert.*
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.contract

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @ExperimentalContracts
    @Test
    fun addition_isCorrect() {

        val i = 2

        assertTrue(i == 2)
    }
}

@ExperimentalContracts
fun assertTrue(actual: Boolean, message: String? = null) {
    contract { returns() implies actual }
    return assertTrue(message ?: "Expected value to be true.", actual)
}

@ExperimentalContracts
fun assertFalse(actual: Boolean, message: String? = null) {
    contract { returns() implies (!actual) }
    return assertTrue(message ?: "Expected value to be false.", !actual)
}

@ExperimentalContracts
fun <T : Any> assertNotNull(actual: T?, message: String? = null): T {
    contract { returns() implies (actual != null) }
    assertNotNull(message, actual)
    return actual!!
}
