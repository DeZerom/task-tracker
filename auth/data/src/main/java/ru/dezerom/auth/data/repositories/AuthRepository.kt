package ru.dezerom.auth.data.repositories

interface AuthRepository {
    suspend fun authorize(login: String, password: String): Result<Boolean>

    suspend fun register(login: String, password: String): Result<Boolean>

    suspend fun getAuthToken(): String?

    suspend fun refreshTokens(): Result<Boolean>

    suspend fun unAuthorize(): Result<Boolean>
}