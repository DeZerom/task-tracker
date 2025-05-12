package ru.dezerom.core.ui.kit.buttons

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.vector.ImageVector
import ru.dezerom.core.tools.consts.Colors
import ru.dezerom.core.tools.consts.Dimens
import ru.dezerom.core.ui.kit.text_style.TS
import ru.dezerom.core.ui.kit.widgets.HSpacer

@Composable
fun AccentExpandableFAB(
    onClick: () -> Unit,
    icon: ImageVector,
    expandedText: String,
    isExpanded: Boolean = false
) {
    FloatingActionButton(
        containerColor = Colors.accent,
        onClick = onClick
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            if (isExpanded) {
                HSpacer(Dimens.Padding.Medium)
            }
            Icon(
                icon,
                contentDescription = null,
            )
            AnimatedVisibility(isExpanded) {
                Row {
                    HSpacer(Dimens.Padding.VerySmall)
                    Text(
                        text = expandedText,
                        style = TS.bodyMedium
                    )
                    HSpacer(Dimens.Padding.Medium)
                }

            }
        }
    }
}
