package ru.dezerom.auth.domain.interactor

import ru.dezerom.auth.data.repositories.AuthRepository
import javax.inject.Inject

class AuthInteractor @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend fun authorize(login: String, pass: String): Result<Boolean> {
        return authRepository.authorize(login, pass)
    }
}
