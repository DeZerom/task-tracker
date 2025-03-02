package ru.dezerom.ui.auth

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import ru.dezerom.auth.domain.interactor.AuthInteractor
import javax.inject.Inject

@HiltViewModel
internal class AuthViewModel @Inject constructor(
    private val authInteractor: AuthInteractor,
): ViewModel() {

    private val _state = MutableStateFlow(AuthScreenState())
    val state = _state.asStateFlow()

    fun onEvent(event: AuthScreenEvent) {

    }

}
