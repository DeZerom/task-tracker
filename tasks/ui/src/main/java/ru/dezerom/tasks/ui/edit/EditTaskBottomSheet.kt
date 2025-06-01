package ru.dezerom.tasks.ui.edit

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import ru.dezerom.core.tools.R
import ru.dezerom.core.ui.kit.theme.TaskTrackerTheme
import ru.dezerom.core.ui.kit.widgets.BottomSheet
import ru.dezerom.core.ui.tools.ProcessSideEffects
import ru.dezerom.tasks.domain.models.TaskModel
import ru.dezerom.tasks.ui.models.TaskEditingState
import ru.dezerom.tasks.ui.widgets.TaskEditingForm

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun EditTaskBottomSheet(
    onDismiss: () -> Unit,
    task: TaskModel
) {
    val viewModel: EditTaskViewModel = hiltViewModel()

    val state by viewModel.state.collectAsState()

    LaunchedEffect(task) {
        viewModel.initializeWith(task)
    }

    ProcessSideEffects(viewModel.sideEffects) {
        when (it) {
            EditTaskSideEffect.Close -> onDismiss()
        }
    }

    BottomSheet(
        onDismiss = { viewModel.close() },
        snackbarHostState = viewModel.snackbarHostState
    ) {
        EditTaskContent(
            task = state,
            onEvent = viewModel::handleEvent
        )
    }
}

@Composable
private fun EditTaskContent(
    task: TaskEditingState,
    onEvent: (EditTaskEvent) -> Unit
) {
    TaskEditingForm(
        state = task,
        onNewName = { onEvent(EditTaskEvent.NewName(it)) },
        onNewDescription = { onEvent(EditTaskEvent.NewDescription(it)) },
        onNewDeadline = { onEvent(EditTaskEvent.NewDeadline(it)) },
        onButtonClick = { onEvent(EditTaskEvent.ButtonClicked) },
        title = stringResource(R.string.edit_task),
        buttonText = stringResource(R.string.edit_task),
    )
}

@Preview
@Composable
private fun Preview() {
    TaskTrackerTheme {
        TaskEditingForm(
            state = TaskEditingState(),
            onNewName = {},
            onNewDescription = {},
            onNewDeadline = {},
            onButtonClick = {},
            title = stringResource(R.string.edit_task),
            buttonText = stringResource(R.string.edit_task),
        )
    }
}