package ru.dezerom.tasks.data.network

import kotlinx.coroutines.delay
import ru.dezerom.core.data.models.ResponseDto
import ru.dezerom.tasks.data.network.models.TaskNetworkDto
import ru.dezerom.tasks.data.network.models.TasksListNetworkDto

class TasksMockApi: TasksApi {
    override suspend fun getAllTasks(): ResponseDto<TasksListNetworkDto?> {
        delay(1000)

        return ResponseDto(
            success = true,
            body = TasksListNetworkDto(
                tasks = listOf(
                    TaskNetworkDto(
                        id = "1",
                        name = "qwe1",
                        description = "asd1",
                        createdAt = 1100,
                        completedAt = 11000,
                        deadline = 111000,
                        isCompleted = true,
                    ),
                    TaskNetworkDto(
                        id = "2",
                        name = "qwe2",
                    ),
                    TaskNetworkDto(
                        id = "3",
                        name = "qwe3",
                        completedAt = 1300,
                        isCompleted = true
                    ),
                )
            )
        )
    }
}
