package ru.dezerom.core.ui.tools

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import ru.dezerom.core.tools.string_container.StringContainer
import ru.dezerom.core.ui.kit.snackbars.showError
import ru.dezerom.core.ui.kit.snackbars.showSuccess

object ScaffoldStateHolder {
    private val _state = MutableStateFlow(ScaffoldState())
    val state = _state.asStateFlow()

    @Composable
    fun SetTopBar(topBar: @Composable () -> Unit) {
        LaunchedEffect(topBar) {
            _state.value = state.value.copy(topBar = topBar)
        }
    }

    @Composable
    fun SetFab(fab: @Composable () -> Unit) {
        LaunchedEffect(fab) {
            _state.value = state.value.copy(fab = fab)
        }
    }

    @Composable
    fun ShowBottomNavBar(show: Boolean) {
        LaunchedEffect(show) {
            _state.value = state.value.copy(showBottomNavBar = show)
        }
    }

    suspend fun showError(throwable: Throwable) {
        state.value.snackbarHostState.showError(throwable)
    }

    suspend fun showError(err: StringContainer) {
        state.value.snackbarHostState.showError(err)
    }

    suspend fun showSuccess(message: StringContainer) {
        state.value.snackbarHostState.showSuccess(message)
    }
}

data class ScaffoldState(
    val topBar: @Composable () -> Unit = {},
    val fab: @Composable () -> Unit = {},
    val snackbarHostState: SnackbarHostState = SnackbarHostState(),
    val showBottomNavBar: Boolean = true
)
