package ru.dezerom.core.ui.kit.snackbars

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import ru.dezerom.core.tools.consts.Colors
import ru.dezerom.core.tools.consts.Dimens
import ru.dezerom.core.tools.string_container.StringContainer
import ru.dezerom.core.tools.string_container.getString
import ru.dezerom.core.ui.kit.text_style.TS
import ru.dezerom.core.ui.test_tools.TestTags

@Composable
fun BaseSnackbar(
    icon: ImageVector,
    messageContainer: StringContainer,
    bgColor: Color,
    testTag: String = "",
) {
    Card(
        colors = CardDefaults.cardColors().copy(containerColor = bgColor),
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = Dimens.Padding.Big)
            .padding(horizontal = Dimens.Padding.Medium)
            .imePadding()
            .testTag(testTag)
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(Dimens.Padding.Small),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(Dimens.Padding.Medium)
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = Colors.white,
            )
            Text(
                text = messageContainer.getString(),
                style = TS.bodyMedium,
                modifier = Modifier
                    .testTag("$testTag${TestTags.Snackbar.SNACKBAR_TEXT_POSTFIX}")
            )
        }
    }
}

@Composable
fun SuccessSnackbar(
    messageContainer: StringContainer
) {
    BaseSnackbar(
        icon = Icons.Outlined.CheckCircle,
        messageContainer = messageContainer,
        bgColor = Colors.success,
        testTag = TestTags.Snackbar.SUCCESS_SNACKBAR,
    )
}

@Composable
fun ErrorSnackbar(
    messageContainer: StringContainer
) {
    BaseSnackbar(
        icon = Icons.Outlined.Close,
        messageContainer = messageContainer,
        bgColor = Colors.error,
        testTag = TestTags.Snackbar.ERROR_SNACKBAR,
    )
}
