package ru.dezerom.tasks.domain

import ru.dezerom.core.tools.R
import ru.dezerom.core.tools.errors.NetworkError
import ru.dezerom.core.tools.string_container.toStringContainer
import ru.dezerom.tasks.data.repositories.TasksRepository
import ru.dezerom.tasks.domain.mappers.toDomain
import ru.dezerom.tasks.domain.models.ChangeCompletedStatusModel
import ru.dezerom.tasks.domain.models.TaskModel
import javax.inject.Inject

class TasksListInteractor @Inject constructor(
    private val repository: TasksRepository
) {

    suspend fun getAllTasks() =
        repository.getAll().map { body -> body.map { it.toDomain() } }

    suspend fun createTask(
        name: String,
        description: String?,
        deadline: Long?
    ): Result<TaskModel> {
        if (name.isBlank())
            return Result.failure(NetworkError(R.string.name_field_is_required.toStringContainer()))

        return repository.createTask(name, description, deadline).map { it.toDomain() }
    }

    suspend fun changeCompleteStatus(taskId: String): Result<ChangeCompletedStatusModel> {
        return repository.changeCompleteStatus(taskId).map { it.toDomain() }
    }

    suspend fun editTask(id: String, name: String, description: String?, deadline: Long?): Result<TaskModel> {
        return repository.editTask(id, name, description, deadline).map { it.toDomain() }
    }
}
