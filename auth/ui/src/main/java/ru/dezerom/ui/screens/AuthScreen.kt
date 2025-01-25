package ru.dezerom.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import ru.dezerom.core.ui.kit.text_input.TextInput

@Composable
fun HelloWorld() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        TextInput(value = "qwerty", onValueChanged = {})
    }
}
