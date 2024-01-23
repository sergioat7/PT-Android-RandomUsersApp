package com.aragones.sergio.randomusersapp.model

import com.aragones.sergio.randomusersapp.R
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

    fun getStringResourceForGender(): Int {

        return when (gender) {
            Gender.MALE -> R.string.male
            Gender.FEMALE -> R.string.female
            Gender.OTHER -> R.string.other
        }
    }
}

enum class Gender {
    MALE, FEMALE, OTHER
}