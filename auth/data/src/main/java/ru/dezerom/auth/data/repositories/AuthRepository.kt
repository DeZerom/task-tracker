package ru.dezerom.auth.data.repositories

interface AuthRepository {
    suspend fun authorize(login: String, password: String): Result<Boolean>
}