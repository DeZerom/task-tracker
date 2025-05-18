package ru.dezerom.tasks.ui.create

internal sealed class CreateTaskEvent {
    class OnNameChanged(val newName: String): CreateTaskEvent()
    class OnDescriptionChanged(val newDescription: String): CreateTaskEvent()
    class OnNewDeadline(val newDeadline: Long?): CreateTaskEvent()
    data object OnCreateTaskClicked: CreateTaskEvent()
}
