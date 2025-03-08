package ru.dezerom.auth.data.network

import kotlinx.coroutines.delay
import ru.dezerom.auth.data.network.models.CredentialsDto
import ru.dezerom.auth.data.network.models.TokensNetworkDto
import ru.dezerom.core.data.models.ResponseDto

internal class MockAuthApi: AuthApi {
    override suspend fun authorize(credentialsDto: CredentialsDto): ResponseDto<TokensNetworkDto> {
        delay(1000)

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
