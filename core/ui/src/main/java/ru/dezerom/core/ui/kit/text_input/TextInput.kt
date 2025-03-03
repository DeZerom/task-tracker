package ru.dezerom.core.ui.kit.text_input

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.VisualTransformation
import ru.dezerom.core.tools.consts.Colors
import ru.dezerom.core.tools.consts.Dimens
import ru.dezerom.core.ui.kit.text_style.TS

@Composable
fun TextInput(
    value: String,
    labelText: String,
    onValueChanged: (String) -> Unit,
    modifier: Modifier = Modifier,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    isError: Boolean = false,
    error: String? = null,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(Dimens.Padding.VerySmall)
    ) {
        OutlinedTextField(
            value = value,
            onValueChange = onValueChanged,
            textStyle = TS.bodyMedium,
            isError = isError,
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Colors.darkSurface,
                unfocusedContainerColor = Colors.darkSurface,
                cursorColor = Colors.accent,
                focusedIndicatorColor = Colors.accent,
                unfocusedIndicatorColor = Colors.darkSurface,
                errorContainerColor = Colors.darkSurface,
                errorIndicatorColor = Colors.error,
            ),
            shape = RoundedCornerShape(Dimens.CornerRadius.Default),
            label = {
                Text(
                    text = labelText,
                    style = TS.bodySmall,
                    color = Colors.secondaryText,
                )
            },
            visualTransformation = visualTransformation,
            modifier = modifier,
        )
        if (isError && !error.isNullOrBlank()) {
            Text(
                text = error,
                style = TS.bodySmall.copy(color = Colors.error)
            )
        }
    }
}
