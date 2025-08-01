package ru.dezerom.core.ui.view_models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import ru.dezerom.core.tools.string_container.StringContainer
import ru.dezerom.core.ui.tools.ScaffoldStateHolder

open class BaseViewModel: ViewModel() {
    private val snackbarScope =
        CoroutineScope(SupervisorJob() + viewModelScope.coroutineContext)

    protected fun showSuccess(message: StringContainer) = snackbarScope.launch {
        ScaffoldStateHolder.showSuccess(message)
    }

    protected fun showError(message: StringContainer) = snackbarScope.launch {
        ScaffoldStateHolder.showError(message)
    }

    protected fun showError(throwable: Throwable) = snackbarScope.launch {
        ScaffoldStateHolder.showError(throwable)
    }
}
