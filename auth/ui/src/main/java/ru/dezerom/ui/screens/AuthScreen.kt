package ru.dezerom.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import ru.dezerom.core.ui.kit.consts.Colors
import ru.dezerom.core.ui.kit.text_input.TextInput

@Composable
fun HelloWorld() {
    Scaffold { innerPadding ->
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(color = Colors.background)
        ) {
            TextInput(value = "qwewq«", labelText = "asdfg", onValueChanged = {})
        }
    }
}
