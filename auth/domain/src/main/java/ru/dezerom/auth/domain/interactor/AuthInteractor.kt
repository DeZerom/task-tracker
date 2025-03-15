package ru.dezerom.auth.domain.interactor

interface AuthInteractor {
    suspend fun authorize(login: String, pass: String): Result<Boolean>

    suspend fun register(login: String, pass: String): Result<Boolean>
}