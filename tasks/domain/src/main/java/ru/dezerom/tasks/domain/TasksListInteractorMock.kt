package ru.dezerom.tasks.domain

import kotlinx.coroutines.CoroutineDispatcher
import ru.dezerom.tasks.data.repositories.TasksRepositoryMock

class TasksListInteractorMock(dispatcher: CoroutineDispatcher) {
    val impl = TasksListInteractor(TasksRepositoryMock(dispatcher))
}
