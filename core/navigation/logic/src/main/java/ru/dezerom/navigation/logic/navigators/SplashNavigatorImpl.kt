package ru.dezerom.navigation.logic.navigators

import androidx.navigation.NavController
import ru.dezerom.navigation.api.destinations.AuthGraph
import ru.dezerom.navigation.api.destinations.SplashGraph
import ru.dezerom.navigation.api.destinations.TasksGraph
import ru.dezerom.navigation.api.navigators.SplashNavigator

class SplashNavigatorImpl(private val navController: NavController): SplashNavigator {
    override fun fromSplashToAuth() {
        navController.navigate(AuthGraph) {
            popUpTo<SplashGraph> { inclusive = true }
        }
    }

    override fun fromSplashToTasks() {
        navController.navigate(TasksGraph) {
            popUpTo<SplashGraph> { inclusive = true }
        }
    }
}