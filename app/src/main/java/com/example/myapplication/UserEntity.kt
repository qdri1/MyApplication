package com.example.myapplication

class UserEntity {

    var name: String? = null
    var age: Int? = null
    var hobby: String? = null

}

fun UserEntity.toModel() = UserModel(name ?: "", age ?: 0, hobby ?: "")