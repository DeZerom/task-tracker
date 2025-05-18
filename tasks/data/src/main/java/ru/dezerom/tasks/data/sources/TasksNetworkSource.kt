package ru.dezerom.tasks.data.sources

import kotlinx.coroutines.CoroutineDispatcher
import ru.dezerom.core.data.di.IODispatcher
import ru.dezerom.core.data.utils.safeApiCall
import ru.dezerom.tasks.data.mappers.toDataModel
import ru.dezerom.tasks.data.mappers.toDomainList
import ru.dezerom.tasks.data.models.TaskDataModel
import ru.dezerom.tasks.data.network.TasksApi
import ru.dezerom.tasks.data.network.models.CreateTaskNetworkDto
import javax.inject.Inject

internal class TasksNetworkSource @Inject constructor(
    private val api: TasksApi,
    @IODispatcher private val dispatcher: CoroutineDispatcher
) {

    suspend fun getAllTasks(): Result<List<TaskDataModel>> {
        return safeApiCall(
            dispatcher = dispatcher
        ) {
            api.getAllTasks()
        }.map { it.toDomainList() }
    }

    suspend fun createTask(
        name: String,
        description: String?,
        deadline: Long?,
    ): Result<TaskDataModel> {
        return safeApiCall(dispatcher = dispatcher) {
            api.createTask(
                CreateTaskNetworkDto(
                    name = name,
                    description = description,
                    deadline = deadline
                )
            )
        }.map { it.toDataModel() }
    }
}
