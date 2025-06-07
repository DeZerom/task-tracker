package ru.dezerom.navigation.logic

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.navigation
import ru.dezerom.navigation.api.destinations.AuthGraph
import ru.dezerom.navigation.api.destinations.MainDestinations
import ru.dezerom.navigation.api.destinations.MainGraph
import ru.dezerom.navigation.api.destinations.ProfileGraph
import ru.dezerom.navigation.api.destinations.RootGraph
import ru.dezerom.navigation.api.destinations.TasksGraph

fun NavGraphBuilder.rootNavGraph(navHost: NavHostController) {
    navigation<RootGraph>(
        startDestination = AuthGraph,
        builder = {
            authNavGraph(navHost)
            mainNavGraph()
        }
    )
}

fun NavGraphBuilder.mainNavGraph() {
    navigation<MainGraph>(
        startDestination = MainDestinations.Tasks,
        builder = {
            tasksMainNavGraph()
            profileMainNavGraph()
        }
    )
}

private fun NavGraphBuilder.tasksMainNavGraph() {
    navigation<MainDestinations.Tasks>(
        startDestination = TasksGraph,
        builder = { tasksNavGraph() }
    )
}

private fun NavGraphBuilder.profileMainNavGraph() {
    navigation<MainDestinations.Profile>(
        startDestination = ProfileGraph,
        builder = { profileNavGraph() }
    )
}
