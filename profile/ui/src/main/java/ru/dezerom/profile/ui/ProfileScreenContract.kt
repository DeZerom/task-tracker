package ru.dezerom.profile.ui

import ru.dezerom.core.tools.string_container.StringContainer
import ru.dezerom.profile.domain.models.UserModel

class ProfileScreenContract {
    sealed class State {
        data object Loading: State()
        class Error(val error: StringContainer): State()
        data class Loaded(
            val user: UserModel = UserModel.default(),
            val logoutLoading: Boolean = false
        ): State()
    }

    sealed class Event {
        data object Logout: Event()
        data object TryAgain: Event()
    }
}