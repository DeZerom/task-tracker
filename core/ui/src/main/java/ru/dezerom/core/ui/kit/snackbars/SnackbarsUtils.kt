package ru.dezerom.core.ui.kit.snackbars

import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import ru.dezerom.core.tools.errors.NetworkError
import ru.dezerom.core.tools.string_container.StringContainer
import ru.dezerom.core.tools.string_container.toStringContainer

suspend fun SnackbarHostState.showSuccess(container: StringContainer) = showSnackbar(
    KitSnackbarVisuals.Success(
        messageContainer = container,
        message = "",
        actionLabel = "",
        withDismissAction = false,
        duration = SnackbarDuration.Short,
    )
)

suspend fun SnackbarHostState.showError(container: StringContainer) = showSnackbar(
    KitSnackbarVisuals.Error(
        messageContainer = container,
        message = "",
        actionLabel = "",
        withDismissAction = false,
        duration = SnackbarDuration.Short
    )
)

suspend fun SnackbarHostState.showError(e: Throwable) = showError(
    container = if (e is NetworkError) e.messageRes else (e.message ?: "").toStringContainer()
)
