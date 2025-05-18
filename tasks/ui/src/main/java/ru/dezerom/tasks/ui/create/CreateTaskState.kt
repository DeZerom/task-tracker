package ru.dezerom.tasks.ui.create

import androidx.compose.runtime.Immutable
import ru.dezerom.tasks.ui.models.TaskUiModel

@Immutable
internal data class CreateTaskState(
    val task: TaskUiModel,
    val isLoading: Boolean
)
