package com.aragones.sergio.randomusersapp.network

import com.aragones.sergio.randomusersapp.model.UserRaw
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UserListService @Inject constructor(
    private val api: UserApi
) {

    fun fetchUserList(page: Int, results: Int): Flow<Result<List<UserRaw>>> {
        return flow {
            emit(Result.success(api.fetchAllUsers(page, results).results))
        }.catch {
            emit(Result.failure(it))
        }
    }
}