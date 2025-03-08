package ru.dezerom.core.ui.view_models

import androidx.compose.material3.SnackbarHostState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import ru.dezerom.core.tools.string_container.StringContainer
import ru.dezerom.core.ui.kit.snackbars.showError
import ru.dezerom.core.ui.kit.snackbars.showSuccess

open class BaseViewModel: ViewModel() {
    val snackbarHostState = SnackbarHostState()

    private val snackbarScope =
        CoroutineScope(SupervisorJob() + viewModelScope.coroutineContext)

    protected fun showSuccess(message: StringContainer) = snackbarScope.launch {
        snackbarHostState.showSuccess(message)
    }

    protected fun showError(message: StringContainer) = snackbarScope.launch {
        snackbarHostState.showError(message)
    }

    protected fun showError(throwable: Throwable) = snackbarScope.launch {
        snackbarHostState.showError(throwable)
    }
}
