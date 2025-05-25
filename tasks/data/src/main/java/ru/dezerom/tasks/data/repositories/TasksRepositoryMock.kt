package ru.dezerom.tasks.data.repositories

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import ru.dezerom.tasks.data.models.TaskDataModel
import ru.dezerom.tasks.data.network.TasksMockApi
import ru.dezerom.tasks.data.sources.TasksNetworkSource

class TasksRepositoryMock(
    dispatcher: CoroutineDispatcher = Dispatchers.Default,
): TasksRepository {
    private val impl = TasksRepositoryImpl(TasksNetworkSource(TasksMockApi(), dispatcher))

    override suspend fun getAll(): Result<List<TaskDataModel>> {
        return impl.getAll()
    }

    override suspend fun createTask(
        name: String,
        description: String?,
        deadline: Long?
    ): Result<TaskDataModel> {
        return impl.createTask(name, description, deadline)
    }

    override suspend fun changeCompleteStatus(taskId: String): Result<Boolean> {
        return impl.changeCompleteStatus(taskId)
    }
}
