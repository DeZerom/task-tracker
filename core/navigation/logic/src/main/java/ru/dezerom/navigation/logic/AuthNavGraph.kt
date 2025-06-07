package ru.dezerom.navigation.logic

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import ru.dezerom.navigation.api.destinations.AuthDestinations
import ru.dezerom.navigation.api.destinations.AuthGraph
import ru.dezerom.navigation.logic.navigators.AuthNavigatorImpl
import ru.dezerom.ui.auth.AuthScreen
import ru.dezerom.ui.registration.RegistrationScreen

fun NavGraphBuilder.authNavGraph(navController: NavController) {
    navigation<AuthGraph>(
        startDestination = AuthDestinations.AuthDestination,
        builder = {
            composable<AuthDestinations.AuthDestination> { AuthScreen(AuthNavigatorImpl(navController)) }
            composable<AuthDestinations.RegistrationDestination> { RegistrationScreen(AuthNavigatorImpl(navController)) }
        }
    )
}