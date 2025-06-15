package ru.dezerom.profile.domain.models

data class UserModel(
    val id: String,
    val login: String,
    val tasks: Int,
    val completedTasks: Int,
    val uncompletedTasks: Int
)
