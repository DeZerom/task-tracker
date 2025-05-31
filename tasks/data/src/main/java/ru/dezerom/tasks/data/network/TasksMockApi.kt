package ru.dezerom.tasks.data.network

import kotlinx.coroutines.delay
import ru.dezerom.core.data.models.ResponseDto
import ru.dezerom.tasks.data.network.models.ChangeCompletedStatusNetworkDto
import ru.dezerom.tasks.data.network.models.CreateTaskNetworkDto
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

    override suspend fun createTask(dto: CreateTaskNetworkDto): ResponseDto<TaskNetworkDto?> {
        delay(1000)

        if (dto.name.isBlank()) return ResponseDto(
            success = false,
            body = null,
            error = "unknownNetworkError()"
        )

        return ResponseDto(
            success = true,
            body = TaskNetworkDto(
                id = "3",
                name = dto.name,
                description = dto.description,
                deadline = dto.deadline
            ),
        )
    }

    override suspend fun changeCompleteStatus(taskId: String): ResponseDto<ChangeCompletedStatusNetworkDto?> {
        delay(1000)

        if (taskId == "1") {
            return ResponseDto(
                success = true,
                body = ChangeCompletedStatusNetworkDto(
                    success = true,
                    completedAt = System.currentTimeMillis()
                )
            )
        } else {
            return ResponseDto(
                success = false,
                body = null,
                error = "qweqwe"
            )
        }
    }
}
