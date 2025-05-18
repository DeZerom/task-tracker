package ru.dezerom.tasks.ui.create

sealed class CreateTaskSideEffect {
    data object DismissDialog: CreateTaskSideEffect()
}
