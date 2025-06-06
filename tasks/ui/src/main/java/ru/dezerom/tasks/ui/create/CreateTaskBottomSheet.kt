package ru.dezerom.tasks.ui.create

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import ru.dezerom.core.tools.R
import ru.dezerom.core.tools.string_container.toStringContainer
import ru.dezerom.core.ui.kit.buttons.WhiteButton
import ru.dezerom.core.ui.kit.theme.TaskTrackerTheme
import ru.dezerom.core.ui.kit.widgets.BottomSheet
import ru.dezerom.core.ui.tools.ProcessSideEffects
import ru.dezerom.tasks.ui.models.TaskEditingState
import ru.dezerom.tasks.ui.widgets.TaskEditingForm

@Composable
internal fun CreateTaskBottomSheet(
    onDismiss: () -> Unit,
) {
    val viewModel: CreateTaskViewModel = hiltViewModel()

    ProcessSideEffects(viewModel.sideEffects) {
        when (it) {
            CreateTaskSideEffect.DismissDialog -> {
                onDismiss()
            }
        }
    }

    CreateTaskContent(
        onDismiss = { viewModel.onEvent(CreateTaskEvent.OnDismissRequest) },
        onEvent = viewModel::onEvent,
        state = viewModel.state.collectAsState().value,
        snackbarHostState = viewModel.snackbarHostState
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CreateTaskContent(
    onDismiss: () -> Unit,
    onEvent: (CreateTaskEvent) -> Unit,
    state: TaskEditingState,
    snackbarHostState: SnackbarHostState
) {
    BottomSheet(
        onDismiss = onDismiss,
        snackbarHostState = snackbarHostState
    ) {
        TaskEditingForm(
            state = state,
            onNewName = { onEvent(CreateTaskEvent.OnNameChanged(it)) },
            onNewDescription = { onEvent(CreateTaskEvent.OnDescriptionChanged(it)) },
            onNewDeadline = { onEvent(CreateTaskEvent.OnDeadlineChanged(it)) },
            onButtonClick = { onEvent(CreateTaskEvent.OnCreateTaskClicked) },
            title = stringResource(R.string.task),
            buttonText = stringResource(R.string.add_task)
        )
    }
}

@Composable
@Preview
private fun CreateTaskBottomSheetPreview() {
    var show by remember { mutableStateOf(false) }
    var task by remember {
        mutableStateOf(
            TaskEditingState(
                name = "",
                nameError = "".toStringContainer(),
                description = "",
                deadline = null,
            )
        )
    }

    TaskTrackerTheme {
        Box {
            WhiteButton(
                "Show",
                onClick = { show = true }
            )
        }

        CreateTaskContent(
            state = task,
            onDismiss = { show = false },
            onEvent = {
                when (it) {
                    is CreateTaskEvent.OnDeadlineChanged -> task = task.copy(deadline = it.newDeadline)
                    else -> {}
                }
            },
            snackbarHostState = SnackbarHostState()
        )
    }
}
