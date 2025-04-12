package ru.dezerom.tasks.domain

import ru.dezerom.tasks.data.repositories.TasksRepository
import ru.dezerom.tasks.domain.mappers.toDomain
import javax.inject.Inject

class TasksListInteractor @Inject constructor(
    private val repository: TasksRepository
) {

    suspend fun getAllTasks() =
        repository.getAll().map { body -> body.map { it.toDomain() } }

}
