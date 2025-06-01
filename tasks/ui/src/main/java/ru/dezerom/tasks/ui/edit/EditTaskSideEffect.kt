package ru.dezerom.tasks.ui.edit

internal sealed class EditTaskSideEffect {
    data object Close: EditTaskSideEffect()
}
