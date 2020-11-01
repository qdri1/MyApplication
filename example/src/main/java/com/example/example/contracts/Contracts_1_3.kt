package com.example.example.contracts

import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

// contract for lambda invocation
@ExperimentalContracts
fun main() {
    initValue()
}

@ExperimentalContracts
fun initValue() {
    val intValue: Int
    calledOnlyOnce {
        intValue = 1
    }
    println(intValue)
}

@ExperimentalContracts
inline fun calledOnlyOnce(run: () -> Unit) {
    contract {
        callsInPlace(run, InvocationKind.EXACTLY_ONCE)
    }
    run()
}