package ru.dezerom.tasks.data.repositories

import ru.dezerom.tasks.data.models.TaskModel
import ru.dezerom.tasks.data.network.TasksMockApi
import ru.dezerom.tasks.data.sources.TasksNetworkSource

class TasksRepositoryMock: TasksRepository {
    private val impl = TasksRepositoryImpl(TasksNetworkSource(TasksMockApi()))

    override suspend fun getAll(): Result<List<TaskModel>> {
        return impl.getAll()
    }
}
