package ru.dezerom.ui.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import ru.dezerom.auth.domain.interactor.AuthInteractor
import javax.inject.Inject

@HiltViewModel
internal class AuthViewModel @Inject constructor(
    private val authInteractor: AuthInteractor,
): ViewModel() {

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
        _state.value = state.value.copy(login = login)
    }

    private fun onPasswordChanged(password: String) {
        _state.value = state.value.copy(password = password)
    }

    private fun onCreateAccountClicked() = viewModelScope.launch {
        _sideEffects.send(AuthScreenSideEffect.GoToRegistration)
    }

    private fun onAuthorizeClicked() {

    }
}
