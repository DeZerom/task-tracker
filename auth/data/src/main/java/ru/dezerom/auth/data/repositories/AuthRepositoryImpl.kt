package ru.dezerom.auth.data.repositories

import ru.dezerom.auth.data.sources.AuthNetworkSource
import javax.inject.Inject

internal class AuthRepositoryImpl @Inject constructor(
    private val networkSource: AuthNetworkSource
) : AuthRepository {
    override suspend fun authorize(login: String, password: String): Result<Boolean> {
        return networkSource.authorize(login, password).map {
            it.accessToken.isNotEmpty() && it.refreshToken.isNotEmpty()
        }
    }

    override suspend fun register(login: String, password: String): Result<Boolean> {
        return networkSource.register(login, password)
    }
}
