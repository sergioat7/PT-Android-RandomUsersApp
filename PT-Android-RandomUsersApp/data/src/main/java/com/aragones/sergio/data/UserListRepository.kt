package com.aragones.sergio.data

import com.aragones.sergio.network.UserListService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UserListRepository @Inject constructor(
    private val service: UserListService,
    private val mapper: UserListMapper
) {

    fun getUsers(page: Int, results: Int): Flow<Result<List<User>>> =
        service.fetchUserList(page, results).map {

            if (it.isSuccess) {
                Result.success(mapper(it.getOrNull() ?: listOf()))
            } else {
                Result.failure(it.exceptionOrNull() ?: RuntimeException("Mapper error"))
            }
        }
}
