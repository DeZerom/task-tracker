package ru.dezerom.tasks.domain

import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test
import ru.dezerom.core.tools.R
import ru.dezerom.core.tools.errors.NetworkError
import ru.dezerom.core.tools.string_container.StringContainer
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

    @Test
    fun createTask_emptyName() = runBlocking {
        val result = useCase.createTask("", null, null)
        emptyNameTaskAssertions(result)
    }

    @Test
    fun createTask_blankName() = runBlocking {
        val result = useCase.createTask("  ", null, null)
        emptyNameTaskAssertions(result)
    }

    @Test
    fun createTask_success() = runBlocking {
        val result = useCase.createTask("qwe", "asd", null)

        assertTrue(result.isSuccess)
        assertNotNull(result.getOrNull())
        val body = result.getOrNull()!!

        assertTrue(body)
    }

    private fun emptyNameTaskAssertions(result: Result<Boolean>) {
        assertFalse(result.isSuccess)
        assertNotNull(result.exceptionOrNull())
        val exception = result.exceptionOrNull()!!

        assertTrue(exception is NetworkError)
        exception as NetworkError

        assertTrue(exception.messageRes is StringContainer.StringRes)
        val res = exception.messageRes as StringContainer.StringRes

        assertEquals(R.string.name_field_is_required, res.res)
    }
}