package ru.dezerom.navigation.logic

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import ru.dezerom.navigation.api.destinations.AuthDestination
import ru.dezerom.navigation.api.destinations.AuthGraph
import ru.dezerom.navigation.api.destinations.RegistrationDestination
import ru.dezerom.navigation.api.destinations.TasksGraph
import ru.dezerom.navigation.api.destinations.TasksListDestination
import ru.dezerom.tasks.ui.list.TasksListScreen
import ru.dezerom.ui.auth.AuthScreen
import ru.dezerom.ui.registration.RegistrationScreen

fun NavGraphBuilder.rootNavGraph() {
    navigation<AuthGraph>(
        startDestination = AuthDestination,
        builder = { authNavGraph() }
    )
    navigation<TasksGraph>(
        startDestination = TasksListDestination,
        builder = { tasksNavGraph() }
    )
}

private fun NavGraphBuilder.authNavGraph() {
    composable<AuthDestination> { AuthScreen() }
    composable<RegistrationDestination> { RegistrationScreen() }
}

private fun NavGraphBuilder.tasksNavGraph() {
    composable<TasksListDestination> { TasksListScreen() }
}
