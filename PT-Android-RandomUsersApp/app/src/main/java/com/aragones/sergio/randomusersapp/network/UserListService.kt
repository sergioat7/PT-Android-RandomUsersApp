package com.aragones.sergio.randomusersapp.network

import com.aragones.sergio.randomusersapp.model.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UserListService @Inject constructor(
    private val api: UserApi
) {

    fun fetchUserList(): Flow<Result<List<User>>> {
        return flow {
            emit(Result.success(api.fetchAllUsers()))
        }.catch {
            emit(Result.failure(it))
        }
    }
}