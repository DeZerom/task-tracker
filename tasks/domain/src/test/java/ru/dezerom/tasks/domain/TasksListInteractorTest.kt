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
import ru.dezerom.tasks.domain.models.ChangeCompletedStatusModel
import ru.dezerom.tasks.domain.models.TaskModel

class TasksListInteractorTest {
    private val interactor = TasksListInteractor(TasksRepositoryMock())

    @Test
    fun getAll_success() = runBlocking {
        val result = interactor.getAllTasks()

        assertTrue(result.isSuccess)
        assertNotNull(result.getOrNull())

        val body = result.getOrNull()!!
        assertTrue(body.isNotEmpty())
    }

    @Test
    fun createTask_emptyName() = runBlocking {
        val result = interactor.createTask("", null, null)
        emptyNameTaskAssertions(result)
    }

    @Test
    fun createTask_blankName() = runBlocking {
        val result = interactor.createTask("  ", null, null)
        emptyNameTaskAssertions(result)
    }

    @Test
    fun createTask_success() = runBlocking {
        val result = interactor.createTask("qwe", "asd", null)

        assertTrue(result.isSuccess)
        assertNotNull(result.getOrNull())
        val body = result.getOrNull()!!

        assertEquals("qwe", body.name)
        assertEquals("asd", body.description)
        assertEquals(null, body.deadline)
    }

    private fun emptyNameTaskAssertions(result: Result<TaskModel>) {
        assertFalse(result.isSuccess)
        assertNotNull(result.exceptionOrNull())
        val exception = result.exceptionOrNull()!!

        assertTrue(exception is NetworkError)
        exception as NetworkError

        assertTrue(exception.messageRes is StringContainer.StringRes)
        val res = exception.messageRes as StringContainer.StringRes

        assertEquals(R.string.name_field_is_required, res.res)
    }

    @Test
    fun changeComplete_success() = runBlocking {
        val result = interactor.changeCompleteStatus("1")

        assertTrue(result.isSuccess)
        assertTrue(result.getOrNull() is ChangeCompletedStatusModel)
        val body = result.getOrNull() as ChangeCompletedStatusModel

        assertEquals(true, body.success)
    }

    @Test
    fun changeComplete_failure() = runBlocking {
        val result = interactor.changeCompleteStatus("2")

        assertFalse(result.isSuccess)
        assertTrue(result.exceptionOrNull() is NetworkError)
    }
}