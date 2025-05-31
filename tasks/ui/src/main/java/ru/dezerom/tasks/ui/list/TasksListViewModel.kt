package ru.dezerom.tasks.ui.list

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.dezerom.core.tools.R
import ru.dezerom.core.tools.errors.NetworkError
import ru.dezerom.core.tools.errors.unknownNetworkError
import ru.dezerom.core.tools.string_container.toStringContainer
import ru.dezerom.core.ui.view_models.BaseViewModel
import ru.dezerom.tasks.domain.TasksListInteractor
import ru.dezerom.tasks.domain.models.TaskModel
import ru.dezerom.tasks.ui.notifiers.TasksChangeListener
import ru.dezerom.tasks.ui.notifiers.TasksChangeListenersHolder
import ru.dezerom.tasks.ui.notifiers.TasksChangedPayload
import javax.inject.Inject

@HiltViewModel
internal class TasksListViewModel @Inject constructor(
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
            TasksListEvent.OnRefresh -> refresh()
            is TasksListEvent.OnChangeCompleteStatus -> changeCompleteStatus(event.taskId)
        }
    }

    private fun changeCompleteStatus(taskId: String) {
        val s = state.value
        if (s !is TasksListState.Loaded) return

        val task = findTask(taskId)
        if (task == null) {
            showError(R.string.unknown_error.toStringContainer())
            return
        }
        changeTaskState(taskId) { copy(isLoading = true) }

        viewModelScope.launch {
            tasksInteractor.changeCompleteStatus(taskId).fold(
                onSuccess = {
                    if (!it.success) {
                        showError(R.string.unknown_error.toStringContainer())
                    } else {
                        changeInnerTask(taskId) {
                            copy(
                                isCompleted = !task.task.isCompleted,
                                completedAt = it.completedAt
                            )
                        }
                    }
                },
                onFailure = {
                    showError(R.string.unknown_error.toStringContainer())
                }
            )

            changeTaskState(taskId) { copy(isLoading = false) }
        }
    }

    private fun refresh() = viewModelScope.launch {
        val s = state.value
        if (s !is TasksListState.Loaded || s.isRefreshing) return@launch

        _state.value = s.copy(isRefreshing = true)
        fetchTasks()
    }

    private suspend fun fetchTasks() {
        tasksInteractor.getAllTasks().fold(
            onSuccess = {
                _state.value = TasksListState.Loaded(it.map(TaskModel::toUiState), false)
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
            tasks = s.tasks + task.toUiState()
        )
    }

    private fun changeTaskState(id: String, reducer: TaskUiState.() -> TaskUiState) {
        val s = state.value
        if (s !is TasksListState.Loaded) return

        _state.value = s.copy(
            tasks = s.tasks.map {
                if (it.task.id == id) reducer(it)
                else it
            }
        )
    }

    private fun changeInnerTask(id: String, reducer: TaskModel.() -> TaskModel) {
        changeTaskState(id) { copy(task = reducer(task)) }
    }

    private fun findTask(id: String): TaskUiState? {
        val s = state.value
        if (s !is TasksListState.Loaded) return null

        return s.tasks.find { it.task.id == id }
    }
}
