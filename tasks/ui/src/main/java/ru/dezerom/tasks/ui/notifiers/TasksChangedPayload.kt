package ru.dezerom.tasks.ui.notifiers

import ru.dezerom.tasks.domain.models.TaskModel

sealed class TasksChangedPayload {
    class TaskAdded(val newTask: TaskModel): TasksChangedPayload()
}
