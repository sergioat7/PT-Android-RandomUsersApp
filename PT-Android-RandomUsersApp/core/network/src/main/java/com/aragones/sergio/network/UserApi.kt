package com.aragones.sergio.network

import com.aragones.sergio.domain.ResultsRaw
import retrofit2.http.GET
import retrofit2.http.Query

interface UserApi {

    @GET("api/")
    suspend fun fetchAllUsers(
        @Query("page") page: Int,
        @Query("results") results: Int,
        @Query("seed") seed: String = "randomusersapp"
    ): ResultsRaw
}