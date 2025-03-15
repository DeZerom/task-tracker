package ru.dezerom.auth.data.network

import retrofit2.http.Body
import retrofit2.http.POST
import ru.dezerom.auth.data.network.models.CredentialsDto
import ru.dezerom.auth.data.network.models.TokensNetworkDto
import ru.dezerom.core.data.models.BooleanResponse
import ru.dezerom.core.data.models.ResponseDto

internal interface AuthApi {

    @POST("/auth/auth")
    suspend fun authorize(@Body credentialsDto: CredentialsDto): ResponseDto<TokensNetworkDto?>

    @POST("auth/register")
    suspend fun register(@Body credentialsDto: CredentialsDto): ResponseDto<BooleanResponse?>
}
