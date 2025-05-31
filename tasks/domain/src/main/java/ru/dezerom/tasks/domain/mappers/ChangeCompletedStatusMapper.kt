package ru.dezerom.tasks.domain.mappers

import ru.dezerom.tasks.data.models.ChangeCompletedStatusDto
import ru.dezerom.tasks.domain.models.ChangeCompletedStatusModel

fun ChangeCompletedStatusDto.toDomain() = ChangeCompletedStatusModel(
    success = success,
    completedAt = completedAt
)