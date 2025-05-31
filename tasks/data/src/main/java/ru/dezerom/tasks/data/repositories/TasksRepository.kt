package ru.dezerom.tasks.data.repositories

import ru.dezerom.tasks.data.models.ChangeCompletedStatusDto
import ru.dezerom.tasks.data.models.TaskDataModel

interface TasksRepository {

    suspend fun getAll(): Result<List<TaskDataModel>>

    suspend fun createTask(
        name: String,
        description: String?,
        deadline: Long?
    ): Result<TaskDataModel>

    suspend fun changeCompleteStatus(taskId: String): Result<ChangeCompletedStatusDto>
}
