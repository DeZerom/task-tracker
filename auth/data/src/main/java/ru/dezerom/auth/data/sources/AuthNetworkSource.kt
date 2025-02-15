package ru.dezerom.auth.data.sources

import ru.dezerom.auth.data.models.TokensModel
import ru.dezerom.auth.data.network.AuthApi

class AuthNetworkSource(private val api: AuthApi) {

    suspend fun authorize(login: String, password: String): Result<TokensModel> {

    }

}
