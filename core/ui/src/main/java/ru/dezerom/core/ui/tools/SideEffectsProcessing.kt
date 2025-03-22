package ru.dezerom.core.ui.tools

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

@Composable
fun <T> ProcessSideEffects(sideEffects: Flow<T>, processor: (T) -> Unit) {
    LaunchedEffect(sideEffects) {
        launch {
            sideEffects.collect { sideEffect -> processor(sideEffect) }
        }
    }
}
