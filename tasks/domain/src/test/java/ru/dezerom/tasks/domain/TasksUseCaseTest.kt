package ru.dezerom.tasks.domain

import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test
import ru.dezerom.tasks.data.repositories.TasksRepositoryMock

class TasksUseCaseTest {
    private val useCase = TasksListInteractor(TasksRepositoryMock())

    @Test
    fun getAll_success() = runBlocking {
        val result = useCase.getAllTasks()

        assertTrue(result.isSuccess)
        assertNotNull(result.getOrNull())

        val body = result.getOrNull()!!
        assertTrue(body.isNotEmpty())
    }
}