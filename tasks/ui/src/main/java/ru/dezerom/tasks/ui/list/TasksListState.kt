package ru.dezerom.tasks.ui.list

import ru.dezerom.core.tools.string_container.StringContainer
import ru.dezerom.tasks.domain.models.TaskModel

sealed class TasksListState {
    data object Loading: TasksListState()

    data class Loaded(
        val tasks: List<TaskModel>,
        val isRefreshing: Boolean,
    ): TasksListState()

    data class Error(
        val error: StringContainer
    ): TasksListState()
}
