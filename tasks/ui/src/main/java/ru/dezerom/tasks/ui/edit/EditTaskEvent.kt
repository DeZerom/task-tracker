package ru.dezerom.tasks.ui.edit

internal sealed class EditTaskEvent {
    class NewName(val newName: String): EditTaskEvent()
    class NewDescription(val newDescription: String): EditTaskEvent()
    class NewDeadline(val newDeadline: Long?): EditTaskEvent()

    data object ButtonClicked: EditTaskEvent()
}