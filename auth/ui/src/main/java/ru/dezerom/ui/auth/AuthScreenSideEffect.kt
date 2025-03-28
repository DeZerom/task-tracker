package ru.dezerom.ui.auth

sealed class AuthScreenSideEffect {
    data object GoToRegistration: AuthScreenSideEffect()

    data object GoToTasks: AuthScreenSideEffect()
}
