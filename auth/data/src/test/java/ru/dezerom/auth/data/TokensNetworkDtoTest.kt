package ru.dezerom.auth.data

import com.google.gson.Gson
import com.google.gson.JsonNull
import com.google.gson.JsonObject
import org.junit.Assert.assertEquals
import org.junit.Test
import ru.dezerom.auth.data.network.models.TokensNetworkDto

internal class TokensNetworkDtoTest {
    @Test
    fun testToJson() {
        val dto1 = TokensNetworkDto("qwe", "qwe")
        val json1 = Gson().toJsonTree(dto1).asJsonObject
        assertEquals(json1["accessToken"].asString, dto1.accessToken)
        assertEquals(json1["refreshToken"].asString, dto1.refreshTokens)

        val dto2 = TokensNetworkDto(null, "asd")
        val json2 = Gson().toJsonTree(dto2).asJsonObject
        assertEquals(json2["accessToken"], null)
        assertEquals(json2["refreshToken"].asString, dto2.refreshTokens)

        val dto3 = TokensNetworkDto("null", null)
        val json3 = Gson().toJsonTree(dto3).asJsonObject
        assertEquals(json3["accessToken"].asString, dto3.accessToken)
        assertEquals(json3["refreshToken"], null)

        val dto4 = TokensNetworkDto(null, null)
        val json4 = Gson().toJsonTree(dto4).asJsonObject
        assertEquals(json4["accessToken"], null)
        assertEquals(json4["refreshToken"], null)
    }

    @Test
    fun testFromJson() {
        val qwerty = "qwerty"
        val json1 = JsonObject().apply {
            addProperty("accessToken", qwerty)
            addProperty("refreshToken", qwerty)
        }
        val dto1 = Gson().fromJson(json1, TokensNetworkDto::class.java)

        assertEquals(dto1.accessToken, qwerty)
        assertEquals(dto1.refreshTokens, qwerty)

        val json2 = JsonObject().apply {
            add("accessToken", JsonNull.INSTANCE)
            add("refreshToken", JsonNull.INSTANCE)
        }
        val dto2 = Gson().fromJson(json2, TokensNetworkDto::class.java)

        assertEquals(dto2.accessToken, null)
        assertEquals(dto2.refreshTokens, null)
    }
}
