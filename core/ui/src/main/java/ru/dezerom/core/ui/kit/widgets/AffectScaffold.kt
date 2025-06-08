package ru.dezerom.core.ui.kit.widgets

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ru.dezerom.core.ui.tools.ScaffoldStateHolder

@Composable
fun AffectScaffold(
    modifier: Modifier = Modifier,
    topBar: @Composable () -> Unit = {},
    fab: @Composable () -> Unit = {},
    showBottomNavBar: Boolean = true,
    content: @Composable () -> Unit
) {
    ScaffoldStateHolder.SetTopBar(topBar)
    ScaffoldStateHolder.SetFab(fab)
    ScaffoldStateHolder.ShowBottomNavBar(showBottomNavBar)

    Box(modifier) {
        content()
    }
}