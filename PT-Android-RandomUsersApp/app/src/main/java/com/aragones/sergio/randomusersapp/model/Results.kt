package com.aragones.sergio.randomusersapp.model

data class ResultsRaw(
    val results: List<UserRaw>,
    val info: InfoRaw
)

data class UserRaw(
    val gender: String,
    val name: NameRaw,
    val location: LocationRaw,
    val email: String,
    val registered: RegisteredRaw,
    val phone: String,
    val picture: PictureRaw
)

data class NameRaw(
    val first: String,
    val last: String
)

data class LocationRaw(
    val coordinates: CoordinatesRaw
)

data class CoordinatesRaw(
    val latitude: String,
    val longitude: String
)

data class RegisteredRaw(
    val date: String
)

data class PictureRaw(
    val large: String
)

data class InfoRaw(
    val results: Int,
    val page: Int
)
