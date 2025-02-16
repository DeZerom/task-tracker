package ru.dezerom.auth.data.mappers

import ru.dezerom.auth.data.models.TokensModel
import ru.dezerom.auth.data.network.models.TokensNetworkDto

internal fun TokensNetworkDto.toModel() = TokensModel(
    accessToken = accessToken ?: "",
    refreshToken = refreshTokens ?: "",
)
