package ru.dezerom.tasks.ui.models

import androidx.compose.runtime.Immutable
import ru.dezerom.core.tools.string_container.StringContainer

@Immutable
internal data class TaskEdidtingState(
    val name: String = "",
    val nameError: StringContainer? = null,
    val description: String = "",
    val deadline: Long? = null,
)