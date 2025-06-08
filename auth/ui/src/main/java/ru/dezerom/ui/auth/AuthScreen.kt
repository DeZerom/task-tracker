package ru.dezerom.ui.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
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
import ru.dezerom.core.tools.consts.Colors
import ru.dezerom.core.tools.consts.Dimens
import ru.dezerom.core.tools.string_container.getString
import ru.dezerom.core.ui.kit.buttons.WhiteButton
import ru.dezerom.core.ui.kit.text_input.PasswordInput
import ru.dezerom.core.ui.kit.text_input.TextInput
import ru.dezerom.core.ui.kit.text_style.TS
import ru.dezerom.core.ui.kit.theme.TaskTrackerTheme
import ru.dezerom.core.ui.kit.widgets.AffectScaffold
import ru.dezerom.core.ui.kit.widgets.VSpacer
import ru.dezerom.core.ui.test_tools.TestTags
import ru.dezerom.core.ui.tools.ProcessSideEffects
import ru.dezerom.navigation.api.navigators.AuthNavigator

@Composable
fun AuthScreen(
    navigator: AuthNavigator
) {
    val viewModel: AuthViewModel = hiltViewModel()

    ProcessSideEffects(viewModel.sideEffect) {
        when (it) {
            AuthScreenSideEffect.GoToRegistration -> navigator.fromAuthToRegistration()
            AuthScreenSideEffect.GoToTasks -> navigator.fromAuthToTasks()
        }
    }

    val state = viewModel.state.collectAsState()

    if (state.value.isInitializing) {
        AuthScreenInit()
    } else {
        AuthScreenContent(
            onEvent = viewModel::onEvent,
            state = state.value,
        )
    }
}

@Composable
private fun AuthScreenInit() {
    Scaffold { innerPadding ->
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Image(
                painter = painterResource(id = R.drawable.app_icon),
                contentDescription = null,
                modifier = Modifier
                    .size(Dimens.Sizes.IconExtraBig)
                    .testTag(TestTags.Image.APP_ICON),
            )
        }
    }
}

@Composable
internal fun AuthScreenContent(
    onEvent: (AuthScreenEvent) -> Unit,
    state: AuthScreenState,
) {
    AffectScaffold(
        showBottomNavBar = false
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
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
                stringResource(id = R.string.authorization),
                style = TS.titleLarge
            )
            VSpacer(height = Dimens.Padding.Big)
            TextInput(
                value = state.login,
                labelText = stringResource(id = R.string.login),
                isError = state.loginError != null,
                error = state.loginError?.getString(),
                onValueChanged = { onEvent(AuthScreenEvent.LoginChanged(it)) },
                modifier = Modifier.fillMaxWidth(),
                testTag = TestTags.TextInput.LOGIN,
            )
            VSpacer(height = Dimens.Padding.Medium)
            PasswordInput(
                value = state.password,
                labelText = stringResource(id = R.string.password),
                isError = state.passwordError != null,
                error = state.passwordError?.getString(),
                onValueChanged = { onEvent(AuthScreenEvent.PasswordChanged(it)) },
                modifier = Modifier.fillMaxWidth(),
                testTag = TestTags.TextInput.PASSWORD,
            )
            VSpacer(height = Dimens.Padding.Small)
            Text(
                text = stringResource(id = R.string.create_account),
                color = Colors.secondaryText,
                style = TS.bodySmall,
                modifier = Modifier
                    .align(Alignment.Start)
                    .clickable { onEvent(AuthScreenEvent.OnCreateAccClicked) }
                    .testTag(TestTags.Button.CREATE_ACC_BUTTON)
            )
            Spacer(modifier = Modifier.weight(1f))
            WhiteButton(
                onClick = { onEvent(AuthScreenEvent.OnAuthorizeClicked) },
                text = stringResource(id = R.string.authorize),
                isLoading = state.isLoading,
                modifier = Modifier.fillMaxWidth(),
                testTag = TestTags.Button.AUTH_BUTTON,
            )
        }
    }
}

@Composable
@Preview
private fun AuthScreenPreview() {
    TaskTrackerTheme {
        AuthScreenContent(
            onEvent = {},
            state = AuthScreenState(),
        )
    }
}