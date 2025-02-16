package ru.dezerom.auth.data.sources

import ru.dezerom.auth.data.mappers.toModel
import ru.dezerom.auth.data.models.TokensModel
import ru.dezerom.auth.data.network.AuthApi
import ru.dezerom.auth.data.network.models.CredentialsDto
import ru.dezerom.core.data.utils.safeApiCall

internal class AuthNetworkSource(private val api: AuthApi) {

    suspend fun authorize(login: String, password: String): Result<TokensModel> {
        return safeApiCall {
            api.authorize(CredentialsDto(login, password))
        }.map { it.toModel() }
    }

}
