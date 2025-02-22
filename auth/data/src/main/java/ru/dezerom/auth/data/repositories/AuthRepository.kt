package ru.dezerom.auth.data.repositories

import ru.dezerom.auth.data.sources.AuthNetworkSource
import javax.inject.Inject

class AuthRepository @Inject internal constructor(
    private val networkSource: AuthNetworkSource
) {
    suspend fun authorize(login: String, password: String): Result<Boolean> {
        val tokens = networkSource.authorize(login, password)

        return Result.success(true)
    }
}
