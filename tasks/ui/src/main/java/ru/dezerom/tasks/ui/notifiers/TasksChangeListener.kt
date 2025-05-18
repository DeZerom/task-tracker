package ru.dezerom.tasks.ui.notifiers

interface TasksChangeListener {
    fun onTasksChanged(payload: TasksChangedPayload)
}
