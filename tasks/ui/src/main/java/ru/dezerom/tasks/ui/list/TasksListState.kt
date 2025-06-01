package ru.dezerom.tasks.ui.list

import ru.dezerom.core.tools.string_container.StringContainer
import ru.dezerom.tasks.domain.models.TaskModel

internal sealed class TasksListState {
    data object Loading: TasksListState()

    data class Loaded(
        val tasks: List<TaskUiState>,
        val isRefreshing: Boolean,
        val editingTask: TaskModel? = null,
    ): TasksListState()

    data class Error(
        val error: StringContainer
    ): TasksListState()
}
