package ru.dezerom.tasks.domain.models

data class ChangeCompletedStatusModel(
    val success: Boolean,
    val completedAt: Long?
)
