package ru.dezerom.tasks.data.mappers

import ru.dezerom.tasks.data.models.ChangeCompletedStatusDto
import ru.dezerom.tasks.data.network.models.ChangeCompletedStatusNetworkDto

fun ChangeCompletedStatusNetworkDto.toDto() = ChangeCompletedStatusDto(
    success = success == true,
    completedAt = completedAt
)