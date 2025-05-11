package ru.dezerom.tasks.ui.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import ru.dezerom.core.tools.consts.Dimens
import ru.dezerom.core.tools.string_container.StringContainer
import ru.dezerom.core.ui.kit.snackbars.KitSnackbarHost
import ru.dezerom.core.ui.kit.theme.TaskTrackerTheme
import ru.dezerom.core.ui.kit.widgets.DefaultErrorComponent
import ru.dezerom.core.ui.kit.widgets.DefaultLoaderComponent
import ru.dezerom.core.ui.kit.widgets.EmptyListComponent
import ru.dezerom.tasks.domain.models.TaskModel

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
                is TasksListState.Loaded ->
                    if (state.tasks.isEmpty()) {
                        EmptyListComponent(stringResource(R.string.tasks_empty))
                    } else {
                        TasksListContent(state)
                    }
            }
        }
    }
}

@Composable
private fun TasksListContent(
    state: TasksListState.Loaded
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(Dimens.Padding.Medium),
        modifier = Modifier
            .fillMaxSize()
            .padding(Dimens.Padding.Medium)
    ) {
        items(
            items = state.tasks,
            key = { it.id }
        ) {
            TaskComponent(
                it,
                onCompleted = {},
                modifier = Modifier.fillMaxWidth()
            )
        }
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
private fun TasksListScreenPreview() {
//    val state = TasksListState.Error("Some error".repeat(10).toStringContainer())
//    val state = TasksListState.Loading
    val state = TasksListState.Loaded(
        tasks = listOf(
            TaskModel(
                id = "1",
                name = "Task name",
                description = "asd",
                createdAt = 1000,
                isCompleted = false,
                deadline = null,
                completedAt = null,
            ),
            TaskModel(
                id = "12",
                name = "Long long long long task name",
                description = "asd",
                createdAt = 1000,
                isCompleted = true,
                deadline = null,
                completedAt = null,
            ),
            TaskModel(
                id = "123",
                name = "Long long long long task name Long long long long task name Long long long long task name Long long long long task name",
                description = "asd",
                createdAt = 1000,
                isCompleted = true,
                deadline = null,
                completedAt = null,
            )
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
