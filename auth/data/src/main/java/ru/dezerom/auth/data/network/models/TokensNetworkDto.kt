package ru.dezerom.auth.data.network.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

internal data class TokensNetworkDto(
    @Expose
    @SerializedName("accessToken")
    val accessToken: String,

    @Expose
    @SerializedName("refreshToken")
    val refreshTokens: String
)
