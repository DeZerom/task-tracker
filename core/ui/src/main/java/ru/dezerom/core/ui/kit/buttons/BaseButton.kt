package ru.dezerom.core.ui.kit.buttons

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import ru.dezerom.core.tools.consts.Colors
import ru.dezerom.core.tools.consts.Dimens
import ru.dezerom.core.ui.kit.text_style.TS
import ru.dezerom.core.ui.test_tools.TestTags

@Composable
fun BaseButton(
    onClick: () -> Unit,
    text: String,
    backgroundColor: Color,
    modifier: Modifier = Modifier,
    textColor: Color = Colors.white,
    isLoading: Boolean = false,
    testTag: String = "",
) {
    Button(
        onClick = { if (!isLoading) onClick() },
        content = {
            if (isLoading) {
                CircularProgressIndicator(
                    color = Colors.darkSurface,
                    modifier = Modifier
                        .size(Dimens.Sizes.ButtonLoaderSize)
                        .testTag("$testTag${TestTags.Button.BUTTON_LOADER_POSTFIX}")
                )
            } else {
                Text(
                    text = text,
                    style = TS.bodyLarge,
                    color = textColor,
                    modifier = Modifier
                        .testTag("$testTag${TestTags.Button.BUTTON_TEXT_POSTFIX}")
                )
            }
        },
        colors = ButtonDefaults.buttonColors(
            containerColor = backgroundColor
        ),
        shape = RoundedCornerShape(Dimens.CornerRadius.Default),
        modifier = Modifier
            .height(Dimens.Sizes.ButtonHeight)
            .then(modifier)
            .testTag(testTag)
    )
}
