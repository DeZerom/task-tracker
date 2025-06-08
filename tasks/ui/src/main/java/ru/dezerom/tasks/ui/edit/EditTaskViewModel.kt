package ru.dezerom.tasks.ui.edit

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import ru.dezerom.core.tools.R
import ru.dezerom.core.tools.string_container.toStringContainer
import ru.dezerom.core.ui.view_models.BottomSheetBaseViewModel
import ru.dezerom.tasks.domain.TasksListInteractor
import ru.dezerom.tasks.domain.models.TaskModel
import ru.dezerom.tasks.ui.models.TaskEditingState
import ru.dezerom.tasks.ui.models.toTaskEditingState
import ru.dezerom.tasks.ui.notifiers.TasksChangeListenersHolder
import ru.dezerom.tasks.ui.notifiers.TasksChangedPayload
import javax.inject.Inject

@HiltViewModel
internal class EditTaskViewModel @Inject constructor(
    private val tasksListInteractor: TasksListInteractor
) : BottomSheetBaseViewModel() {
    private val _state = MutableStateFlow(TaskEditingState())
    val state = _state.asStateFlow()

    private val _sideEffects = Channel<EditTaskSideEffect>(Channel.BUFFERED)
    val sideEffects = _sideEffects.receiveAsFlow()

    private var taskId: String = ""

    fun handleEvent(event: EditTaskEvent) {
        when (event) {
            EditTaskEvent.ButtonClicked -> editTask()
            is EditTaskEvent.NewDeadline -> onNewDeadline(event)
            is EditTaskEvent.NewDescription -> onNewDescription(event)
            is EditTaskEvent.NewName -> onNewName(event)
        }
    }

    fun initializeWith(task: TaskModel) {
        _state.value = task.toTaskEditingState()
        taskId = task.id
    }

    fun close(scope: CoroutineScope = viewModelScope) {
        _state.value = TaskEditingState()
        taskId = ""
        scope.launch { _sideEffects.send(EditTaskSideEffect.Close) }
    }

    private fun onNewName(event: EditTaskEvent.NewName) {
        _state.value = state.value.copy(
            name = event.newName,
            nameError = if (event.newName.isBlank())
                R.string.field_must_not_be_empty.toStringContainer()
            else
                null,
        )
    }

    private fun onNewDescription(event: EditTaskEvent.NewDescription) {
        _state.value = state.value.copy(description = event.newDescription)
    }

    private fun onNewDeadline(event: EditTaskEvent.NewDeadline) {
        _state.value = state.value.copy(deadline = event.newDeadline)
    }

    private fun editTask() = viewModelScope.launch {
        if (state.value.isLoading) return@launch

        if (!validate()) {
            showError(R.string.field_must_not_be_empty.toStringContainer())
            return@launch
        }

        _state.value = state.value.copy(isLoading = true)

        tasksListInteractor.editTask(
            id = taskId,
            name = state.value.name,
            description = state.value.description.ifBlank { null },
            deadline = state.value.deadline
        ).fold(
            onSuccess = {
                TasksChangeListenersHolder.triggerChange(TasksChangedPayload.TaskEdited(it))
                close(this)
            },
            onFailure = { showError(it) }
        )
    }

    private fun validate(): Boolean {
        return state.value.name.isNotBlank()
    }
}
