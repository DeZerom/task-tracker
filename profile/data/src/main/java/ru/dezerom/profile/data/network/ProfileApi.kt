package ru.dezerom.profile.data.network

import retrofit2.http.GET
import ru.dezerom.core.data.models.ResponseDto
import ru.dezerom.profile.data.network.models.UserNetworkDto

internal interface ProfileApi {
    @GET("auth/me")
    suspend fun getMe(): ResponseDto<UserNetworkDto?>
}