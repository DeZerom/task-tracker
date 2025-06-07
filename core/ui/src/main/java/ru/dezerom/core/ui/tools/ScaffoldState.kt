package ru.dezerom.core.ui.tools

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

object ScaffoldState {
    private val _topBar = MutableStateFlow<@Composable () -> Unit> {}
    val topBar = _topBar.asStateFlow()

    @Composable
    fun SetTopBar(topBar: @Composable () -> Unit) {
        LaunchedEffect(topBar) {
            _topBar.value = topBar
        }
    }
}
