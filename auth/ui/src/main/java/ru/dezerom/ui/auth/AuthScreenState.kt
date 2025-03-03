package ru.dezerom.ui.auth

internal data class AuthScreenState(
    val isLoading: Boolean = false,
    val login: String = "",
    val password: String = "",
)
