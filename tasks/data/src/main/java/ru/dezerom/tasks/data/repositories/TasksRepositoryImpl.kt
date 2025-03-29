package ru.dezerom.tasks.data.repositories

import ru.dezerom.tasks.data.models.TaskModel
import ru.dezerom.tasks.data.sources.TasksNetworkSource
import javax.inject.Inject

internal class TasksRepositoryImpl @Inject constructor(
    private val networkSource: TasksNetworkSource,
): TasksRepository {

    override suspend fun getAll(): Result<List<TaskModel>> {
        return networkSource.getAllTasks()
    }
}
