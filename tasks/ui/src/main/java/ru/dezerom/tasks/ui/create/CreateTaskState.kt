package ru.dezerom.tasks.ui.create

import androidx.compose.runtime.Immutable
import ru.dezerom.tasks.ui.models.TaskEdidtingState

@Immutable
internal data class CreateTaskState(
    val task: TaskEdidtingState = TaskEdidtingState(),
    val isLoading: Boolean = false
)
