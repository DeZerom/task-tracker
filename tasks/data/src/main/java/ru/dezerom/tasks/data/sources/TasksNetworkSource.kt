package ru.dezerom.tasks.data.sources

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import ru.dezerom.core.data.utils.safeApiCall
import ru.dezerom.tasks.data.mappers.toDomainList
import ru.dezerom.tasks.data.models.TaskDataModel
import ru.dezerom.tasks.data.network.TasksApi
import javax.inject.Inject

internal class TasksNetworkSource @Inject constructor(
    private val api: TasksApi,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) {

    suspend fun getAllTasks(): Result<List<TaskDataModel>> {
        return safeApiCall(
            dispatcher = dispatcher
        ) {
            api.getAllTasks()
        }.map { it.toDomainList() }
    }

}
