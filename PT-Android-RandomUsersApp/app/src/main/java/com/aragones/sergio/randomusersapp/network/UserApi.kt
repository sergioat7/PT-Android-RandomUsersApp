package com.aragones.sergio.randomusersapp.network

import com.aragones.sergio.randomusersapp.model.User
import retrofit2.http.GET

interface UserApi {

    @GET("api")
    suspend fun fetchAllUsers(): List<User>
}