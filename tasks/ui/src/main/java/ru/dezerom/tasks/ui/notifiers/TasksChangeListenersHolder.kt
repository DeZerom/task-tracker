package ru.dezerom.tasks.ui.notifiers

object TasksChangeListenersHolder {
    private val listeners = mutableListOf<TasksChangeListener>()

    fun register(listener: TasksChangeListener) = listeners.add(listener)

    fun unregister(listener: TasksChangeListener) = listeners.remove(listener)

    fun triggerChange(payload: TasksChangedPayload) =
        listeners.forEach { it.onTasksChanged(payload) }
}
