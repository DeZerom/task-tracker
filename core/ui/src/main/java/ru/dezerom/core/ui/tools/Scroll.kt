package ru.dezerom.core.ui.tools

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember

@Composable
fun isScrolledToBottom(listState: LazyListState): Boolean {
    val isAtBottom = remember { derivedStateOf { !listState.canScrollForward } }

    return isAtBottom.value
}
