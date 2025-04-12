package ru.dezerom.tasks.domain

import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertTrue
import org.junit.Test
import ru.dezerom.tasks.data.models.TaskDataModel
import ru.dezerom.tasks.data.repositories.TasksRepositoryMock
import ru.dezerom.tasks.domain.mappers.toDomain
import ru.dezerom.tasks.domain.models.TaskModel

class TaskDataModelMapperTest {

    @Test
    fun taskDataModelMapper_success() = runBlocking {
        val repo = TasksRepositoryMock()
        val data = repo.getAll().getOrNull()!!

        val domain = data.map { it.toDomain() }

        data.forEachIndexed { i, d ->
            assertTrue(eq(d, domain[i]))
        }
    }

    private fun eq(data: TaskDataModel, domain: TaskModel): Boolean =
        data.id == domain.id
                && data.name == domain.name
                && data.description == domain.description
                && data.createdAt == domain.createdAt
                && data.deadline == domain.deadline
                && data.completedAt == domain.completedAt
                && data.isCompleted == domain.isCompleted


}
