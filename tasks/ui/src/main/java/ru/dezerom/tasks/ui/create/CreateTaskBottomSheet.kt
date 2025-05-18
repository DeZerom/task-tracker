package ru.dezerom.tasks.ui.create

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import ru.dezerom.core.ui.kit.buttons.WhiteButton
import ru.dezerom.core.ui.kit.theme.TaskTrackerTheme
import ru.dezerom.core.ui.kit.widgets.BottomSheet
import ru.dezerom.tasks.ui.models.TaskUiModel
import ru.dezerom.tasks.ui.widgets.TaskEditingForm

@Composable
internal fun CreateTaskBottomSheet(
    task: CreateTaskState,
    show: Boolean,
    onDismiss: () -> Unit,
) {
    val viewModel: CreateTaskViewModel = hiltViewModel()


}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CreateTaskContent(
    show: Boolean,
    onDismiss: () -> Unit,
    state: CreateTaskState,
    onEvent: (CreateTaskEvent) -> Unit
) {
    BottomSheet(
        show = show,
        onDismiss = onDismiss
    ) {
        TaskEditingForm(
            task = state.task,
            isLoading = state.isLoading,
            onNewName = { onEvent(CreateTaskEvent.OnNameChanged(it)) },
            onNewDescription = { onEvent(CreateTaskEvent.OnDescriptionChanged(it)) },
            onNewDeadline = { onEvent(CreateTaskEvent.OnNewDeadline(it)) },
            onCreate = { onEvent(CreateTaskEvent.OnCreateTaskClicked) }
        )
    }
}

@Composable
@Preview
private fun CreateTaskBottomSheetPreview() {
    var show by remember { mutableStateOf(false) }
    var task by remember { mutableStateOf(TaskUiModel("", "", null)) }

    TaskTrackerTheme {
        Box {
            WhiteButton(
                "Show",
                onClick = { show = true }
            )
        }

        CreateTaskContent(
            state = CreateTaskState(
                task = task,
                isLoading = false,
            ),
            show = show,
            onDismiss = { show = false },
            onEvent = {
                when (it) {
                    CreateTaskEvent.OnCreateTaskClicked -> {}
                    is CreateTaskEvent.OnDescriptionChanged -> task = task.copy(description = it.newDescription)
                    is CreateTaskEvent.OnNameChanged -> task = task.copy(name = it.newName)
                    is CreateTaskEvent.OnNewDeadline -> task = task.copy(deadline = it.newDeadline)
                }
            }
        )
    }
}
