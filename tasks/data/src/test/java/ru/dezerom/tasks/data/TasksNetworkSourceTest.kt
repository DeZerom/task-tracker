package ru.dezerom.tasks.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test
import ru.dezerom.tasks.data.network.TasksMockApi
import ru.dezerom.tasks.data.sources.TasksNetworkSource

internal class TasksNetworkSourceTest {
    private val source = TasksNetworkSource(TasksMockApi(), Dispatchers.Default)

    @Test
    fun getAll_success() = runBlocking {
        val tasksResult = source.getAllTasks()

        assertTrue(tasksResult.isSuccess)
        assertNotNull(tasksResult.getOrNull())

        val tasks = tasksResult.getOrNull()!!
        assertTrue(tasks.isNotEmpty())
    }

    @Test
    fun createTask_emptyName() = runBlocking {
        val result = source.createTask("", null, null)

        assertFalse(result.isSuccess)
        assertFalse(result.getOrNull() == true)
    }

    @Test
    fun createTasks_success() = runBlocking {
        val result = source.createTask("qwe", "asd", 1000L)

        assertTrue(result.isSuccess)
        assertNotNull(result.getOrNull())
        val body = result.getOrNull()!!

        assertTrue(body)
    }
}
