package ru.dezerom.core.ui.kit.snackbars

import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun KitSnackbarHost(
    hostState: SnackbarHostState,
    modifier: Modifier = Modifier,
) {
    SnackbarHost(
        hostState = hostState,
        snackbar = { data ->
            when (data.visuals) {
                is KitSnackbarVisuals.Success -> SuccessSnackbar(data.visuals.message)
                is KitSnackbarVisuals.Error -> ErrorSnackbar(data.visuals.message)
                else -> Snackbar(data)
            }
        },
        modifier = modifier,
    )
}
