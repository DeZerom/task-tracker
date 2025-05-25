package ru.dezerom.tasks.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test
import ru.dezerom.core.tools.errors.NetworkError
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
        assertNotNull(result.exceptionOrNull())
        assertNull(result.getOrNull())
    }

    @Test
    fun createTasks_success() = runBlocking {
        val result = source.createTask("qwe", "asd", 1000L)

        assertTrue(result.isSuccess)
        assertNotNull(result.getOrNull())
        val body = result.getOrNull()!!

        assertEquals("qwe", body.name)
        assertEquals("asd", body.description)
        assertEquals(1000L, body.deadline)
    }

    @Test
    fun changeComplete_success() = runBlocking {
        val result = source.changeCompleteStatus("1")

        assertTrue(result.isSuccess)
        assertTrue(result.getOrNull() == true)
    }

    @Test
    fun changeComplete_failure() = runBlocking {
        val result = source.changeCompleteStatus("2")

        assertFalse(result.isSuccess)
        assertTrue(result.exceptionOrNull() is NetworkError)
    }
}
