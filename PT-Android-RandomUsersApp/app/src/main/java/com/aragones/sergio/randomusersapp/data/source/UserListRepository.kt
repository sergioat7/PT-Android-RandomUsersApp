package com.aragones.sergio.randomusersapp.data.source

import com.aragones.sergio.randomusersapp.model.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class UserListRepository {

    fun getUsers(): Flow<Result<List<User>>> {
        return flow {  }
    }
}
