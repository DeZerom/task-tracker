package ru.dezerom.tasks.ui.create

import androidx.compose.runtime.Immutable
import ru.dezerom.tasks.ui.models.TaskEditingState

@Immutable
internal data class CreateTaskState(
    val task: TaskEditingState = TaskEditingState(),
    val isLoading: Boolean = false
)
