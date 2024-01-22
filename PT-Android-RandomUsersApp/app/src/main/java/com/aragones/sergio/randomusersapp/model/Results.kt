package com.aragones.sergio.randomusersapp.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ResultsRaw(
    val results: List<UserRaw>,
    val info: InfoRaw
)

@JsonClass(generateAdapter = true)
data class UserRaw(
    val gender: String,
    val name: NameRaw,
    val location: LocationRaw,
    val email: String,
    val registered: RegisteredRaw,
    val phone: String,
    val picture: PictureRaw
)

@JsonClass(generateAdapter = true)
data class NameRaw(
    val first: String,
    val last: String
)

@JsonClass(generateAdapter = true)
data class LocationRaw(
    val coordinates: CoordinatesRaw
)

@JsonClass(generateAdapter = true)
data class CoordinatesRaw(
    val latitude: String,
    val longitude: String
)

@JsonClass(generateAdapter = true)
data class RegisteredRaw(
    val date: String
)

@JsonClass(generateAdapter = true)
data class PictureRaw(
    val large: String
)

@JsonClass(generateAdapter = true)
data class InfoRaw(
    val results: Int,
    val page: Int
)
