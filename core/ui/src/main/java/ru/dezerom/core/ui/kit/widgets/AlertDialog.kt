package ru.dezerom.core.ui.kit.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import ru.dezerom.core.tools.R
import ru.dezerom.core.tools.consts.Colors
import ru.dezerom.core.tools.consts.Dimens
import ru.dezerom.core.ui.kit.text_style.TS
import ru.dezerom.core.ui.kit.theme.TaskTrackerTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DefaultAlertDialog(
    onDismiss: () -> Unit,
    title: String,
    message: String,
    onPositiveButtonClick: () -> Unit,
    onNegativeButtonClick: () -> Unit = onDismiss,
    positiveButtonText: String = stringResource(R.string.yes),
    negativeButtonText: String = stringResource(R.string.cancel)
) {
    BasicAlertDialog(
        onDismissRequest = onDismiss
    ) {
        Surface(
            shape = RoundedCornerShape(Dimens.CornerRadius.Default)
        ) {
            Column(
                modifier = Modifier.padding(Dimens.Padding.Medium)
            ) {
                Text(
                    text = title,
                    style = TS.titleLarge
                )
                VSpacer(Dimens.Padding.Small)
                Text(
                    text = message,
                    style = TS.bodyMedium.copy(color = Colors.secondaryText)
                )
                Row(
                    modifier = Modifier.align(Alignment.End)
                ) {
                    TextButton(
                        onClick = onNegativeButtonClick
                    ) {
                        Text(
                            text = negativeButtonText,
                            style = TS.bodyMedium
                        )
                    }
                    TextButton(
                        onClick = onPositiveButtonClick
                    ) {
                        Text(
                            text = positiveButtonText,
                            style = TS.bodyMedium.copy(color = Colors.accent)
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun Preview() {
    TaskTrackerTheme {
        Box(
            modifier = Modifier.background(Colors.white)
        ) {
            DefaultAlertDialog(
                onDismiss = {},
                title = "Вы уверены?",
                message = "Задача \"Забрать посылку\" будет удалена. Это невозможно отменить",
                onPositiveButtonClick = {},
                onNegativeButtonClick = {}
            )
        }
    }
}