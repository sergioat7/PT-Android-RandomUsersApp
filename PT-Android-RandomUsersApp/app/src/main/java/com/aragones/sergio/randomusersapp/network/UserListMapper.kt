package com.aragones.sergio.randomusersapp.network

import com.aragones.sergio.randomusersapp.model.User
import com.aragones.sergio.randomusersapp.model.UserRaw
import javax.inject.Inject

class UserListMapper @Inject constructor() : Function1<List<UserRaw>, List<User>> {

    override fun invoke(usersRaw: List<UserRaw>): List<User> {

        return usersRaw.map {
            User(it.name.first, it.name.last, it.email, it.picture.large)
        }
    }
}