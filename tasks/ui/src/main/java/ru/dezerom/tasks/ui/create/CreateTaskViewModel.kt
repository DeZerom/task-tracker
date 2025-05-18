package ru.dezerom.tasks.ui.create

import dagger.hilt.android.lifecycle.HiltViewModel
import ru.dezerom.core.ui.view_models.BaseViewModel
import ru.dezerom.tasks.domain.TasksListInteractor
import javax.inject.Inject

@HiltViewModel
class CreateTaskViewModel @Inject constructor(
    interactor: TasksListInteractor
): BaseViewModel() {

}
