package ru.dezerom.core.ui.kit.text_input

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import ru.dezerom.core.tools.consts.Colors
import ru.dezerom.core.tools.consts.Dimens
import ru.dezerom.core.ui.kit.text_style.TS

@Composable
fun PasswordInput(
    value: String,
    labelText: String,
    onValueChanged: (String) -> Unit,
    modifier: Modifier = Modifier,
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
        shape = RoundedCornerShape(Dimens.CornerRadius.Default),
        label = {
            Text(
                text = labelText,
                style = TS.bodySmall,
                color = Colors.secondaryText,
            )
        },
        visualTransformation = PasswordVisualTransformation('*'),
        modifier = modifier,
    )
}

