package ru.dezerom.auth.data

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import ru.dezerom.auth.data.mappers.toModel
import ru.dezerom.auth.data.models.TokensModel
import ru.dezerom.auth.data.network.models.TokensNetworkDto

internal class TokensMapperTest {
    @Test
    fun dtoToModel() {
        val dto = TokensNetworkDto("qwe", "qwe")
        val model = dto.toModel()

        assertEqualTokens(model, dto)
    }

    @Test
    fun emptyFields() {
        val dto1 = TokensNetworkDto("", "qwe")
        val dto2 = TokensNetworkDto("asd", "")
        val dto3 = TokensNetworkDto("", "")

        val model1 = dto1.toModel()
        val model2 = dto2.toModel()
        val model3 = dto3.toModel()

        assertEqualTokens(model1, dto1)
        assertEqualTokens(model2, dto2)
        assertEqualTokens(model3, dto3)
    }

    @Test
    fun nullFields() {
        val dto1 = TokensNetworkDto(null, "qwe")
        val dto2 = TokensNetworkDto("asd", null)
        val dto3 = TokensNetworkDto(null, null)

        val model1 = dto1.toModel()
        val model2 = dto2.toModel()
        val model3 = dto3.toModel()

        assertTrue(model1.accessToken.isEmpty() && model1.refreshToken == dto1.refreshTokens)
        assertTrue(model2.accessToken == dto2.accessToken && model2.refreshToken.isEmpty())
        assertTrue(model3.accessToken.isEmpty() && model3.refreshToken.isEmpty())
    }

    private fun assertEqualTokens(model: TokensModel, dto: TokensNetworkDto) {
        assertEquals(model.accessToken, dto.accessToken)
        assertEquals(model.refreshToken, dto.refreshTokens)
    }
}