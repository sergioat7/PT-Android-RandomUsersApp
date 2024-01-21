package com.aragones.sergio.randomusersapp.network

import com.aragones.sergio.randomusersapp.model.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class UserListService {

    fun fetchUserList(): Flow<Result<List<User>>> {
        return flow {  }
    }
}