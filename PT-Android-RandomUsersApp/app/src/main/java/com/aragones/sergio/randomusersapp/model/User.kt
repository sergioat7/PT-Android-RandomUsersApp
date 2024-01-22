package com.aragones.sergio.randomusersapp.model

data class User(
    val name: String,
    val surname: String,
    val email: String,
    val image: String
) {

    fun getFullName(): String {
        return "$name $surname"
    }
}