package ru.dezerom.profile.data.models

data class UserDataModel(
    val id: String? = null,
    val login: String? = null,
    val tasks: Int? = null,
    val completedTasks: Int? = null,
    val uncompletedTasks: Int? = null
)
