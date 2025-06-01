package ru.dezerom.tasks.ui.edit

import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import ru.dezerom.core.ui.view_models.BaseViewModel
import ru.dezerom.tasks.domain.TasksListInteractor
import ru.dezerom.tasks.domain.models.TaskModel
import ru.dezerom.tasks.ui.models.TaskEditingState

@HiltViewModel(assistedFactory = EditTaskViewModel.Factory::class)
internal class EditTaskViewModel @AssistedInject constructor(
    @Assisted("task") val task: TaskModel,
    private val tasksListInteractor: TasksListInteractor
) : BaseViewModel() {
    private val _state = MutableStateFlow(TaskEditingState())
    val state = _state.asStateFlow()

    @AssistedFactory
    interface Factory {
        fun create(@Assisted("task") task: TaskModel): EditTaskViewModel
    }
}
