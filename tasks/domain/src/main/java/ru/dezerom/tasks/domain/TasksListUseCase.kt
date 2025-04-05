package ru.dezerom.tasks.domain

import ru.dezerom.tasks.data.repositories.TasksRepository
import javax.inject.Inject

class TasksListUseCase @Inject constructor(
    private val repository: TasksRepository
) {

    suspend fun getAllTasks() = repository.getAll()

}
