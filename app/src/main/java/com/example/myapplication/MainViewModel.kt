package com.example.myapplication

interface MainViewStateDelegate {

    fun updateUser(user: UserModel)

}

class MainViewModel {

    var delegate: MainViewStateDelegate? = null
    private val repository = UserRepository()

    fun onViewPrepared() {
        val user: UserModel = repository.fetchUser()
        delegate?.updateUser(user)
    }

}