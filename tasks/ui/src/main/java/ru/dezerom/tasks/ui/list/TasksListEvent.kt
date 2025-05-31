package ru.dezerom.tasks.ui.list

sealed class TasksListEvent {
    data object OnTryAgainClicked: TasksListEvent()

    data object OnRefresh: TasksListEvent()

    class OnChangeCompleteStatus(val taskId: String): TasksListEvent()

    class OnEditClicked(val taskId: String): TasksListEvent()

    class OnDeleteClicked(val taskId: String): TasksListEvent()
}
