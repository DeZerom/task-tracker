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
import ru.dezerom.tasks.ui.models.TaskEditingState
import ru.dezerom.tasks.ui.notifiers.TasksChangeListenersHolder
import ru.dezerom.tasks.ui.notifiers.TasksChangedPayload
import javax.inject.Inject

@HiltViewModel
internal class CreateTaskViewModel @Inject constructor(
    private val interactor: TasksListInteractor
): BaseViewModel() {
    private val _state = MutableStateFlow(TaskEditingState())
    val state = _state.asStateFlow()

    private val _sideEffects = Channel<CreateTaskSideEffect>(Channel.BUFFERED)
    val sideEffects = _sideEffects.receiveAsFlow()

    fun onEvent(event: CreateTaskEvent) {
        when (event) {
            is CreateTaskEvent.OnNameChanged -> onNameChanged(event.newName)
            is CreateTaskEvent.OnDescriptionChanged -> onDescriptionChanged(event.newDescription)
            is CreateTaskEvent.OnDeadlineChanged -> onDeadlineChanged(event.newDeadline)
            CreateTaskEvent.OnCreateTaskClicked -> viewModelScope.launch { createTask() }
            CreateTaskEvent.OnDismissRequest -> viewModelScope.launch { clearAndClose() }
        }
    }

    private fun onNameChanged(newName: String) {
        _state.value = state.value.copy(
            name = newName,
            nameError = if (newName.isBlank())
                R.string.field_must_not_be_empty.toStringContainer()
            else
                null
        )
    }

    private fun onDescriptionChanged(newDescription: String) {
        _state.value = state.value.copy(description = newDescription)
    }

    private fun onDeadlineChanged(newDeadline: Long?) {
        _state.value = state.value.copy(deadline = newDeadline)
    }

    private suspend fun createTask() {
        _state.value = state.value.copy(
            isLoading = true,
            nameError = if (state.value.name.isBlank())
                R.string.field_must_not_be_empty.toStringContainer()
            else
                null
        )

        val result = interactor.createTask(
            name = state.value.name,
            description = state.value.description,
            deadline = state.value.deadline
        )

        var success = false
        result.fold(
            onSuccess = {
                TasksChangeListenersHolder.triggerChange(
                    TasksChangedPayload.TaskAdded(it)
                )
                success = true
            },
            onFailure = { showError(it) }
        )

        _state.value = state.value.copy(isLoading = false)
        if (success) clearAndClose()
    }

    private suspend fun clearAndClose() {
        _state.value = TaskEditingState()
        _sideEffects.send(CreateTaskSideEffect.DismissDialog)
    }
}
