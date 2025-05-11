package ru.dezerom.tasktracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.runtime.CompositionLocalProvider
import androidx.core.view.WindowInsetsControllerCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.createGraph
import dagger.hilt.android.AndroidEntryPoint
import ru.dezerom.core.ui.kit.theme.TaskTrackerTheme
import ru.dezerom.navigation.api.destinations.AuthGraph
import ru.dezerom.navigation.api.tools.LocalNavController
import ru.dezerom.navigation.logic.rootNavGraph

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)

        WindowInsetsControllerCompat(window, window.decorView).apply {
            isAppearanceLightStatusBars = false
            isAppearanceLightNavigationBars = false
        }

        setContent {
            val navController = rememberNavController()

            CompositionLocalProvider(
                LocalNavController provides navController
            ) {
                TaskTrackerTheme {
                    NavHost(
                        navController = navController,
                        graph = navController.createGraph(
                            startDestination = AuthGraph,
                            builder = { rootNavGraph() }
                        )
                    )
                }
            }
        }
    }
}