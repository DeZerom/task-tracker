package ru.dezerom.tasks.ui.list

import ru.dezerom.core.tools.string_container.StringContainer

internal sealed class TasksListState {
    data object Loading: TasksListState()

    data class Loaded(
        val tasks: List<TaskUiState>,
        val isRefreshing: Boolean,
    ): TasksListState()

    data class Error(
        val error: StringContainer
    ): TasksListState()
}
