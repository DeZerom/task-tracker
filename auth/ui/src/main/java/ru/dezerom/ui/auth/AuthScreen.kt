package ru.dezerom.ui.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.launch
import ru.dezerom.core.tools.R
import ru.dezerom.core.tools.consts.Colors
import ru.dezerom.core.tools.consts.Dimens
import ru.dezerom.core.tools.string_container.getString
import ru.dezerom.core.ui.kit.buttons.WhiteButton
import ru.dezerom.core.ui.kit.snackbars.KitSnackbarHost
import ru.dezerom.core.ui.kit.text_input.PasswordInput
import ru.dezerom.core.ui.kit.text_input.TextInput
import ru.dezerom.core.ui.kit.text_style.TS
import ru.dezerom.core.ui.kit.theme.TaskTrackerTheme
import ru.dezerom.core.ui.kit.widgets.VSpacer
import ru.dezerom.navigation.api.destinations.RegistrationDestination
import ru.dezerom.navigation.api.tools.LocalNavController

@Composable
fun AuthScreen() {
    val viewModel: AuthViewModel = hiltViewModel()
    val navController = LocalNavController.current

    LaunchedEffect(viewModel.sideEffect) {
        launch {
            viewModel.sideEffect.collect { sideEffect ->
                when (sideEffect) {
                    AuthScreenSideEffect.GoToRegistration ->
                        navController.navigate(RegistrationDestination)
                }
            }
        }
    }

    val state = viewModel.state.collectAsState()

    AuthScreenContent(
        onEvent = viewModel::onEvent,
        state = state.value,
        viewModel.snackbarHostState,
    )
}

@Composable
internal fun AuthScreenContent(
    onEvent: (AuthScreenEvent) -> Unit,
    state: AuthScreenState,
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
                modifier = Modifier.fillMaxWidth()
            )
            VSpacer(height = Dimens.Padding.Medium)
            PasswordInput(
                value = state.password,
                labelText = stringResource(id = R.string.password),
                isError = state.passwordError != null,
                error = state.passwordError?.getString(),
                onValueChanged = { onEvent(AuthScreenEvent.PasswordChanged(it)) },
                modifier = Modifier.fillMaxWidth()
            )
            VSpacer(height = Dimens.Padding.Small)
            Text(
                text = stringResource(id = R.string.create_account),
                color = Colors.secondaryText,
                style = TS.bodySmall,
                modifier = Modifier
                    .align(Alignment.Start)
                    .clickable { onEvent(AuthScreenEvent.OnCreateAccClicked) }
            )
            Spacer(modifier = Modifier.weight(1f))
            WhiteButton(
                onClick = { onEvent(AuthScreenEvent.OnAuthorizeClicked) },
                text = stringResource(id = R.string.authorize),
                isLoading = state.isLoading,
                modifier = Modifier.fillMaxWidth(),
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
            snackbarHostState = SnackbarHostState()
        )
    }
}