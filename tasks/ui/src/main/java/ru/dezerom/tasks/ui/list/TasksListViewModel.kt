package ru.dezerom.tasks.ui.list

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.dezerom.core.tools.errors.NetworkError
import ru.dezerom.core.tools.errors.unknownNetworkError
import ru.dezerom.core.ui.view_models.BaseViewModel
import ru.dezerom.tasks.domain.TasksListInteractor
import ru.dezerom.tasks.domain.models.TaskModel
import ru.dezerom.tasks.ui.notifiers.TasksChangeListener
import ru.dezerom.tasks.ui.notifiers.TasksChangeListenersHolder
import ru.dezerom.tasks.ui.notifiers.TasksChangedPayload
import javax.inject.Inject

@HiltViewModel
class TasksListViewModel @Inject constructor(
    private val tasksInteractor: TasksListInteractor
): BaseViewModel(), TasksChangeListener {
    private val _state = MutableStateFlow<TasksListState>(TasksListState.Loading)
    val state = _state.asStateFlow()

    init {
        TasksChangeListenersHolder.register(this)

        initialize()
    }

    override fun onCleared() {
        TasksChangeListenersHolder.unregister(this)

        super.onCleared()
    }

    private fun initialize() = viewModelScope.launch {
        _state.value = TasksListState.Loading
        fetchTasks()
    }

    override fun onTasksChanged(payload: TasksChangedPayload) {
        when (payload) {
            is TasksChangedPayload.TaskAdded -> addTask(payload.newTask)
        }
    }

    fun onEvent(event: TasksListEvent) {
        when (event) {
            TasksListEvent.OnTryAgainClicked -> initialize()
        }
    }

    private suspend fun fetchTasks() {
        tasksInteractor.getAllTasks().fold(
            onSuccess = {
                _state.value = TasksListState.Loaded(it)
            },
            onFailure = {
                val err = it as? NetworkError ?: unknownNetworkError()
                _state.value = TasksListState.Error(err.messageRes)
            }
        )
    }

    private fun addTask(task: TaskModel) {
        val s = state.value
        if (s !is TasksListState.Loaded) return

        _state.value = s.copy(
            tasks = s.tasks + task
        )
    }
}
