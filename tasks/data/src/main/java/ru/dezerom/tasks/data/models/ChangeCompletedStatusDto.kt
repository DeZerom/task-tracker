package ru.dezerom.tasks.data.models

data class ChangeCompletedStatusDto(
    val success: Boolean,
    val completedAt: Long?
)