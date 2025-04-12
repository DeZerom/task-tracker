package ru.dezerom.core.ui.kit.widgets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import ru.dezerom.core.tools.consts.Colors
import ru.dezerom.core.tools.consts.Dimens
import ru.dezerom.core.ui.kit.text_style.TS
import ru.dezerom.core.ui.test_tools.TestTags

@Composable
fun EmptyListComponent(
    title: String
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Icon(
            imageVector = Icons.Filled.Info,
            contentDescription = null,
            tint = Colors.accent,
            modifier = Modifier
                .size(Dimens.Sizes.IconExtraBig)
                .testTag(TestTags.Image.WARNING_ICON)
        )
        VSpacer(Dimens.Padding.Medium)
        Text(
            text = title,
            style = TS.bodyLarge
        )
    }
}
