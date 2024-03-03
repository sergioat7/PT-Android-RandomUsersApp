package com.aragones.sergio.randomusersapp.data.source

import com.aragones.sergio.randomusersapp.extensions.toDate
import com.aragones.sergio.randomusersapp.model.Gender
import com.aragones.sergio.randomusersapp.model.User
import com.aragones.sergio.domain.UserRaw
import javax.inject.Inject

class UserListMapper @Inject constructor() : Function1<List<UserRaw>, List<User>> {

    override fun invoke(usersRaw: List<UserRaw>): List<User> {

        return usersRaw.map {

            val gender = when (it.gender) {
                "male" -> Gender.MALE
                "female" -> Gender.FEMALE
                else -> Gender.OTHER
            }
            User(
                it.name.first,
                it.name.last,
                it.email,
                it.picture.large,
                gender,
                it.registered.date.toDate(),
                it.phone
            )
        }
    }
}