package ru.dezerom.ui.auth

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import ru.dezerom.auth.domain.interactor.AuthInteractor
import ru.dezerom.core.tools.R
import ru.dezerom.core.tools.string_container.StringContainer
import ru.dezerom.core.tools.string_container.toStringContainer
import ru.dezerom.core.ui.view_models.BaseViewModel
import javax.inject.Inject

@HiltViewModel
internal class AuthViewModel @Inject constructor(
    private val authInteractor: AuthInteractor,
) : BaseViewModel() {

    private val _state = MutableStateFlow(AuthScreenState())
    val state = _state.asStateFlow()

    private val _sideEffects = Channel<AuthScreenSideEffect>(Channel.BUFFERED)
    val sideEffect = _sideEffects.receiveAsFlow()

    fun onEvent(event: AuthScreenEvent) {
        when (event) {
            is AuthScreenEvent.LoginChanged -> onLoginChanged(event.newLogin)
            is AuthScreenEvent.PasswordChanged -> onPasswordChanged(event.newPassword)
            AuthScreenEvent.OnAuthorizeClicked -> onAuthorizeClicked()
            AuthScreenEvent.OnCreateAccClicked -> onCreateAccountClicked()
        }
    }

    private fun onLoginChanged(login: String) {
        _state.value = state.value.copy(
            login = login,
            loginError = if (login.isNotBlank())
                null
            else
                StringContainer.StringRes(R.string.field_must_not_be_empty),
        )
    }

    private fun onPasswordChanged(password: String) {
        _state.value = state.value.copy(
            password = password,
            passwordError = if (password.isNotBlank())
                null
            else
                StringContainer.StringRes(R.string.field_must_not_be_empty),
        )
    }

    private fun onCreateAccountClicked() = viewModelScope.launch {
        _sideEffects.send(AuthScreenSideEffect.GoToRegistration)
    }

    private fun onAuthorizeClicked() = viewModelScope.launch {
        if (state.value.isLoading) return@launch

        if (!validate()) return@launch

        _state.value = state.value.copy(isLoading = true)

        val result = authInteractor.authorize(
            login = state.value.login,
            pass = state.value.password
        )

        result.fold(
            onSuccess = {
                if (it) {
                    _sideEffects.send(AuthScreenSideEffect.GoToTasks)
                } else {
                    showError(R.string.unknown_error.toStringContainer())
                }
            },
            onFailure = {
                showError(it)
            }
        )

        _state.value = state.value.copy(isLoading = false)
    }

    private fun validate(): Boolean {
        val s = state.value
        val res = s.login.isNotBlank() && s.password.isNotBlank()

        if (!res) {
            val error = StringContainer.StringRes(R.string.field_must_not_be_empty)

            _state.value = s.copy(
                loginError = if (s.login.isBlank()) error else null,
                passwordError = if (s.password.isBlank()) error else null
            )
        }

        return res
    }
}
