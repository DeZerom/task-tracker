package ru.dezerom.tasks.ui.list

sealed class TasksListEvent {
    data object OnTryAgainClicked: TasksListEvent()
}
