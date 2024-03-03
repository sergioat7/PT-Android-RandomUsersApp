package com.aragones.sergio.data

import java.util.Date

data class User(
    val name: String,
    val surname: String,
    val email: String,
    val image: String,
    val gender: Gender,
    val registrationDate: Date?,
    val phone: String
) {

    fun getFullName(): String {
        return "$name $surname"
    }
}

enum class Gender {
    MALE, FEMALE, OTHER
}