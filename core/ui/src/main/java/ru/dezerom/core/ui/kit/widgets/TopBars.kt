package ru.dezerom.core.ui.kit.widgets

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import ru.dezerom.core.tools.consts.Colors
import ru.dezerom.core.ui.kit.text_style.TS
import ru.dezerom.core.ui.kit.theme.TaskTrackerTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopLevelTopBar(
    title: String,
    scrollBehavior: TopAppBarScrollBehavior
) {
    MediumTopAppBar(
        title = {
            Text(
                text = title,
                style = TS.headlineLarge
            )
        },
        colors = TopAppBarDefaults.mediumTopAppBarColors().copy(
            containerColor = Colors.darkSurface,
            scrolledContainerColor = Colors.background
        ),
        scrollBehavior = scrollBehavior
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    title: String,
    onBackPressed: () -> Unit,
    showBack: Boolean = true
) {
    TopAppBar(
        title = {
            Text(
                text = title,
                style = TS.headlineLarge
            )
        }
    )
}

@Preview
@Composable
private fun TopBarPreview() {
    TaskTrackerTheme {
        TopBar(
            title = "Заголовок",
            onBackPressed = {},
            showBack = true
        )
    }
}
