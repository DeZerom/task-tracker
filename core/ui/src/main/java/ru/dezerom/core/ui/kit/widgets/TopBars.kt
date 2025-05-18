package ru.dezerom.core.ui.kit.widgets

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import ru.dezerom.core.tools.consts.Colors
import ru.dezerom.core.ui.kit.text_style.TS

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

@Composable
fun BottomSheetTopBar(
    title: String
) {
    Text(
        text = title,
        style = TS.headlineLarge,
        textAlign = TextAlign.Center,
        modifier = Modifier.fillMaxWidth(),
    )
}
