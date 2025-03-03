package ru.dezerom.core.ui.kit.buttons

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ru.dezerom.core.tools.consts.Colors

@Composable
fun WhiteButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    isLoading: Boolean = false,
) {
    BaseButton(
        onClick = onClick,
        text = text,
        textColor = Colors.darkSurface,
        backgroundColor = Colors.white,
        modifier = modifier,
        isLoading = isLoading
    )
}