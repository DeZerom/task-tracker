package ru.dezerom.tasks.data.mappers

import ru.dezerom.tasks.data.models.TaskDataModel
import ru.dezerom.tasks.data.network.models.TaskNetworkDto

internal fun TaskNetworkDto.toDataModel() = TaskDataModel(
    id = id ?: "",
    name = name ?: "",
    description = description ?: "",
    createdAt = createdAt ?: 0,
    deadline = deadline,
    isCompleted = isCompleted == true,
    completedAt = completedAt
)
