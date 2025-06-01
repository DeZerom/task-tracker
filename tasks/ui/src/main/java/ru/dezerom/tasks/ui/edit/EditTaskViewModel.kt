package ru.dezerom.tasks.ui.edit

import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import ru.dezerom.core.tools.R
import ru.dezerom.core.tools.string_container.toStringContainer
import ru.dezerom.core.ui.view_models.BaseViewModel
import ru.dezerom.tasks.domain.TasksListInteractor
import ru.dezerom.tasks.domain.models.TaskModel
import ru.dezerom.tasks.ui.models.TaskEditingState
import ru.dezerom.tasks.ui.models.toTaskEditingState

@HiltViewModel(assistedFactory = EditTaskViewModel.Factory::class)
internal class EditTaskViewModel @AssistedInject constructor(
    @Assisted("task") val task: TaskModel,
    private val tasksListInteractor: TasksListInteractor
) : BaseViewModel() {
    private val _state = MutableStateFlow(TaskEditingState())
    val state = _state.asStateFlow()

    init {
        initialize()
    }

    fun handleEvent(event: EditTaskEvent) {
        when (event) {
            EditTaskEvent.ButtonClicked -> editTask()
            is EditTaskEvent.NewDeadline -> onNewDeadline(event)
            is EditTaskEvent.NewDescription -> onNewDescription(event)
            is EditTaskEvent.NewName -> onNewName(event)
        }
    }

    private fun initialize() {
        _state.value = task.toTaskEditingState()
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

    private fun editTask() {

    }

    @AssistedFactory
    interface Factory {
        fun create(@Assisted("task") task: TaskModel): EditTaskViewModel
    }
}
