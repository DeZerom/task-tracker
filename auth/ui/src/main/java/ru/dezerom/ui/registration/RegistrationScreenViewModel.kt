package ru.dezerom.ui.registration

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import ru.dezerom.auth.domain.interactor.AuthInteractor
import ru.dezerom.core.tools.R
import ru.dezerom.core.tools.string_container.toStringContainer
import ru.dezerom.core.ui.kit.snackbars.showError
import ru.dezerom.core.ui.view_models.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class RegistrationScreenViewModel @Inject constructor(
    private val authInteractor: AuthInteractor
): BaseViewModel() {
    private val _state = MutableStateFlow(RegistrationScreenState())
    val state = _state.asStateFlow()

    private val _sideEffects = Channel<RegistrationScreenSideEffect>(Channel.BUFFERED)
    val sideEffects = _sideEffects.receiveAsFlow()

    fun onEvent(event: RegistrationScreenEvent) {
        when (event) {
            is RegistrationScreenEvent.LoginChanged -> onLoginChanged(event.newLogin)
            is RegistrationScreenEvent.PasswordChanged -> onPasswordChanged(event.newPassword)
            RegistrationScreenEvent.OnRegisterClicked -> onRegisterClicked()
        }
    }

    private fun onLoginChanged(newLogin: String) {
        _state.value = state.value.copy(
            login = newLogin,
            loginError = if (newLogin.isBlank())
                R.string.field_must_not_be_empty.toStringContainer()
            else
                null
        )
    }

    private fun onPasswordChanged(newPassword: String) {
        _state.value = state.value.copy(
            password = newPassword,
            passwordError = if (newPassword.isBlank())
                R.string.field_must_not_be_empty.toStringContainer()
            else
                null
        )
    }

    private fun onRegisterClicked() = viewModelScope.launch {
        if (!validateData()) return@launch

        val s = state.value

        _state.value = s.copy(isLoading = true)

        authInteractor.register(s.login, s.password).fold(
            onSuccess = {
                if (it) {
                    showSuccess(R.string.success_reg.toStringContainer())
                    delay(500)
                    _sideEffects.send(RegistrationScreenSideEffect.GoBack)
                } else {
                    showError(R.string.unknown_error.toStringContainer())
                }
            },
            onFailure = { snackbarHostState.showError(it) }
        )

        _state.value = s.copy(isLoading = false)
    }

    private fun validateData(): Boolean {
        var s = state.value
        var res = true

        if (s.login.isEmpty()) {
            res = false
            s = s.copy(loginError = R.string.field_must_not_be_empty.toStringContainer())
        }

        if (s.password.isEmpty()) {
            res = false
            s = s.copy(passwordError = R.string.field_must_not_be_empty.toStringContainer())
        }

        if (!res) _state.value = s

        return res
    }
}

