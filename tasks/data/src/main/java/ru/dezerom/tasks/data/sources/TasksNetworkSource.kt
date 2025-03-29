package ru.dezerom.tasks.data.sources

import ru.dezerom.core.data.utils.safeApiCall
import ru.dezerom.tasks.data.mappers.toDomainList
import ru.dezerom.tasks.data.models.TaskModel
import ru.dezerom.tasks.data.network.TasksApi
import javax.inject.Inject

internal class TasksNetworkSource @Inject constructor(
    private val api: TasksApi
) {

    suspend fun getAllTasks(): Result<List<TaskModel>> {
        return safeApiCall {
            api.getAllTasks()
        }.map { it.toDomainList() }
    }

}
