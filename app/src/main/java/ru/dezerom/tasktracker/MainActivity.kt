package ru.dezerom.tasktracker

//noinspection UsingMaterialAndMaterial3Libraries
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.core.view.WindowInsetsControllerCompat
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.createGraph
import dagger.hilt.android.AndroidEntryPoint
import ru.dezerom.core.tools.consts.Colors
import ru.dezerom.core.tools.string_container.getString
import ru.dezerom.core.ui.kit.text_style.TS
import ru.dezerom.core.ui.kit.theme.TaskTrackerTheme
import ru.dezerom.core.ui.tools.ScaffoldState
import ru.dezerom.navigation.api.destinations.MainDestinations
import ru.dezerom.navigation.api.destinations.RootGraph
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

            val topBar = ScaffoldState.topBar.collectAsState()

            TaskTrackerTheme {
                Scaffold(
                    topBar = topBar.value,
                    bottomBar = { BottomNavBar(navController) }
                ) { padding ->
                    NavHost(
                        navController = navController,
                        graph = navController.createGraph(
                            startDestination = RootGraph,
                            builder = { rootNavGraph(navController) }
                        ),
                        modifier = Modifier.padding(padding)
                    )
                }
            }
        }
    }
}

@Composable
fun BottomNavBar(
    navController: NavHostController
) {
    val currentDest by navController.currentBackStackEntryAsState()

    BottomNavigation(
        backgroundColor = Colors.darkSurface,
        modifier = Modifier.navigationBarsPadding()
    ) {
        MainDestinations.items.forEach { item ->
            val selected = currentDest?.destination?.hierarchy?.any { it.hasRoute(item::class) } == true

            BottomNavigationItem(
                selected = selected,
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = null,
                        tint = if (selected) Colors.accent else Colors.secondaryText
                    )
                },
                label = {
                    Text(
                        text = item.name.getString(),
                        style = TS.bodyMedium.copy(
                            color = if (selected) Colors.white else Colors.secondaryText
                        )
                    )
                },
                onClick = {
                    navController.navigate(item) {
                        popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}