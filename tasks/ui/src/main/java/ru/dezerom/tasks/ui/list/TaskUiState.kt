package ru.dezerom.tasks.ui.list

import androidx.compose.runtime.Immutable
import ru.dezerom.tasks.domain.models.TaskModel

@Immutable
internal data class TaskUiState(
    val task: TaskModel,
    val isLoading: Boolean,
)

internal fun TaskModel.toUiState() = TaskUiState(
    task = this,
    isLoading = false
)