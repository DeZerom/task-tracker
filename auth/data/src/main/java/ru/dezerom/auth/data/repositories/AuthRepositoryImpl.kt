package ru.dezerom.auth.data.repositories

import ru.dezerom.auth.data.sources.AuthCacheSource
import ru.dezerom.auth.data.sources.AuthNetworkSource
import ru.dezerom.core.tools.errors.unAuthorizedNetworkError
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

    override suspend fun getAuthToken(): String? {
        return cacheSource.getAccessToken()
    }

    override suspend fun refreshTokens(): Result<Boolean> {
        val refreshToken = cacheSource.getRefreshToken()
            ?: return Result.failure(unAuthorizedNetworkError())

        val tokens = networkSource.refreshTokens(refreshToken).fold(
            onSuccess = { it },
            onFailure = { return Result.failure(it) }
        )

        cacheSource.rememberTokens(tokens)

        return Result.success(true)
    }

    override suspend fun unAuthorize(): Result<Boolean> {
        cacheSource.clear()
        return Result.success(true)
    }
}
