package ru.dezerom.navigation.logic.navigators

import androidx.navigation.NavController
import ru.dezerom.navigation.api.destinations.AuthDestinations
import ru.dezerom.navigation.api.destinations.AuthGraph
import ru.dezerom.navigation.api.destinations.MainGraph
import ru.dezerom.navigation.api.navigators.AuthNavigator

internal class AuthNavigatorImpl(
    private val navController: NavController
): AuthNavigator {
    override fun fromAuthToRegistration() {
        navController.navigate(AuthDestinations.RegistrationDestination)
    }

    override fun fromRegistrationToAuth() {
        navController.popBackStack()
    }

    override fun fromAuthToTasks() {
//        val graph = navController.createGraph(MainGraph) { mainNavGraph() }
//        navController.setGraph(graph, null)

        navController.navigate(MainGraph) {
            popUpTo<AuthGraph> { inclusive = true }
        }
    }
}