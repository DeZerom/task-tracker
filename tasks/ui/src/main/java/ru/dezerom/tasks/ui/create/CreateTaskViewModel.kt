package ru.dezerom.tasks.ui.create

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import ru.dezerom.core.tools.R
import ru.dezerom.core.tools.string_container.toStringContainer
import ru.dezerom.core.ui.view_models.BaseViewModel
import ru.dezerom.tasks.domain.TasksListInteractor
import ru.dezerom.tasks.ui.notifiers.TasksChangeListenersHolder
import ru.dezerom.tasks.ui.notifiers.TasksChangedPayload
import javax.inject.Inject

@HiltViewModel
internal class CreateTaskViewModel @Inject constructor(
    private val interactor: TasksListInteractor
): BaseViewModel() {
    private val _state = MutableStateFlow(CreateTaskState())
    val state = _state.asStateFlow()

    private val _sideEffects = Channel<CreateTaskSideEffect>(Channel.BUFFERED)
    val sideEffects = _sideEffects.receiveAsFlow()

    fun onEvent(event: CreateTaskEvent) {
        when (event) {
            is CreateTaskEvent.OnNameChanged -> onNameChanged(event.newName)
            is CreateTaskEvent.OnDescriptionChanged -> onDescriptionChanged(event.newDescription)
            is CreateTaskEvent.OnDeadlineChanged -> onDeadlineChanged(event.newDeadline)
            CreateTaskEvent.OnCreateTaskClicked -> createTask()
        }
    }

    private fun onNameChanged(newName: String) {
        _state.value = state.value.copy(
            task = state.value.task.copy(
                name = newName,
                nameError = if (newName.isBlank())
                    R.string.field_must_not_be_empty.toStringContainer()
                else
                    null
            ),
        )
    }

    private fun onDescriptionChanged(newDescription: String) {
        _state.value = state.value.copy(
            task = state.value.task.copy(description = newDescription)
        )
    }

    private fun onDeadlineChanged(newDeadline: Long?) {
        _state.value = state.value.copy(
            task = state.value.task.copy(deadline = newDeadline)
        )
    }

    private fun createTask() = viewModelScope.launch {
        if (state.value.task.name.isBlank()) return@launch

        _state.value = state.value.copy(isLoading = true)

        val result = interactor.createTask(
            name = state.value.task.name,
            description = state.value.task.description,
            deadline = state.value.task.deadline
        )

        result.fold(
            onSuccess = {
                TasksChangeListenersHolder.triggerChange(
                    TasksChangedPayload.TaskAdded(it)
                )
            },
            onFailure = { showError(it) }
        )

        _state.value = state.value.copy(isLoading = false)
        _sideEffects.send(CreateTaskSideEffect.DismissDialog)
    }
}
