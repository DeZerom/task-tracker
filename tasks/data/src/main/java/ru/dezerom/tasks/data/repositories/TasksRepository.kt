package ru.dezerom.tasks.data.repositories

import ru.dezerom.tasks.data.models.TaskModel

interface TasksRepository {

    suspend fun getAll(): Result<List<TaskModel>>

}
