package ru.dezerom.navigation.logic

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import ru.dezerom.navigation.api.destinations.TasksDestinations
import ru.dezerom.navigation.api.destinations.TasksGraph
import ru.dezerom.tasks.ui.list.TasksListScreen

fun NavGraphBuilder.tasksNavGraph() {
    navigation<TasksGraph>(
        startDestination = TasksDestinations.TasksListDestination,
        builder = {
            composable<TasksDestinations.TasksListDestination> { TasksListScreen() }
        }
    )
}