package ru.dezerom.auth.domain.interactor

import ru.dezerom.auth.data.repositories.MockAuthRepository

class MockAuthInteractor: AuthInteractor {
    private val impl = AuthInteractorImpl(MockAuthRepository())

    override suspend fun authorize(login: String, pass: String): Result<Boolean> =
        impl.authorize(login, pass)

    override suspend fun register(login: String, pass: String): Result<Boolean> =
        impl.register(login, pass)
}
