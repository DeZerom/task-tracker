package ru.dezerom.tasks.ui.list

import ru.dezerom.tasks.ui.models.TaskEdidtingState

internal sealed class TaskSideEffect {
    class OpenEditTask(val editTaskState: TaskEdidtingState): TaskSideEffect()
}