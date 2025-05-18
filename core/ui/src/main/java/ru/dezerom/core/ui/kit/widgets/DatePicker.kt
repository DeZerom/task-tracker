package ru.dezerom.core.ui.kit.widgets

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import ru.dezerom.core.tools.R
import ru.dezerom.core.tools.consts.Colors
import ru.dezerom.core.tools.consts.Dimens
import ru.dezerom.core.tools.extensions.toYearMonthDay
import ru.dezerom.core.ui.kit.text_style.TS
import ru.dezerom.core.ui.kit.theme.TaskTrackerTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerWidget(
    value: Long?,
    onValueChanged: (Long?) -> Unit,
    headingText: String,
) {
    var showPicker by remember { mutableStateOf(false) }
    val pickerState = rememberDatePickerState(value)

    if (showPicker) {
        DatePickerDialog(
            onDismissRequest = { showPicker = false },
            confirmButton = {
                TextButton(
                    onClick = {
                        onValueChanged(pickerState.selectedDateMillis)
                        showPicker = false
                    }
                ) {
                    Text(
                        text = stringResource(R.string.ok),
                        style = TS.bodyMedium.copy(color = Colors.accent)
                    )
                }
            }
        ) {
            DatePicker(
                state = pickerState,
                colors = DatePickerDefaults.colors(
                    todayDateBorderColor = Colors.accent,
                    selectedDayContainerColor = Colors.accent,
                    selectedYearContainerColor = Colors.accent,
                )
            )
        }
    }

    if (value != null)
        HasDate(
            headingText = headingText,
            value = value,
            onChooseClicked = { showPicker = true },
            onClear = {
                pickerState.selectedDateMillis = null
                onValueChanged(null)
            }
        )
    else
        NoDate(
            onChooseClicked = {
                showPicker = true
            }
        )
}

@Composable
private fun HasDate(
    headingText: String,
    value: Long,
    onChooseClicked: () -> Unit,
    onClear: () -> Unit,
) {

    val formattedDate = remember(value) { value.toYearMonthDay() }

    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "$headingText:",
            style = TS.bodyMedium
        )
        HSpacer(Dimens.Padding.Small)
        Text(
            text = formattedDate,
            style = TS.bodyMedium
        )
        HSpacer(Dimens.Padding.Small)
        Text(
            text = stringResource(R.string.change),
            style = TS.bodyMedium.copy(color = Colors.accent),
            modifier = Modifier.clickable { onChooseClicked() }
        )
        HSpacer(Dimens.Padding.Small)
        Text(
            text = stringResource(R.string.clear),
            style = TS.bodyMedium.copy(color = Colors.secondaryText),
            modifier = Modifier.clickable { onClear() }
        )

    }
}

@Composable
private fun NoDate(
    onChooseClicked: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.clickable { onChooseClicked() }
    ) {
        Icon(
            Icons.Default.Add,
            contentDescription = stringResource(R.string.choose_date),
            tint = Colors.accent,
            modifier = Modifier.size(Dimens.Sizes.IconVerySmall)
        )
        HSpacer(Dimens.Padding.Small)
        Text(
            text = stringResource(R.string.choose_date),
            style = TS.bodyMedium.copy(color = Colors.accent)
        )
    }
}

@Preview
@Composable
private fun DatePickerPreview() {
    TaskTrackerTheme {
        DatePickerWidget(
            value = null,
            onValueChanged = {},
            headingText = stringResource(R.string.deadline)
        )
    }
}
