package ru.dezerom.splash.ui

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import ru.dezerom.auth.domain.interactor.AuthInteractor
import ru.dezerom.core.ui.view_models.BaseViewModel
import ru.dezerom.splash.ui.SplashScreenContract.SideEffect
import javax.inject.Inject

@HiltViewModel
internal class SplashScreenViewModel @Inject constructor(
    private val authInteractor: AuthInteractor
): BaseViewModel() {
    private val _sideEffect = Channel<SideEffect>(Channel.BUFFERED)
    val sideEffect = _sideEffect.receiveAsFlow()

    init {
        initialize()
    }

    private fun initialize() = viewModelScope.launch {
        val isAuthorized = authInteractor.isAuthorized()

        _sideEffect.send(
            if (isAuthorized) SideEffect.GoToTasks
            else SideEffect.GoToAuth
        )
    }
}