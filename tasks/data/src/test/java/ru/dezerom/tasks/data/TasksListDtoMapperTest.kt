package ru.dezerom.tasks.data

import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import ru.dezerom.tasks.data.mappers.toDomainList
import ru.dezerom.tasks.data.models.TaskDataModel
import ru.dezerom.tasks.data.network.TasksMockApi
import ru.dezerom.tasks.data.network.models.TaskNetworkDto
import ru.dezerom.tasks.data.network.models.TasksListNetworkDto

class TasksListDtoMapperTest {

    @Test
    fun mapTasksListDto_success() = runBlocking {
        val dto = TasksMockApi().getAllTasks().body!!

        val model = dto.toDomainList()

        assertTrue(model.isNotEmpty())
        assertEquals(dto.tasks.size, model.size)

        assertTrue(dto.tasks.all { t -> model.any { m-> eq(t, m) } })
    }

    @Test
    fun mapTasksListDto_empty() {
        val dto = TasksListNetworkDto()
        val model = dto.toDomainList()

        assertTrue(model.isEmpty())
    }

    private fun eq(dto: TaskNetworkDto, model: TaskDataModel): Boolean {
        return strEqOrEmptyIfNull(dto.id, model.id)
                && strEqOrEmptyIfNull(dto.name, model.name)
                && strEqOrEmptyIfNull(dto.description, model.description)
                && dto.deadline == model.deadline
                && longEqOrZeroIfNull(dto.createdAt, model.createdAt)
                && boolEqOrFalseIfNull(dto.isCompleted, model.isCompleted)
                && dto.completedAt == model.completedAt
    }

    private fun strEqOrEmptyIfNull(first: String?, second: String): Boolean {
        return first == second
                || first == null && second.isEmpty()
    }

    private fun longEqOrZeroIfNull(first: Long?, second: Long): Boolean {
        return first == second
                || first == null && second == 0L
    }

    private fun boolEqOrFalseIfNull(first: Boolean?, second: Boolean): Boolean {
        return first == second
                || first == null && !second
    }
}
