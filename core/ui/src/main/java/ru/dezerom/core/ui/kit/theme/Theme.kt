package ru.dezerom.core.ui.kit.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import ru.dezerom.core.ui.kit.text_style.TS

@Composable
fun TaskTrackerTheme(
    content: @Composable () -> Unit
) {
    val colorScheme = darkColorScheme(
        background = ru.dezerom.core.tools.consts.Colors.background
    )

    MaterialTheme(
        colorScheme = colorScheme,
        typography = TS,
        content = content
    )
}