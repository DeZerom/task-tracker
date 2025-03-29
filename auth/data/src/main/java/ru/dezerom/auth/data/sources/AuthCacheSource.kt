package ru.dezerom.auth.data.sources

import ru.dezerom.auth.data.models.TokensModel
import ru.dezerom.core.data.cache.KeyValueCache
import javax.inject.Inject

internal class AuthCacheSource @Inject constructor(
    private val cache: KeyValueCache
) {

    suspend fun rememberTokens(tokens: TokensModel) {
        cache.writeString(ACCESS_TOKEN_KEY, tokens.accessToken)
        cache.writeString(REFRESH_TOKEN_KEY, tokens.refreshToken)
    }

    suspend fun getAccessToken(): String? {
        return cache.readString(ACCESS_TOKEN_KEY)
    }

    suspend fun getRefreshToken(): String? {
        return cache.readString(REFRESH_TOKEN_KEY)
    }

    companion object {
        private const val ACCESS_TOKEN_KEY = "access_token_key"
        private const val REFRESH_TOKEN_KEY = "refresh_token_key"
    }
}
