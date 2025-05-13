package ru.dezerom.tasks.data.repositories

import ru.dezerom.tasks.data.models.TaskDataModel
import ru.dezerom.tasks.data.sources.TasksNetworkSource
import javax.inject.Inject

internal class TasksRepositoryImpl @Inject constructor(
    private val networkSource: TasksNetworkSource,
): TasksRepository {

    override suspend fun getAll(): Result<List<TaskDataModel>> {
        return networkSource.getAllTasks()
    }

    override suspend fun createTask(
        name: String,
        description: String?,
        deadline: Long?
    ): Result<Boolean> {
        return networkSource.createTask(name, description, deadline)
    }
}
