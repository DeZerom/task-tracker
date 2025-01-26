package ru.dezerom.tasktracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import ru.dezerom.ui.screens.HelloWorld

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ru.dezerom.core.ui.kit.theme.TaskTrackerTheme {
                HelloWorld()
            }
        }
    }
}