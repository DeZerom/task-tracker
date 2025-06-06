package ru.dezerom.core.tools.string_container

import androidx.compose.runtime.Composable

@Composable
fun StringContainer.getString(): String {
    return stringResource(this)
}

@Composable
private fun stringResource(container: StringContainer): String {
    return when (container) {
        is StringContainer.RawString -> container.str
        is StringContainer.StringRes -> androidx.compose.ui.res.stringResource(container.res)
    }
}

fun String.toStringContainer() = StringContainer.RawString(this)

fun Int.toStringContainer() = StringContainer.StringRes(this)
