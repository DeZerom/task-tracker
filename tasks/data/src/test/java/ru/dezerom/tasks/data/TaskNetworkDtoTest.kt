package ru.dezerom.tasks.data

import com.google.gson.Gson
import com.google.gson.JsonObject
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Test
import ru.dezerom.tasks.data.network.models.TaskNetworkDto

class TaskNetworkDtoTest {

    @Test
    fun taskNetworkDto_testFromJsonWithNull() {
        val json = JsonObject().apply {
            add("id", null)
            add("name", null)
            add("description", null)
            add("deadline", null)
            add("created_at", null)
            add("is_completed", null)
            add("completed_at", null)
        }

        val task = Gson().fromJson(json, TaskNetworkDto::class.java)
        assertNotNull(task)

        assertNull(task.id)
        assertNull(task.name)
        assertNull(task.description)
        assertNull(task.deadline)
        assertNull(task.createdAt)
        assertNull(task.completedAt)
        assertNull(task.completedAt)
    }

    @Test
    fun tasksNetworkDto_testFromJson() {
        val json = JsonObject().apply {
            addProperty("id", "1")
            addProperty("name", "qwe")
            addProperty("description", "asd")
            addProperty("deadline", 1000)
            addProperty("created_at", 900)
            addProperty("is_completed", true)
            addProperty("completed_at", 950)
        }

        val task = Gson().fromJson(json, TaskNetworkDto::class.java)
        assertNotNull(task)

        assertEquals("1", task.id)
        assertEquals("qwe", task.name)
        assertEquals("asd", task.description)
        assertEquals(1000L, task.deadline)
        assertEquals(900L, task.createdAt)
        assertEquals(true, task.isCompleted)
        assertEquals(950L, task.completedAt)
    }

}
