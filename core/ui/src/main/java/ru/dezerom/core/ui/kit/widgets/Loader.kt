package ru.dezerom.core.ui.kit.widgets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import ru.dezerom.core.tools.R
import ru.dezerom.core.tools.consts.Colors
import ru.dezerom.core.tools.consts.Dimens
import ru.dezerom.core.ui.kit.text_style.TS
import ru.dezerom.core.ui.test_tools.TestTags

@Composable
fun DefaultLoaderComponent() {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(Dimens.Padding.Medium)
            .testTag(TestTags.Components.LOADER_COMPONENT)
    ) {
        Loader()
        VSpacer(Dimens.Padding.Small)
        Text(
            text = stringResource(R.string.loading),
            style = TS.bodyLarge
        )
    }
}

@Composable
fun Loader(
    size: Dp = Dimens.Sizes.LoaderSize,
    color: Color = Colors.white,
) {
    CircularProgressIndicator(
        color = color,
        modifier = Modifier
            .size(size)
            .testTag(TestTags.Objects.LOADER)
    )
}