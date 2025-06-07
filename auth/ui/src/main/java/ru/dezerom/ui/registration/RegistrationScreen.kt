package ru.dezerom.ui.registration

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import ru.dezerom.core.tools.R
import ru.dezerom.core.tools.consts.Dimens
import ru.dezerom.core.tools.string_container.getString
import ru.dezerom.core.ui.kit.buttons.WhiteButton
import ru.dezerom.core.ui.kit.snackbars.KitSnackbarHost
import ru.dezerom.core.ui.kit.text_input.PasswordInput
import ru.dezerom.core.ui.kit.text_input.TextInput
import ru.dezerom.core.ui.kit.text_style.TS
import ru.dezerom.core.ui.kit.theme.TaskTrackerTheme
import ru.dezerom.core.ui.kit.widgets.VSpacer
import ru.dezerom.core.ui.test_tools.TestTags
import ru.dezerom.core.ui.tools.ProcessSideEffects
import ru.dezerom.navigation.api.navigators.AuthNavigator

@Composable
fun RegistrationScreen(
    navigator: AuthNavigator
) {
    val viewModel: RegistrationScreenViewModel = hiltViewModel()

    ProcessSideEffects(viewModel.sideEffects) {
        when (it) {
            RegistrationScreenSideEffect.GoBack -> navigator.fromRegistrationToAuth()
        }
    }

    val state = viewModel.state.collectAsState()

    RegistrationContent(
        state = state.value,
        onEvent = viewModel::onEvent,
        snackbarHostState = viewModel.snackbarHostState
    )
}

@Composable
internal fun RegistrationContent(
    state: RegistrationScreenState,
    onEvent: (RegistrationScreenEvent) -> Unit,
    snackbarHostState: SnackbarHostState,
) {
    Scaffold(
        snackbarHost = { KitSnackbarHost(snackbarHostState) }
    ) { innerPadding ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(innerPadding)
                .imePadding()
                .fillMaxSize()
                .padding(all = Dimens.Padding.Medium)
        ) {
            VSpacer(height = Dimens.Padding.XXXBig)
            Image(
                painter = painterResource(id = R.drawable.app_icon),
                contentDescription = null,
                modifier = Modifier.testTag(TestTags.Image.APP_ICON),
            )
            VSpacer(height = Dimens.Padding.MediumPlus)
            Text(
                stringResource(id = R.string.registration),
                style = TS.titleLarge
            )
            VSpacer(height = Dimens.Padding.Big)
            TextInput(
                value = state.login,
                labelText = stringResource(id = R.string.login),
                isError = state.loginError != null,
                error = state.loginError?.getString(),
                onValueChanged = { onEvent(RegistrationScreenEvent.LoginChanged(it)) },
                modifier = Modifier.fillMaxWidth(),
                testTag = TestTags.TextInput.LOGIN,
            )
            VSpacer(height = Dimens.Padding.Medium)
            PasswordInput(
                value = state.password,
                labelText = stringResource(id = R.string.password),
                isError = state.passwordError != null,
                error = state.passwordError?.getString(),
                onValueChanged = { onEvent(RegistrationScreenEvent.PasswordChanged(it)) },
                modifier = Modifier.fillMaxWidth(),
                testTag = TestTags.TextInput.PASSWORD,
            )
            Spacer(modifier = Modifier.weight(1f))
            WhiteButton(
                onClick = { onEvent(RegistrationScreenEvent.OnRegisterClicked) },
                text = stringResource(id = R.string.register),
                isLoading = state.isLoading,
                modifier = Modifier.fillMaxWidth(),
                testTag = TestTags.Button.AUTH_BUTTON,
            )
        }
    }
}

@Composable
@Preview
private fun RegistrationPreview() {
    TaskTrackerTheme {
        RegistrationContent(
            state = RegistrationScreenState(),
            snackbarHostState = SnackbarHostState(),
            onEvent = {}
        )
    }
}
