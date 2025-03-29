package ru.dezerom.auth.data.repositories

import ru.dezerom.auth.data.sources.AuthCacheSource
import ru.dezerom.auth.data.sources.AuthNetworkSource
import javax.inject.Inject

internal class AuthRepositoryImpl @Inject constructor(
    private val networkSource: AuthNetworkSource,
    private val cacheSource: AuthCacheSource,
) : AuthRepository {
    override suspend fun authorize(login: String, password: String): Result<Boolean> {
        val tokens = networkSource.authorize(login, password).fold(
            onSuccess = { it },
            onFailure = { return Result.failure(it) }
        )

        cacheSource.rememberTokens(tokens)

        return Result.success(true)
    }

    override suspend fun register(login: String, password: String): Result<Boolean> {
        return networkSource.register(login, password)
    }
}
