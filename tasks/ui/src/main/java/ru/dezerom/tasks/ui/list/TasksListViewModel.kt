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
import javax.inject.Inject

@HiltViewModel
class TasksListViewModel @Inject constructor(
    private val tasksInteractor: TasksListInteractor
): BaseViewModel() {
    private val _state = MutableStateFlow<TasksListState>(TasksListState.Loading)
    val state = _state.asStateFlow()

    init {
        initialize()
    }

    private fun initialize() = viewModelScope.launch {
        _state.value = TasksListState.Loading
        fetchTasks()
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
}
