package ru.dezerom.core.ui.tools

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import ru.dezerom.core.ui.kit.theme.TaskTrackerTheme

@Composable
fun PreviewWrapper(content: @Composable () -> Unit) {
    TaskTrackerTheme {
        val state by ScaffoldStateHolder.state.collectAsState()

        Scaffold(
            topBar = state.topBar,
            floatingActionButton = state.fab
        ) {
            Box(modifier = Modifier.padding(it)) {
                content()
            }
        }
    }
}