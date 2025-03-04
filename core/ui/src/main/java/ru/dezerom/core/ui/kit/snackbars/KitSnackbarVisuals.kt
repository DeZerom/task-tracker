package ru.dezerom.core.ui.kit.snackbars

import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarVisuals

sealed class KitSnackbarVisuals: SnackbarVisuals {

    class Success(
        override val actionLabel: String?,
        override val duration: SnackbarDuration,
        override val message: String,
        override val withDismissAction: Boolean
    ) : KitSnackbarVisuals()

    class Error(
        override val actionLabel: String?,
        override val duration: SnackbarDuration,
        override val message: String,
        override val withDismissAction: Boolean
    ) : KitSnackbarVisuals()

}
