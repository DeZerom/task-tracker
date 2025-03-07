package ru.dezerom.auth.data.network

import ru.dezerom.auth.data.network.models.CredentialsDto
import ru.dezerom.auth.data.network.models.TokensNetworkDto
import ru.dezerom.core.data.models.ResponseDto

internal class AuthMockApi: AuthApi {
    override suspend fun authorize(credentialsDto: CredentialsDto): ResponseDto<TokensNetworkDto> {
        return if (credentialsDto.login == "qwe" && credentialsDto.password == "qwe")
            ResponseDto(
                true,
                TokensNetworkDto("qwe", "qwe")
            )
        else
            ResponseDto(
                false,
                TokensNetworkDto(null, null),
                error = "Some error message"
            )
    }

}
