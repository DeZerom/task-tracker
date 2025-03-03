package ru.dezerom.ui.auth

import androidx.compose.runtime.Immutable
import ru.dezerom.core.tools.string_container.StringContainer

@Immutable
internal data class AuthScreenState(
    val isLoading: Boolean = false,
    val login: String = "",
    val password: String = "",
    val loginError: StringContainer? = null,
    val passwordError: StringContainer? = null,
)
