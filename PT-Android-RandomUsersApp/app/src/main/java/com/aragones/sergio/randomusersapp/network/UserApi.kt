package com.aragones.sergio.randomusersapp.network

import com.aragones.sergio.randomusersapp.model.ResultsRaw
import retrofit2.http.GET

interface UserApi {

    @GET("api/?results=50")
    suspend fun fetchAllUsers(): ResultsRaw
}