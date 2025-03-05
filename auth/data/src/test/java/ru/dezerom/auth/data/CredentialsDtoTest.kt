package ru.dezerom.auth.data

import com.google.gson.Gson
import com.google.gson.JsonObject
import org.junit.Assert.assertEquals
import org.junit.Test
import ru.dezerom.auth.data.network.models.CredentialsDto

internal class CredentialsDtoTest {
    @Test
    fun testToJson() {
        val dto = CredentialsDto("wqert", "qwe")
        val json = Gson().toJsonTree(dto).asJsonObject

        assertEquals(json["login"].asString, dto.login)
        assertEquals(json["password"].asString, dto.password)
    }

    @Test
    fun testFromJson() {
        val qwerty = "qwerty"
        val json = JsonObject().apply {
            addProperty("login", qwerty)
            addProperty("password", qwerty)
        }

        val dto = Gson().fromJson(json, CredentialsDto::class.java)

        assertEquals(dto.login, qwerty)
        assertEquals(dto.password, qwerty)
    }
}
