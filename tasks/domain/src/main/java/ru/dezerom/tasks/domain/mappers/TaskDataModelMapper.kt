package ru.dezerom.tasks.domain.mappers

import ru.dezerom.tasks.data.models.TaskDataModel
import ru.dezerom.tasks.domain.models.TaskModel

internal fun TaskDataModel.toDomain() = TaskModel(
    id = id,
    name = name,
    description = description,
    createdAt = createdAt,
    deadline = deadline,
    isCompleted = isCompleted,
    completedAt = completedAt
)