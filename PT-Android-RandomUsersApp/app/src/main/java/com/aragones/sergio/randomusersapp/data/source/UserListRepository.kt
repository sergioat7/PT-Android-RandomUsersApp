package com.aragones.sergio.randomusersapp.data.source

import com.aragones.sergio.randomusersapp.model.User
import com.aragones.sergio.randomusersapp.network.UserListService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserListRepository(
    private val service: UserListService
) {

    fun getUsers(): Flow<Result<List<User>>> = service.fetchUserList()
}
