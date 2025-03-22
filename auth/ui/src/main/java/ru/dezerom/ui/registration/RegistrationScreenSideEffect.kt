package ru.dezerom.ui.registration

sealed class RegistrationScreenSideEffect {
    data object GoBack: RegistrationScreenSideEffect()
}
