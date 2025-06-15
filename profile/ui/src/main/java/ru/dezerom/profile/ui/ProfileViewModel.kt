package ru.dezerom.profile.ui

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.dezerom.core.tools.errors.NetworkError
import ru.dezerom.core.tools.errors.unknownNetworkError
import ru.dezerom.core.ui.view_models.BaseViewModel
import ru.dezerom.profile.domain.interactor.ProfileInteractor
import ru.dezerom.profile.ui.ProfileScreenContract.Event
import ru.dezerom.profile.ui.ProfileScreenContract.State
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val profileInteractor: ProfileInteractor
): BaseViewModel() {
    private val _state = MutableStateFlow<State>(State.Loading)
    val state = _state.asStateFlow()

    init {
        initialize()
    }

    fun onEvent(event: Event) {
        when (event) {
            Event.Logout -> TODO()
            Event.TryAgain -> TODO()
        }
    }

    private fun initialize() = viewModelScope.launch {
        profileInteractor.getMe().fold(
            onSuccess = {
                _state.value = State.Loaded(it)
            },
            onFailure = {
                if (it is NetworkError) {
                    _state.value = State.Error(it.messageRes)
                } else {
                    _state.value = State.Error(unknownNetworkError().messageRes)
                }
            }
        )
    }
}