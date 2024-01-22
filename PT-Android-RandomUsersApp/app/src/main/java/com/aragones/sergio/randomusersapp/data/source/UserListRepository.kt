package com.aragones.sergio.randomusersapp.data.source

import com.aragones.sergio.randomusersapp.model.User
import com.aragones.sergio.randomusersapp.network.UserListMapper
import com.aragones.sergio.randomusersapp.network.UserListService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UserListRepository @Inject constructor(
    private val service: UserListService,
    private val mapper: UserListMapper
) {

    fun getUsers(): Flow<Result<List<User>>> =
        service.fetchUserList().map {

            if (it.isSuccess) {
                Result.success(mapper(it.getOrNull() ?: listOf()))
            } else {
                Result.failure(it.exceptionOrNull() ?: RuntimeException("Mapper error"))
            }
        }
}
