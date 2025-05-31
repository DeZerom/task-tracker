package ru.dezerom.tasks.ui.edit

import dagger.hilt.android.lifecycle.HiltViewModel
import ru.dezerom.core.ui.view_models.BaseViewModel
import ru.dezerom.tasks.domain.TasksListInteractor
import javax.inject.Inject

@HiltViewModel
class EditTaskViewModel @Inject constructor(
    private val tasksListInteractor: TasksListInteractor
) : BaseViewModel() {

}
