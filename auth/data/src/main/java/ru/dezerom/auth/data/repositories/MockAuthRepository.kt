package ru.dezerom.auth.data.repositories

import ru.dezerom.auth.data.network.MockAuthApi
import ru.dezerom.auth.data.sources.AuthCacheSource
import ru.dezerom.auth.data.sources.AuthNetworkSource
import ru.dezerom.core.data.cache.InMemoryKeyValueCache

class MockAuthRepository: AuthRepository {
    private val impl = AuthRepositoryImpl(
        networkSource = AuthNetworkSource(MockAuthApi()),
        cacheSource = AuthCacheSource(InMemoryKeyValueCache())
    )

    override suspend fun authorize(login: String, password: String): Result<Boolean> =
        impl.authorize(login, password)

    override suspend fun register(login: String, password: String): Result<Boolean> =
        impl.register(login, password)
}
