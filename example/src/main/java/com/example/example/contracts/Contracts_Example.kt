package com.example.example.contracts

import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.contract

@ExperimentalContracts
fun main() {

    val topic: String? = null
    if (!topic.isNullOrEmpty()) {
        topic.length
    }

    val user: User? = null
    if (user != null) {
        println(user.name)
    }

    user?.let {
        println(user.name)
    }

    user?.apply {

    }

    with(user) {

    }

    user?.run {

    }

}

class User(
    val name: String
)
