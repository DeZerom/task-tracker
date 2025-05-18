package ru.dezerom.tasks.ui.models

import androidx.compose.runtime.Immutable

@Immutable
internal data class TaskUiModel(
    val name: String,
    val description: String,
    val deadline: Long?,
)