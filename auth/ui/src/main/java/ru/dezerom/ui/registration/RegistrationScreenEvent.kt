package ru.dezerom.ui.registration

sealed class RegistrationScreenEvent {
    class LoginChanged(val newLogin: String): RegistrationScreenEvent()

    class PasswordChanged(val newPassword: String): RegistrationScreenEvent()

    object OnRegisterClicked: RegistrationScreenEvent()
}
