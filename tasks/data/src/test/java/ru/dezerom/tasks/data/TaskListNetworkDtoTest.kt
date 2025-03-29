package ru.dezerom.tasks.data

import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import ru.dezerom.tasks.data.network.models.TaskNetworkDto
import ru.dezerom.tasks.data.network.models.TasksListNetworkDto

class TaskListNetworkDtoTest {

    @Test
    fun taskListNetworkDto_testFromJson() {
        val task1 = TaskNetworkDto(
            id = "1",
            name = "qwe",
            description = "descQWE",
            deadline = null,
            createdAt = 1000,
            isCompleted = false,
            completedAt = null,
        )
        val task2 = TaskNetworkDto(
            id = "2",
            name = "asd",
            description = null,
            deadline = 1500,
            createdAt = 1100,
            isCompleted = true,
            completedAt = 1200
        )
        val tasks = JsonArray().apply {
            add(Gson().toJsonTree(task1))
            add(Gson().toJsonTree(task2))
        }

        val json = JsonObject().apply {
            add("tasks", tasks)
        }

        val tasksList = Gson().fromJson(json, TasksListNetworkDto::class.java)

        assertTrue(tasksList.tasks.isNotEmpty())
        assertTrue(tasksList.tasks.size == 2)
        assertEquals(tasksList.tasks[0], task1)
        assertEquals(tasksList.tasks[1], task2)
    }

}
