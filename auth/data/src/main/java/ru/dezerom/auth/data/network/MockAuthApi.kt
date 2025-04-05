package ru.dezerom.auth.data.network

import kotlinx.coroutines.delay
import ru.dezerom.auth.data.network.models.CredentialsDto
import ru.dezerom.auth.data.network.models.TokensNetworkDto
import ru.dezerom.core.data.models.BooleanResponse
import ru.dezerom.core.data.models.ResponseDto

internal class MockAuthApi: AuthApi {
    private val storage = mutableMapOf("qwe" to "qwe")

    override suspend fun authorize(credentialsDto: CredentialsDto): ResponseDto<TokensNetworkDto?> {
        delay(1000)

        return if (storage[credentialsDto.login] == credentialsDto.password)
            ResponseDto(
                true,
                TokensNetworkDto("qwe", "qwerty")
            )
        else
            ResponseDto(
                false,
                TokensNetworkDto(null, null),
                error = "Some error message"
            )
    }

    override suspend fun register(credentialsDto: CredentialsDto): ResponseDto<BooleanResponse?> {
        delay(1000)

        if (credentialsDto.login.isBlank() || credentialsDto.password.isBlank())
            return ResponseDto(
                success = false,
                body = null,
                error = "Empty credentials"
            )

        if (storage.contains(credentialsDto.login))
            return ResponseDto(
                success = false,
                body = null,
                error = "Has user with this login"
            )

        storage[credentialsDto.login] = credentialsDto.password

        return ResponseDto(
            success = true,
            body = BooleanResponse(true),
            error = null
        )
    }

    override suspend fun refreshTokens(refreshToken: String): ResponseDto<TokensNetworkDto?> {
        delay(1000)
        return if (refreshToken == "qwerty") return ResponseDto(
            success = true,
            body = TokensNetworkDto(
                accessToken = "qwerty",
                refreshTokens = "asdfgh",
            )
        ) else ResponseDto(
            success = false,
            body = null,
            error = "Wrong refresh"
        )
    }
}
