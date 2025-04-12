package ru.dezerom.tasks.ui.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Warning
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import ru.dezerom.core.tools.R
import ru.dezerom.core.tools.consts.Colors
import ru.dezerom.core.tools.consts.Dimens
import ru.dezerom.core.tools.string_container.StringContainer
import ru.dezerom.core.tools.string_container.getString
import ru.dezerom.core.tools.string_container.toStringContainer
import ru.dezerom.core.ui.kit.buttons.WhiteButton
import ru.dezerom.core.ui.kit.snackbars.KitSnackbarHost
import ru.dezerom.core.ui.kit.text_style.TS
import ru.dezerom.core.ui.kit.theme.TaskTrackerTheme
import ru.dezerom.core.ui.kit.widgets.VSpacer
import ru.dezerom.core.ui.test_tools.TestTags

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
                is TasksListState.Error ->
                    ErrorComponent(state.error) { onEvent(TasksListEvent.OnTryAgainClicked) }
                is TasksListState.Loaded -> TODO()
                TasksListState.Loading -> TODO()
            }
        }
    }
}

@Composable
internal fun TasksListContent() {

}

@Composable
private fun ErrorComponent(err: StringContainer, onTryAgain: () -> Unit) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(Dimens.Padding.Medium)
    ) {
        Icon(
            imageVector = Icons.Outlined.Warning,
            contentDescription = null,
            tint = Colors.darkSurface,
            modifier = Modifier
                .size(Dimens.Sizes.IconExtraBig)
                .testTag(TestTags.Image.WARNING_ICON)
        )
        VSpacer(Dimens.Padding.Medium)
        Text(
            text = stringResource(R.string.error_while_loading),
            style = TS.titleLarge
        )
        VSpacer(Dimens.Padding.Small)
        Text(
            text = err.getString(),
            style = TS.bodySmall.copy(textAlign = TextAlign.Center)
        )
        VSpacer(Dimens.Padding.MediumPlus)
        WhiteButton(
            text = stringResource(R.string.try_again),
            onClick = onTryAgain,
            modifier = Modifier.fillMaxWidth(),
            testTag = TestTags.Button.TRY_AGAIN_BUTTON
        )
    }
}

@Composable
@Preview
private fun Preview() {
    TaskTrackerTheme {
        TasksListComponent(
            state = TasksListState.Error("Some error".repeat(10).toStringContainer()),
            snackbarHostState = SnackbarHostState(),
            onEvent = {}
        )
    }
}
