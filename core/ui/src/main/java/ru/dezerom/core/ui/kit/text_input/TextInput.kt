package ru.dezerom.core.ui.kit.text_input

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.dezerom.core.ui.kit.consts.Colors
import ru.dezerom.core.ui.kit.text_style.TS

@Composable
fun TextInput(
    value: String,
    labelText: String,
    onValueChanged: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChanged,
        textStyle = TS.bodyMedium,
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Colors.darkSurface,
            unfocusedContainerColor = Colors.darkSurface,
            cursorColor = Colors.accent,
            focusedIndicatorColor = Colors.accent,
            unfocusedIndicatorColor = Colors.darkSurface,
        ),
        shape = RoundedCornerShape(8.dp),
        label = {
            Text(
                text = labelText,
                style = TS.bodySmall,
                color = Colors.secondaryText,
            )
        },
        modifier = modifier,
    )
}
