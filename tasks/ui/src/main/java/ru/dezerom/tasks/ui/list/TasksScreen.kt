package ru.dezerom.tasks.ui.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import ru.dezerom.core.tools.R
import ru.dezerom.core.tools.consts.Dimens
import ru.dezerom.core.tools.string_container.StringContainer
import ru.dezerom.core.ui.kit.buttons.AccentExpandableFAB
import ru.dezerom.core.ui.kit.snackbars.KitSnackbarHost
import ru.dezerom.core.ui.kit.theme.TaskTrackerTheme
import ru.dezerom.core.ui.kit.widgets.DefaultErrorComponent
import ru.dezerom.core.ui.kit.widgets.DefaultLoaderComponent
import ru.dezerom.core.ui.kit.widgets.EmptyListComponent
import ru.dezerom.core.ui.kit.widgets.TopLevelTopBar
import ru.dezerom.core.ui.tools.isScrolledToBottom
import ru.dezerom.tasks.domain.models.TaskModel
import ru.dezerom.tasks.ui.create.CreateTaskBottomSheet
import ru.dezerom.tasks.ui.edit.EditTaskBottomSheet

@Composable
fun TasksListScreen() {
    val viewModel: TasksListViewModel = hiltViewModel()

    val state by viewModel.state.collectAsState()

    var showAddTask by remember { mutableStateOf(false) }

    TasksListComponent(
        state = state,
        snackbarHostState = viewModel.snackbarHostState,
        onEvent = viewModel::onEvent,
        onAddTaskClicked = { showAddTask = true },
        addTaskBuilder = {
            if (showAddTask) {
                CreateTaskBottomSheet(
                    onDismiss = { showAddTask = false }
                )
            }
        },
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun TasksListComponent(
    state: TasksListState,
    snackbarHostState: SnackbarHostState,
    onEvent: (TasksListEvent) -> Unit,
    onAddTaskClicked: () -> Unit,
    addTaskBuilder: @Composable () -> Unit,
) {
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
    val listState = rememberLazyListState()

    Scaffold(
        topBar = {
            TopLevelTopBar(
                title = stringResource(R.string.tasks),
                scrollBehavior = scrollBehavior
            )
        },
        floatingActionButton = {
            if (state is TasksListState.Loaded) {
                AccentExpandableFAB(
                    icon = Icons.Default.Add,
                    expandedText = stringResource(R.string.add_task),
                    isExpanded = isScrolledToBottom(listState),
                    onClick = { onAddTaskClicked() }
                )
            }
        },
        snackbarHost = { KitSnackbarHost(snackbarHostState) }
    ) { innerPadding ->
        Box(
            modifier = Modifier.padding(innerPadding)
        ) {
            addTaskBuilder()

            when (state) {
                TasksListState.Loading -> Loading()

                is TasksListState.Error ->
                    ErrorComponent(state.error) { onEvent(TasksListEvent.OnTryAgainClicked) }

                is TasksListState.Loaded ->
                    if (state.tasks.isEmpty()) {
                        EmptyListComponent(stringResource(R.string.tasks_empty))
                    } else {
                        if (state.editingTask != null) {
                            EditTaskBottomSheet(
                                onDismiss = {},
                                task = state.editingTask
                            )
                        }

                        TasksListContent(
                            state = state,
                            onRefresh = { onEvent(TasksListEvent.OnRefresh) },
                            onChangeCompleteStatus = { onEvent(TasksListEvent.OnChangeCompleteStatus(it)) },
                            onEditClicked = { onEvent(TasksListEvent.OnEditClicked(it)) },
                            onDeleteClicked = { onEvent(TasksListEvent.OnDeleteClicked(it)) },
                            scrollBehavior = scrollBehavior,
                            listState = listState
                        )
                    }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TasksListContent(
    state: TasksListState.Loaded,
    onRefresh: () -> Unit,
    onChangeCompleteStatus: (String) -> Unit,
    onEditClicked: (String) -> Unit,
    onDeleteClicked: (String) -> Unit,
    scrollBehavior: TopAppBarScrollBehavior,
    listState: LazyListState
) {
    PullToRefreshBox(
        isRefreshing = state.isRefreshing,
        onRefresh = onRefresh,
    ) {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(Dimens.Padding.Medium),
            state = listState,
            modifier = Modifier
                .fillMaxSize()
                .nestedScroll(scrollBehavior.nestedScrollConnection)
                .padding(horizontal = Dimens.Padding.Medium)
        ) {
            items(
                count = state.tasks.size,
                key = { i -> state.tasks[i].task.id }
            ) { i ->
                val task = state.tasks[i]

                TaskComponent(
                    task.task,
                    isLoading = task.isLoading,
                    onChangeCompleteStatus = { onChangeCompleteStatus(task.task.id) },
                    onEditClicked = { onEditClicked(task.task.id) },
                    onDeleteClicked = { onDeleteClicked(task.task.id) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            top = if (i == 0) Dimens.Padding.Medium else 0.dp,
                            bottom = if (i >= state.tasks.size - 1)
                                Dimens.Padding.Medium * 2 + 56.dp
                            else
                                0.dp
                        )
                )
            }
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
            TaskUiState(
                task = TaskModel(
                    id = "1",
                    name = "Task name",
                    description = "asd",
                    createdAt = 1000,
                    isCompleted = false,
                    deadline = null,
                    completedAt = null,
                ),
                isLoading = false
            ),
            TaskUiState(
                task = TaskModel(
                    id = "12",
                    name = "Long long long long task name",
                    description = "asd",
                    createdAt = 1000,
                    isCompleted = true,
                    deadline = null,
                    completedAt = null,
                ),
                isLoading = true
            ),
            TaskUiState(
                task = TaskModel(
                    id = "123",
                    name = "Long long long long task name Long long long long task name Long long long long task name Long long long long task name",
                    description = "asd",
                    createdAt = 1000,
                    isCompleted = true,
                    deadline = null,
                    completedAt = null,
                ),
                isLoading = false
            ),
            TaskUiState(
                task = TaskModel(
                    id = "2",
                    name = "Task name",
                    description = "asd",
                    createdAt = 1000,
                    isCompleted = false,
                    deadline = null,
                    completedAt = null,
                ),
                isLoading = false
            ),
            TaskUiState(
                task = TaskModel(
                    id = "3",
                    name = "Long long long long task name",
                    description = "asd",
                    createdAt = 1000,
                    isCompleted = true,
                    deadline = null,
                    completedAt = null,
                ),
                isLoading = true
            ),
            TaskUiState(
                task = TaskModel(
                    id = "4",
                    name = "Long long long long task name Long long long long task name Long long long long task name Long long long long task name",
                    description = "asd",
                    createdAt = 1000,
                    isCompleted = true,
                    deadline = null,
                    completedAt = null,
                ),
                isLoading = true
            ),
            TaskUiState(
                task = TaskModel(
                    id = "21",
                    name = "Task name",
                    description = "asd",
                    createdAt = 1000,
                    isCompleted = false,
                    deadline = null,
                    completedAt = null,
                ),
                isLoading = false
            ),
            TaskUiState(
                task = TaskModel(
                    id = "31",
                    name = "Long long long long task name",
                    description = "asd",
                    createdAt = 1000,
                    isCompleted = true,
                    deadline = null,
                    completedAt = null,
                ),
                isLoading = false
            ),
            TaskUiState(
                task = TaskModel(
                    id = "41",
                    name = "Long long long long task name Long long long long task name Long long long long task name Long long long long task name",
                    description = "asd",
                    createdAt = 1000,
                    isCompleted = true,
                    deadline = null,
                    completedAt = null,
                ),
                isLoading = false
            )
        ),
        isRefreshing = false
    )

    TaskTrackerTheme {
        TasksListComponent(
            state = state,
            snackbarHostState = SnackbarHostState(),
            onEvent = {},
            onAddTaskClicked = {},
            addTaskBuilder = {},
        )
    }
}
