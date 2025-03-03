package ru.dezerom.ui.auth

internal sealed class AuthScreenEvent {

    class LoginChanged(val newLogin: String): AuthScreenEvent()

    class PasswordChanged(val newPassword: String): AuthScreenEvent()

    data object OnCreateAccClicked: AuthScreenEvent()

    data object OnAuthorizeClicked: AuthScreenEvent()
}
