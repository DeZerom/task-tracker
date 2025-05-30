package ru.dezerom.tasks.data.models

data class TaskDataModel(
    val id: String,
    val name: String,
    val description: String,
    val deadline: Long?,
    val createdAt: Long,
    val isCompleted: Boolean,
    val completedAt: Long?,
)
