package ru.dezerom.tasktracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.CompositionLocalProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.createGraph
import dagger.hilt.android.AndroidEntryPoint
import ru.dezerom.core.ui.kit.theme.TaskTrackerTheme
import ru.dezerom.navigation.api.destinations.AuthDestination
import ru.dezerom.navigation.api.tools.LocalNavController
import ru.dezerom.navigation.logic.auth.authNavGraph

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()

            CompositionLocalProvider(
                LocalNavController provides navController
            ) {
                TaskTrackerTheme {
                    NavHost(
                        navController = navController,
                        graph = navController.createGraph(
                            startDestination = AuthDestination,
                            builder = { authNavGraph() }
                        )
                    )
                }
            }
        }
    }
}