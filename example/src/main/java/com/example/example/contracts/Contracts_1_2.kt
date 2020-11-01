package com.example.example.contracts

import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.contract

// contact for casting
@ExperimentalContracts
fun main() {
    val event = MyEvent("message")
    processEvent(event)
}

data class MyEvent(val message: String)

@ExperimentalContracts
fun processEvent(event: Any?) {
    if (isInterested(event)) {
        println(event.message)
    }
}

@ExperimentalContracts
fun isInterested(event: Any?): Boolean {
    contract {
        returns(true) implies (event is MyEvent)
    }
    return event is MyEvent
}