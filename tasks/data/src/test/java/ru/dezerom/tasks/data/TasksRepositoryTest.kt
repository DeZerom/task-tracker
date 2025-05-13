package ru.dezerom.tasks.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test
import ru.dezerom.tasks.data.network.TasksMockApi
import ru.dezerom.tasks.data.repositories.TasksRepositoryImpl
import ru.dezerom.tasks.data.sources.TasksNetworkSource

class TasksRepositoryTest {
    private val repo = TasksRepositoryImpl(TasksNetworkSource(TasksMockApi(), Dispatchers.Default))

    @Test
    fun getAll_success() = runBlocking {
        val result = repo.getAll()
        assertTrue(result.isSuccess)
        assertNotNull(result.getOrNull())

        val tasks = result.getOrNull()!!
        assertTrue(tasks.isNotEmpty())
    }

    @Test
    fun createTask_emptyName() = runBlocking {
        val result = repo.createTask("", null, null)

        assertFalse(result.isSuccess)
    }

    @Test
    fun createTask_success() = runBlocking {
        val result = repo.createTask("qwe", "asd", 1000L)

        assertTrue(result.isSuccess)
        assertTrue(result.getOrNull() == true)
    }
}
