package ru.dezerom.auth.data.repositories

import ru.dezerom.auth.data.network.MockAuthApi
import ru.dezerom.auth.data.sources.AuthNetworkSource

class MockAuthRepository: AuthRepository {
    private val impl = AuthRepositoryImpl(AuthNetworkSource(MockAuthApi()))

    override suspend fun authorize(login: String, password: String): Result<Boolean> =
        impl.authorize(login, password)
}