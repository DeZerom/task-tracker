package ru.dezerom.tasks.ui.models

import androidx.compose.runtime.Immutable
import ru.dezerom.core.tools.string_container.StringContainer
import ru.dezerom.tasks.domain.models.TaskModel

@Immutable
internal data class TaskEditingState(
    val name: String = "",
    val nameError: StringContainer? = null,
    val description: String = "",
    val deadline: Long? = null,
)

internal fun TaskModel.toTaskEditingState() = TaskEditingState(
    name = name,
    nameError = null,
    description = description,
    deadline = deadline
)