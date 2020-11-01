package com.example.example.contracts

import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.contract


// contract for nullability check
@ExperimentalContracts
fun main() {
    val service = Service()
    val request = Request("choco")
    service.execute(request)
}

data class Request(val arg: String)

class Service {
    @ExperimentalContracts
    fun execute(request: Request?) {
        validate(request)
        println(request.arg) // Doesn't compile because request might be null
    }
}

@ExperimentalContracts
private fun validate(request: Request?) {
    contract {
        returns() implies (request != null)
    }
    if (request == null) {
        throw IllegalArgumentException("Undefined request")
    }
    if (request.arg.isBlank()) {
        throw IllegalArgumentException("No argument is provided")
    }
}