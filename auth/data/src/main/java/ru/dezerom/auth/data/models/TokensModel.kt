package ru.dezerom.auth.data.models

data class TokensModel(
    val accessToken: String,
    val refreshToken: String
)
