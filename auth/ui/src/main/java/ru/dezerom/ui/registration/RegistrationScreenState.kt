package ru.dezerom.ui.registration

import androidx.compose.runtime.Immutable
import ru.dezerom.core.tools.string_container.StringContainer

@Immutable
data class RegistrationScreenState(
    val login: String = "",
    val password: String = "",
    val loginError: StringContainer? = null,
    val passwordError: StringContainer? = null,
    val isLoading: Boolean = false,
)
