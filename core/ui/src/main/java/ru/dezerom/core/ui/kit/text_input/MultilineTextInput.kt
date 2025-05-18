package ru.dezerom.core.ui.kit.text_input

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.VisualTransformation

@Composable
fun MultilineTextInput(
    value: String,
    labelText: String,
    onValueChanged: (String) -> Unit,
    modifier: Modifier = Modifier,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    isError: Boolean = false,
    error: String? = null,
    testTag: String = "",
) {
    TextInput(
        value = value,
        onValueChanged = onValueChanged,
        labelText = labelText,
        modifier = modifier,
        visualTransformation = visualTransformation,
        isError = isError,
        error = error,
        testTag = testTag,
        singleLine = false,
        minLines = 4,
    )
}