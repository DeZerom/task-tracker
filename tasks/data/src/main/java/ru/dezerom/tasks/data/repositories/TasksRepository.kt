package ru.dezerom.tasks.data.repositories

import ru.dezerom.tasks.data.models.TaskDataModel

interface TasksRepository {

    suspend fun getAll(): Result<List<TaskDataModel>>

}
