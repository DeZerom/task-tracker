package ru.dezerom.tasks.ui.list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import ru.dezerom.core.tools.R
import ru.dezerom.core.tools.string_container.StringContainer
import ru.dezerom.core.ui.kit.snackbars.KitSnackbarHost
import ru.dezerom.core.ui.kit.theme.TaskTrackerTheme
import ru.dezerom.core.ui.kit.widgets.DefaultErrorComponent
import ru.dezerom.core.ui.kit.widgets.DefaultLoaderComponent
import ru.dezerom.core.ui.kit.widgets.EmptyListComponent

@Composable
fun TasksListScreen() {
    val viewModel: TasksListViewModel = hiltViewModel()

    val state by viewModel.state.collectAsState()

    TasksListComponent(
        state = state,
        snackbarHostState = viewModel.snackbarHostState,
        onEvent = viewModel::onEvent
    )
}

@Composable
internal fun TasksListComponent(
    state: TasksListState,
    snackbarHostState: SnackbarHostState,
    onEvent: (TasksListEvent) -> Unit
) {
    Scaffold(
        snackbarHost = { KitSnackbarHost(snackbarHostState) }
    ) { innerPadding ->
        Box(
            modifier = Modifier.padding(innerPadding)
        ) {
            when (state) {
                TasksListState.Loading -> Loading()
                is TasksListState.Error ->
                    ErrorComponent(state.error) { onEvent(TasksListEvent.OnTryAgainClicked) }
                is TasksListState.Loaded -> TasksListContent(state)
            }
        }
    }
}

@Composable
private fun TasksListContent(
    state: TasksListState.Loaded
) {
    if (state.tasks.isEmpty()) {
        EmptyListComponent(stringResource(R.string.tasks_empty))
    }
}

@Composable
private fun Loading() {
    DefaultLoaderComponent()
}

@Composable
private fun ErrorComponent(err: StringContainer, onTryAgain: () -> Unit) {
    DefaultErrorComponent(err, onTryAgain)
}

@Composable
@Preview
private fun Preview() {
//    val state = TasksListState.Error("Some error".repeat(10).toStringContainer())
//    val state = TasksListState.Loading
    val state = TasksListState.Loaded(
        tasks = listOf(
//            TaskModel(
//                id = "",
//                name = "qwe",
//                description = "asd",
//                createdAt = 1000,
//                isCompleted = false,
//                deadline = null,
//                completedAt = null,
//            )
        )
    )

    TaskTrackerTheme {
        TasksListComponent(
            state = state,
            snackbarHostState = SnackbarHostState(),
            onEvent = {}
        )
    }
}
