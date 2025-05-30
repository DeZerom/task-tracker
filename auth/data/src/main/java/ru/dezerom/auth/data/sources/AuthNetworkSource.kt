package ru.dezerom.auth.data.sources

import ru.dezerom.auth.data.mappers.toModel
import ru.dezerom.auth.data.models.TokensModel
import ru.dezerom.auth.data.network.AuthApi
import ru.dezerom.auth.data.network.models.CredentialsDto
import ru.dezerom.core.data.utils.safeApiCall
import javax.inject.Inject

internal class AuthNetworkSource @Inject constructor(private val api: AuthApi) {

    suspend fun authorize(login: String, password: String): Result<TokensModel> {
        return safeApiCall {
            api.authorize(CredentialsDto(login, password))
        }.map { it.toModel() }
    }

    suspend fun register(login: String, password: String): Result<Boolean> {
        return safeApiCall {
            api.register(CredentialsDto(login, password))
        }.map { it.response == true }
    }

    suspend fun refreshTokens(refreshToken: String): Result<TokensModel> {
        return safeApiCall {
            api.refreshTokens("Bearer $refreshToken")
        }.map { it.toModel() }
    }
}
