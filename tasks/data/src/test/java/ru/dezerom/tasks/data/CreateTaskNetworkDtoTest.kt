package ru.dezerom.tasks.data

import com.google.gson.Gson
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test
import ru.dezerom.tasks.data.network.models.CreateTaskNetworkDto

class CreateTaskNetworkDtoTest {

    @Test
    fun createTaskNetworkDto_toJsonWithNulls() {
        val dto = CreateTaskNetworkDto(
            name = "",
            deadline = null,
            description = null
        )

        val json = Gson().toJsonTree(dto).asJsonObject

        assertNull(json["description"])
        assertNull(json["deadline"])
    }

    @Test
    fun createTaskNetworkDto_toJson() {
        val dto = CreateTaskNetworkDto(
            name = "qwe",
            deadline = 10000L,
            description = "asd"
        )

        val json = Gson().toJsonTree(dto).asJsonObject

        assertEquals(dto.name, json["name"].asString)
        assertEquals(dto.description, json["description"].asString)
        assertEquals(dto.deadline, json["deadline"].asLong)
    }
}
