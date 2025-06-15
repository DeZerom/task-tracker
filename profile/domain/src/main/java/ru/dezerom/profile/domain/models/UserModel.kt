package ru.dezerom.profile.domain.models

data class UserModel(
    val id: String,
    val login: String,
    val tasks: Int,
    val completedTasks: Int,
    val uncompletedTasks: Int
) {
    companion object {
        fun default() = UserModel(
            id = "",
            login = "",
            tasks = 0,
            completedTasks = 0,
            uncompletedTasks = 0
        )
    }
}
