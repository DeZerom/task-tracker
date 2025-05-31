package ru.dezerom.tasks.ui.edit

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import ru.dezerom.core.tools.R
import ru.dezerom.core.ui.kit.theme.TaskTrackerTheme
import ru.dezerom.core.ui.kit.widgets.BottomSheet
import ru.dezerom.tasks.ui.models.TaskEdidtingState
import ru.dezerom.tasks.ui.widgets.TaskEditingForm

@Composable
internal fun EditTaskBottomSheet(
    show: Boolean,
    onDismiss: () -> Unit
) {
    val viewModel: EditTaskViewModel = hiltViewModel()

    EditTaskContent(
        show = show,
        onDismiss = onDismiss,
        task = TaskEdidtingState()
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun EditTaskContent(
    show: Boolean,
    onDismiss: () -> Unit,
    task: TaskEdidtingState,
) {
    BottomSheet(
        show = show,
        onDismiss = onDismiss
    ) {
        TaskEditingForm(
            task = task,
            onNewName = {},
            onNewDescription = {},
            onNewDeadline = {},
            onCreate = {},
            title = stringResource(R.string.edit_task),
            isLoading = false
        )
    }
}

@Preview
@Composable
private fun Preview() {
    TaskTrackerTheme {
        TaskEditingForm(
            task = TaskEdidtingState(),
            onNewName = {},
            onNewDescription = {},
            onNewDeadline = {},
            onCreate = {},
            title = stringResource(R.string.edit_task),
            isLoading = false
        )
    }
}