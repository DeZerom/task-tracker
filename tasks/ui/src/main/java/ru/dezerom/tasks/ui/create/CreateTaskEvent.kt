package ru.dezerom.tasks.ui.create

internal sealed class CreateTaskEvent {
    class OnNameChanged(val newName: String): CreateTaskEvent()
    class OnDescriptionChanged(val newDescription: String): CreateTaskEvent()
    class OnDeadlineChanged(val newDeadline: Long?): CreateTaskEvent()
    data object OnCreateTaskClicked: CreateTaskEvent()
}
