package com.example.myapplication

class UserRepository {

    fun fetchUser(): UserModel {

        val entity = UserEntity()
        entity.name = "Kudri"
        entity.age = 25
        entity.hobby = "Crossfit"

        return entity.toModel()
    }

}