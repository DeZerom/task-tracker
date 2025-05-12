package ru.dezerom.core.ui.kit.theme

import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import ru.dezerom.core.tools.consts.Colors
import ru.dezerom.core.ui.kit.text_style.TS

@Composable
fun TaskTrackerTheme(
    content: @Composable () -> Unit
) {
    val colorScheme = darkColorScheme(
        background = Colors.background
    )

    MaterialTheme(
        colorScheme = colorScheme,
        typography = TS,
        content = {
            CompositionLocalProvider(
                LocalContentColor provides Colors.white
            ) {
                content()
            }
        }
    )
}